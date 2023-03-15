import java.io.IOException;

/**
 * A Brighten command object.
 */
public class Brighten implements Command {

  String[] commands;

  /**
   * Constructor for a Brighten command object.
   *
   * @param commands String array of commands for object.
   */
  public Brighten(String[] commands) {
    this.commands = commands;
  }

  /**
   * Method to have model m run its brighten command.
   *
   * @param m A model.
   */
  @Override
  public void run(Model m) throws IOException {
    int brightenScale = Integer.parseInt(commands[1]);
    String brightenImageName = commands[2];
    String brightenNewImageName = commands[3];
    m.brighten(brightenImageName, brightenNewImageName, brightenScale);
  }
}
