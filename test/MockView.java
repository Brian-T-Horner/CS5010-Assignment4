import ime.view.Features;
import ime.view.View;
import java.awt.image.BufferedImage;
import java.io.Reader;
import java.util.Scanner;

public class MockView implements View {


  private StringBuilder log;
  private final int uniqueCode;
  private Readable in;


  public MockView(StringBuilder newLog, int newUniqueCode, Readable in) {
    this.log = newLog;
    this.uniqueCode = newUniqueCode;
    this.in = in;
  }


  @Override
  public void textPrompt() {
    log.append("textPrompt:");
    log.append(" uniqueCode = ");
    log.append(this.uniqueCode);
    log.append("\n");


  }

  @Override
  public void unknownCommandPrompt() {
    log.append("unknown command prompt:");
    log.append(" uniqueCode = ");
    log.append(this.uniqueCode);
    log.append("\n");


  }

  @Override
  public void printGeneralError(String errorMessage) {
    log.append("printing error message: error message = ");
    log.append(errorMessage);
    log.append(" uniqueCode = ");
    log.append(this.uniqueCode);
    log.append("\n");

  }

  @Override
  public Scanner getScanner() {
    log.append("getting scanner:");
    log.append(" uniqueCode = ");
    log.append(this.uniqueCode);
    log.append("\n");
    return new Scanner(in);
  }

  @Override
  public void setImage(BufferedImage img) {
    log.append("setting image:");
    log.append(" uniqueCode = ");
    log.append(this.uniqueCode);
    log.append("\n");
  }

  @Override
  public void addFeatures(Features features) {
    log.append("adding features:");
    log.append(" uniqueCode = ");
    log.append(this.uniqueCode);
    log.append("\n");
  }

  @Override
  public void setChartPanelVisible() {
    log.append("setting chart panel visible:");
    log.append(" uniqueCode = ");
    log.append(this.uniqueCode);
    log.append("\n");
  }

  @Override
  public void updateColoredChartPanel(int[][] red, int[][] green, int[][] blue, int[][] intensity) {
    log.append("updating colored chart panel:");
    log.append(" uniqueCode = ");
    log.append(this.uniqueCode);
    log.append("\n");
  }

  @Override
  public void updateGreyChartPanel(int[][] intensity) {
    log.append("updating grey chart panel:");
    log.append(" uniqueCode = ");
    log.append(this.uniqueCode);
    log.append("\n");
  }
}
