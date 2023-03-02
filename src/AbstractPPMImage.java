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


  @Override
  public boolean equals(Object o){
    if(o == this){
      return true;
    }

    if(!(o instanceof AbstractPPMImage)){
      return false;
    }

    AbstractPPMImage c = (AbstractPPMImage) o;

    if(c.height == this.height && c.width == this.width){
      return true;
    }

    return false;
  }
  @Override
  public int hashCode(){
    return 67499 * this.getWidth() * this.getHeight();
  }
}
