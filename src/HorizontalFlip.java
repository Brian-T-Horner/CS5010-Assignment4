import java.io.IOException;

/**
 * A HorizontalFlip command object.
 */
public class HorizontalFlip implements Command {

  String[] commands;

  /**
   * Constructor for a HorizontalFlip command object.
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
  public void run(Model m) throws IOException {
    String imageName = commands[1];
    String newImageName = commands[2];
    m.flipHorizontal(imageName, newImageName);
  }
}
