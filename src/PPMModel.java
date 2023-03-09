import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PPMModel implements Model{

  Map<String, Image> images = new HashMap<>();

  public PPMModel() {
    //only one default field
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
    try {
      ImageUtil.writeToPPMFile(i,imagePath);
    } catch(IOException e) {
      System.out.println(e.getMessage());
      return 0;
    }
    return 1;
  }

  @Override
  public int getRedComponent(String currentImageName, String newImageName) {
    Image i = images.get(currentImageName);
    int[][] r =  i.getRedComponent();
    images.put(newImageName,new PPMImage(i.getWidth(),i.getHeight(),r,r,r));
    return 1;
  }

  @Override
  public int getGreenComponent(String currentImageName, String newImageName) {
    Image i = images.get(currentImageName);
    int[][] r =  i.getGreenComponent();
    images.put(newImageName,new PPMImage(i.getWidth(),i.getHeight(),r,r,r));
    return 1;
  }

  @Override
  public int getBlueComponent(String currentImageName, String newImageName) {
    Image i = images.get(currentImageName);
    int[][] r =  i.getBlueComponent();
    images.put(newImageName,new PPMImage(i.getWidth(),i.getHeight(),r,r,r));
    return 1;
  }

  @Override
  public int flipHorizontal(String currentImageName, String newImageName) {
    Image i = images.get(currentImageName);
    images.put(newImageName,i.flipHorizontal());
    return 1;
  }

  @Override
  public int flipVertical(String currentImageName, String newImageName) {
    Image i = images.get(currentImageName);
    images.put(newImageName,i.flipHorizontal());
    return 1;
  }

  @Override
  public int getValueImage(String currentImageName, String newImageName) {
    Image i = images.get(currentImageName);
    images.put(newImageName,i.getValueImage());
    return 1;
  }

  @Override
  public int getIntensityImage(String currentImageName, String newImageName) {
    Image i = images.get(currentImageName);
    images.put(newImageName,i.getIntensityImage());
    return 1;
  }

  @Override
  public int getLumaImage(String currentImageName, String newImageName) {
    Image i = images.get(currentImageName);
    images.put(newImageName,i.getLumaImage());
    return 1;
  }

  @Override
  public int brighten(String currentImageName, String newImageName, int scale) {
    Image i = images.get(currentImageName);
    images.put(newImageName,i.brighten(scale));
    return 1;
  }


  //TODO: Implement
  @Override
  public int rgbSplit(String currentImageName, String newImageName, String redImageName, String greenImageName, String blueImageName) {
    return 1;
  }
}
