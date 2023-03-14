import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * PPMModel that controls the method of a PPMImage.
 */
public class PPMModel implements Model {

  private Map<String, Image> images = new HashMap<>();

  /**
   * Constructor for PPMModel. No parameters as only one default field.
   */
  public PPMModel() {
    //only two default field
  }

  /**
   * Method to load a PPMImage - calls the PPMImage object method.
   *
   * @param imagePath    The path of the PPMImage to load.
   * @param newImageName The name to assign to the loaded PPMImage.
   * @return 0 for failure, other numbers for success.
   */
  @Override
  public void loadImage(String imagePath, String newImageName) throws FileNotFoundException {
    Image i = ImageUtil.readIntoPPMImage(imagePath);
    if (i == null) {
      throw new FileNotFoundException("File \"" + imagePath + "\" not found!");
    }
    images.put(newImageName, i);
  }

  /**
   * Method to save an PPMImage - calls the PPMImage object method.
   *
   * @param imagePath The path of where to save the PPMImage.
   * @param imageName The name of the PPMImage to save.
   * @return 0 for failure, other numbers for success.
   */
  @Override
  public void saveImage(String imagePath, String imageName) throws IOException, NoSuchElementException {
    Image i = images.get(imageName);
    if (i == null) {
      throw new NoSuchElementException("Image with name \"" + imageName + "\" not in memory.");
    }
    if (!imagePath.endsWith(".ppm")) {
      throw new IOException("Filename needs to end with .ppm");
    }
    try {
      ImageUtil.writeToPPMFile(i, imagePath);
    } catch (IOException e) {
      throw new IOException("Invalid image path");
    }
  }

  /**
   * Method to get the red component of an PPMImage - calls the PPMImage object method.
   *
   * @param currentImageName The name of the PPMImage to get the red component from.
   * @param newImageName     The name to save the resulting PPMImage as.
   * @return 0 for failure, other numbers for success.
   */
  @Override
  public void getRedComponent(String currentImageName, String newImageName) throws NoSuchElementException {
    Image i = images.get(currentImageName);
    if (i != null) {
      int[][] r = i.getRedComponent();
      images.put(newImageName, new PPMImage(i.getWidth(), i.getHeight(), r, r, r));
    } else {
      throw new NoSuchElementException("Image with name " + currentImageName + " not in memory.");
    }
  }

  /**
   * Method to get the green component of an PPMImage - calls the PPMImage object method.
   *
   * @param currentImageName The name of the PPMImage to get the green component from.
   * @param newImageName     The name to save the resulting PPMImage as.
   * @return 0 for failure, other numbers for success.
   */
  @Override
  public void getGreenComponent(String currentImageName, String newImageName) throws NoSuchElementException {
    Image i = images.get(currentImageName);
    if (i != null) {
      int[][] r = i.getGreenComponent();
      images.put(newImageName, new PPMImage(i.getWidth(), i.getHeight(), r, r, r));
    } else {
      throw new NoSuchElementException("Image with name " + currentImageName + " not in memory.");
    }
  }

  /**
   * Method to get the blue component of an PPMImage - calls the PPMImage object method.
   *
   * @param currentImageName The name of the PPMImage to get the blue component from.
   * @param newImageName     The name to save the resulting PPMImage as.
   * @return 0 for failure, other numbers for success.
   */
  @Override
  public void getBlueComponent(String currentImageName, String newImageName) throws NoSuchElementException {
    Image i = images.get(currentImageName);
    if (i != null) {
      int[][] r = i.getBlueComponent();
      images.put(newImageName, new PPMImage(i.getWidth(), i.getHeight(), r, r, r));
    } else {
      throw new NoSuchElementException("Image with name " + currentImageName + " not in memory.");
    }
  }

  /**
   * Method to flip an PPMImage horizontally - calls the PPMImage object method.
   *
   * @param currentImageName The name of the PPMImage to flip horizontally.
   * @param newImageName     The name to save the resulting PPMImage as.
   */
  @Override
  public void flipHorizontal(String currentImageName, String newImageName) throws NoSuchElementException {
    Image i = images.get(currentImageName);
    if (i == null) {
      throw new NoSuchElementException("Image with name " + currentImageName + " not in memory.");
    }
    int[][] red = flipArrayHorizontal(i, i.getRedComponent());
    int[][] green = flipArrayHorizontal(i, i.getGreenComponent());
    int[][] blue = flipArrayHorizontal(i, i.getBlueComponent());
    Image horizontal = new PPMImage(i.getWidth(), i.getHeight(), red, blue, green);

    images.put(newImageName, horizontal);
  }

  /**
   * Method to flip an PPMImage vertically - calls the PPMImage object method.
   *
   * @param currentImageName The name of the PPMImage to flip vertically.
   * @param newImageName     The name to save the resulting PPMImage as.
   * @return 0 for failure, other numbers for success.
   */
  @Override
  public void flipVertical(String currentImageName, String newImageName) {
    Image i = images.get(currentImageName);
    if (i == null) {
      throw new NoSuchElementException("Image with name " + currentImageName + " not in memory.");
    }
    int[][] red = flipArrayVertical(i, i.getRedComponent());
    int[][] green = flipArrayVertical(i, i.getGreenComponent());
    int[][] blue = flipArrayVertical(i, i.getBlueComponent());
    Image vertical = new PPMImage(i.getWidth(), i.getHeight(), red, blue, green);

    images.put(newImageName, vertical);
  }

