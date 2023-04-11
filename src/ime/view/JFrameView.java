package ime.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


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

  private JButton rgbCombineButton;

  private JButton brightenButton;

  private final JButton sharpenButton;

  private final JButton sepiaButton;

  private final JLabel currentImage;

  private final JTextField pathInput;

  private JTextField brightenValue;

  private JTextField rgbCombine1;

  private JTextField rgbCombine2;

  private JTextField rgbCombine3;

  private final JFrame parent = this;
  private String path;

  public JFrameView(String caption) {
    super(caption);

    setSize(500, 300);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    this.setLayout(new FlowLayout());

    //image display
    currentImage = new JLabel();
    this.add(currentImage);

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

    //sepia button
    rgbSplitButton = new JButton("Split Image into RGB");
    rgbSplitButton.setActionCommand("Split Button");
    this.add(rgbSplitButton);


    //save path input
    pathInput = new JFormattedTextField(System.getProperty("user.dir") + "/img.png");
    pathInput.setPreferredSize(new Dimension(100, 20));
    JLabel saveLabel = new JLabel("Save to path:", JLabel.RIGHT);
    saveLabel.setLabelFor(pathInput);
    this.add(pathInput);
    this.add(saveLabel);

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
            "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public Scanner getScanner() {
    throw new UnsupportedOperationException("Scanner unused.");
  }


  @Override
  public void setImage(BufferedImage img) {
    currentImage.setIcon(new ImageIcon(img));
  }


  @Override
  public void addFeatures(Features features) {
    loadButton.addActionListener(evt -> {
      //TODO config to make more user friendly
      JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
      Action details = fileChooser.getActionMap().get("viewTypeDetails");
      details.actionPerformed(null);
      FileNameExtensionFilter filter = new FileNameExtensionFilter("Images(.bmp, .jpg,"
              + " .png, .ppm)", "ppm", "png","bmp","jpg");
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
    saveButton.addActionListener(evt -> features.save(pathInput.getText()));
    ditherButton.addActionListener(evt -> features.dither());
    vflipButton.addActionListener(evt -> features.verticalFlip());
    blurButton.addActionListener(evt -> features.blur());
    greyscaleButton.addActionListener(evt -> features.greyscale());
    hflipButton.addActionListener(evt -> features.horizontalFlip());
    sharpenButton.addActionListener(evt -> features.sharpen());
    sepiaButton.addActionListener(evt -> features.sepia());
    rgbSplitButton.addActionListener(evt -> features.rgbSplit());


    //TODO rgb combine and brighten
  }
}
