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


  }


  /**
   * New main method
   * @throws IOException
   * @throws IllegalArgumentException
   */
  public static void main(String[] args) {
    System.out.println("$: ");
    try {
      new Controller(new InputStreamReader(System.in), System.out).run(new PPMModel());
    } catch (Exception e) {
      if (e instanceof IllegalArgumentException) {
      e.printStackTrace();
      System.out.println(e);

    }
  }


  @Override
  public void run(Model currentModel) throws IOException, IllegalArgumentException {
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
    String imageName = commands[1];
    String newImageName = commands[2];

    // Check if there was a provided new name for the resulting image/images
    if (commands.length < 3) {
      throw new IllegalArgumentException("Must have a name for the new image");
    }

    // Will it be a problem that the image names are the same when going through the loop?
    // aka two vFlippedImage objects in the map. How can we avoid this
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
          break; //TODO:
        case "brighten":
          return currentModel.brighten(imageName, newImageName, commandVal);
        case "rgb-split":
          if (commands.length < 5) {
            throw new IllegalArgumentException("Needs 3 names for the new red, green and blue images");
          }


          String rImageName = commands[2];
          String gImageName = commands[3];
          String bImageName = commands[4];
          return currentModel.rgbSplit(imageName, newImageName, rImageName, gImageName, bImageName);
        case "buffer-image":
          break; //TODO:
        default:

          break;
      }
    return 1;
  }
}
