package ime.control.commands;

import ime.model.Model;
import java.io.IOException;

/**
 * A commands.Save command object.
 */
public class Save implements Command {

  String[] commands;

  /**
   * Constructor for a commands.Save command object.
   *
   * @param commands String array of commands for the object.
   */
  public Save(String[] commands) {
    this.commands = commands;
  }

  /**
   * Method to have model m run its saveImage method.
   *
   * @param m A model.
   */
  @Override
  public void run(Model m) throws IOException {
    String saveImagePath = commands[1];
    String saveImageName = commands[2];
    m.saveImage(saveImagePath, saveImageName);
  }
}
