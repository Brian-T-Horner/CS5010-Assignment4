package ime.control;

import ime.model.Model;
import ime.view.View;

import java.io.IOException;

/**
 * Interface for controller objects for the MVC model of this program.
 */
public interface Controller {

  /**
   * Run method for the program. Takes in input, parses input and calls methods to instruct
   *    its model.
   *
   * @param currentModel Current model that the controller is communicating with.
   */
  void run(Model currentModel, View view) throws IOException;

  /**
   * Determines whether the program should exit.
   *
   * @return true if quit prompted false if not prompted
   */
  boolean isQuit();

}
