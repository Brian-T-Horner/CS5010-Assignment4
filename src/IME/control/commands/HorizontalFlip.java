package IME.control.commands;

import IME.model.Model;
import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * A commands.HorizontalFlip command object.
 */
public class HorizontalFlip implements Command {

  String[] commands;

  /**
   * Constructor for a commands.HorizontalFlip command object.
   *
   * @param commands String array of commands for object.
   */
  public HorizontalFlip(String[] commands) {
    this.commands = commands;
  }

  /**
   * Method to have model m run its flipHorizontal method.
   *
   * @param m A model.
   */
  @Override
  public void run(Model m) throws NoSuchElementException,IllegalArgumentException {
    if(commands.length != 3) {
      throw new IllegalArgumentException("Invalid number of arguments for command \"horizontal-flip\". 2 required.");
    }
    String imageName = commands[1];
    String newImageName = commands[2];
    m.flipHorizontal(imageName, newImageName);
  }
}
