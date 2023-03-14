import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Scanner;

/**
 * Class that is the Controller of our MVC model.
 */
public class ImageController implements Controller {

  final Readable in;
  final Appendable out;

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
  @Override
  public void executeCommands(String[] commands, Model currentModel) throws IOException {
    String imageName = commands[1];
    String newImageName = commands[2];
    int commandVal;

    try {
      switch (commands[0]) {
        case "load":
          String loadImagePath = commands[1];
          String loadImageName = commands[2];
          currentModel.loadImage(loadImagePath, loadImageName);
          break;
        case "save":
          String saveImagePath = commands[1];
          String saveImageName = commands[2];
          currentModel.saveImage(saveImagePath, saveImageName);
          break;
        case "brighten":
          try {
            commandVal = Integer.parseInt(commands[1]);
          } catch (Exception e) {
            out.append("Second argument of \"brighten\" must be a valid integer.\n");
            break;
          }
          String brightenImageName = commands[2];
          String brightenNewImageName = commands[3];
          currentModel.brighten(brightenImageName, brightenNewImageName, commandVal);
          break;
        case "redscale":
          currentModel.getRedComponent(imageName, newImageName);
          break;
        case "greenscale":
          currentModel.getGreenComponent(imageName, newImageName);
          break;
        case "bluescale":
          currentModel.getBlueComponent(imageName, newImageName);
          break;
        case "vertical-flip":
          currentModel.flipVertical(imageName, newImageName);
          break;
        case "horizontal-flip":
          currentModel.flipHorizontal(imageName, newImageName);
          break;
        case "value":
          currentModel.getValueImage(imageName, newImageName);
          break;
        case "intensity":
          currentModel.getIntensityImage(imageName, newImageName);
          break;
        case "luma":
          currentModel.getLumaImage(imageName, newImageName);
          break;
        case "greyscale":
          String greyScaleComponent = commands[1];
          String currentImageName = commands[2];
          String destImageName = commands[3];
          currentModel.greyscale(greyScaleComponent, currentImageName, destImageName);
          break;
        case "rgb-split":
          String newRImageName = commands[2];
          String newGImageName = commands[3];
          String newBImageName = commands[4];
          currentModel.rgbSplit(imageName, newRImageName, newGImageName, newBImageName);
          break;
        case "rgb-combine":
          String rImageName = commands[2];
          String gImageName = commands[3];
          String bImageName = commands[4];
          currentModel.rgbCombine(imageName, rImageName, gImageName, bImageName);
          break;
      }
    } catch (Exception e) {
      out.append(e.getMessage()).append("\n");
    }
  }
}
