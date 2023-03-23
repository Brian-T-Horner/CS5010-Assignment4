package ime.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import ime.ImageUtil;

/**
 * IME.model.PPMModel that controls the method of a PPMImage.
 */
public class PPMModel implements Model {

  private final Map<String, Image> images = new HashMap<>();

  /**
   * Constructor for IME.model.PPMModel. No parameters as only one default field.
   */
  public PPMModel() {
    //only one default field
  }

  @Override
  public Image getImage(String imageName) throws NoSuchElementException {
    Image i = images.get(imageName);
    if (i == null) {
      throw new NoSuchElementException("IME.model.Image with name \"" + imageName
              + "\" not in memory.");
    }
    return i;
  }

  /**
   * Method to load a PPMImage.
   *
   * @param imagePath    The path of the PPMImage to load.
   * @param newImageName The name to assign to the loaded PPMImage.
   */
  @Override
  public void loadImage(String imagePath, String newImageName) throws FileNotFoundException, IllegalArgumentException {
    if (!(imagePath.endsWith(".ppm") || imagePath.endsWith(".bmp") || imagePath.endsWith(".png") || imagePath.endsWith(".jpg"))) {
      throw new IllegalArgumentException("File must be a .bmp, .jpg, .png, or .ppm file.");
    }
    Image i = ImageUtil.loadImage(imagePath);
    if (i == null) {
      throw new FileNotFoundException("File \"" + imagePath + "\" not found!");
    }
    images.put(newImageName, i);
  }

  /**
   * Method to save an PPMImage.
   *
   * @param imagePath The path of where to save the PPMImage.
   * @param imageName The name of the PPMImage to save.
   */
  @Override
  public void saveImage(String imagePath, String imageName)
          throws IOException, NoSuchElementException {
    Image i = images.get(imageName);
    if (i == null) {
      throw new NoSuchElementException("IME.model.Image with name \"" + imageName
              + "\" not in memory.");
    }
    if (!(imagePath.endsWith(".ppm") || imagePath.endsWith(".bmp") || imagePath.endsWith(".png") || imagePath.endsWith(".jpg"))) {
      throw new IOException("File must be a .bmp, .jpg, .png, or .ppm file.");
    }
    ImageUtil.saveImage(i, imagePath);
  }

  /**
   * Method to get the redscale image of an PPMImage.
   *
   * @param currentImageName The name of the PPMImage to get the red component from.
   * @param newImageName     The name to save the resulting PPMImage as.
   */
  @Override
  public void getRedComponent(String currentImageName, String newImageName)
          throws NoSuchElementException {
    Image i = images.get(currentImageName);
    if (i != null) {
      int[][] r = i.getRedComponent();
      images.put(newImageName, new PPMImage(i.getWidth(), i.getHeight(), r, r, r));
    } else {
      throw new NoSuchElementException("IME.model.Image with name " + currentImageName
              + " not in memory.");
    }
  }

  /**
   * Method to get the greenscale image of an PPMImage.
   *
   * @param currentImageName The name of the PPMImage to get the green component from.
   * @param newImageName     The name to save the resulting PPMImage as.
   */
  @Override
  public void getGreenComponent(String currentImageName, String newImageName)
          throws NoSuchElementException {
    Image i = images.get(currentImageName);
    if (i != null) {
      int[][] r = i.getGreenComponent();
      images.put(newImageName, new PPMImage(i.getWidth(), i.getHeight(), r, r, r));
    } else {
      throw new NoSuchElementException("IME.model.Image with name " + currentImageName
              + " not in memory.");
    }
  }

  /**
   * Method to get the bluescale image of an PPMImage.
   *
   * @param currentImageName The name of the PPMImage to get the blue component from.
   * @param newImageName     The name to save the resulting PPMImage as.
   */
  @Override
  public void getBlueComponent(String currentImageName, String newImageName)
          throws NoSuchElementException {
    Image i = images.get(currentImageName);
    if (i != null) {
      int[][] r = i.getBlueComponent();
      images.put(newImageName, new PPMImage(i.getWidth(), i.getHeight(), r, r, r));
    } else {
      throw new NoSuchElementException("IME.model.Image with name " + currentImageName
              + " not in memory.");
    }
  }

