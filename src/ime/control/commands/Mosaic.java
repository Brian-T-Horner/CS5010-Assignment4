package ime.control.commands;

import java.util.NoSuchElementException;

import ime.model.Model;

/**
 * Class for mosaic command.
 */
public class Mosaic implements Command {

  String[] commands;

  /**
   * Constructor for mosaic.
   *
   * @param commands String array of commands.
   */
  public Mosaic(String[] commands) {
    this.commands = commands;
  }

  /**
   * Method to have model m run its mosaic method.
   *
   * @param m A model.
   */
  @Override
  public void run(Model m) throws NoSuchElementException, IllegalArgumentException {
    if (commands.length != 3) {
      throw new IllegalArgumentException("Invalid number of arguments for command \"mosaic\". "
              + "2 required.");
    }
    String imageName = commands[0];
    String newImageName = commands[1];
    int seed = Integer.parseInt(commands[2]);
    m.mosaic(imageName, newImageName,seed);
  }
}

