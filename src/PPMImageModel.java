
public class PPMImageModel implements PPMImageModelInter {

  private StringBuilder log;
  private final int uniqueCode;

  public PPMImageModel(StringBuilder log, int uniqueCode) {
    this.log = log;
    this.uniqueCode = uniqueCode;
  }
  @Override
  public int getRedscaleImage(String name) {
    log.append("getRedScaleImage: String name = " + name + "\n");
    return uniqueCode;
  }

  @Override
  public int getGreenscaleImage(String name) {
    log.append("getGreenScaleImage: String name = " + name + "\n");
    return uniqueCode;
  }

  @Override
  public int getBluescaleImage(String name) {
    log.append("getBlueScaleImage: String name = " + name + "\n");
    return uniqueCode;
  }


  @Override
  public int flipHorizontal(String name) {
    log.append("flipHorizontal: String name = " + name + "\n");
    return uniqueCode;
  }

  @Override
  public int flipVertical(String name) {
    log.append("flipVertical: String name = " + name + "\n");
    return uniqueCode;
  }

  @Override
  public int getValueImage(String name) {
    log.append("getValueImage: String name = " + name + "\n");
    return uniqueCode;
  }

  @Override
  public int getIntensityImage(String name) {
    log.append("getIntensityImage: String name = " + name + "\n");
    return uniqueCode;
  }

  @Override
  public int getLumaImage(String name) {
    log.append("getLumaImage: String name = " + name + "\n");
    return uniqueCode;
  }

  @Override
  public int brighten(String name, int scale) {
    log.append("brighten: String name = " + name + "\n");
    return uniqueCode;
  }

  @Override
  public int writeBufferedImage() {
    log.append("writeBufferedImage: \n");
    return uniqueCode;
  }
}
