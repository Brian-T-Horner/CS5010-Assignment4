import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Controller implements ControllerImp {

  final Readable in;
  final Appendable out;

  Map<String, PPMImage> currentImages;





  Controller(Readable in, Appendable out){
    this.in = in;
    this.out = out;
    currentImages = new HashMap<>();
  }



  @Override
  public void run(ImageUtil model) throws IOException, IllegalArgumentException {
    Objects.requireNonNull(model);
    String[] currentCommands = new String[5];
    int valueCommand;
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
            PPMImage newImage =  model.readIntoPPMImage(currentCommands[1], currentCommands[2]);
            newImage.setName(currentCommands[2]);
            currentImages.put(currentCommands[2], newImage);
          break;
        case "save":
          currentCommands[0] = "save";
          currentCommands[1] = scan.next();
          currentCommands[2] = scan.next();
          currentCommands[3] = null;
          PPMImage copyImage = currentImages.get(currentCommands[2]);
          if (copyImage == null) {
            throw new IllegalArgumentException("There does not exist a PPMImage of that name");
          }
          model.writeToPPMFile(copyImage, currentCommands[1]);
          break;
        default:
          currentCommands[0] = firstCommand;
          valueCommand = scan.nextInt();
          int i = 1;
          while(scan.hasNext()){
            currentCommands[i] = scan.next();
            i++;
          }
          executeCommands(model, currentCommands, valueCommand);
          break;
      }
    }







  }

  @Override
  public void executeCommands(ImageUtil model, String[] commands, int commandVal) {
    String commandLower = commands[0].toLowerCase();
    String imageName = commands[1];
    if (currentImages.get(imageName) == null) {
      throw new IllegalArgumentException("There does not exist a PPMImage of that name");
    }

    // Will it be a problem that the image names are the same when going through the loop?
    // aka two vFlippedImage objects in the map. How can we avoid this
      switch(commandLower){
        case "redscale":
          PPMImage redScaleImage = currentImages.get(imageName).getRedscaleImage(commands[2]);
          currentImages.put(commands[2], redScaleImage);
          break;
        case "greenscale":
          PPMImage greenScaleImage = currentImages.get(imageName).getGreenscaleImage(commands[2]);
          currentImages.put(commands[2], greenScaleImage);
          break;
        case "bluescale":
          PPMImage blueScaleImage = currentImages.get(imageName).getBluescaleImage(commands[2]);
          currentImages.put(commands[2], blueScaleImage);
          break;
        case "vertical-flip":
          PPMImage vFlippedImage = currentImages.get(imageName).flipVertical(commands[2]);
          currentImages.put(commands[2], vFlippedImage);
          break;
        case "horizontal-flip":
          PPMImage hFlippedImage = currentImages.get(imageName).flipHorizontal(commands[2]);
          currentImages.put(commands[2], hFlippedImage);
          break;
        case "value":
          PPMImage valueImage = currentImages.get(imageName).getValueImage(commands[2]);
          currentImages.put(commands[2], valueImage);
          break;
        case "intensity":
          PPMImage intensityImage = currentImages.get(imageName).getIntensityImage(commands[2]);
          currentImages.put(commands[2], intensityImage);
          break;
        case "luma":
          PPMImage lumaImage = currentImages.get(imageName).getLumaImage(commands[2]);
          currentImages.put(commands[2], lumaImage);
          break;
        case "greyscale":
          break;
        case "brighten":
          PPMImage brightenImage = currentImages.get(imageName).brighten(commands[2], commandVal);
          currentImages.put(commands[2], brightenImage);
          break;
        case "rgb-split":
          if (commands.length < 5) {
            throw new IllegalArgumentException("Needs 3 names for the new red, green and blue images");
          }
          PPMImage rImage = currentImages.get(imageName).getRedscaleImage(commands[2]);
          PPMImage gImage = currentImages.get(imageName).getGreenscaleImage(commands[3]);
          PPMImage bImage = currentImages.get(imageName).getBluescaleImage(commands[4]);
          break;
        case "buffer-image":
          break;
        default:
          break;
      }
  }
}
