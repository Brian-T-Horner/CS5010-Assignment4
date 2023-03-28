package ime.control.commands;

import ime.model.Model;
import java.util.NoSuchElementException;

/**
 * An commands.RGBSplit command object.
 */
public class RGBSplit implements Command {

  String[] commands;

  /**
   * Constructor for an commands.RGBSplit command object.
   *
   * @param commands String array of commands for object.
   */
  public RGBSplit(String[] commands) {
    this.commands = commands;
  }

  /**
   * Method to have model m run its rgbSplit method.
   *
   * @param m A model.
   */
  @Override
  public void run(Model m) throws NoSuchElementException,IllegalArgumentException {
    if (commands.length != 4) {
      throw new IllegalArgumentException("Invalid number of arguments for command \"rgb-split\". "
          + "4 required.");
    }
    String imageName = commands[0];
    String newRImageName = commands[1];
    String newGImageName = commands[2];
    String newBImageName = commands[3];
    m.rgbSplit(imageName, newRImageName, newGImageName, newBImageName);
  }
}
