package IME.control.commands;

import IME.model.Model;
import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * A commands.Brighten command object.
 */
public class Brighten implements Command {

  String[] commands;

  /**
   * Constructor for a commands.Brighten command object.
   *
   * @param commands String array of commands for object.
   */
  public Brighten(String[] commands) {
    this.commands = commands;
  }

  /**
   * Method to have model m run its brighten command.
   *
   * @param m A model.
   */
  @Override
  public void run(Model m) throws NoSuchElementException {
    if(commands.length != 4) {
      throw new IllegalArgumentException("Invalid number of arguments for command \"brighten\". 4 required.");
    }
    int brightenScale = Integer.parseInt(commands[1]);
    String brightenImageName = commands[2];
    String brightenNewImageName = commands[3];
    m.brighten(brightenImageName, brightenNewImageName, brightenScale);
  }
}
