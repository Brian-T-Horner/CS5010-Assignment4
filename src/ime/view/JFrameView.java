package ime.view;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;


public class JFrameView extends JFrame implements View {

  private final JButton loadButton;

  private final JButton exitButton;

  private final JButton saveButton;

  private final JButton blurButton;

  private final JButton ditherButton;

  private final JButton greyscaleButton;

  private final JButton hflipButton;

  private final JButton vflipButton;

  private final JButton rgbSplitButton;

  private final JButton rgbCombineButton;

  private final JButton brightenButton;

  private final JButton sharpenButton;

  private final JButton sepiaButton;

  private final JButton rgbCombine1;

  private final JButton rgbCombine2;

  private final JButton rgbCombine3;

  private final JButton lumaButton;

  private final JButton intensityButton;

  private final JButton valueButton;

  private String redPath;

  private String greenPath;

  private String bluePath;

  private final JLabel currentImage;

  private final JFrame parent = this;
  private String path;

  private final JPanel chartPanel;

  private final XYChart chart;

  public JFrameView(String caption) {

    super(caption);

    redPath ="";

    bluePath = "";

    greenPath = "";


    //TODO: Make histogram check if image is colored or not
    //TODO: Greyscale - intensity
    //TODO: Color - value of each rgb and intensity

    //---------------------------------------------Messing with charts------------------------------------
    this.chart = new XYChartBuilder().width(600).height(400).title("Histogram of Pixel Values").
        xAxisTitle("Pixel Values 1-255").yAxisTitle("Number of Occurrences of Value in Image")
        .build();

    // Customize Chart
    chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
    chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

    double[] xAxis = new double[256];
    double[] defaultYAxis = new double[256];
    Arrays.fill(defaultYAxis, 0);

    for (int i = 0; i < 256; i++) {
      xAxis[i] = i + 1;
    }


    // Series
    chart.addSeries("r", xAxis, defaultYAxis);
    chart.addSeries("g", xAxis, defaultYAxis);
    chart.addSeries("b", xAxis, defaultYAxis);
    chart.addSeries("intensity", xAxis, defaultYAxis);

    //---------------------------------------End of messing with chart ---------------------------

    setSize(1000, 1000);
    //--------------------------------------- Flatlaf -------------------------------
    try {
      UIManager.setLookAndFeel(new FlatDarculaLaf());
    } catch (Exception ex) {
      System.err.println("Failed to initialize Laf");
    }
    //-------------------------------------------
    setSize(500, 300);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    this.setLayout(new FlowLayout());

    //image display
    currentImage = new JLabel();
    this.add(currentImage);
    // TODO fix image scrolling
    JScrollPane scroller = new JScrollPane(currentImage,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    this.add(scroller);

    // Adding random chart
    this.chartPanel = new XChartPanel<XYChart>(chart);
    this.add(chartPanel);
    chartPanel.setVisible(false);

    //exit button
    exitButton = new JButton("Exit");
    exitButton.setActionCommand("Exit Button");
    this.add(exitButton);

    //load button
    loadButton = new JButton("Load");
    loadButton.setActionCommand("Load Button");
    this.add(loadButton);

    //save button
    saveButton = new JButton("Save");
    saveButton.setActionCommand("Save Button");
    this.add(saveButton);

    //dither button
    ditherButton = new JButton("Dither");
    ditherButton.setActionCommand("Dither Button");
    this.add(ditherButton);

    //vertical flip button
    vflipButton = new JButton("Vertical Flip");
    vflipButton.setActionCommand("Vertical Flip Button");
    this.add(vflipButton);

    //blur button
    blurButton = new JButton("Blur");
    blurButton.setActionCommand("Blur Button");
    this.add(blurButton);

    //greyscale button
    greyscaleButton = new JButton("Greyscale");
    greyscaleButton.setActionCommand("Greyscale Button");
    this.add(greyscaleButton);

    //horizontal flip button
    hflipButton = new JButton("Horizontal Flip");
    hflipButton.setActionCommand("Horizontal Button");
    this.add(hflipButton);

    //sharpen button
    sharpenButton = new JButton("Sharpen");
    sharpenButton.setActionCommand("Sharpen Button");
    this.add(sharpenButton);

    //sepia button
    sepiaButton = new JButton("Sepiascale");
    sepiaButton.setActionCommand("Sepia Button");
    this.add(sepiaButton);

    //rgbSplit button
    rgbSplitButton = new JButton("Split Image into RGB");
    rgbSplitButton.setActionCommand("Split Button");
    this.add(rgbSplitButton);

    //brighten button
    brightenButton = new JButton("Brighten");
    brightenButton.setActionCommand("Brighten Button");
    this.add(brightenButton);


    //rgbCombine button
    rgbCombineButton = new JButton("Combine red, green, and blue channels");
    rgbCombineButton.setActionCommand("RGBCombine Button");
    this.add(rgbCombineButton);

    //rgbCombine buttons

    rgbCombine1 = new JButton("Choose red image");
    rgbCombine1.setActionCommand("rgbCombine1");
    this.add(rgbCombine1);

    rgbCombine2 = new JButton("Choose green image");
    rgbCombine2.setActionCommand("rgbCombine2");
    this.add(rgbCombine2);

    rgbCombine3 = new JButton("Choose blue image");
    rgbCombine3.setActionCommand("rgbCombine3");
    this.add(rgbCombine3);

    lumaButton = new JButton("Display luma component");
    lumaButton.setActionCommand("luma");
    this.add(lumaButton);

    intensityButton = new JButton("Display intensity component");
    intensityButton.setActionCommand("intensity");
    this.add(intensityButton);

    valueButton = new JButton("Display value component");
    valueButton.setActionCommand("value");
    this.add(valueButton);




    //luma button

    //TODO initialize all other ui elements
    setVisible(true);
  }


  @Override
  public void textPrompt() {
    throw new UnsupportedOperationException("Text prompt unused.");
  }

  @Override
  public void unknownCommandPrompt() {
    throw new UnsupportedOperationException("Unknown prompt unused.");
  }

  @Override
  public void printGeneralError(String errorMessage) {
    JOptionPane.showMessageDialog(this, errorMessage,
            "User error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public Scanner getScanner() {
    throw new UnsupportedOperationException("Scanner unused.");
  }


  @Override
  public void setImage(BufferedImage img) {
    currentImage.setIcon(new ImageIcon(img));
  }

  public void setChartPanelVisible() {
    this.chartPanel.setVisible(true);
  }

  public void updateColoredChartPanel(int[][] red2D, int[][] green2D, int[][] blue2D, int[][] intensity2D) {
    double[] red = new double[256];
    double[] blue = new double[256];
    double[] green = new double[256];
    double[] intensity = new double[256];
    Arrays.fill(red, 0);
    Arrays.fill(blue, 0);
    Arrays.fill(green, 0);
    Arrays.fill(intensity, 0);

    for (int i = 0; i < red2D.length; i++) {
      for (int j = 0; j < red2D[i].length; j++) {
        red[red2D[i][j]]++;
        blue[blue2D[i][j]]++;
        green[green2D[i][j]]++;
        intensity[intensity2D[i][j]]++;

      }
    }

    double[] xAxis = new double[256];
    for (int i = 0; i < 256; i++) {
      xAxis[i] = i + 1;
    }


    chart.updateXYSeries("r", xAxis, red, null);
    chart.updateXYSeries("g", xAxis, green, null);
    chart.updateXYSeries("b", xAxis, blue, null);
    chart.updateXYSeries("intensity", xAxis, intensity, null);
    chartPanel.revalidate();
    chartPanel.repaint();
    //    To make it real-time, simply call updateXYSeries on the XYChart instance
    //    to update the series data, followed by revalidate() and repaint() on
    //    the XChartPanel instance to repaint.

  }

  @Override
  public void updateGreyChartPanel(int[][] intensity2D) {
    double[] intensity = new double[256];
    Arrays.fill(intensity, 0);

    for (int i = 0; i < intensity2D.length; i++) {
      for (int j = 0; j < intensity2D[i].length; j++) {
        intensity[intensity2D[i][j]]++;
      }
    }

    double[] xAxis = new double[256];
    for (int i = 0; i < 256; i++) {
      xAxis[i] = i + 1;
    }

    chart.updateXYSeries("intensity", xAxis, intensity, null);
    chartPanel.revalidate();
    chartPanel.repaint();

  }


  @Override
  public void addFeatures(Features features) {
    loadButton.addActionListener(evt -> {
      //TODO config to make more user friendly
      JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
      Action details = fileChooser.getActionMap().get("viewTypeDetails");
      details.actionPerformed(null);
      FileNameExtensionFilter filter = new FileNameExtensionFilter("Images(.bmp, .jpg,"
              + " .png, .ppm)", "ppm", "png", "bmp", "jpg");
      fileChooser.setFileFilter(filter);
      fileChooser.setDialogTitle("Select a file");

      // Show the file chooser dialog and wait for the user to select a file
      int userSelection = fileChooser.showOpenDialog(parent);

      if (userSelection == JFileChooser.APPROVE_OPTION) {
        // Get the selected file path as a string and display it in the label
        path = fileChooser.getSelectedFile().getAbsolutePath();
        features.loadImage(path);
      }
    });
    exitButton.addActionListener(evt -> features.exit());

    ditherButton.addActionListener(evt -> features.dither());
    vflipButton.addActionListener(evt -> features.verticalFlip());
    blurButton.addActionListener(evt -> features.blur());
    greyscaleButton.addActionListener(evt -> features.greyscale());
    hflipButton.addActionListener(evt -> features.horizontalFlip());
    sharpenButton.addActionListener(evt -> features.sharpen());
    sepiaButton.addActionListener(evt -> features.sepia());
    rgbSplitButton.addActionListener(evt -> features.rgbSplit());
    lumaButton.addActionListener(evt -> features.luma());
    intensityButton.addActionListener(evt -> features.intensity());
    valueButton.addActionListener(evt -> features.value());


    saveButton.addActionListener(evt -> {
      JDialog dialog = new JDialog(this, "Save Dialog", true);
      dialog.setSize(300, 150);
      JFormattedTextField txtFilePath = new JFormattedTextField();
      JButton btnChooseFile = new JButton("Choose File");
      dialog.add(txtFilePath);
      dialog.add(btnChooseFile);

      btnChooseFile.addActionListener(e -> {
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir") + "/img.png");
        Action details = fileChooser.getActionMap().get("viewTypeDetails");
        details.actionPerformed(null);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images(.bmp, .jpg,"
                + " .png, .ppm)", "ppm", "png", "bmp", "jpg");
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("Select a file");
        int returnValue = fileChooser.showOpenDialog(dialog);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          File selectedFile = fileChooser.getSelectedFile();
          String filePath = selectedFile.getAbsolutePath();
          features.save(filePath);
          dialog.setVisible(false);
        }
      });
      dialog.setVisible(true);
    });

    brightenButton.addActionListener(evt -> {
      String input = JOptionPane.showInputDialog(parent, "Enter an integer:");
      try {
        int scale = Integer.parseInt(input);
        features.brighten(scale);
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(parent, "Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
      }
    });

    rgbCombine1.addActionListener(evt -> {
      JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir") + "/img.png");
      Action details = fileChooser.getActionMap().get("viewTypeDetails");
      details.actionPerformed(null);
      FileNameExtensionFilter filter = new FileNameExtensionFilter("Images(.bmp, .jpg,"
              + " .png, .ppm)", "ppm", "png", "bmp", "jpg");
      fileChooser.setFileFilter(filter);
      fileChooser.setDialogTitle("Select a file");
      int returnValue = fileChooser.showOpenDialog(this);
      if (returnValue == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        redPath = selectedFile.getAbsolutePath();
      }

    });

    rgbCombine2.addActionListener(evt -> {
      JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir") + "/img.png");
      Action details = fileChooser.getActionMap().get("viewTypeDetails");
      details.actionPerformed(null);
      FileNameExtensionFilter filter = new FileNameExtensionFilter("Images(.bmp, .jpg,"
              + " .png, .ppm)", "ppm", "png", "bmp", "jpg");
      fileChooser.setFileFilter(filter);
      fileChooser.setDialogTitle("Select a file");
      int returnValue = fileChooser.showOpenDialog(this);
      if (returnValue == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        greenPath = selectedFile.getAbsolutePath();
      }

    });

    rgbCombine3.addActionListener(evt -> {
      JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir") + "/img.png");
      Action details = fileChooser.getActionMap().get("viewTypeDetails");
      details.actionPerformed(null);
      FileNameExtensionFilter filter = new FileNameExtensionFilter("Images(.bmp, .jpg,"
              + " .png, .ppm)", "ppm", "png", "bmp", "jpg");
      fileChooser.setFileFilter(filter);
      fileChooser.setDialogTitle("Select a file");
      int returnValue = fileChooser.showOpenDialog(this);
      if (returnValue == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        bluePath = selectedFile.getAbsolutePath();
      }

    });

    rgbCombineButton.addActionListener(evt -> {
      if(greenPath.isEmpty() || redPath.isEmpty() || bluePath.isEmpty()) {
        JOptionPane.showMessageDialog(parent, "Please enter a valid red, green, and blue paths."
                , "Error", JOptionPane.ERROR_MESSAGE);
      } else {
        features.rgbCombine(redPath,greenPath,bluePath);
        redPath = "";
        bluePath = "";
        greenPath = "";
      }
    });
  }
}
