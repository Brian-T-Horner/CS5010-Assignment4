import java.io.IOException;
import java.util.NoSuchElementException;

public interface ControllerImp {

   int run(Model currentModel) throws IOException, IllegalArgumentException, NoSuchElementException;
   int executeCommands(String[] commands, int commandVal, Model currentModel) throws IOException;

}
