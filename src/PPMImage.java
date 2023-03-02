import java.awt.image.BufferedImage;

/**
 * Interface for manipulating an image file.
 */
public interface PPMImage {

  boolean isCompositePPMImage();

  PPMImage getRedscaleImage();

  PPMImage getGreenscaleImage();

  PPMImage getBluescaleImage();

  int getIndex(int i, int j);

  int getHeight();

  int getWidth();

  PPMImage flipHorizontal();

  PPMImage flipVertical();

  PPMImage getValueImage();

  PPMImage getIntensityImage();

  PPMImage getLumaImage();

  PPMImage greyscale();

  PPMImage brighten();

  BufferedImage writePPM();


}