  /**
   * Method to flip an PPMImage horizontally.
   *
   * @param currentImageName The name of the PPMImage to flip horizontally.
   * @param newImageName     The name to save the resulting PPMImage as.
   */
  @Override
  public void flipHorizontal(String currentImageName, String newImageName)
          throws NoSuchElementException {
    Image i = images.get(currentImageName);
    if (i == null) {
      throw new NoSuchElementException("IME.model.Image with name " + currentImageName
              + " not in memory.");
    }
    int[][] red = flipArrayHorizontal(i, i.getRedComponent());
    int[][] green = flipArrayHorizontal(i, i.getGreenComponent());
    int[][] blue = flipArrayHorizontal(i, i.getBlueComponent());
    Image horizontal = new PPMImage(i.getWidth(), i.getHeight(), red, blue, green);

    images.put(newImageName, horizontal);
  }

  /**
   * Method to flip an PPMImage vertically.
   *
   * @param currentImageName The name of the PPMImage to flip vertically.
   * @param newImageName     The name to save the resulting PPMImage as.
   */
  @Override
  public void flipVertical(String currentImageName, String newImageName) {
    Image i = images.get(currentImageName);
    if (i == null) {
      throw new NoSuchElementException("IME.model.Image with name " + currentImageName
              + " not in memory.");
    }
    int[][] red = flipArrayVertical(i, i.getRedComponent());
    int[][] green = flipArrayVertical(i, i.getGreenComponent());
    int[][] blue = flipArrayVertical(i, i.getBlueComponent());
    Image vertical = new PPMImage(i.getWidth(), i.getHeight(), red, blue, green);

    images.put(newImageName, vertical);
  }

