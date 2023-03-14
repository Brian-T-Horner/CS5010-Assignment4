import java.io.IOException;

public class Save implements Command {

  String[] commands;

  public Save(String[] commands) {
    this.commands = commands;
  }
  /**
   * Method to run the inheriting commands functionality on a model m.
   *
   * @param m A model.
   */
  @Override
  public void go(Model m) throws IOException {
    String saveImagePath = commands[1];
    String saveImageName = commands[2];
    m.saveImage(saveImagePath, saveImageName);

  }
}
