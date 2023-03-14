
import java.io.IOException;

public class HorizontalFlip implements Command {

  String[] commands;

  public HorizontalFlip(String[] commands){
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
    m.flipHorizontal(imageName, newImageName);
  }
}
