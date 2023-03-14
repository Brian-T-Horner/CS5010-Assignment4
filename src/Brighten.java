import java.io.IOException;

public class Brighten implements Command {

  String[] commands;

  public Brighten(String[] commands){
    this.commands = commands;
  }

  /**
   * Method to run the inheriting commands functionality on a model m.
   *
   * @param m A model.
   */
  @Override
  public void go(Model m) throws IOException {
    int brightenScale = Integer.parseInt(commands[1]);
    String brightenImageName = commands[2];
    String brightenNewImageName = commands[3];
    m.brighten(brightenImageName, brightenNewImageName, brightenScale);
  }
}
