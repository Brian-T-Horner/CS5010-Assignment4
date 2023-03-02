/**
 * Interface for manipulating an image file.
 */
public interface Image {

  public Image getRedComponentImage();

  public Image getGreenComponentImage();

  public Image getBlueComponentImage();

  public int getHeight();

  public int getWidth();


  public int getRedAtIndex(int i, int j);


  public int getBlueAtIndex(int i, int j);

  public int getGreenAtIndex(int i, int j);
}
