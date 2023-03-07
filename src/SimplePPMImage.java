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
  public PPMImage getRedscaleImage(String name) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("cannot get redscale of simple image");
  }

  @Override
  public PPMImage getGreenscaleImage(String name) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("cannot get greenscale of simple image");
  }

  @Override
  public PPMImage getBluescaleImage(String name) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("cannot get bluescale of simple image");
  }

  @Override
  public PPMImage flipHorizontal(String name) {
    int temp;
    int[][] flippedH = new int[width][height];
    for (int i = 0; i < getHeight(); i++) {
      for (int j = 0; j <  getWidth() / 2; j++) {
        temp = getIndex(getWidth() - j -1 , i);
        flippedH[getWidth() - j -1][i] = getIndex(j,i);
        flippedH[j][i] = temp;
      }
    }

    return new SimplePPMImage(name, width, height, flippedH);
  }

  @Override
  public PPMImage flipVertical(String name) {
    int temp;
    int[][] flippedV = new int[width][height];
    for (int i = 0; i < getWidth(); i++) {
      for (int j = 0; j < getHeight() / 2; j++) {
        temp = getIndex(i, j);
        flippedV[i][j] = getIndex(i, getHeight() - 1 - j);
        flippedV[i][getHeight() - 1 - j] = temp;
      }
    }

    return new SimplePPMImage(name, width, height, flippedV);
  }

  @Override
  public PPMImage getValueImage(String name) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("simple image has no value component");
  }

  @Override
  public PPMImage getIntensityImage(String name) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("simple image has no intensity component");
  }

  @Override
  public PPMImage getLumaImage(String name) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("simple image has no luma component");
  }


  @Override
  public PPMImage brighten(String name, int scale) {
    int[][] brightened = new int[width][height];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        int val = component[i][j] + scale;
        if (val > 255) {
          val = 255;
        } else if (val < 0) {
          val = 0;
        }
        brightened[i][j] = val;
      }
    }
    return new SimplePPMImage(name, width, height, brightened);
  }

  @Override
  public BufferedImage writeBufferedImage() {
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    int p, pixel;
    for (int i = 0; i < getWidth(); i++) {
      for (int j = 0; j < getHeight(); j++) {
        p = getIndex(i, j);
        pixel = 0xFF000000 + (p << 16) + (p << 8) + p;
        image.setRGB(i, j, pixel);
      }
    }
    return image;
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
