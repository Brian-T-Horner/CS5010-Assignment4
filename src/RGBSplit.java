import java.io.IOException;

public class RGBSplit implements Command {

  String[] commands;
  public RGBSplit(String[] commands) {
    this.commands = commands;
  }

  /**
   * Method to run the inheriting commands functionality on a model m.
   *
   * @param m A model.
   */
  @Override
  public void go(Model m) throws IOException {
    String imageName = commands[1];
    String newRImageName = commands[2];
    String newGImageName = commands[3];
    String newBImageName = commands[4];
    m.rgbSplit(imageName, newRImageName, newGImageName, newBImageName);
  }
}