  /**
   * Method to get the value image of an PPMImage - calls the PPMImage object method.
   *
   * @param currentImageName The name of the PPMImage to get the value image from.
   * @param newImageName     The name to save the resulting value image as.
   * @return 0 for failure, other numbers for success.
   */
  @Override
  public void getValueImage(String currentImageName, String newImageName) throws NoSuchElementException {
    Image img = images.get(currentImageName);
    if (img == null) {
      throw new NoSuchElementException("Image with name " + currentImageName + " not in memory.");
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
   * Method to get the intensity image of an PPMImage - calls the PPMImage object method.
   *
   * @param currentImageName The name of the PPMImage to get the intensity image from.
   * @param newImageName     The name to save the resulting intensity image as.
   * @return 0 for failure, other numbers for success.
   */
  @Override
  public void getIntensityImage(String currentImageName, String newImageName) throws NoSuchElementException {
    Image img = images.get(currentImageName);
    if (img == null) {
      throw new NoSuchElementException("Image with name " + currentImageName + " not in memory.");
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
   * Method to get the luma image of an PPMImage - calls the PPMImage object method.
   *
   * @param currentImageName The name of the PPMImage to get the luma image from.
   * @param newImageName     The name to save the resulting luma image as.
   * @return 0 for failure, other numbers for success.
   */
  @Override
  public void getLumaImage(String currentImageName, String newImageName) throws NoSuchElementException {
    Image img = images.get(currentImageName);
    if (img == null) {
      throw new NoSuchElementException("Image with name " + currentImageName + " not in memory.");
    }
    int width = img.getWidth();
    int height = img.getHeight();
    int[][] luma = new int[width][height];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        int redValue = img.getRedComponent()[i][j];
        int greenValue = img.getGreenComponent()[i][j];
        int blueValue = img.getBlueComponent()[i][j];
        int lumaValue = (int) Math.ceil((double) 0.2126 * redValue + 0.7152 * greenValue + 0.0722 * blueValue);
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
   * Method to brighten or darken an PPMImage - calls the PPMImage object method.
   *
   * @param currentImageName The name of the PPMImage to brighten or darken.
   * @param newImageName     The name to save the resulting PPMImage as.
   * @param scale            The scale to brighten or darken the PPMImage.
   * @return 0 for failure, other numbers for success.
   */
  @Override
  public void brighten(String currentImageName, String newImageName, int scale) throws NoSuchElementException {
    Image img = images.get(currentImageName);
    if (img == null) {
      throw new NoSuchElementException("Image with name \"" + currentImageName + "\" not in memory.");
    }
    int width = img.getWidth();
    int height = img.getHeight();
    int[][] red = brightenArray(img,img.getRedComponent(), scale);
    int[][] green = brightenArray(img,img.getGreenComponent(), scale);
    int[][] blue = brightenArray(img,img.getBlueComponent(), scale);
    Image brightened = new PPMImage(width, height, red, blue, green);

    images.put(newImageName, brightened);

  }

  /**
   * Method to split an PPMImage into its rgb components - calls the PPMImage object method.
   *
   * @param currentImageName The name of the PPMImage to split into its rgb components.
   * @param redImageName     The name to save the resulting red component PPMImage as.
   * @param greenImageName   The name to save the resulting green component PPMImage as.
   * @param blueImageName    The name to save the resulting blue component PPMImage as.
   * @return 0 for failure, other numbers for success.
   */
  @Override
  public void rgbSplit(String currentImageName, String redImageName, String greenImageName, String blueImageName) throws NoSuchElementException {
    Image i = images.get(currentImageName);
    if (i == null) {
      throw new NoSuchElementException("Image with name \"" + currentImageName + "\" not in memory.");
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
   * @return 0 for failure, other numbers for success.
   */
  @Override
  public void rgbCombine(String newImageName, String rImageName, String gImageName, String bImageName) {
    Image redImage = images.get(rImageName);
    Image greenImage = images.get(gImageName);
    Image blueImage = images.get(bImageName);
    String exceptionOut = "";
    if (redImage == null) {
      exceptionOut += "Image with name \"" + rImageName + "\" not in memory.\t";
    }
    if (blueImage == null) {
      exceptionOut += "Image with name \"" + bImageName + "\" not in memory.\t";
    }
    if (greenImage == null) {
      exceptionOut += "Image with name \"" + gImageName + "\" not in memory.\t";
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
   * Method to get the greyscale image of an PPMImage - calls the PPMImage object method.
   *
   * @param greyScaleComponent The method to get the grey scale of the PPMImage.
   * @param imageName          The PPMImage to get the grey scale image from.
   * @param newImageName       The name to save the resulting grey scale image as.
   * @return 0 for failure, other numbers for success.
   */
  @Override
  public void greyscale(String greyScaleComponent, String imageName, String newImageName) throws IllegalArgumentException {
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
        throw new IllegalArgumentException("Argument \"" + greyScaleComponent + "\" invalid for \"greyscale\"");
    }
  }

  /**
   * Helper method to compare dimensions of two PPMImages and see height and width are equal.
   *
   * @param firstImage  First PPMImage to compare.
   * @param secondImage Second PPMImage to compare.
   * @return True if height and widths are equal, false if otherwise.
   */
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

  /**
   * A method to brighten a matrix array of the PPMImage.
   *
   * @param arr   Array to brighten.
   * @param scale The scale to brighten.
   * @return A new matrix that is the result of the brighten operation.
   */
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
      super(width, height,red,
      blue, green);
    }

  }


}
