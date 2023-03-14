/**
 * Interface for manipulating an image file.
 */
public interface Image {

  /**
   * Method to return the red component of an Image.
   *
   * @return An integer array of red component values.
   */
  int[][] getRedComponent();

  /**
   * Method to return the green component of an Image.
   *
   * @return An integer array of green component values.
   */
  int[][] getGreenComponent();

  /**
   * Method to return the blue component of an Image.
   *
   * @return
   */
  int[][] getBlueComponent();

  /**
   * Method to return the height of the Image in pixels.
   *
   * @return Integer representing pixel height of the Image.
   */
  int getHeight();

  /**
   * Method to return the width of the Image in pixels.
   *
   * @return Integer representing the pixel width of the Image.
   */
  int getWidth();

}
