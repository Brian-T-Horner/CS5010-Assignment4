package ime.control.commands;

import java.io.FileNotFoundException;

import ime.model.Model;

/**
 * A commands.Greenscale command object.
 */
public class Greenscale implements Command {

  String [] commands;

  /**
   * Constructor for a commands.Greenscale command object.
   *
   * @param commands String array of commands for the object.
   */
  public Greenscale(String[] commands) {
    this.commands = commands;
  }


  /**
   * Method to have model m run its getGreenComponent method.
   *
   * @param m A model.
   */
  @Override
  public void run(Model m) throws FileNotFoundException {
    if(commands.length != 3) {
      throw new IllegalArgumentException("Invalid number of arguments for command \"greenscale\". 3 required.");
    }
    String imageName = commands[1];
    String newImageName = commands[2];
    m.getGreenComponent(imageName, newImageName);
  }
}
