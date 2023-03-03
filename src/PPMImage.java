import java.awt.image.BufferedImage;

/**
 * Interface for manipulating an image file.
 */
public interface PPMImage {

  boolean isCompositePPMImage();

  PPMImage getRedscaleImage(String name);

  PPMImage getGreenscaleImage(String name);

  PPMImage getBluescaleImage(String name);

  int getIndex(int i, int j);

  int getHeight();

  int getWidth();

  String getName();

  PPMImage flipHorizontal(String name);

  PPMImage flipVertical(String name);

  PPMImage getValueImage(String name);

  PPMImage getIntensityImage(String name);

  PPMImage getLumaImage(String name);

  PPMImage brighten(String name,int scale);

  BufferedImage writeBufferedImage();


}
