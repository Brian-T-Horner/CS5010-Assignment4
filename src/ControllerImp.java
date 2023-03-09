import java.io.IOException;

public interface ControllerImp {

   void run(Model currentModel) throws IOException, IllegalArgumentException;
   int executeCommands(String[] commands, int commandVal, Model currentModel);

}
