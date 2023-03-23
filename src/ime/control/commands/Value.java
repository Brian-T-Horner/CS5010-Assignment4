package ime.control.commands;

import java.util.NoSuchElementException;

import ime.model.Model;

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
  public void run(Model m) throws NoSuchElementException, IllegalArgumentException {
    if(commands.length != 3) {
      throw new IllegalArgumentException("Invalid number of arguments for command \"value\". "
          + "2 required.");
    }
    String imageName = commands[1];
    String newImageName = commands[2];
    m.getValueImage(imageName, newImageName);
  }
}
