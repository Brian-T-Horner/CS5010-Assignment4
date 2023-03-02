/**
 * Class for manipulating a .ppm image file.
 */
abstract class AbstractPPMImage implements Image {

  private final String name;
  private final int height;
  private final int width;



  public AbstractPPMImage(String name, int width, int height) {
    this.name = name;
    this.width = width;
    this.height = height;
  }

  public boolean isCompositePPMImage(){
    return false;
  }

  public boolean isSimplePPMImage(){
    return false;
  }

  @Override
  public Image getRedscaleImage() {return null;}

  @Override
  public Image getGreenscaleImage() {return null;}

  @Override
  public Image getBluescaleImage() {return null;}

//  public int getAtIndex(component, int i, int j){
//    return component.
//  }

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }
}
