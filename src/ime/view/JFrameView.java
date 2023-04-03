package ime.view;

import java.util.Scanner;

import javax.swing.JFrame;

public class JFrameView extends JFrame implements View{


  @Override
  public Appendable getOutstream() {
    return null;
  }

  @Override
  public void textPrompt() {

  }

  @Override
  public void unknownCommandPrompt() {

  }

  @Override
  public void printGeneralError(String errorMessage) {

  }

  @Override
  public Scanner getUserInput() {
    return null;
  }

  @Override
  public void setImage() {

  }

  @Override
  public void addFeatures(Features features) {

  }
}
