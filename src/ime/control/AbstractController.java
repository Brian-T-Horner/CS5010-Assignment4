package ime.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import ime.control.commands.Bluescale;
import ime.control.commands.Blur;
import ime.control.commands.Brighten;
import ime.control.commands.Command;
import ime.control.commands.Dither;
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
import ime.control.commands.Sepia;
import ime.control.commands.Sharpen;
import ime.control.commands.Value;
import ime.control.commands.VerticalFlip;
import ime.model.Model;
import ime.view.View;


/**
 * Abstract class to extending the Controller interface.
 */
public abstract class AbstractController implements Controller {

  protected Model model;

  protected View view;
  static Map<String, Function<Scanner, Command>> knownCommands;

  protected boolean quit;


  /**
   * Contructor for AbstractController.
   */
  public AbstractController(Model model, View view) {
    this.model = model;
    this.view = view;
    knownCommands = new HashMap<>();
    quit = false;
  }

  public boolean isQuit() {
    return quit;
  }


  @Override
  public abstract void run() throws IOException;


  public void putCommands() {
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
    knownCommands.put("sepia", s -> new Sepia(s.nextLine().trim().split(" ")));
    knownCommands.put("sharpen", s -> new Sharpen(s.nextLine().trim().split(" ")));
    knownCommands.put("blur", s -> new Blur(s.nextLine().trim().split(" ")));
    knownCommands.put("dither", s -> new Dither(s.nextLine().trim().split(" ")));
  }

  public void executeCommand(String in,Scanner scan){
    Function<Scanner, Command> cmd = knownCommands.getOrDefault(in, null);
    Command c;
    if (cmd == null) {
      view.unknownCommandPrompt();
    } else {
      c = cmd.apply(scan);
      try {
        c.run(model);
      } catch (Exception e) {
        view.printGeneralError(e.getMessage());
      }
    }
  }
}