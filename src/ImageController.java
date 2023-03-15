import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Class that is the Controller of our MVC model.
 */
public class ImageController implements Controller {

  final Readable in;
  final Appendable out;

  Map<String, Function<String[], Command>> knownCommands;

  /**
   * Contructor for the creation of a Controller object.
   *
   * @param in  Input stream for the controller.
   * @param out Output stream for the controller.
   */
  ImageController(Readable in, Appendable out) {
    this.in = in;
    this.out = out;
  }

  /**
   * Main method of the program.
   *
   * @param args Command line arguments to be passed to the program.
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
   * Method to run the program, accept inputs and pass load, save images to Model.
   *
   * @param currentModel The model the controller object is instructing.
   */
  @Override
  public void run(Model currentModel) throws IOException {
    Objects.requireNonNull(currentModel);

    Scanner scan = new Scanner(this.in);
    out.append("$ ");
    while (scan.hasNextLine()) {
      String commandString = scan.nextLine();
      String[] commands = commandString.split(" ");

      if (commands[0].equals("quit")) {
        out.append("Exiting application...");
        return;
      }

      if (commandString.startsWith("run") && commandString.endsWith(".txt")) {
        String filepath = commandString.replace("run", "").trim();
        out.append(runFromFile(filepath, currentModel)).append("\n").append("$ ");
        continue;
      }


      if (!checkCommands(commands)) {
        out.append("$ ");
        continue;
      }
      

      executeCommands(commands, currentModel);
      out.append("$ ");

    }
  }

  private String runFromFile(String filepath, Model currentModel) throws IOException {

    BufferedReader br;
    try {
      File f = new File(filepath);
      br = new BufferedReader(new FileReader(f));
    } catch (Exception e) {
      return e.getMessage();
    }

    String line;
    while ((line = br.readLine()) != null) {
      String[] commands = line.split(" ");
      if (!checkCommands(commands)) {
        continue;
      }
      executeCommands(commands, currentModel);
    }
    return "";

  }


  // TODO for each command, test number of args errors, test error for each specific command

  /**
   * Private method to check all other commands but save and load.
   *
   * @param commands Array of strings of commands to check.
   */
  private boolean checkCommands(String[] commands) throws IOException {

    if (commands[0].isEmpty() || commands[0].charAt(0) == '#') {
      return false;
    }

    switch (commands[0]) {
      case "load":
      case "save":
      case "redscale":
      case "greenscale":
      case "bluescale":
      case "vertical-flip":
      case "horizontal-flip":
      case "luma":
      case "intensity":
      case "value":
        if (commands.length != 3) {
          out.append("Invalid number of arguments for command ").append(commands[0]).append(".\n");
          return false;
        }
        break;
      case "rgb-split":
      case "rgb-combine":
        if (commands.length != 5) {
          out.append("Invalid number of arguments for command ").append(commands[0]).append(".\n");
          return false;
        }
        break;
      case "greyscale":
      case "brighten":
        if (commands.length != 4) {
          out.append("Invalid number of arguments for command ").append(commands[0]).append(".\n");
          return false;
        }
        break;
      default:
        out.append("Invalid command. Please try again.\n");
        return false;
    }
    return true;
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
        default:
          out.append(String.format("Unknown command %s", commands[0]));
          break;
      }
      if(cmd != null){
        cmd.go(currentModel);
      }
    } catch (Exception e) {
      out.append(e.getMessage()).append("\n");
    }

  }
}
