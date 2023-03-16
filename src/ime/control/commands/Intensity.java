package ime.control.commands;

import java.util.NoSuchElementException;

import ime.model.Model;

/**
 * An commands.Intensity command object.
 */
public class Intensity implements Command {

  String[] commands;

  /**
   * Constructor for an commands.Intensity command object.
   *
   * @param commands String array of commands for the object.
   */
  public Intensity(String[] commands) {
    this.commands = commands;
  }

  /**
   * Method to have model m run its getIntensityImage method.
   *
   * @param m A model.
   */
  @Override
  public void run(Model m) throws NoSuchElementException {
    String imageName = commands[1];
    String newImageName = commands[2];
    m.getIntensityImage(imageName, newImageName);
  }
}
