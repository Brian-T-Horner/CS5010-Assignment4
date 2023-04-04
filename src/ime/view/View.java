package ime.view;

import java.awt.image.BufferedImage;
import java.util.Scanner;

public interface View {
  void textPrompt();

  void unknownCommandPrompt();

  void printGeneralError(String errorMessage);

  Scanner getScanner();

  void readUserInput();

  void setImage(BufferedImage img);

  void addFeatures(Features features);


}

