import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Controller implements ControllerImp {

  final Readable in;
  final Appendable out;





  Controller(Readable in, Appendable out){
    this.in = in;
    this.out = out;



  }


  /**
   * New main method
   * @throws IOException
   * @throws IllegalArgumentException
   */
  public static void main(String[] args) {
    boolean status = true;
    Model newModel = new PPMModel();
    while (status) {
      System.out.print("$: ");
      try {
        new Controller(new InputStreamReader(System.in), System.out).run(newModel);
      } catch (Exception e) {
        if (e instanceof IllegalArgumentException) {
          e.printStackTrace();
          System.out.println(e);
          break;
        } else {
          e.printStackTrace();
          System.out.println(e);
          status = false;
          break;
        }
      }
    }
  }


  @Override
  public void run(Model currentModel) throws IllegalArgumentException {
    String[] currentCommands = new String[5];
    int valueCommand = 0;
    Scanner scan = new Scanner(this.in);
    String firstCommand;
    int status = 1;
    while(status == 1) {
      firstCommand = scan.next().toLowerCase();
      switch (firstCommand) {
        case "load":
            currentCommands[0] = "load";
            currentCommands[1] = scan.next();
            currentCommands[2] = scan.next();
            currentCommands[3] = null;
            status = currentModel.loadPPMImage(currentCommands[1], currentCommands[2]);
          break;
        case "save":
          currentCommands[0] = "save";
          currentCommands[1] = scan.next();
          currentCommands[2] = scan.next();
          currentCommands[3] = null;
          status = currentModel.savePPMImage(currentCommands[1], currentCommands[2]);
          break;
        default:
          // Default for all other commands other than load and save
          currentCommands[0] = firstCommand;

          // Check if an int would be passed for the command
          if(currentCommands[0].equals("brighten")) {
            // Try to get the int command for brighten
            try {
              valueCommand = scan.nextInt();
            } catch(Exception e) {
              e.printStackTrace();
              System.out.println(e);
              status = 0;
              break;
            }

            // Fix value command if outside capped region.
            if (valueCommand < 0) {
              valueCommand = 0;
            }
            if(valueCommand > 255) {
              valueCommand = 255;
            }
          }
          // Get the rest of the commands in the currentCommands array
          int i = 1;
          while(scan.hasNext()){
            currentCommands[i] = scan.next();
            i++;
          }
          status = executeCommands(currentCommands, valueCommand, currentModel);
          break;
      }
    }
  }


  //TODO: implementation
  private boolean checkCommands(String[] commands, Model currentModel) throws IllegalArgumentException {
    if (!(currentModel.getCommands().contains(commands[0]))) {
      throw new IllegalArgumentException("Invalid command for this current model");
    }

    switch(commands[0]) {
      case ("rgb-split"):
      case ("rgb-combine"):
        if (commands.length < 5) {

//          throw new IllegalArgumentException("Invalid number of arguments for rgb-split command.");
          return false;
        }
        break;
      //          throw new IllegalArgumentException("Invalid number of arguments for rgb-combine command.");
      case ("greyscale"):
        if (commands.length < 4) {
          return false;
        }
        break;
      default:
        if (commands.length < 3) {
//          throw new IllegalArgumentException("Invalid number of arguments for standard command");
          return false;
        }
        break;
    }

      return true;
  }

  @Override
  public int executeCommands(String[] commands, int commandVal, Model currentModel) {
    String imageName = commands[1];
    String newImageName = commands[2];

      // If commands do not pass checks.
      if(!checkCommands(commands, currentModel)){
        return 0;
      }

      switch(commands[0]){
        case "redscale":
          return currentModel.getRedComponent(imageName, newImageName);
        case "greenscale":
          return currentModel.getGreenComponent(imageName, newImageName);
        case "bluescale":
          return currentModel.getBlueComponent(imageName, newImageName);
        case "vertical-flip":
          return currentModel.flipVertical(imageName, newImageName);
        case "horizontal-flip":
          return currentModel.flipHorizontal(imageName, newImageName);
        case "value":
          return currentModel.getValueImage(imageName, newImageName);
        case "intensity":
          return currentModel.getIntensityImage(imageName, newImageName);
        case "luma":
          return currentModel.getLumaImage(imageName, newImageName);
        case "greyscale":
          String greyScaleComponent = commands[1];
          String currentImageName = commands[2];
          String destImageName = commands[3];
          return currentModel.greyscale(greyScaleComponent, currentImageName, destImageName);
        case "brighten":
          return currentModel.brighten(imageName, newImageName, commandVal);
        case "rgb-split":
          String newRImageName = commands[2];
          String newGImageName = commands[3];
          String newBImageName = commands[4];
          return currentModel.rgbSplit(imageName, newRImageName, newGImageName, newBImageName);
        case "rgb-combine":
          String rImageName = commands[2];
          String gImageName = commands[3];
          String bImageName = commands[4];
          return currentModel.rgbCombine(imageName, rImageName, gImageName, bImageName);
        default:
          break;
      }
    return 1;
  }
}
