package ime.control;

import ime.control.commands.*;
import ime.model.Model;
import ime.model.PPMModel;


import java.io.IOException;
import java.io.InputStreamReader;
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

/**
 * Class that is the IME.control.Controller of our MVC model.
 */
public class ImageController implements Controller {

  final Readable in;
  final Appendable out;
  static Map<String, Function<Scanner, Command>> knownCommands;

  private boolean quit = false;


  /**
   * Contructor for the creation of a IME.control.Controller object.
   *
   * @param in  Input stream for the controller.
   * @param out Output stream for the controller.
   */
  public ImageController(Readable in, Appendable out) {
    this.in = in;
    this.out = out;
    knownCommands = new HashMap<>();
  }

  /**
   * Main method of the program.
   *
   * @param args commands.Command line arguments to be passed to the program.
   */
  public static void main(String[] args) throws IOException {
    Model newModel = new PPMModel();
    if (args.length > 0) {
      if (args.length == 2) {
        String fileIn;
        try {
          fileIn = readFile(args[1]);
          Reader in = new StringReader(fileIn);
          Controller fileController = new ImageController(in, System.out);
          fileController.run(newModel);
        } catch (IOException e) {
          System.out.println(e.getMessage());
        }
        //TODO need to abstract this i wrote this in a super hack-y way
      } else {
        System.out.println("To run a text file please input \"-file file-path\" as command line arguments.");
      }

      System.exit(0);
    }
    Controller controller = new ImageController(new InputStreamReader(System.in), System.out);
    controller.run(newModel);

    System.exit(0);

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
    out.append("$ ");
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
    knownCommands.put("blur", s -> new Blur(s.nextLine().trim().split(" ")));
    knownCommands.put("sharpen", s -> new Sharpen(s.nextLine().trim().split(" ")));
    knownCommands.put("sepia", s -> new Sepia(s.nextLine().trim().split(" ")));
    knownCommands.put("dither", s -> new Dither(s.nextLine().trim().split(" ")));



    while (scan.hasNext()) {
      Command c;

      String in = scan.next();


      // If quit command
      if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
        return;
      }


      // if run command
      if (in.equalsIgnoreCase("run")) {
        if (!scan.hasNext()) {
          throw new IllegalArgumentException(
                  "Invalid number of arguments for command \"run\". 1 required.");
        }
        String fileIn;
        fileIn = readFile(scan.next());
        Reader newIn = new StringReader(fileIn);
        Controller fileController = new ImageController(newIn, System.out);
        fileController.run(currentModel);
        return;
      }

      // If command is empty of pound sign provided
      if (in.isEmpty() || in.charAt(0) == '#' ) {
        out.append("$ ");
        continue;
      }


      // If looking for command in hashmap
      Function<Scanner, Command> cmd = knownCommands.getOrDefault(in, null);
      // If command is not recognized
      if (cmd == null) {
        throw new IllegalArgumentException("Unrecognized command");
      } else {
        // apply lambda function and run the command
        c = cmd.apply(scan);
        c.run(currentModel);
      }
      out.append("$ ");
    }
  }


  private static String readFile(String path)
          throws IOException {
    byte[] encoded = Files.readAllBytes(Paths.get(path));
    return new String(encoded, StandardCharsets.UTF_8);
  }

}








