package ime.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.EventListener;
import java.util.Scanner;

import javax.swing.*;

import jdk.jfr.Event;

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
