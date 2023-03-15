import java.io.IOException;

/**
 * An RGBCombine command object.
 */
public class RGBCombine implements Command {

  String[] commands;

  /**
   * Constructor for a RGBCombine command object.
   *
   * @param commands String array of commands for object.
   */
  public RGBCombine(String[] commands) {
    this.commands = commands;
  }

  /**
   * Method to have model m call its rgbCombine method.
   *
   * @param m A model.
   */
  @Override
  public void run(Model m) throws IOException {
    String imageName = commands[1];
    String rImageName = commands[2];
    String gImageName = commands[3];
    String bImageName = commands[4];
    m.rgbCombine(imageName, rImageName, gImageName, bImageName);
  }
}
