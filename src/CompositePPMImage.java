import java.awt.image.BufferedImage;

public class CompositePPMImage extends AbstractPPMImage {

  private final PPMImage redComponent;

  private final PPMImage greenComponent;

  private final PPMImage blueComponent;


  public CompositePPMImage(String name, int width, int height, PPMImage red,
                           PPMImage blue, PPMImage green) {
    super(name, width, height);
    this.redComponent = red;
    this.greenComponent = green;
    this.blueComponent = blue;
  }


  @Override
  public boolean isCompositePPMImage() {
    return true;
  }

  @Override
  public PPMImage getRedscaleImage(String name) {
    return redComponent;
  }

  @Override
  public PPMImage getGreenscaleImage(String name) {
    return greenComponent;
  }

  @Override
  public PPMImage getBluescaleImage(String name) {
    return blueComponent;
  }

  @Override
  public PPMImage flipHorizontal(String name) {
    PPMImage red = getRedscaleImage(name).flipHorizontal(name);
    PPMImage green = getGreenscaleImage(name).flipHorizontal(name);
    PPMImage blue = getBluescaleImage(name).flipHorizontal(name);
    return new CompositePPMImage(name, width, height, red, blue, green);
  }

  @Override
  public PPMImage flipVertical(String name) {
    PPMImage red = getRedscaleImage(name).flipVertical(name);
    PPMImage green = getGreenscaleImage(name).flipVertical(name);
    PPMImage blue = getBluescaleImage(name).flipVertical(name);
    return new CompositePPMImage(name, width, height, red, blue, green);
  }

  @Override
  public PPMImage getValueImage(String name) {
    int[][] value = new int[width][height];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        int redValue = redComponent.getIndex(i,j);
        int greenValue = redComponent.getIndex(i,j);
        int blueValue = redComponent.getIndex(i,j);
        int max = Math.max(redValue,greenValue);
        max = Math.max(max,blueValue);
        value[i][j] = max;
      }
    }
    return new SimplePPMImage(name,width,height,value);
  }

  @Override
  public PPMImage getIntensityImage(String name) {
    return null;
  }

  @Override
  public PPMImage getLumaImage(String name) {
    return null;
  }

  @Override
  public PPMImage brighten(String name,int scale) {
    PPMImage red = getRedscaleImage(name).brighten(name,scale);
    PPMImage green = getGreenscaleImage(name).brighten(name,scale);
    PPMImage blue = getBluescaleImage(name).brighten(name,scale);
    return new CompositePPMImage(name, width, height, red, blue, green);
  }

  @Override
  public BufferedImage writeBufferedImage() {
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    int r, g, b, pixel;
    for (int i = 0; i < getWidth(); i++) {
      for (int j = 0; j < getHeight(); j++) {
        r = getRedscaleImage(name).getIndex(i, j);
        g = getGreenscaleImage(name).getIndex(i, j);
        b = getBluescaleImage(name).getIndex(i, j);
        pixel = 0xFF000000 + (r << 16) + (g << 8) + b;
        image.setRGB(i, j, pixel);
      }
    }
    return image;
  }


  @Override
  public int getIndex(int i, int j) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("composite image has no individual indices");
  }


  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (!(o instanceof CompositePPMImage)) {
      return false;
    }

    CompositePPMImage c = (CompositePPMImage) o;

    if (c.getWidth() == this.getWidth() && c.getHeight() == this.getHeight() &&
            c.blueComponent.equals(blueComponent) && c.greenComponent.equals(greenComponent) &&
            c.redComponent.equals(redComponent)) {
      return true;
    }

    return false;

  }


}

