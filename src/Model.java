
public interface Model {

  int loadPPMImage(String imagePath, String newImageName);

  int savePPMImage(String imagePath, String imageName);

  int getRedComponent(String currentImageName, String newImageName);

  int getGreenComponent(String currentImageName, String newImageName);

  int getBlueComponent(String currentImageName, String newImageName);

  int flipHorizontal(String currentImageName, String newImageName);

  int flipVertical(String currentImageName, String newImageName);

  int getValueImage(String currentImageName, String newImageName);

  int getIntensityImage(String currentImageName, String newImageName);

  int getLumaImage(String currentImageName, String newImageName);

  int brighten(String currentImageName, String newImageName, int scale);

}
