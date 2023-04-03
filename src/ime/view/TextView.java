package ime.view;

import java.io.IOException;
import java.util.Scanner;

public class TextView implements View{

  final Appendable out;

  final Readable in;

  public TextView(Appendable out, Readable in) {
    this.out = out;
    this.in = in;
  }

  @Override
  public Appendable getOutstream() {
    return out;
  }

  @Override
  public void textPrompt() {
    try{
      out.append("$ ");
    } catch(IOException e) {
      System.out.println("View:User prompt failed to append");
    }
  }

  public void unknownCommandPrompt() {
    try{
      out.append("Unrecognized command\n");
    } catch(IOException e) {
      System.out.println("View:Unknown command prompt failed to append");
    }
  }

  public void printGeneralError(String errorMessage) {
    try{
      out.append(errorMessage).append("\n");
    } catch(IOException e) {
      System.out.println("View:Error message failed to append");
    }
  }

  @Override
  public Scanner getUserInput() {
    return new Scanner(this.in);
  }

  @Override
  public void setImage() {
    // do nothing
  }

  @Override
  public void addFeatures(Features features) {
    // do nothing
  }

}
