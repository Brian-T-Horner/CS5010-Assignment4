
/**
 * A class to represent an image in PPM Format.
 */
public class PPMImage implements Image {

  private final int height;
  private final int width;

  private final int[][] redComponent;

  private final int[][] greenComponent;

  private final int[][] blueComponent;

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
    this.height = height;
    this.width = width;
    this.redComponent = red;
    this.greenComponent = green;
    this.blueComponent = blue;
  }


  /**
   * Method to return the red component of the PPMImage objects rgb.
   *
   * @return A matrix representing the red component of the PPMImage.
   */
  @Override
  public int[][] getRedComponent() {
    return redComponent;
  }

  /**
   * Method to return the green component of the PPMImage objects rgb.
   *
   * @return A matrix representing the green component of the PPMImage.
   */
  @Override
  public int[][] getGreenComponent() {
    return greenComponent;
  }

  /**
   * Method to return the blue component of the PPMImage objects rgb.
   *
   * @return A matrix representing the blue component of the PPMImage.
   */
  @Override
  public int[][] getBlueComponent() {
    return blueComponent;
  }

  /**
   * Method to return the height of the PPMImage in pixels.
   *
   * @return An integer representing the height of the PPMImage in pixels.
   */
  @Override
  public int getHeight() {
    return height;
  }

  /**
   * Method to return the width of the PPMImage in pixels.
   *
   * @return An integer representing the width of the PPMImage in pixels.
   */
  @Override
  public int getWidth() {
    return width;
  }

  /**
   * A method to flip the PPMImage horizontally and return a resulting Image.
   *
   * @return The resulting image of the flip horizontal of the PPMImage.
   */
  @Override
  public Image flipHorizontal() {
    int[][] red = flipArrayHorizontal(redComponent);
    int[][] green = flipArrayHorizontal(greenComponent);
    int[][] blue = flipArrayHorizontal(blueComponent);
    return new PPMImage(width, height, red, blue, green);
  }

  /**
   * A method flip horizontal the individual rgb components of an image. Supports flipHorizontal method.
   *
   * @param arr The array to flip horizontally.
   * @return The result of flipping the arr array horizontally.
   */
  private int[][] flipArrayHorizontal(int[][] arr) {
    int temp;
    int[][] flippedH = new int[width][height];
    for (int i = 0; i < getHeight(); i++) {
      for (int j = 0; j < getWidth() / 2; j++) {
        temp = arr[getWidth() - j - 1][i];
        flippedH[getWidth() - j - 1][i] = arr[j][i];
        flippedH[j][i] = temp;
      }
    }

    return flippedH;
  }

  /**
   * A method to flip the PPMImage vertically.
   *
   * @return The resulting image of the flip vertical of the PPMImage.
   */
  @Override
  public Image flipVertical() {
    int[][] red = flipArrayVertical(redComponent);
    int[][] green = flipArrayVertical(greenComponent);
    int[][] blue = flipArrayVertical(blueComponent);
    return new PPMImage(width, height, red, blue, green);
  }

  /**
   * A method flip vertical the individual rgb components of an image. Supports flipVertical method.
   *
   * @param arr The array to flip vertical.
   * @return The result of flipping the arr array vertical.
   */
  private int[][] flipArrayVertical(int[][] arr) {
    int temp;
    int[][] flippedV = new int[width][height];
    for (int i = 0; i < getWidth(); i++) {
      for (int j = 0; j < getHeight() / 2; j++) {
        temp = arr[i][j];
        flippedV[i][j] = arr[i][getHeight() - 1 - j];
        flippedV[i][getHeight() - 1 - j] = temp;
      }
    }
    return flippedV;
  }


  /**
   * Method to get the value image from the PPMImage object.
   *
   * @return A new image that is the value image from the PPMImage.
   */
  @Override
  public Image getValueImage() {
    int[][] value = new int[width][height];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        int redValue = redComponent[i][j];
        int greenValue = greenComponent[i][j];
        int blueValue = blueComponent[i][j];
        int max = Math.max(redValue, greenValue);
        max = Math.max(max, blueValue);
        value[i][j] = max;
      }
    }
    return new PPMImage(width, height, value, value, value);
  }

  /**
   * A method to get the intensity image of a PPMImage.
   *
   * @return A new image that is the intensity image of the PPMImage.
   */
  @Override
  public Image getIntensityImage() {
    int[][] intensity = new int[width][height];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        int redValue = redComponent[i][j];
        int greenValue = greenComponent[i][j];
        int blueValue = blueComponent[i][j];
        int intensityValue = (int) Math.ceil((double) (redValue + greenValue + blueValue) / 3);
        if (intensityValue > 255) {
          intensityValue = 255;
        } else if (intensityValue < 0) {
          intensityValue = 0;
        }
        intensity[i][j] = intensityValue;
      }
    }
    return new PPMImage(width, height, intensity, intensity, intensity);
  }

  /**
   * Method to get the luma image of a PPMImage.
   *
   * @return A new image that is the luma image of the PPMImage.
   */
  @Override
  public Image getLumaImage() {
    int[][] luma = new int[width][height];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        int redValue = redComponent[i][j];
        int greenValue = greenComponent[i][j];
        int blueValue = blueComponent[i][j];
        int lumaValue = (int) Math.ceil((double) 0.2126 * redValue + 0.7152 * greenValue + 0.0722 * blueValue);
        if (lumaValue > 255) {
          lumaValue = 255;
        } else if (lumaValue < 0) {
          lumaValue = 0;
        }
        luma[i][j] = lumaValue;
      }
    }
    return new PPMImage(width, height, luma, luma, luma);
  }

  /**
   * Method to brighten a PPMImage
   *
   * @param scale Scale to brighten (positive number) the image or darken (negative number) the image.
   * @return An image that is the result of brightening the PPMImage.
   */
  @Override
  public Image brighten(int scale) {
    int[][] red = brightenArray(redComponent, scale);
    int[][] green = brightenArray(greenComponent, scale);
    int[][] blue = brightenArray(blueComponent, scale);
    return new PPMImage(width, height, red, blue, green);
  }

  /**
   * A method to brighten a matrix array of the PPMImage.
   *
   * @param arr   Array to brighten.
   * @param scale The scale to brighten.
   * @return A new matrix that is the result of the brighten operation.
   */
  private int[][] brightenArray(int[][] arr, int scale) {
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
   * Method to determine if two PPMImage objects are equal.
   *
   * @param o An object to check if equal to {@code this} PPMImage.
   * @return True if equal, false if otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof PPMImage)) {
      return false;
    }
    PPMImage c = (PPMImage) o;

    return c.getWidth() == this.getWidth() && c.getHeight() == this.getHeight() &&
            c.blueComponent.equals(blueComponent) && c.greenComponent.equals(greenComponent) &&
            c.redComponent.equals(redComponent);

  }

  /**
   * Method to get a hashCode for a PPMImage.
   *
   * @return An integer hashcode for the object.
   */
  @Override
  public int hashCode() {
    return 67499 * this.getWidth() * this.getHeight();
  }

}

