package ime.control.commands;

import ime.model.Model;
import java.util.NoSuchElementException;

/**
 * A commands.VerticalFlip command object.
 */
public class VerticalFlip implements Command {

  String[] commands;

  /**
   * Constructor for a verticalFlip command object.
   *
   * @param commands String array of commands for object.
   */
  public VerticalFlip(String[] commands) {
    this.commands = commands;
  }

  /**
   * Method to have model m call its flipVertical method.
   *
   * @param m A model.
   */
  @Override
  public void run(Model m) throws NoSuchElementException, IllegalArgumentException {
    if(commands.length != 2) {
      throw new IllegalArgumentException("Invalid number of arguments for command "
          + "\"vertical-flip\". 2 required.");
    }
    String imageName = commands[0];
    String newImageName = commands[1];
    m.flipVertical(imageName, newImageName);
  }
}
