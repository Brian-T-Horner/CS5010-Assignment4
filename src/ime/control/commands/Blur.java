package ime.control.commands;

import ime.model.Model;
import java.io.FileNotFoundException;

public class Blur implements Command {

    String[] commands;

    public Blur(String[] commands) {
        this.commands = commands;
    }
    @Override
    public void run(Model m) throws FileNotFoundException, IllegalArgumentException {
        if (commands.length != 2) {
            throw new IllegalArgumentException("Invalid number of arguments for command \"dither\". "
                    + "2 required.");
        }
        String imageName = commands[0];
        String newImageName = commands[1];
        m.blur(imageName, newImageName);
    }
}
