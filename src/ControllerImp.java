import java.io.IOException;

public interface ControllerImp {

   void run(ImageUtil model) throws IOException, IllegalArgumentException;
   void executeCommands(ImageUtil model, String[] commands, int commandVal);

}
