import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Controller implements ControllerImp {

  final Readable in;
  final Appendable out;

  //TODO: Make this a set? How to combat multiple objects with the same name for access
  // Not their field but actual memory access name
  Map<String, PPMImage> currentImages;





  Controller(Readable in, Appendable out){
    this.in = in;
    this.out = out;
    currentImages = new HashMap<>();
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
  public void run() throws IOException, IllegalArgumentException {
    String[] currentCommands = new String[5];
    int valueCommand = 0;
    Scanner scan = new Scanner(this.in);
    String firstCommand;
    while(true) {
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
            currentImages.put(currentCommands[2], ImageUtil.readIntoPPMImage(currentCommands[1], currentCommands[2]));
          break;
        case "save":
          currentCommands[0] = "save";
          currentCommands[1] = scan.next();
          currentCommands[2] = scan.next();
          currentCommands[3] = null;
          if (currentImages.get(currentCommands[2]) == null) {
            throw new IllegalArgumentException("There does not exist a PPMImage of that name");
          }
          ImageUtil.writeToPPMFile(currentImages.get(currentCommands[2]), currentCommands[1]);
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
          executeCommands(currentCommands, valueCommand, currentImages.get(currentCommands[1]));
          break;
      }
    }
  }


  //TODO: implementation
  private boolean checkCommands(String[] commands, int commandVal) {
      return false;
  }

  @Override
  public void executeCommands(String[] commands, int commandVal, PPMImage currentImage) {
    String commandLower = commands[0].toLowerCase();
//    String imageName = commands[1];
    String newImageName = commands[2];


    // Check that we have the image saved in our hashmap buffer to operate on
    if (currentImage == null) {
      throw new IllegalArgumentException("There does not exist a PPMImage of that name");
    }

    // Check if there was a provided new name for the resulting image/images
    if (commands.length < 3) {
      throw new IllegalArgumentException("Must have a name for the new image");
    }

    // Will it be a problem that the image names are the same when going through the loop?
    // aka two vFlippedImage objects in the map. How can we avoid this
      switch(commandLower){
        case "redscale":
          currentImages.put(newImageName, currentImage.getRedscaleImage(newImageName));
          break;
        case "greenscale":
          currentImages.put(newImageName, currentImage.getGreenscaleImage(newImageName));
          break;
        case "bluescale":
          currentImages.put(newImageName, currentImage.getBluescaleImage(newImageName));
          break;
        case "vertical-flip":

          currentImages.put(newImageName, currentImage.flipVertical(newImageName));
          break;
        case "horizontal-flip":
          currentImages.put(newImageName, currentImage.flipHorizontal(newImageName));
          break;
        case "value":
          currentImages.put(newImageName, currentImage.getValueImage(newImageName));
          break;
        case "intensity":
          currentImages.put(newImageName, currentImage.getIntensityImage(newImageName));
          break;
        case "luma":
          currentImages.put(newImageName, currentImage.getLumaImage(newImageName));
          break;
        case "greyscale":
          break; //TODO:
        case "brighten":
          currentImages.put(newImageName, currentImage.brighten(newImageName, commandVal));
          break;
        case "rgb-split":
          if (commands.length < 5) {
            throw new IllegalArgumentException("Needs 3 names for the new red, green and blue images");
          }

          String rImageName = commands[2];
          String gImageName = commands[3];
          String bImageName = commands[4];

          currentImages.put(rImageName, currentImage.getRedscaleImage(rImageName));
          currentImages.put(gImageName, currentImage.getGreenscaleImage(gImageName));
          currentImages.put(bImageName, currentImage.getBluescaleImage(bImageName));
          break;
        case "buffer-image":
          break; //TODO:
        default:
          break;
      }
  }
}
