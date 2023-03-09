import java.io.IOException;

public class ModelImageUtil implements ModelImageUtilInter {

  private StringBuilder log;
  private final int uniqueCode;

  public ModelImageUtil(StringBuilder log, int uniqueCode){
    this.log = log;
    this.uniqueCode = uniqueCode;
  }

  @Override
  public int readIntoPPMImage(String filename, String newName) {
    log.append("readIntoPPMImage: filename = " + filename + " newname = " + newName + "\n");
    return uniqueCode;
  }

  @Override
  public int writeToPPMImage(String imageName, String filename) throws IOException {
    log.append("writeToPPMImage: imageName = " + imageName + " filename = " + filename + "\n");
    return uniqueCode;
  }
}
