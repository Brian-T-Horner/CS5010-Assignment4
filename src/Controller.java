import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Controller implements ControllerImp {

  final Readable in;
  final Appendable out;





  Controller(Readable in, Appendable out){
    this.in = in;
    this.out = out;
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
          break;
        case "save":
          currentCommands[0] = "save";
          currentCommands[1] = scan.next();
          currentCommands[2] = scan.next();
          currentCommands[3] = null;
          try {
              Class checkClass = Class.forName(currentCommands[2]);
              if(checkClass.isInstance(PPMImage)){

                model.writeToPPMFile(checkClass, currentCommands[1]);
              }
          } catch (Exception e) {
            throw Invalid
          }
          PPMImage image = currentCommands[2];
          model.writeToPPMFile(image, currentCommands[1]);
          break;
        default:
          currentCommands[0] = firstCommand;
          valueCommand = scan.nextInt();
          int i = 1;
          while(scan.hasNext()){
            currentCommands[i] = scan.next();
            i++;
            executeCommands(model, currentCommands, valueCommand);
          }
          break;
      }
    }







  }

  @Override
  public void executeCommands(ImageUtil model, String[] commands, int commandVal) {

  }
}
