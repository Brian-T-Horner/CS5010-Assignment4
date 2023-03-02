import java.awt.image.BufferedImage;

/**
 * Class for manipulating a .ppm image file.
 */
abstract class AbstractPPMImage implements PPMImage {

  protected final String name;
  protected final int height;
  protected final int width;


  public AbstractPPMImage(String name, int width, int height) {
    this.name = name;
    this.width = width;
    this.height = height;
  }

  public abstract boolean isCompositePPMImage();

  @Override
  public abstract PPMImage getRedscaleImage();

  @Override
  public abstract PPMImage getGreenscaleImage();

  @Override
  public abstract PPMImage getBluescaleImage();

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }

  public abstract PPMImage flipHorizontal();

  public abstract PPMImage flipVertical();

  public abstract PPMImage getValueImage();

  public abstract PPMImage getIntensityImage();

  public abstract PPMImage getLumaImage();

  public abstract PPMImage greyscale();

  public abstract PPMImage brighten();

  public abstract BufferedImage writePPM();

  @Override
  public int hashCode() {
    return 67499 * this.getWidth() * this.getHeight();
  }
}
