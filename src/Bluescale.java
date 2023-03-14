public class Bluescale implements Command {

  String[] commands;

  public Bluescale(String[] commands){
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
    m.getBlueComponent(imageName, newImageName);

  }
}
