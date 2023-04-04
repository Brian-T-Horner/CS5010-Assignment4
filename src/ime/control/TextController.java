package ime.control;
import ime.model.ImageModel;
import ime.model.Model;
import ime.view.FileView;
import ime.view.TextView;
import ime.view.View;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

/**
 * Class that is the IME.control.Controller of our MVC model.
 */
public class TextController extends AbstractController {


  /**
   * Contructor for the creation of a IME.control.Controller object.
   *
   */
  public TextController(Model model, View view) {
    super(model, view);
  }
  @Override
  public void run() throws IOException {
    Objects.requireNonNull(model);
    Objects.requireNonNull(view);

    view.textPrompt();
    putCommands();

    // LOGIC FOR TEXT/FILE BASED BELOW
    Scanner scan = view.getScanner();
    while (scan.hasNext()) {
      boolean cont = false;
      String in = scan.next();

      // If quit command
      if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
        quit = true;
        cont = true;
      }

      // if run command
      if (in.equalsIgnoreCase("run")) {
        String[] commands = scan.nextLine().trim().split(" ");
        if (commands.length != 1) {
          throw new IllegalArgumentException(
                  "Invalid number of arguments for command \"run\". 1 required.");
        }
        try {
          quit = runFile(commands[0], model);
        } catch (IOException e) {
          view.printGeneralError("Could not get path: " + e.getMessage());
        }
        cont = true;
      }

      // If command is empty of pound sign provided
      if (in.isEmpty() || in.charAt(0) == '#') {
        cont = true;
      }

      if (cont) {
        if (isQuit()) {
          return;
        }
        view.textPrompt();
        continue;
      }
      executeCommand(in,scan);
      view.textPrompt();
    }
  }


  /**
   * Method to read text files into a String.
   *
   * @param path image path
   * @return String contents of file
   * @throws IOException thrown if Files.readAllBytes cannot read the file
   */
  private static String readFile(String path)
          throws IOException {
    byte[] encoded = Files.readAllBytes(Paths.get(path));
    return new String(encoded, StandardCharsets.UTF_8);
  }


  /**
   * Method to run a script text file using a FileController.
   *
   * @param fileIn       text file to read from
   * @param currentModel current model used
   * @return quit status of FileController
   * @throws IOException if readFile encounters an IO error
   */
  public static boolean runFile(String fileIn, Model currentModel) throws IOException {
    String file;
    try {
      file = readFile(fileIn);
    } catch (IOException e) {
      throw new IOException(e.getMessage());
    }
    Reader newIn = new StringReader(file);
    Controller fileController = new TextController(currentModel,new FileView(null, newIn));

    try {
      fileController.run();
    } catch (IOException e) {
      throw new IOException(e.getMessage());
    }
    return fileController.isQuit();
  }
}








