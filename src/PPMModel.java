import java.io.IOException;
import java.util.*;

/**
 * PPMModel that controls the method of a PPMImage.
 */
public class PPMModel implements Model{

  Map<String, Image> images = new HashMap<>();

  Set<String> commands = new HashSet<>(Arrays.asList("vertical-flip", "horizontal-flip", "greyscale", "brighten",
          "rgb-split", "rgb-combine", "value", "intensity", "luma", "save", "load", "darken"));

  /**
   * Constructor for PPMModel. No parameters as only one default field.
   */
  public PPMModel() {
    //only one default field
  }

  /**
   * Method to return the commands of the PPMModel.
   * @return A Set of Strings that are the commands of the PPMModel.
   */
  public Set<String> getCommands() {
    return commands;
  }

  /**
   * Method to load a PPMImage - calls the PPMImage object method.
   * @param imagePath The path of the PPMImage to load.
   * @param newImageName The name to assign to the loaded PPMImage.
   * @return 0 for failure, other numbers for success.
   */
  @Override
  public int loadImage(String imagePath, String newImageName) {
    Image i = ImageUtil.readIntoPPMImage(imagePath);
    images.put(newImageName,i);
    return 1;
  }

  /**
   * Method to save an PPMImage - calls the PPMImage object method.
   * @param imagePath The path of where to save the PPMImage.
   * @param imageName The name of the PPMImage to save.
   * @return 0 for failure, other numbers for success.
   */
  @Override
  public int saveImage(String imagePath, String imageName) {
    Image i = images.get(imageName);
    if(i != null) {
      try {
        ImageUtil.writeToPPMFile(i, imagePath);
      } catch (IOException e) {
        System.out.println(e.getMessage());
        return 0;
      }
    }
    return 1;
  }

  /**
   * Method to get the red component of an PPMImage - calls the PPMImage object method.
   * @param currentImageName The name of the PPMImage to get the red component from.
   * @param newImageName The name to save the resulting PPMImage as.
   * @return 0 for failure, other numbers for success.
   */
  @Override
  public int getRedComponent(String currentImageName, String newImageName) {
    Image i = images.get(currentImageName);
    if (i != null) {
      int[][] r = i.getRedComponent();
      images.put(newImageName, new PPMImage(i.getWidth(), i.getHeight(), r, r, r));
      return 1;
    }
    return 0;
  }

  /**
   * Method to get the green component of an PPMImage - calls the PPMImage object method.
   * @param currentImageName The name of the PPMImage to get the green component from.
   * @param newImageName The name to save the resulting PPMImage as.
   * @return 0 for failure, other numbers for success.
   */
  @Override
  public int getGreenComponent(String currentImageName, String newImageName) {
    Image i = images.get(currentImageName);
    if (i != null) {
      int[][] r = i.getGreenComponent();
      images.put(newImageName, new PPMImage(i.getWidth(), i.getHeight(), r, r, r));
      return 1;
    }
    return 0;
  }

  /**
   * Method to get the blue component of an PPMImage - calls the PPMImage object method.
   * @param currentImageName The name of the PPMImage to get the blue component from.
   * @param newImageName The name to save the resulting PPMImage as.
   * @return 0 for failure, other numbers for success.
   */
  @Override
  public int getBlueComponent(String currentImageName, String newImageName) {
    Image i = images.get(currentImageName);
    if (i != null) {
      int[][] r = i.getBlueComponent();
      images.put(newImageName, new PPMImage(i.getWidth(), i.getHeight(), r, r, r));
      return 1;
    }
    return 0;
  }

  /**
   * Method to flip an PPMImage horizontally - calls the PPMImage object method.
   * @param currentImageName The name of the PPMImage to flip horizontally.
   * @param newImageName The name to save the resulting PPMImage as.
   * @return 0 for failure, other numbers for success.
   */
  @Override
  public int flipHorizontal(String currentImageName, String newImageName) {
    Image i = images.get(currentImageName);
    if (i != null) {
      images.put(newImageName, i.flipHorizontal());
      return 1;
    }
    return 0;
  }

  /**
   * Method to flip an PPMImage vertically - calls the PPMImage object method.
   * @param currentImageName The name of the PPMImage to flip vertically.
   * @param newImageName The name to save the resulting PPMImage as.
   * @return 0 for failure, other numbers for success.
   */
  @Override
  public int flipVertical(String currentImageName, String newImageName) {
    Image i = images.get(currentImageName);
    if (i != null) {
      images.put(newImageName, i.flipHorizontal());
      return 1;
    }
    return 0;
  }

