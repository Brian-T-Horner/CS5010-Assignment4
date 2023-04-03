package ime.view;

import java.util.Scanner;

public interface View {

  Appendable getOutstream();
  void textPrompt();

  void unknownCommandPrompt();

  void printGeneralError(String errorMessage);

  Scanner getUserInput();

  void setImage();

  void addFeatures(Features features);


}

