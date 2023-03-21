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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Scanner;

/**
 * Class that is the IME.control.Controller of our MVC model.
 */
public class ImageController implements Controller {

  final Readable in;
  final Appendable out;

  private boolean quit = false;

  //Map<String, Function<String[], commands.Command>> knownCommands;

  /**
   * Contructor for the creation of a IME.control.Controller object.
   *
   * @param in  Input stream for the controller.
   * @param out Output stream for the controller.
   */
  public ImageController(Readable in, Appendable out) {
    this.in = in;
    this.out = out;
  }

  /**
   * Main method of the program.
   *
   * @param args commands.Command line arguments to be passed to the program.
   */
  public static void main(String[] args) {
    Model newModel = new PPMModel();
    Controller controller = new ImageController(new InputStreamReader(System.in), System.out);
    try {
      controller.run(newModel);
    } catch (IOException e) {
      System.out.println("IO error: output cannot be appended.");
    }
    System.exit(0);

  }

  /**
   * Method to run the program, accept inputs and pass load, save images to IME.model.Model.
   *
   * @param currentModel The model the controller object is instructing.
   */
  @Override
  public void run(Model currentModel) throws IOException {
    Objects.requireNonNull(currentModel);

    Scanner scan = new Scanner(this.in);
    out.append("$ ");
    while (scan.hasNextLine()) {
      String commandString = scan.nextLine().trim();
      String[] commands = commandString.split(" ");

      executeCommands(commands, currentModel);

      if(quit) {
        out.append("Exiting application...");
        return;
      }
      out.append("$ ");
    }
  }

  private void runFromFile(String filepath, Model currentModel) throws IOException {

    BufferedReader br;
      File f = new File(filepath);
      br = new BufferedReader(new FileReader(f));

    String line;
    while ((line = br.readLine()) != null) {
      String[] commands = line.split(" ");
      executeCommands(commands, currentModel);
    }
  }

  /**
   * Method to execute all other commands but save and load.
   *
   * @param commands     String array of current commands to be executed.
   * @param currentModel Current model the controller is communicating with.
   */
  private void executeCommands(String[] commands, Model currentModel) throws IOException {
    Command cmd = null;
    try {
      switch (commands[0]) {
        case "load":
          cmd = new Load(commands);
          break;
        case "save":
          cmd = new Save(commands);
          break;
        case "brighten":
          try {
            Integer.parseInt(commands[1]);
          } catch (Exception e) {
            out.append("Second argument of \"brighten\" must be a valid integer.\n");
            break;
          }
          cmd = new Brighten(commands);
          break;
        case "redscale":
          cmd = new Redscale(commands);
          break;
        case "greenscale":
          cmd = new Greenscale(commands);
          break;
        case "bluescale":
          cmd = new Bluescale(commands);
          break;
        case "vertical-flip":
          cmd = new VerticalFlip(commands);
          break;
        case "horizontal-flip":
          cmd = new HorizontalFlip(commands);
          break;
        case "value":
          cmd = new Value(commands);
          break;
        case "intensity":
          cmd = new Intensity(commands);
          break;
        case "luma":
          cmd = new Luma(commands);
          break;
        case "greyscale":
          cmd = new Greyscale(commands);
          break;
        case "rgb-split":
          cmd = new RGBSplit(commands);
          break;
        case "rgb-combine":
          cmd = new RGBCombine(commands);
          break;
        case "quit":
          quit = true;
          break;
        case "run":
          if(commands.length != 2) {
            throw new IllegalArgumentException("Invalid number of arguments for command \"run\". 1 required.");
          }
          runFromFile(commands[1]
                  .replace("\"","").trim(),currentModel);
          break;
        default:
          if(commands[0].isEmpty() || commands[0].charAt(0) == '#') {
            break;
          }
          out.append(String.format("Unknown command \"%s\"\n", commands[0]));
          break;
      }
      if (cmd != null) {
        cmd.run(currentModel);
      }
    } catch (Exception e) {
      out.append(e.getMessage()).append("\n");
    }
  }
}
