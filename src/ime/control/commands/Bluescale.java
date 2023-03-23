package ime.control.commands;

import java.io.FileNotFoundException;

import ime.model.Model;

/**
 * Class for a commands.Bluescale commands.Command object.
 */
public class Bluescale implements Command {

  String[] commands;

  /**
   * Constructor for a commands.Bluescale command object.
   *
   * @param commands String array of commands for object.
   */
  public Bluescale(String[] commands) {
    this.commands = commands;
  }

  /**
   * Method to have model m call its getBlueComponent method.
   *
   * @param m A model.
   */
  @Override
  public void run(Model m) throws FileNotFoundException,IllegalArgumentException {
    if(commands.length != 2) {
      throw new IllegalArgumentException("Invalid number of arguments for command \"bluescale\". "
          + "2 required.");
    }
    String imageName = commands[0];
    String newImageName = commands[1];
    m.getBlueComponent(imageName, newImageName);

  }
}
