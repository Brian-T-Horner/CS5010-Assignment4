/**
 * Interface for manipulating an image file.
 */
public interface Image {

  Image getRedscaleImage();

  Image getGreenscaleImage();

  Image getBluescaleImage();

  int getHeight();

  int getWidth();

  int getIndex(int i, int j);



}
