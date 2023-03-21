package ime.model;

/**
 * Interface for accessing an image file.
 */
public interface Image {

  /**
   * Method to return the red component of an IME.model.Image.
   *
   * @return An integer array of red component values.
   */
  int[][] getRedComponent();

  /**
   * Method to return the green component of an IME.model.Image.
   *
   * @return An integer array of green component values.
   */
  int[][] getGreenComponent();

  /**
   * Method to return the blue component of an IME.model.Image.
   *
   * @return
   */
  int[][] getBlueComponent();

  /**
   * Method to return the height of the IME.model.Image in pixels.
   *
   * @return Integer representing pixel height of the IME.model.Image.
   */
  int getHeight();

  /**
   * Method to return the width of the IME.model.Image in pixels.
   *
   * @return Integer representing the pixel width of the IME.model.Image.
   */
  int getWidth();

}
