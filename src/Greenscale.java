

public class Greenscale implements Command {

  String [] commands;

  /**
   * Constructor for a Greenscale command object.
   *
   * @param commands String array of commands for the object.
   */
  public Greenscale(String[] commands) {
    this.commands = commands;
  }


  /**
   * Method to run the greenscale command on a model m.
   *
   * @param m A model.
   */
  @Override
  public void go(Model m) {
    String imageName = commands[1];
    String newImageName = commands[2];
    m.getGreenComponent(imageName, newImageName);
  }
}
