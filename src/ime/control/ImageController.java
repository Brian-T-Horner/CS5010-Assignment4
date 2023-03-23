package ime.control;

import ime.control.commands.Bluescale;
import ime.control.commands.Brighten;
import ime.control.commands.Command;
import ime.control.commands.Greenscale;
import ime.control.commands.Greyscale;
import ime.control.commands.HorizontalFlip;
import ime.control.commands.Intensity;
import ime.control.commands.Load;
import ime.control.commands.Luma;
import ime.control.commands.RGBCombine;
import ime.control.commands.RGBSplit;
import ime.control.commands.Redscale;
import ime.control.commands.Save;
import ime.control.commands.Value;
import ime.control.commands.VerticalFlip;
import ime.model.Model;
import ime.model.PPMModel;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;

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
      if (args[0].equals("-file")) {
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








