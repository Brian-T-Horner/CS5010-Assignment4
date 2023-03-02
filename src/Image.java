/**
 * Interface for manipulating an image file.
 */
public interface Image {

  Image getRedscaleImage();

  Image getGreenscaleImage();

  Image getBluescaleImage();

  int getHeight();

  int getWidth();


  int getRedAtIndex(int i, int j);

  int getBlueAtIndex(int i, int j);

  int getGreenAtIndex(int i, int j);



}
