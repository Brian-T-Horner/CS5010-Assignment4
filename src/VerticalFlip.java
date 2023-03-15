import java.io.IOException;

/**
 * A VerticalFlip command object.
 */
public class VerticalFlip implements Command {

  String[] commands;

  /**
   * Constructor for a verticalFlip command object.
   *
   * @param commands String array of commands for object.
   */
  public VerticalFlip(String[] commands) {
    this.commands = commands;
  }

  /**
   * Method to have model m call its flipVertical method.
   *
   * @param m A model.
   */
  @Override
  public void run(Model m) throws IOException {
    String imageName = commands[1];
    String newImageName = commands[2];
    m.flipVertical(imageName, newImageName);
  }
}
