package IME.control.commands;

import IME.model.Model;

/**
 * A commands.Redscale command object.
 */
public class Redscale implements Command {
  String[] commands;

  /**
   * Constructor for a commands.Redscale command object.
   *
   * @param commands String array of commands for the object.
   */
  public Redscale(String[] commands) {
    this.commands = commands;
  }

  /**
   * Method to have model m run its getRedComponent method.
   *
   * @param m A model.
   */
  @Override
  public void run(Model m) {
    String imageName = commands[1];
    String newImageName = commands[2];
    m.getRedComponent(imageName, newImageName);
  }
}

