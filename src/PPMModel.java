import java.io.IOException;
import java.util.*;

public class PPMModel implements Model{

  Map<String, Image> images = new HashMap<>();

  Set<String> commands = new HashSet<>(Arrays.asList("vertical-flip", "horizontal-flip", "greyscale", "brighten",
          "rgb-split", "rgb-combine", "value", "intensity", "luma", "save", "load", "darken"));

  public PPMModel() {
    //only one default field
  }

  public Set<String> getCommands() {
    return commands;
  }

  @Override
  public int loadPPMImage(String imagePath, String newImageName) {
    Image i = ImageUtil.readIntoPPMImage(imagePath);
    images.put(newImageName,i);
    return 1;
  }

  @Override
  public int savePPMImage(String imagePath, String imageName) {
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

  @Override
  public int flipHorizontal(String currentImageName, String newImageName) {
    Image i = images.get(currentImageName);
    if (i != null) {
      images.put(newImageName, i.flipHorizontal());
      return 1;
    }
    return 0;
  }

  @Override
  public int flipVertical(String currentImageName, String newImageName) {
    Image i = images.get(currentImageName);
    if (i != null) {
      images.put(newImageName, i.flipHorizontal());
      return 1;
    }
    return 0;
  }

  @Override
  public int getValueImage(String currentImageName, String newImageName) {
    Image i = images.get(currentImageName);
    if (i != null) {
      images.put(newImageName, i.getValueImage());
      return 1;
    }
    return 0;
  }

  @Override
  public int getIntensityImage(String currentImageName, String newImageName) {
    Image i = images.get(currentImageName);
    if (i != null) {
      images.put(newImageName, i.getIntensityImage());
      return 1;
    }
    return 0;
  }

  @Override
  public int getLumaImage(String currentImageName, String newImageName) {
    Image i = images.get(currentImageName);
    if (i != null) {
      images.put(newImageName, i.getLumaImage());
      return 1;
    }
    return 0;
  }

  @Override
  public int brighten(String currentImageName, String newImageName, int scale) {
    Image i = images.get(currentImageName);
    if (i != null) {
      images.put(newImageName, i.brighten(scale));
      return 1;
    }
    return 0;
  }


  @Override
  public int darken(String currentImageName, String newImageName, int scale) {
    Image i = images.get(currentImageName);
    if (i != null) {
      images.put(newImageName, i.darken(scale));
      return 1;
    }
    return 0;
  }

  @Override
  public int rgbSplit(String currentImageName, String redImageName, String greenImageName, String blueImageName) {
    getRedComponent(currentImageName, redImageName);
    getGreenComponent(currentImageName, greenImageName);
    getBlueComponent(currentImageName, blueImageName);
    return 1;
  }


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

  //TODO: Implement
  @Override
  public int greyscale(String greyScaleComponent, String imageName, String newImageName) {
    return 1;
  }

  private boolean compareDimensions(Image firstImage, Image secondImage) {
    return (firstImage.getHeight() == secondImage.getHeight()
        && firstImage.getWidth() == secondImage.getWidth());
  }
}
