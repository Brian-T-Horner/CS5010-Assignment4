import java.io.IOException;

public interface ModelImageUtilInter {

  int readIntoPPMImage(String filename, String newName);

  int writeToPPMImage(String imageName, String filename) throws IOException;

}
