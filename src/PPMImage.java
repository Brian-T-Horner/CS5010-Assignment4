import java.awt.image.BufferedImage;

/**
 * Interface for manipulating a .ppm image file.
 */
public interface PPMImage {

  boolean isSingleChannel();

  int [][] getRedComponent();

  int [][] getGreenComponent();

  int [][] getBlueComponent();


  int getHeight();

  int getWidth();

  PPMImage flipHorizontal(String name);

  PPMImage flipVertical(String name);

  PPMImage getValueImage(String name);

  PPMImage getIntensityImage(String name);

  PPMImage getLumaImage(String name);

  PPMImage brighten(String name,int scale);

}
