import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Class that is the Controller of our MVC model.
 */
public class Controller implements ControllerImp {

  final Readable in;
  final Appendable out;

  /**
   * Contructor for the creation of a Controller object.
   *
   * @param in  Input stream for the controller.
   * @param out Output stream for the controller.
   */
  Controller(Readable in, Appendable out) {
    this.in = in;
    this.out = out;
  }

  /**
   * Main method of the program.
   *
   * @param args Command line arguments to be passed to the program.
   */
  public static void main(String[] args) throws IOException {
    Model newModel = new PPMModel();
    ControllerImp controller = new Controller(new InputStreamReader(System.in), System.out);
    controller.run(newModel);
  }

  /**
   * Method to run the program, accept inputs and pass load, save images to Model.
   *
   * @param currentModel The model the controller object is instructing.
   */
  @Override
  public void run(Model currentModel) throws IOException {
    Objects.requireNonNull(currentModel);
    while (true) {
      Scanner scan = new Scanner(this.in);
      out.append("$ ");
      /*
      TODO I think final functionality will be entering in commands line by line or read from
      text file.
       */
      while (scan.hasNextLine()) {
        String commandString = scan.nextLine();
        String[] commands = commandString.split(" ");

        if (commandString.startsWith("run") && commandString.endsWith(".txt")) {
          String filepath = commandString.replace("run", "").trim();
          out.append(runFromFile(filepath, currentModel)).append("\n");
          out.append("$ ");
          continue;
        }

        if (commands[0].equals("quit")) {
          out.append("Exiting application...");
          System.exit(0);
        }

        if (!checkCommands(commands)) {
          out.append("$ ");
          continue;
        }
        executeCommands(commands, currentModel);

        out.append("$ ");
      }
    }
  }

  private String runFromFile(String filepath, Model currentModel) throws IOException {
    File f = new File(filepath);
    BufferedReader br;
    try {
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

//  private boolean checkCommand(Appendable out,String commandName, int argsExpected,
//                               int argsSupplied,BiFunction<Integer,Integer,Boolean> b) throws IOException {
//    if(!b.apply(argsSupplied, argsExpected)) {
//      out.append("Invalid number of arguments for command \"").append(commandName).append("\".")
//              .append(" Requires ").append(String.valueOf(argsExpected))
//              .append(", given ").append(String.valueOf(argsSupplied))
//              .append(" arguments.");
//      return false;
//    }
//    return true;
//
//  }


  // TODO break up and use predicates

  // TODO for each command, test number of args errors, test error for each specific command

  /**
   * Private method to check all other commands but save and load.
   *
   * @param commands Array of strings of commands to check.
   */
  private boolean checkCommands(String[] commands) throws IllegalArgumentException, IOException {

    if (commands[0].isEmpty() || commands[0].charAt(0) == '#') {
      return false;
    }

    switch (commands[0]) {
      case "load":
      case "save":
      case ("redscale"):
      case ("greenscale"):
      case ("bluescale"):
      case ("vertical-flip"):
      case ("horizontal-flip"):
      case ("luma"):
      case ("intensity"):
      case ("value"):
        if (commands.length != 3) {
          out.append("Invalid number of arguments for command ").append(commands[0]).append(".\n");
          return false;
        }
        break;
      case ("rgb-split"):
      case ("rgb-combine"):
        if (commands.length != 5) {
          out.append("Invalid number of arguments for command ").append(commands[0]).append(".\n");
          return false;
        }
        break;
      case ("greyscale"):
      case ("brighten"):
        if (commands.length != 4) {
          out.append("Invalid number of arguments for command ").append(commands[0]).append(".\n");
          return false;
        }
        break;
      default:
        out.append("Invalid commands. Please try again.\n");
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
    int commandVal = 0;

    switch (commands[0]) {
      case "load":
        String loadImagePath = commands[1];
        String loadImageName = commands[2];
        try {
          currentModel.loadImage(loadImagePath, loadImageName);
        } catch (Exception e) {
          out.append(e.getMessage()).append("\n");
          break;
        }
        break;
      case "save":
        String saveImagePath = commands[1];
        String saveImageName = commands[2];
        try {
          currentModel.saveImage(saveImagePath, saveImageName);
        } catch (Exception e) {
          out.append(e.getMessage()).append("\n");
          break;
        }
        break;
      case "brighten":
        try {
          commandVal = Integer.parseInt(commands[1]);
        } catch (Exception e) {
          out.append("Second command of brighten must be a valid integer.\n");
        }
        String brightenImageName = commands[2];
        String brightenNewImageName = commands[3];
        try {
          currentModel.brighten(brightenImageName, brightenNewImageName, commandVal);
        } catch (NoSuchElementException e) {
          out.append(e.getMessage()).append("\n");
        }
        break;
      case "redscale":
        try {
          currentModel.getRedComponent(imageName, newImageName);
        } catch (NoSuchElementException e) {
          out.append(e.getMessage()).append("\n");
        }
        break;
      case "greenscale":
        try {
          currentModel.getGreenComponent(imageName, newImageName);
        } catch (NoSuchElementException e) {
          out.append(e.getMessage()).append("\n");
        }
        break;
      case "bluescale":
        try {
          currentModel.getBlueComponent(imageName, newImageName);
        } catch (NoSuchElementException e) {
          out.append(e.getMessage()).append("\n");
        }
        break;
      case "vertical-flip":
        try {
          currentModel.flipVertical(imageName, newImageName);
        } catch (NoSuchElementException e) {
          out.append(e.getMessage()).append("\n");
        }
        break;
      case "horizontal-flip":
        try {
          currentModel.flipHorizontal(imageName, newImageName);
        } catch (NoSuchElementException e) {
          out.append(e.getMessage()).append("\n");
        }
        break;
      case "value":
        try {
          currentModel.getValueImage(imageName, newImageName);
        } catch (NoSuchElementException e) {
          out.append(e.getMessage()).append("\n");
        }
        break;
      case "intensity":
        try {
          currentModel.getIntensityImage(imageName, newImageName);
        } catch (NoSuchElementException e) {
          out.append(e.getMessage()).append("\n");
        }
        break;
      case "luma":
        try {
          currentModel.getLumaImage(imageName, newImageName);
        } catch (NoSuchElementException e) {
          out.append(e.getMessage()).append("\n");
        }
        break;
      case "greyscale":
        String greyScaleComponent = commands[1];
        String currentImageName = commands[2];
        String destImageName = commands[3];
        try {
          currentModel.greyscale(greyScaleComponent, currentImageName, destImageName);
        } catch (NoSuchElementException e) {
          out.append(e.getMessage()).append("\n");
        }
        break;
      case "rgb-split":
        String newRImageName = commands[2];
        String newGImageName = commands[3];
        String newBImageName = commands[4];
        try {
          currentModel.rgbSplit(imageName, newRImageName, newGImageName, newBImageName);
        } catch (NoSuchElementException e) {
          out.append(e.getMessage()).append("\n");
        }
        break;
      case "rgb-combine":
        String rImageName = commands[2];
        String gImageName = commands[3];
        String bImageName = commands[4];
        try {
          currentModel.rgbCombine(imageName, rImageName, gImageName, bImageName);
        } catch (NoSuchElementException e) {
          out.append(e.getMessage()).append("\n");
        }
        break;
    }
  }
}
