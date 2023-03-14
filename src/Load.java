

import java.io.FileNotFoundException;

public class Load implements Command {

  String[] commands;

  public Load(String[] commands) {
    this.commands = commands;
  }

  /**
   * Method to run the inheriting commands functionality on a model m.
   *
   * @param m A model.
   */
  @Override
  public void go(Model m) throws FileNotFoundException {
    String loadImagePath = commands[1];
    String loadImageName = commands[2];
    m.loadImage(loadImagePath, loadImageName);
  }
}
