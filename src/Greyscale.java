
import java.io.IOException;

public class Greyscale implements Command {

  String[] commands;

  public Greyscale(String[] commands) {
    this.commands = commands;
  }

  /**
   * Method to run the inheriting commands functionality on a model m.
   *
   * @param m A model.
   */
  @Override
  public void go(Model m) throws IOException {
    String greyScaleComponent = commands[1];
    String currentImageName = commands[2];
    String destImageName = commands[3];
    m.greyscale(greyScaleComponent, currentImageName, destImageName);

  }
}
