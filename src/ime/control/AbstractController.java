package ime.control;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;

import ime.control.commands.Bluescale;
import ime.control.commands.Brighten;
import ime.control.commands.Command;
import ime.control.commands.Greenscale;
import ime.control.commands.Greyscale;
import ime.control.commands.HorizontalFlip;
import ime.control.commands.Intensity;
import ime.control.commands.Load;
import ime.control.commands.Luma;
import ime.control.commands.RGBCombine;
import ime.control.commands.RGBSplit;
import ime.control.commands.Redscale;
import ime.control.commands.Save;
import ime.control.commands.Value;
import ime.control.commands.VerticalFlip;
import ime.model.Model;

public abstract class AbstractController implements Controller {

  final Readable in;
  final Appendable out;
  static Map<String, Function<Scanner, Command>> knownCommands;


  /**
   * Contructor for the creation of a IME.control.Controller object.
   *
   * @param in  Input stream for the controller.
   * @param out Output stream for the controller.
   */
  public AbstractController(Readable in, Appendable out) {
    this.in = in;
    this.out = out;
    knownCommands = new HashMap<>();
  }



  /**
   * Method to run the program, accept inputs and pass load, save images to IME.model.Model.
   *
   * @param currentModel The model the controller object is instructing.
   */
  @Override
  public void run(Model currentModel) throws IOException {
    Objects.requireNonNull(currentModel);
    Scanner scan = new Scanner(this.in);
    insertCursor();
    knownCommands.put("bluescale", s -> new Bluescale(s.nextLine().trim().split(" ")));
    knownCommands.put("redscale", s -> new Redscale(s.nextLine().trim().split(" ")));
    knownCommands.put("greenscale", s -> new Greenscale(s.nextLine().trim().split(" ")));
    knownCommands.put("horizontal-flip", s -> new HorizontalFlip(s.nextLine().trim().split(" ")));
    knownCommands.put("vertical-flip", s -> new VerticalFlip(s.nextLine().trim().split(" ")));
    knownCommands.put("load", s -> new Load(s.nextLine().trim().split(" ")));
    knownCommands.put("save", s -> new Save(s.nextLine().trim().split(" ")));
    knownCommands.put("greyscale", s -> new Greyscale(s.nextLine().trim().split(" ")));
    knownCommands.put("luma", s -> new Luma(s.nextLine().trim().split(" ")));
    knownCommands.put("value", s -> new Value(s.nextLine().trim().split(" ")));
    knownCommands.put("intensity", s -> new Intensity(s.nextLine().trim().split(" ")));
    knownCommands.put("brighten", s -> new Brighten(s.nextLine().trim().split(" ")));
    knownCommands.put("rgb-split", s -> new RGBSplit(s.nextLine().trim().split(" ")));
    knownCommands.put("rgb-combine", s -> new RGBCombine(s.nextLine().trim().split(" ")));

    while (scan.hasNext()) {
      Command c;
      String in = scan.next();
      // If quit command
      if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
        out.append("Exiting application...");
        return;
      }
      // if run command
      if (in.equalsIgnoreCase("run")) {
        String commandString = scan.nextLine().trim();
        String[] commands = commandString.split(" ");
        if (commands.length != 1) {
          throw new IllegalArgumentException(
                  "Invalid number of arguments for command \"run\". 1 required.");
        }
        runFile(commands[0],currentModel);
        insertCursor();
        continue;
      }

      // If command is empty of pound sign provided
      if (in.isEmpty() || in.charAt(0) == '#') {
        insertCursor();
        continue;
      }
      // If looking for command in hashmap
      Function<Scanner, Command> cmd = knownCommands.getOrDefault(in, null);
      // If command is not recognized
      if (cmd == null) {
        out.append("Unrecognized command\n");
      } else {
        c = cmd.apply(scan);
        try{
          c.run(currentModel);
        } catch(Exception e) {
          out.append(e.getMessage()).append("\n");
        }
      }
      insertCursor();
    }
  };

  protected abstract void insertCursor() throws IOException;

  protected static String readFile(String path)
          throws IOException {
    byte[] encoded = Files.readAllBytes(Paths.get(path));
    return new String(encoded, StandardCharsets.UTF_8);
  }


  protected static void runFile(String fileIn,Model currentModel) {
    String file;
    try {
      file = readFile(fileIn);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    Reader newIn = new StringReader(file);
    Controller fileController = new FileController(newIn, System.out);
    try {
      fileController.run(currentModel);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}


