package ime.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.EventListener;
import java.util.Scanner;

import javax.swing.*;

import jdk.jfr.Event;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;

public class JFrameView extends JFrame implements View {

  private JLabel saveLabel;
  private final JButton loadButton;
  private final JButton exitButton;
  private JButton saveButton;

  private JButton blurButton;

  private JButton ditherButton;

  private JButton greyscaleButton;

  private JButton hflipButton;

  private JButton vflipButton;

  private JButton rgbSplitButton;

  private JButton rgbCombineButton;

  private JButton brightenButton;

  private JButton sharpenButton;

  private JButton sepiaButton;

  private JLabel currentImage;

  private JTextField pathInput;

  private JTextField brightenValue;

  private JTextField rgbCombine1;

  private JTextField rgbCombine2;

  private JTextField RgbCombine3;

  private JFrame parent = this;
  private String path;

  private JPanel chartPanel = null;

  private XYChart chart = null;

  public JFrameView(String caption) {

    super(caption);


    //TODO: Make histogram check if image is colored or not
    //TODO: Greyscale - intensity
    //TODO: Color - value of each rgb and intensity

    //---------------------------------------------Messing with charts------------------------------------
    this.chart = new XYChartBuilder().width(600).height(400).title("Area Chart").
        xAxisTitle("X").yAxisTitle("Y").build();

    // Customize Chart
    chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
    chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

    int[] xAxis = new int[256];
    int[] defaultYAxis = new int[256];
    Arrays.fill(defaultYAxis, 0);

    for(int i = 0; i< 256; i++){
      xAxis[i] = i+1;
    }



    // Series
    chart.addSeries("r", xAxis, defaultYAxis);
    chart.addSeries("g", xAxis, defaultYAxis);
    chart.addSeries("b", xAxis, defaultYAxis);
    chart.addSeries("intensity", xAxis, defaultYAxis);

    //---------------------------------------End of messing with chart ---------------------------

    setSize(500, 300);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    this.setLayout(new FlowLayout());

    //image display
    currentImage = new JLabel();
    this.add(currentImage);

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


    //save path input
    pathInput = new JFormattedTextField();
    pathInput.setPreferredSize(new Dimension(100, 20));
    saveLabel = new JLabel("Save to path:", JLabel.LEFT);
    saveLabel.setLabelFor(pathInput);
    this.add(pathInput);
    this.add(saveLabel);

    //TODO initialize all other ui elements
    setVisible(true);
  }


  @Override
  public void textPrompt() {
    //do nothing
  }

  @Override
  public void unknownCommandPrompt() {
    //TODO use for error popup
  }

  @Override
  public void printGeneralError(String errorMessage) {
    //TODO use for error popup
  }

  @Override
  public Scanner getScanner() {
    //TODO method is unused
    return null;
  }

  @Override
  public void readUserInput() {
    //todo unused i think, stays blank
  }


  @Override
  public void setImage(BufferedImage img) {
    currentImage.setIcon(new ImageIcon(img));
  }

  public void setChartPanelVisible(){
    this.chartPanel.setVisible(true);
  }

  public void updateColoredChartPanel(int[][] red2D, int[][] green2D, int[][] blue2D, int[][] intensity2D){
    int[] red = new int[256];
    int[] blue = new int[256];
    int[] green = new int[256];
    int[] intensity = new int[256];
    Arrays.fill(red, 0);
    Arrays.fill(blue, 0);
    Arrays.fill(green, 0);
    Arrays.fill(intensity, 0);

    for(int i = 0; i < red2D.length; i++) {
      for(int j = 0; j< red2D[i].length; j++){
        red[red2D[i][j]]++;
        blue[blue2D[i][j]]++;
        green[green2D[i][j]]++;
        intensity[intensity2D[i][j]]++;

      }
    }

    int[] xAxis = new int[256];
    for(int i = 0; i< 256; i++){
      xAxis[i] = i+1;
    }

    chart.updateXYSeries("r", xAxis, red);
    chart.addSeries("g", xAxis, green);
    chart.addSeries("b", xAxis, blue);
    chart.addSeries("intensity", xAxis, intensity);
    chartPanel.revalidate();
    chartPanel.repa
    //    To make it real-time, simply call updateXYSeries on the XYChart instance
  //    to update the series data, followed by revalidate() and repaint() on
  //    the XChartPanel instance to repaint.

  }

  public void updateGreyColoredPanel(int[][] intensity2D){
    int[] intensity = new int [256];

  }


  @Override
  public void addFeatures(Features features) {

    ActionListener selectFileAndLoad = e -> {
      // Create a file chooser dialog
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setDialogTitle("Select a file");

      // Show the file chooser dialog and wait for the user to select a file
      int userSelection = fileChooser.showOpenDialog(parent);

      if (userSelection == JFileChooser.APPROVE_OPTION) {
        // Get the selected file path as a string and display it in the label
        path = fileChooser.getSelectedFile().getAbsolutePath();
        features.loadImage(path);
      }

    };


    loadButton.addActionListener(selectFileAndLoad);
    exitButton.addActionListener(evt -> features.exit());
    saveButton.addActionListener(evt -> features.save(pathInput.getText()));
    //todo add actionlisteners with correct functions for each UI element.
  }
}
