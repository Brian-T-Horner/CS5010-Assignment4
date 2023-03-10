/**
 * Interface for manipulating a .ppm image file.
 */
public interface Image {

  boolean isSingleChannel();

  int [][] getRedComponent();

  int [][] getGreenComponent();

  int [][] getBlueComponent();

  int getHeight();

  int getWidth();

  Image flipHorizontal();

  Image flipVertical();

  Image getValueImage();

  Image getIntensityImage();

  Image getLumaImage();

  Image brighten(int scale);

  Image darken(int scale);

}
