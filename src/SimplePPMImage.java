import java.awt.image.BufferedImage;
import java.util.Arrays;

public class SimplePPMImage extends AbstractPPMImage {


  private final int[][] component;


  public SimplePPMImage(String name, int width, int height, int[][] component) {
    super(name, width, height);
    this.component = component;
  }


  public int getIndex(int i, int j) {
    return component[i][j];
  }

  @Override
  public boolean isCompositePPMImage() {
    return false;
  }

  @Override
  public PPMImage getRedscaleImage() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("cannot get redscale of simple image");
  }

  @Override
  public PPMImage getGreenscaleImage() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("cannot get greenscale of simple image");
  }

  @Override
  public PPMImage getBluescaleImage() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("cannot get bluescale of simple image");
  }

  @Override
  public PPMImage flipHorizontal() {
    return null;
  }

  @Override
  public PPMImage flipVertical() {
    return null;
  }

  @Override
  public PPMImage getValueImage() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("simple image has no value component");
  }

  @Override
  public PPMImage getIntensityImage() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("simple image has no intensity component");
  }

  @Override
  public PPMImage getLumaImage() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("simple image has no luma component");
  }

  @Override
  public PPMImage greyscale() {
    return null;
  }

  @Override
  public PPMImage brighten() {
    return null;
  }

  @Override
  public BufferedImage writePPM() {
    return null;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (!(o instanceof SimplePPMImage)) {
      return false;
    }

    SimplePPMImage c = (SimplePPMImage) o;

    if (c.getHeight() == this.getHeight() && c.getWidth() == this.getWidth() &&
            Arrays.equals(c.component, this.component)) {
      return true;
    }

    return false;
  }


}
