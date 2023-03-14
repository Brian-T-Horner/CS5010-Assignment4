import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Interface for commands of the program to implement the Command Design Pattern.
 */
public interface Command {

  /**
   * Method to run the inheriting commands functionality on a model m.
   * @param m A model.
   */
  void go(Model m) throws IOException;

}
