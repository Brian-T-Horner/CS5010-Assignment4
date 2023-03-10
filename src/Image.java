/**
 * Interface for manipulating a .ppm image file.
 */
public interface Image {

  /**
   * Method to check if an Image is a single rgb channel.
   * @return True if all rgb values are the same, false if otherwise.
   */
  boolean isSingleChannel();

  /**
   * Method to return the red component of an Image.
   * @return An integer array of red component values.
   */
  int [][] getRedComponent();

  /**
   * Method to return the green component of an Image.
   * @return An integer array of green component values.
   */
  int [][] getGreenComponent();

  /**
   * Method to return the blue component of an Image.
   * @return
   */
  int [][] getBlueComponent();

  /**
   * Method to return the height of the Image in pixels.
   * @return Integer representing pixel height of the Image.
   */
  int getHeight();

  /**
   * Method to return the width of the Image in pixels.
   * @return Integer representing the pixel width of the Image.
   */
  int getWidth();

  /**
   * Method to flip the current Image horizontally.
   * @return A new Image object that is the previous Image flipped horizontally.
   */
  Image flipHorizontal();

  /**
   * Method to flip the current Image vertically.
   * @return A new Image object that is the previous Image flipped vertically.
   */
  Image flipVertical();

  /**
   * Method to get the value image of the current Image.
   * @return A new Image object that is the value image of the previous Image.
   */
  Image getValueImage();

  /**
   * Method to get the intensity image of the current Image.
   * @return A new Image object that is the intensity image of the previous Image.
   */
  Image getIntensityImage();

  /**
   * Method to get the luma image of the current Image.
   * @return A new Image object that is the intensity image of the previous image.
   */
  Image getLumaImage();

  /**
   * Method to get the brightened or darkened image of the current Image.
   * @param scale Scale to brighten (positive number) the image or darken (negative number) the image.
   * @return A new Image object that is the brightened or darkened image of the previous image.
   */
  Image brighten(int scale);

  /**
   * Method to help get the darkened image of the current Image. Used in conjunction with brighten.
   * @param scale Scale to darken the image. Negative number.
   * @return A new Image object that is the darkened image of the previous image.
   */
  Image darken(int scale);

}
