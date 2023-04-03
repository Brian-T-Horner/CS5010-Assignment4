package ime.control;

import ime.model.ImageModel;
import ime.model.Model;
import ime.view.TextView;
import ime.view.View;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class that is the IME.control.Controller of our MVC model.
 */
public class ImageController extends AbstractController {


  /**
   * Contructor for the creation of a IME.control.Controller object.
   *
   */
  public ImageController() {
    super();
  }

  /**
   * Main method of the program.
   *
   * @param args command line arguments to be passed to the program.
   */
  public static void main(String[] args) throws IOException {
    Model newModel = new ImageModel();
    View textView = new TextView(System.out, new InputStreamReader(System.in));

    if (args.length > 0) {
      if(args[0].equals("-text") && args.length == 1) {
        Controller controller = new ImageController();
        controller.run(newModel,textView);
      } else if (args[0].equals("-file") && args.length == 2) {
        runFile(args[1],newModel,System.out);
      } else {
        System.out.println("To run a text file please input "
                + "\"-file file-path\" as command line arguments.\nTo run in command line, please input \"-text\"");
      }
    } else {
      //TODO run with UI view
    }
    System.out.println("Exiting application...");
  }


}








