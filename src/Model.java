import java.util.Set;

/**
 * Model interface for our MVC designed program.
 */
public interface Model {

  /**
   * Method to load an image - calls the image object method.
   * @param imagePath The path of the image to load.
   * @param newImageName The name to assign to the loaded image.
   */
  void loadImage(String imagePath, String newImageName);

  /**
   * Method to save an image - calls the image object method.
   * @param imagePath The path of where to save the image.
   * @param imageName The name of the image to save.
   */
  void saveImage(String imagePath, String imageName);

  /**
   * Method to get the red component of an image - calls the image object method.
   * @param currentImageName The name of the image to get the red component from.
   * @param newImageName The name to save the resulting image as.
   */
  void getRedComponent(String currentImageName, String newImageName);

  /**
   * Method to get the green component of an image - calls the image object method.
   * @param currentImageName The name of the image to get the green component from.
   * @param newImageName The name to save the resulting image as.
   */
  void getGreenComponent(String currentImageName, String newImageName);

  /**
   * Method to get the blue component of an image - calls the image object method.
   * @param currentImageName The name of the image to get the blue component from.
   * @param newImageName The name to save the resulting image as.
   */
  void getBlueComponent(String currentImageName, String newImageName);

  /**
   * Method to flip an image horizontally - calls the image object method.
   * @param currentImageName The name of the image to flip horizontally.
   * @param newImageName The name to save the resulting image as.
   */
  void flipHorizontal(String currentImageName, String newImageName);

  /**
   * Method to flip an image vertically - calls the image object method.
   * @param currentImageName The name of the image to flip vertically.
   * @param newImageName The name to save the resulting image as.
   */
  void flipVertical(String currentImageName, String newImageName);

  /**
   * Method to get the value image of an image - calls the image object method.
   * @param currentImageName The name of the image to get the value image from.
   * @param newImageName The name to save the resulting image as.
   */
  void getValueImage(String currentImageName, String newImageName);

  /**
   * Method to get the intensity image of an image - calls the image object method.
   * @param currentImageName The name of the image to get the intensity image from.
   * @param newImageName The name to save the resulting image as.
   */
  void getIntensityImage(String currentImageName, String newImageName);

  /**
   * Method to get the luma image of an image - calls the image object method.
   * @param currentImageName The name of the image to get the luma image from.
   * @param newImageName The name to save the resulting image as.
   */
  void getLumaImage(String currentImageName, String newImageName);

  /**
   * Method to brighten or darken an image - calls the image object method.
   * @param currentImageName The name of the image to brighten or darken.
   * @param newImageName The name to save the resulting image as.
   * @param scale The scale to brighten or darken the image.
   */
  void brighten(String currentImageName, String newImageName, int scale);

  /**
   * Method to split an image into its rgb components - calls the image object method.
   * @param currentImageName The name of the image to split into its rgb components.
   * @param redImageName The name to save the resulting red component image as.
   * @param greenImageName The name to save the resulting green component image as.
   * @param blueImageName The name to save the resulting blue component image as.
   */
  void rgbSplit(String currentImageName, String redImageName, String greenImageName, String blueImageName);

  /**
   * Method to combine rgb components into one image.
   * @param newImageName The name to save the resulting image as.
   * @param rImageName The name of the red component image.
   * @param gImageName The name of the green component image.
   * @param bImageName The name of the blue component image.
   */
  void rgbCombine(String newImageName, String rImageName, String gImageName, String bImageName);

  /**
   * Method to get the greyscale image of an image - calls the image object method.
   * @param greyScaleComponent The method to get the grey scale of the image.
   * @param imageName The image to get the grey scale image from.
   * @param newImageName The name to save the resulting image as.
   */
  void greyscale(String greyScaleComponent, String imageName, String newImageName);

  /**
   * Method to get the commands that are available with the Model object.
   * @return Set of string commands for the current model.
   */
  Set<String> getCommands();
}
