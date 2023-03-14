
public class Redscale implements Command {
  String[] commands;

  /**
   * Constructor for a Redscale command object.
   *
   * @param commands String array of commands for the object.
   */
  public Redscale(String[] commands) {
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
    m.getRedComponent(imageName, newImageName);
  }
}