  /**
   * Method to get the value image of an PPMImage.
   *
   * @param currentImageName The name of the PPMImage to get the value image from.
   * @param newImageName     The name to save the resulting value image as.
   */
  @Override
  public void getValueImage(String currentImageName, String newImageName)
          throws NoSuchElementException {
    Image img = images.get(currentImageName);
    if (img == null) {
      throw new NoSuchElementException("IME.model.Image with name " + currentImageName
              + " not in memory.");
    }
    int width = img.getWidth();
    int height = img.getHeight();
    int[][] value = new int[width][height];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        int redValue = img.getRedComponent()[i][j];
        int greenValue = img.getGreenComponent()[i][j];
        int blueValue = img.getBlueComponent()[i][j];
        int max = Math.max(redValue, greenValue);
        max = Math.max(max, blueValue);
        value[i][j] = max;
      }
    }
    Image val = new PPMImage(width, height, value, value, value);

    images.put(newImageName, val);
  }

  /**
   * Method to get the intensity image of an PPMImage.
   *
   * @param currentImageName The name of the PPMImage to get the intensity image from.
   * @param newImageName     The name to save the resulting intensity image as.
   */
  @Override
  public void getIntensityImage(String currentImageName, String newImageName)
          throws NoSuchElementException {
    Image img = images.get(currentImageName);
    if (img == null) {
      throw new NoSuchElementException("IME.model.Image with name " + currentImageName
              + " not in memory.");
    }
    int width = img.getWidth();
    int height = img.getHeight();
    int[][] intensity = new int[width][height];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        int redValue = img.getRedComponent()[i][j];
        int greenValue = img.getGreenComponent()[i][j];
        int blueValue = img.getBlueComponent()[i][j];
        int intensityValue = (int) Math.ceil((double) (redValue + greenValue + blueValue) / 3);
        if (intensityValue > 255) {
          intensityValue = 255;
        } else if (intensityValue < 0) {
          intensityValue = 0;
        }
        intensity[i][j] = intensityValue;
      }
    }
    Image i = new PPMImage(width, height, intensity, intensity, intensity);

    images.put(newImageName, i);
  }

  /**
   * Method to get the luma image of an PPMImage.
   *
   * @param currentImageName The name of the PPMImage to get the luma image from.
   * @param newImageName     The name to save the resulting luma image as.
   */
  @Override
  public void getLumaImage(String currentImageName, String newImageName)
          throws NoSuchElementException {
    Image img = images.get(currentImageName);
    if (img == null) {
      throw new NoSuchElementException("IME.model.Image with name " + currentImageName
              + " not in memory.");
    }
    int width = img.getWidth();
    int height = img.getHeight();
    int[][] luma = new int[width][height];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        int redValue = img.getRedComponent()[i][j];
        int greenValue = img.getGreenComponent()[i][j];
        int blueValue = img.getBlueComponent()[i][j];
        int lumaValue = (int) Math.ceil(0.2126 * redValue + 0.7152
                * greenValue + 0.0722 * blueValue);
        if (lumaValue > 255) {
          lumaValue = 255;
        } else if (lumaValue < 0) {
          lumaValue = 0;
        }
        luma[i][j] = lumaValue;
      }
    }
    Image lumaImg = new PPMImage(width, height, luma, luma, luma);
    images.put(newImageName, lumaImg);
  }

  /**
   * Method to brighten or darken an PPMImage.
   *
   * @param currentImageName The name of the PPMImage to brighten or darken.
   * @param newImageName     The name to save the resulting PPMImage as.
   * @param scale            The scale to brighten or darken the PPMImage.
   */
  @Override
  public void brighten(String currentImageName, String newImageName, int scale)
          throws NoSuchElementException {
    Image img = images.get(currentImageName);
    if (img == null) {
      throw new NoSuchElementException("IME.model.Image with name \"" + currentImageName
              + "\" not in memory.");
    }
    int width = img.getWidth();
    int height = img.getHeight();
    int[][] red = brightenArray(img, img.getRedComponent(), scale);
    int[][] green = brightenArray(img, img.getGreenComponent(), scale);
    int[][] blue = brightenArray(img, img.getBlueComponent(), scale);
    Image brightened = new PPMImage(width, height, red, blue, green);

    images.put(newImageName, brightened);

  }

  /**
   * Method to split an PPMImage into its rgb components.
   *
   * @param currentImageName The name of the PPMImage to split into its rgb components.
   * @param redImageName     The name to save the resulting red component PPMImage as.
   * @param greenImageName   The name to save the resulting green component PPMImage as.
   * @param blueImageName    The name to save the resulting blue component PPMImage as.
   */
  @Override
  public void rgbSplit(String currentImageName, String redImageName, String greenImageName,
                       String blueImageName) throws NoSuchElementException {
    Image i = images.get(currentImageName);
    if (i == null) {
      throw new NoSuchElementException("IME.model.Image with name \"" + currentImageName
              + "\" not in memory.");
    }
    int[][] r = i.getRedComponent();
    int[][] g = i.getGreenComponent();
    int[][] b = i.getBlueComponent();
    int height = i.getHeight();
    int width = i.getWidth();
    Image red = new PPMImage(width, height, r, r, r);
    images.put(redImageName, red);
    Image green = new PPMImage(width, height, g, g, g);
    images.put(greenImageName, green);
    Image blue = new PPMImage(width, height, b, b, b);
    images.put(blueImageName, blue);
  }

  /**
   * Method to combine rgb components into one PPMImage.
   *
   * @param newImageName The name to save the resulting PPMImage as.
   * @param rImageName   The name of the red component PPMImage.
   * @param gImageName   The name of the green component PPMImage.
   * @param bImageName   The name of the blue component PPMImage.
   */
  @Override
  public void rgbCombine(String newImageName, String rImageName, String gImageName,
                         String bImageName) throws NoSuchElementException {
    Image redImage = images.get(rImageName);
    Image greenImage = images.get(gImageName);
    Image blueImage = images.get(bImageName);
    String exceptionOut = "";
    if (redImage == null) {
      exceptionOut += "IME.model.Image with name \"" + rImageName + "\" not in memory.\t";
    }
    if (blueImage == null) {
      exceptionOut += "IME.model.Image with name \"" + bImageName + "\" not in memory.\t";
    }
    if (greenImage == null) {
      exceptionOut += "IME.model.Image with name \"" + gImageName + "\" not in memory.\t";
    }
    if (!exceptionOut.isEmpty()) {
      throw new NoSuchElementException(exceptionOut);
    }

    if (compareDimensions(redImage, greenImage) && compareDimensions(greenImage, blueImage)
            && compareDimensions(blueImage, redImage)) {
      images.put(newImageName, new PPMImage(redImage.getWidth(), redImage.getHeight(),
              redImage.getRedComponent(), blueImage.getBlueComponent(),
              greenImage.getGreenComponent()));
    }
  }

  /**
   * Method to get the greyscale image of an PPMImage.
   *
   * @param greyScaleComponent The method to get the grey scale of the PPMImage.
   * @param imageName          The PPMImage to get the grey scale image from.
   * @param newImageName       The name to save the resulting grey scale image as.
   */
  @Override
  public void greyscale(String greyScaleComponent, String imageName, String newImageName)
          throws IllegalArgumentException {
    switch (greyScaleComponent) {
      case "value-component":
        getValueImage(imageName, newImageName);
        break;
      case "luma-component":
        getLumaImage(imageName, newImageName);
        break;
      case "intensity-component":
        getIntensityImage(imageName, newImageName);
        break;
      case "red-component":
        getRedComponent(imageName, newImageName);
        break;
      case "green-component":
        getGreenComponent(imageName, newImageName);
        break;
      case "blue-component":
        getBlueComponent(imageName, newImageName);
        break;
      default:
        throw new IllegalArgumentException("Argument \"" + greyScaleComponent
                + "\" invalid for \"greyscale\"");
    }
  }

  @Override
  public void dither(String imageName, String newImageName) {
    Image img = images.get(imageName);
    if (img == null) {
      throw new NoSuchElementException("IME.model.Image with name \"" + imageName
              + "\" not in memory.");
    }
    int[][] r = img.getRedComponent();
    int[][] g = img.getGreenComponent();
    int[][] b = img.getBlueComponent();
    int height = img.getHeight();
    int width = img.getWidth();

  }

  @Override
  public void sepia(String imageName, String newImageName) {
    Image img = images.get(imageName);
    if (img == null) {
      throw new NoSuchElementException("IME.model.Image with name \"" + imageName
              + "\" not in memory.");
    }

    int[][] red = img.getRedComponent();
    int[][] green = img.getGreenComponent();
    int[][] blue = img.getBlueComponent();
    int height = img.getHeight();
    int width = img.getWidth();
    int[][] r = new int[width][height];
    int[][] g = new int[width][height];
    int[][] b = new int[width][height];

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        int redVal = (int) Math.rint(0.393 * red[i][j]
                + (0.769 * green[i][j])
                + (0.189 * blue[i][j]));
        int greenVal = (int) Math.rint((0.349 * red[i][j])
                + (0.686 * green[i][j])
                + (0.168 * blue[i][j]));
        int blueVal = (int) Math.rint((0.272 * red[i][j])
                + (0.534 * green[i][j])
                + (0.131 * blue[i][j]));
        r[i][j] = redVal;
        g[i][j] = greenVal;
        b[i][j] = blueVal;

        if (r[i][j] > 255) {
          r[i][j] = 255;
        }
        if (g[i][j] > 255) {
          g[i][j] = 255;
        }
        if (b[i][j] > 255) {
          b[i][j] = 255;
        }

        if (b[i][j] < 0) {
          b[i][j] = 0;
        }

        if (g[i][j] < 0) {
          g[i][j] = 0;
        }

        if (r[i][j] < 0) {
          r[i][j] = 0;
        }

      }
    }

    Image sepiaImg = new PPMImage(width, height, r, b, g);
    images.put(newImageName, sepiaImg);
  }

  @Override
  public void sharpen(String imageName, String newImageName) {
    Image img = images.get(imageName);
    if (img == null) {
      throw new NoSuchElementException("IME.model.Image with name \"" + imageName
              + "\" not in memory.");
    }
    int[][] r = img.getRedComponent();
    int[][] g = img.getGreenComponent();
    int[][] b = img.getBlueComponent();
    int height = img.getHeight();
    int width = img.getWidth();
    double[][] sharpenFilter = new double[5][];
    sharpenFilter[0] = new double[]{-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0};
    sharpenFilter[1] = new double[]{-1.0 / 8.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, -1.0 / 8.0};
    sharpenFilter[2] = new double[]{-1.0 / 8.0, 1.0 / 4.0, 1.0, 1.0 / 4.0, -1.0 / 8.0};
    sharpenFilter[3] = new double[]{-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0};
    sharpenFilter[4] = new double[]{-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0};

  }

  @Override
  public void blur(String imageName, String newImageName) {
    Image img = images.get(imageName);
    if (img == null) {
      throw new NoSuchElementException("IME.model.Image with name \"" + imageName
              + "\" not in memory.");
    }
    int[][] r = img.getRedComponent();
    int[][] g = img.getGreenComponent();
    int[][] b = img.getBlueComponent();
    int height = img.getHeight();
    int width = img.getWidth();
    double[][] blurFilter = new double[3][];
    blurFilter[0] = new double[]{1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0};
    blurFilter[1] = new double[]{1.0 / 8.0, 1.0 / 4.0, 1.0 / 8.0};
    blurFilter[2] = new double[]{1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0};

  }


  @Override
  public void matrixGreyscale(String imageName, String newImageName) {
    Image img = images.get(imageName);
    if (img == null) {
      throw new NoSuchElementException("IME.model.Image with name \"" + imageName
              + "\" not in memory.");
    }
    getLumaImage(imageName, newImageName);
  }

  private boolean compareDimensions(Image firstImage, Image secondImage) {
    return (firstImage.getHeight() == secondImage.getHeight()
            && firstImage.getWidth() == secondImage.getWidth());
  }

  private int[][] flipArrayHorizontal(Image img, int[][] arr) {
    int temp;
    int height = img.getHeight();
    int width = img.getWidth();
    int[][] flippedH = new int[width][height];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width / 2; j++) {
        temp = arr[width - j - 1][i];
        flippedH[width - j - 1][i] = arr[j][i];
        flippedH[j][i] = temp;
      }
    }

    return flippedH;
  }

  private int[][] flipArrayVertical(Image img, int[][] arr) {
    int temp;
    int height = img.getHeight();
    int width = img.getWidth();
    int[][] flippedV = new int[width][height];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height / 2; j++) {
        temp = arr[i][j];
        flippedV[i][j] = arr[i][height - 1 - j];
        flippedV[i][height - 1 - j] = temp;
      }
    }
    return flippedV;
  }


  private int[][] brightenArray(Image img, int[][] arr, int scale) {
    int height = img.getHeight();
    int width = img.getWidth();
    int[][] brightened = new int[width][height];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        int val = arr[i][j] + scale;
        if (val > 255) {
          val = 255;
        } else if (val < 0) {
          val = 0;
        }
        brightened[i][j] = val;
      }
    }
    return brightened;
  }


  public static int[][] applyFilter(double[][] filter, double[][] arr) {
    int vals = 0;
    int height =arr.length;
    int width = arr[0].length;
    int middleI = (int) Math.ceil((double)filter[0].length/2);
    int middleJ = (int) Math.ceil((double)filter.length/2);
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
          int distTowardsUpperEdge = j;
          int distTowardsLowerEdge = height - j;
          int distTowardsLeftEdge = i;
          int distTowardsRightEdge = width - i;
          int distRight = filter[0].length - middleI;
          int distLeft = middleI;
          int distUp = middleJ;
          int distDown = filter.length - middleJ;

        System.out.println(distTowardsUpperEdge);
        System.out.println(distTowardsLowerEdge);
        System.out.println(distTowardsRightEdge);
        System.out.println(distTowardsLeftEdge);

        System.out.println();
        System.out.println(distUp);
        System.out.println(distDown);
        System.out.println(distRight);
        System.out.println(distLeft);

          if(!(distTowardsUpperEdge < distUp)) {
            //dont calculate up
            boolean canGoUp = true;
          }

          if(!(distTowardsLowerEdge < distDown)) {
            boolean canGoDown = true;
          }

          if(!(distTowardsLeftEdge < distLeft)) {
            boolean canGoLeft = true;
          }



      }
    }
    return new int[1][1];
  }

  public static void main(String[] args) {
    double[][] sharpenFilter = new double[5][];
    sharpenFilter[0] = new double[]{-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0};
    sharpenFilter[1] = new double[]{-1.0 / 8.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, -1.0 / 8.0};
    sharpenFilter[2] = new double[]{-1.0 / 8.0, 1.0 / 4.0, 1.0, 1.0 / 4.0, -1.0 / 8.0};
    sharpenFilter[3] = new double[]{-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0};
    sharpenFilter[4] = new double[]{-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0};

    double[][] test = new double[8][6];

    for (double[] row: test)
      Arrays.fill(row, 1.0);

    applyFilter(sharpenFilter, test);
  }


  /**
   * A class to represent an image in PPM Format.
   */
  public static class PPMImage extends AbstractImage {

    /**
     * Constructor for a PPMImage object.
     *
     * @param width  The width of the image in pixels.
     * @param height The height of the image in pixels.
     * @param red    The red component of the rgb of the image.
     * @param blue   The blue component of the rgb of the image.
     * @param green  The green component of the rgb of the image.
     */
    public PPMImage(int width, int height, int[][] red,
                    int[][] blue, int[][] green) {
      super(width, height, red,
              blue, green);
    }

  }
}
