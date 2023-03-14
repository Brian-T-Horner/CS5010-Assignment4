public class Value implements Command {
  String[] commands;
  /**
   * Constructor for a Value command object.
   *
   * @param commands String array of commands for the object.
   */
  public Value(String[] commands) {
    this.commands = commands;
  }

  /**
   * Method to run the value command on a model m.
   *
   * @param m A model.
   */
  @Override
  public void go(Model m) {
    String imageName = commands[1];
    String newImageName = commands[2];
    m.getValueImage(imageName, newImageName);

  }
}
