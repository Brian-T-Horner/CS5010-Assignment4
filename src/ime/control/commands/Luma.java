package ime.control.commands;

import java.util.NoSuchElementException;

import ime.model.Model;

/**
 * A commands.Luma command object.
 */
public class Luma implements Command {

  String[] commands;

  /**
   * Constructor for a commands.Luma command object.
   *
   * @param commands String array of commands for the object.
   */
  public Luma(String[] commands) {
    this.commands = commands;
  }

  /**
   * Method to have model m run its getLumaImage method.
   *
   * @param m A model.
   */
  @Override
  public void run(Model m) throws NoSuchElementException, IllegalArgumentException {
    if (commands.length != 2) {
      throw new IllegalArgumentException("Invalid number of arguments for command \"luma\". "
          + "2 required.");
    }
    String imageName = commands[0];
    String newImageName = commands[1];
    m.getLumaImage(imageName, newImageName);
  }
}

