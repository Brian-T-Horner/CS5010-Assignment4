
public class Intensity implements Command {

  String[] commands;

  /**
   * Constructor for an Intensity command object.
   *
   * @param commands String array of commands for the object.
   */
  public Intensity(String[] commands) {
    this.commands = commands;
  }
  /**
   * Method to run the inheriting commands functionality on a model m.
   *
   * @param m A model.
   */
  @Override
  public void go(Model m) {
    String imageName = commands[1];
    String newImageName = commands[2];
    m.getIntensityImage(imageName, newImageName);
  }
}
