import java.io.IOException;

public class RGBCombine implements Command {

  String[] commands;

  public RGBCombine(String[] commands) {
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
    String rImageName = commands[2];
    String gImageName = commands[3];
    String bImageName = commands[4];
    m.rgbCombine(imageName, rImageName, gImageName, bImageName);
  }
}
