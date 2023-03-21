package IME.control.commands;

import java.util.NoSuchElementException;

import IME.model.Model;

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
    if(commands.length != 3) {
      throw new IllegalArgumentException("Invalid number of arguments for command \"luma\". 2 required.");
    }
    String imageName = commands[1];
    String newImageName = commands[2];
    m.getLumaImage(imageName, newImageName);
  }
}

