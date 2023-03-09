import java.awt.image.BufferedImage;

/**
 * Class for manipulating a .ppm image file.
 */
abstract class AbstractPPMImage implements PPMImage {

  protected String name;
  protected final int height;
  protected final int width;


  public AbstractPPMImage(String name, int width, int height) {
    this.name = name;
    this.width = width;
    this.height = height;
  }

  public abstract boolean isCompositePPMImage();

  @Override
  public abstract PPMImage getRedscaleImage(String name);

  @Override
  public abstract PPMImage getGreenscaleImage(String name);

  @Override
  public abstract PPMImage getBluescaleImage(String name);

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }

  public String getName() {
    return name;
  }

  // Needed for controller arguments?
  public void setName(String name) {this.name = name;}

  public abstract PPMImage flipHorizontal(String name);

  public abstract PPMImage flipVertical(String name);

  public abstract PPMImage getValueImage(String name);

  public abstract PPMImage getIntensityImage(String name);

  public abstract PPMImage getLumaImage(String name);

  public abstract PPMImage brighten(String name,int scale);

  public abstract BufferedImage writeBufferedImage();

  @Override
  public int hashCode() {
    return 67499 * this.getWidth() * this.getHeight();
  }
}
