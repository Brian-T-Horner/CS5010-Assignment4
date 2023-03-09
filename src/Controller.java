import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Controller implements ControllerImp {

  final Readable in;
  final Appendable out;

  Model currentModel;

  Controller(Readable in, Appendable out){
    this.in = in;
    this.out = out;
    currentModel = new PPMModel();

  }


  /**
   * New main method
   * @throws IOException
   * @throws IllegalArgumentException
   */
//  public static void main(String[] args) {
//    try {
//      new Controller(new InputStreamReader(System.in), System.out).run(new ImageUtil());
//    } catch (IOException e) {
//      e.printStackTrace();
//      throw new RuntimeException(e);
//    }
//  }


  @Override
  public void run(Model currentModel) throws IOException, IllegalArgumentException {
    String[] currentCommands = new String[5];
    int valueCommand = 0;
    Scanner scan = new Scanner(this.in);
    String firstCommand;
    int status = 0;
    while(status == 0) {
      try {
        firstCommand = scan.next();
      } catch (Exception e){
        throw new IllegalArgumentException("Invalid first command");
      }
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
          if(currentCommands[0].toLowerCase().equals("brighten")) {
            valueCommand = scan.nextInt();
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
  private boolean checkCommands(String[] commands, int commandVal, Model currentModel) {
      return false;
  }

  @Override
  public int executeCommands(String[] commands, int commandVal, Model currentModel) {
    String commandLower = commands[0].toLowerCase();
    String imageName = commands[1];
    String newImageName = commands[2];

    // Check if there was a provided new name for the resulting image/images
    if (commands.length < 3) {
      throw new IllegalArgumentException("Must have a name for the new image");
    }

    // Will it be a problem that the image names are the same when going through the loop?
    // aka two vFlippedImage objects in the map. How can we avoid this
      switch(commandLower){
        case "redscale":
          return currentModel.getRedscaleImage(imageName, newImageName);
          break;
        case "greenscale":
          return currentModel.getGreenscaleImage(imageName, newImageName);
          break;
        case "bluescale":
          return currentModel.getBluescaleImage(imageName, newImageName);
          break;
        case "vertical-flip":

          return currentModel.flipVertical(imageName, newImageName);
          break;
        case "horizontal-flip":
          return currentModel.flipHorizontal(imageName, newImageName);
          break;
        case "value":
          return currentModel.getValueImage(imageName, newImageName);
          break;
        case "intensity":
          return currentModel.getIntensityImage(imageName, newImageName);
          break;
        case "luma":
          return currentModel.getLumaImage(imageName, newImageName);
          break;
        case "greyscale":
          break; //TODO:
        case "brighten":
          return currentModel.brighten(imageName, newImageName, commandVal);
          break;
        case "rgb-split":
          if (commands.length < 5) {
            throw new IllegalArgumentException("Needs 3 names for the new red, green and blue images");
          }


          String rImageName = commands[2];
          String gImageName = commands[3];
          String bImageName = commands[4];
          currentModel.rgbSplit(imageName, newImageName, rImageName, gImageName, bImageName);
          break;
        case "buffer-image":
          break; //TODO:
        default:
          break;
      }
  }
}
