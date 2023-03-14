import java.io.IOException;

public class VerticalFlip implements Command {

  String[] commands;

  public VerticalFlip(String[] commands) {
    this.commands = commands;
  }
  /**
   * Method to run the inheriting commands functionality on a model m.
   *
   * @param m A model.
   */
  @Override
  public void go(Model m) throws IOException {
    String imageName = commands[1];
    String newImageName = commands[2];
    m.flipVertical(imageName, newImageName);
  }
}
