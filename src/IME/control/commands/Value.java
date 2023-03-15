package IME.control.commands;

import IME.model.Model;

/**
 * A commands.Value command object.
 */
public class Value implements Command {
  String[] commands;

  /**
   * Constructor for a commands.Value command object.
   *
   * @param commands String array of commands for the object.
   */
  public Value(String[] commands) {
    this.commands = commands;
  }

  /**
   * Method to have model m call its getValueImage method.
   *
   * @param m A model.
   */
  @Override
  public void run(Model m) {
    String imageName = commands[1];
    String newImageName = commands[2];
    m.getValueImage(imageName, newImageName);

  }
}
