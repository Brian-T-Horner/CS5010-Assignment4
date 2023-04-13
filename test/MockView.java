import ime.view.Features;
import ime.view.JFrameView;
import ime.view.View;
import java.awt.image.BufferedImage;
import java.util.Scanner;
import javax.swing.JFrame;

public class MockView  implements View {


  private StringBuilder log;
  private final int uniqueCode;

  public MockView(StringBuilder newLog, int newUniqueCode) {
    this.log = newLog;
    this.uniqueCode = newUniqueCode;
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
  public Scanner getScanner() {
    return null;
  }

  @Override
  public void setImage(BufferedImage img) {

  }

  @Override
  public void addFeatures(Features features) {

  }

  @Override
  public void setChartPanelVisible() {

  }

  @Override
  public void updateColoredChartPanel(int[][] red, int[][] green, int[][] blue, int[][] intensity) {

  }

  @Override
  public void updateGreyChartPanel(int[][] intensity) {

  }
}
