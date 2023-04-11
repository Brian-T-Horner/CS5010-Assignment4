package ime.view;

import org.knowm.xchart.XYChart;

import java.awt.image.BufferedImage;
import java.util.Scanner;

public interface View {

  void textPrompt();

  void unknownCommandPrompt();

  void printGeneralError(String errorMessage);

  Scanner getScanner();

  void setImage(BufferedImage img);

  void addFeatures(Features features);

  void setChartPanelVisible();

  void updateColoredChartPanel(int[][] red, int[][] green, int[][] blue, int[][] intensity);

  void updateGreyChartPanel(int[][] intensity);


}

