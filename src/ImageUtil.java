import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents. Feel free to change this method
 * as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static void printPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        System.out.println("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);
      }
    }
  }

  public static PPMImage readIntoPPMImage(String filename, String newName) {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return null;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    int[][] red = new int[width][height];

    int[][] green = new int[width][height];

    int[][] blue = new int[width][height];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        red[j][i] = r;
        green[j][i] = g;
        blue[j][i] = b;
      }
    }
      return new CompositePPMImage(newName, width, height, red, blue, green);
  }

  public static void writeToPPMFile(PPMImage image, String fileName) throws IOException {
    String fullFileName = String.format("%s.ppm", fileName);
    File ppmFile = new File(fullFileName);
    FileWriter ppmWriter = new FileWriter(fullFileName);
    ppmWriter.write("P3\n");
    ppmWriter.write(String.format("%s %s\n",image.getWidth(),image.getHeight()));
    ppmWriter.write("255\n");
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
          ppmWriter.write(String.format("%d\n",image.getRedComponent()[j][i]));
          ppmWriter.write(String.format("%d\n",image.getGreenComponent()[j][i]));
          ppmWriter.write(String.format("%d\n",image.getBlueComponent()[j][i]));
      }
    }
    ppmWriter.close();
  }
  public static BufferedImage writeBufferedImage(PPMImage image, String name) {
    BufferedImage buffImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
    int r, g, b, pixel;
    for (int i = 0; i < image.getWidth(); i++) {
      for (int j = 0; j < image.getHeight(); j++) {
        r = image.getRedComponent()[i][j];
        g = image.getGreenComponent()[i][j];
        b = image.getBlueComponent()[i][j];
        pixel = 0xFF000000 + (r << 16) + (g << 8) + b;
        buffImage.setRGB(i, j, pixel);
      }
    }
    return buffImage;
  }

}

