package ime.control;

import ime.model.Model;
import ime.model.PPMModel;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class that is the IME.control.Controller of our MVC model.
 */
public class ImageController extends AbstractController {


  /**
   * Contructor for the creation of a IME.control.Controller object.
   *
   * @param in  Input stream for the controller.
   * @param out Output stream for the controller.
   */
  public ImageController(Readable in, Appendable out) {
    super(in,out);
  }

  /**
   * Main method of the program.
   *
   * @param args commands.Command line arguments to be passed to the program.
   */
  public static void main(String[] args) throws IOException {
    Model newModel = new PPMModel();
    if (args.length > 0) {
      if (args[0].equals("-file") && args.length == 2) {
        runFile(args[1],newModel);
      } else {
        System.out.println("To run a text file please input \"-file file-path\" as command line arguments.");
      }
      System.out.println("Exiting application...");
      return;
    }
    Controller controller = new ImageController(new InputStreamReader(System.in), System.out);
    controller.run(newModel);
  }

  @Override
  protected void insertCursor() throws IOException {
    out.append("$ ");
  }
}








