import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
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
   * @param in Input stream for the controller.
   * @param out Output stream for the controller.
   */
  Controller(Readable in, Appendable out){
    this.in = in;
    this.out = out;
  }

  /**
   * Main method of the program.
   * @param args Command line arguments to be passed to the program.
   */
  public static void main(String[] args) {
    Model newModel = new PPMModel();
    while (true) {
      System.out.print("$: ");
      try {
        new Controller(new InputStreamReader(System.in), System.out).run(newModel);
      } catch (Exception e) {
        if (e instanceof IllegalArgumentException) {
          e.printStackTrace();
          System.out.println(e);
          System.out.println("Please try again with appropriate arguments.");
        } else if (e instanceof IOException) {
          e.printStackTrace();
          System.out.println(e);
          System.out.println("Please try with a valid path.");
        } else if (e instanceof FileNotFoundException) {
          e.printStackTrace();
          System.out.println(e);
          System.out.println("Please try loading an image again with a valid file name.");
        } else if (e instanceof NoSuchElementException) {
          e.printStackTrace();
          System.out.println(e);
          System.out.println("Please again after loading the appropriate image or be sure to pass arguments in.");
        } else if (e instanceof InputMismatchException) {
            e.printStackTrace();
            System.out.println(e);
            System.out.println("The command you are trying to run needs an integer as an argument. Please try again.");
        } else {
          e.printStackTrace();
          System.out.println(e);
          System.exit(1);
        }
      }
    }
  }


  /**
   * Method to run the program, accept inputs and pass load, save images to Model.
   * @param currentModel The model the controller object is instructing.
   */
  @Override
  public void run(Model currentModel) {
    Objects.requireNonNull(currentModel);
    String[] currentCommands = new String[5];
    int valueCommand = 0;
    Scanner scan = new Scanner(this.in);
      currentCommands[0] = scan.next();
      currentCommands[0] = currentCommands[0].toLowerCase();
      switch (currentCommands[0]) {
        case "load":
          String loadImagePath = scan.next();
          String loadImageName = scan.next();
          currentModel.loadImage(loadImagePath, loadImageName);
          break;
        case "save":
          String saveImagePath = scan.next();
          String saveImageName = scan.next();
          currentModel.saveImage(saveImagePath, saveImageName);
          break;
        case "quit":
          System.out.println("Exiting application...");
          System.exit(0);
          break;
        default:
          // Default for all other commands other than load and save

          // Check if an int would be passed for the command
          if(currentCommands[0].equals("brighten")) {
            // Try to get the int command for brighten
            try {
              valueCommand = scan.nextInt();
            } catch (Exception e) {
              e.printStackTrace();
              System.out.println(e);
              break;
            }
          }

          // Get the rest of the commands in the currentCommands array
          int i = 1;
          while(scan.hasNext()){
            currentCommands[i] = scan.next();
            i++;
          }
          executeCommands(currentCommands, valueCommand, currentModel);
          break;
      }

  }


  /**
   * Private method to check all other commands but save and load.
   * @param commands Array of strings of commands to check.
   * @param currentModel Current model controller is communicating with.
   */
  private boolean checkCommands(String[] commands, Model currentModel) throws IllegalArgumentException {
    if (!(currentModel.getCommands().contains(commands[0]))) {
      throw new IllegalArgumentException("Invalid command " + commands[0] + " for this current model");
    }
    switch(commands[0]) {
      case ("rgb-split"):
      case ("rgb-combine"):
        if (commands.length < 5) {
          throw new IllegalArgumentException("Invalid number of arguments for command " + commands[0] + ".");
        }
        break;
      case ("greyscale"):
        if (commands.length < 4) {
          throw new IllegalArgumentException("Invalid number of arguments for command " + commands[0] + ".");
        }
        break;
      default:
        if (commands.length < 3) {
          throw new IllegalArgumentException("Invalid number of arguments for command " + commands[0] + ".");
        }
        break;
    }
      return true;
  }

  /**
   * Method to execute all other commands but save and load.
   * @param commands String array of current commands to be executed.
   * @param commandVal Integer values for commands that take a value.
   * @param currentModel Current model the controller is communicating with.
   */
  @Override
  public void executeCommands(String[] commands, int commandVal, Model currentModel) {
    String imageName = commands[1];
    String newImageName = commands[2];

      // If commands do not pass checks.
      checkCommands(commands, currentModel);


      switch(commands[0]){
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
        case "brighten":
          currentModel.brighten(imageName, newImageName, commandVal);
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
        default:
          break;
      }
  }
}