  /**
   * Method to get the value image of an PPMImage - calls the PPMImage object method.
   * @param currentImageName The name of the PPMImage to get the value image from.
   * @param newImageName The name to save the resulting value image as.
   * @return 0 for failure, other numbers for success.
   */
  @Override
  public int getValueImage(String currentImageName, String newImageName) {
    Image i = images.get(currentImageName);
    if (i != null) {
      images.put(newImageName, i.getValueImage());
      return 1;
    }
    return 0;
  }

  /**
   * Method to get the intensity image of an PPMImage - calls the PPMImage object method.
   * @param currentImageName The name of the PPMImage to get the intensity image from.
   * @param newImageName The name to save the resulting intensity image as.
   * @return 0 for failure, other numbers for success.
   */
  @Override
  public int getIntensityImage(String currentImageName, String newImageName) {
    Image i = images.get(currentImageName);
    if (i != null) {
      images.put(newImageName, i.getIntensityImage());
      return 1;
    }
    return 0;
  }

  /**
   * Method to get the luma image of an PPMImage - calls the PPMImage object method.
   * @param currentImageName The name of the PPMImage to get the luma image from.
   * @param newImageName The name to save the resulting luma image as.
   * @return 0 for failure, other numbers for success.
   */
  @Override
  public int getLumaImage(String currentImageName, String newImageName) {
    Image i = images.get(currentImageName);
    if (i != null) {
      images.put(newImageName, i.getLumaImage());
      return 1;
    }
    return 0;
  }

  /**
   * Method to brighten or darken an PPMImage - calls the PPMImage object method.
   * @param currentImageName The name of the PPMImage to brighten or darken.
   * @param newImageName The name to save the resulting PPMImage as.
   * @param scale The scale to brighten or darken the PPMImage.
   * @return 0 for failure, other numbers for success.
   */
  @Override
  public int brighten(String currentImageName, String newImageName, int scale) {
    Image i = images.get(currentImageName);
    if (i != null) {
      if (scale > 0) {
        images.put(newImageName, i.brighten(scale));
      } else {
        images.put(newImageName, i.darken(-1 * scale));
      }
      return 1;
    }
    return 0;
  }

  /**
   * Method to split an PPMImage into its rgb components - calls the PPMImage object method.
   * @param currentImageName The name of the PPMImage to split into its rgb components.
   * @param redImageName The name to save the resulting red component PPMImage as.
   * @param greenImageName The name to save the resulting green component PPMImage as.
   * @param blueImageName The name to save the resulting blue component PPMImage as.
   * @return 0 for failure, other numbers for success.
   */
  @Override
  public int rgbSplit(String currentImageName, String redImageName, String greenImageName, String blueImageName) {
    getRedComponent(currentImageName, redImageName);
    getGreenComponent(currentImageName, greenImageName);
    getBlueComponent(currentImageName, blueImageName);
    return 1;
  }

  /**
   * Method to combine rgb components into one PPMImage.
   * @param newImageName The name to save the resulting PPMImage as.
   * @param rImageName The name of the red component PPMImage.
   * @param gImageName The name of the green component PPMImage.
   * @param bImageName The name of the blue component PPMImage.
   * @return 0 for failure, other numbers for success.
   */
  @Override
  public int rgbCombine(String newImageName, String rImageName, String gImageName, String bImageName) {
    Image redImage = images.get(rImageName);
    Image greenImage = images.get(gImageName);
    Image blueImage = images.get(bImageName);
    if ( redImage != null && greenImage != null && blueImage != null) {
      if (compareDimensions(redImage, greenImage) && compareDimensions(greenImage, blueImage)
          && compareDimensions(blueImage, redImage)) {
        images.put(newImageName, new PPMImage(redImage.getWidth(), redImage.getHeight(),
            redImage.getRedComponent(), blueImage.getBlueComponent(),
            greenImage.getGreenComponent()));
        return 1;
      }
    }
      return 0;
  }

  /**
   * Method to get the greyscale image of an PPMImage - calls the PPMImage object method.
   * @param greyScaleComponent The method to get the grey scale of the PPMImage.
   * @param imageName The PPMImage to get the grey scale image from.
   * @param newImageName The name to save the resulting grey scale image as.
   * @return 0 for failure, other numbers for success.
   */
  //TODO: Implement
  @Override
  public int greyscale(String greyScaleComponent, String imageName, String newImageName) {
    return 1;
  }

  /**
   * Helper method to compare dimensions of two PPMImages and see height and width are equal.
   * @param firstImage First PPMImage to compare.
   * @param secondImage Second PPMImage to compare.
   * @return True if height and widths are equal, false if otherwise.
   */
  private boolean compareDimensions(Image firstImage, Image secondImage) {
    return (firstImage.getHeight() == secondImage.getHeight()
        && firstImage.getWidth() == secondImage.getWidth());
  }
}
