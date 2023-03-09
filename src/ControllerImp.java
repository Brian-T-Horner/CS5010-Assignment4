import java.io.IOException;

public interface ControllerImp {

   void run() throws IOException, IllegalArgumentException;
   void executeCommands(String[] commands, int commandVal, PPMImage currentImage);

}
