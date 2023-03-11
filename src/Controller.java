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
      int valueCommand = 0;
      out.append("$ ");
      //TODO add multiline usage
      //TODO add read from file usage
      while (scan.hasNextLine()) {
        String commandString = scan.nextLine();
        String[] currentCommands = commandString.split(" ");
        if (currentCommands[0].isEmpty() || currentCommands[0].charAt(0) == '#') {
          break;
        }

        if (currentCommands[0].equals("quit")) {
          out.append("Exiting application...");
          System.exit(0);
        }

        if (currentCommands.length < 3) {
          out.append("Every command needs at least 3 parameters.\n");
          break;
        }
        if (currentCommands.length > 5) {
          out.append("No command needs more than 5 parameters.\n");
          break;
        }

        switch (currentCommands[0]) {
          case "load":
            if (currentCommands.length > 3) {
              out.append("Load command only takes 3 parameters.\n");
              break;
            }
            String loadImagePath = currentCommands[1];
            String loadImageName = currentCommands[2];
            try {
              currentModel.loadImage(loadImagePath, loadImageName);
            } catch (Exception e) {
              out.append(e.getMessage()).append("\n");
              break;
            }
            break;
          case "save":
            if (currentCommands.length > 3) {
              out.append("Save command only takes 3 parameters.\n");
              break;
            }
            String saveImagePath = currentCommands[1];
            String saveImageName = currentCommands[2];
            try {
              currentModel.saveImage(saveImagePath, saveImageName);
            } catch (Exception e) {
              out.append(e.getMessage()).append("\n");
              break;
            }
            break;
          case "rgb-split":
          case "rgb-combine":
            if (currentCommands.length < 5) {
              out.append("rgb-split and rgb-combine commands need to take 5 parameters.\n");
              break;
            }
            executeCommands(currentCommands, valueCommand, currentModel);
            break;
          case "brighten":
            try {
              valueCommand = Integer.parseInt(currentCommands[1]);
            } catch (Exception e) {
              out.append("Second command of brighten must be a valid integer.\n");
              break;
            }
            executeCommands(currentCommands, valueCommand, currentModel);
            break;
          default:
            // Default for all other commands other than load and save
            // Get the rest of the commands in the currentCommands array
            executeCommands(currentCommands, valueCommand, currentModel);
            break;
        }
        out.append("$ ");
      }
    }
  }


  /**
   * Private method to check all other commands but save and load.
   *
   * @param commands     Array of strings of commands to check.
   * @param currentModel Current model controller is communicating with.
   */
  private boolean checkCommands(String[] commands, Model currentModel) throws IllegalArgumentException, IOException {
    switch (commands[0]) {
      case ("rgb-split"):
      case ("rgb-combine"):
        if (commands.length < 5) {
//          throw new IllegalArgumentException("Invalid number of arguments for command " + commands[0] + ".");
          out.append("Invalid number of arguments for command ").append(commands[0]).append(".");
        }
        break;
      case ("greyscale"):
        if (commands.length < 4) {
//          throw new IllegalArgumentException("Invalid number of arguments for command " + commands[0] + ".");
          out.append("Invalid number of arguments for command ").append(commands[0]).append(".");
        }
        break;
      default:
        if (commands.length < 3) {
//          throw new IllegalArgumentException("Invalid number of arguments for command " + commands[0] + ".");
          out.append("Invalid number of arguments for command ").append(commands[0]).append(".");
        }
        break;
    }
    return true;
  }

  /**
   * Method to execute all other commands but save and load.
   *
   * @param commands     String array of current commands to be executed.
   * @param commandVal   Integer values for commands that take a value.
   * @param currentModel Current model the controller is communicating with.
   */
  @Override
  public void executeCommands(String[] commands, int commandVal, Model currentModel) throws IOException {
    String imageName = commands[1];
    String newImageName = commands[2];

    // If commands do not pass checks.
    checkCommands(commands, currentModel);


    switch (commands[0]) {
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
      case "brighten":
        String brightenImageName = commands[2];
        String brightenNewImageName = commands[3];
        try {
          currentModel.brighten(brightenImageName, brightenNewImageName, commandVal);
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
      default:
        out.append("Invalid commands. Please try again\n");
        break;
    }
  }
}
