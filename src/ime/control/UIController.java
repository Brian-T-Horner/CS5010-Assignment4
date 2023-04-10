package ime.control;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Objects;

import ime.ImageUtil;
import ime.model.Model;
import ime.view.Features;
import ime.view.View;

public class UIController extends AbstractController implements Features {
  /**
   * Contructor for UIController.
   *
   * @param model model for controller
   * @param view  view for controller
   */
  public UIController(Model model, View view) {
    super(model, view);
  }

  @Override
  public void run() throws IOException {
    Objects.requireNonNull(model);
    Objects.requireNonNull(view);
    view.addFeatures(this);
  }

  @Override
  public void blur() {
    if (checkImageInMemory()) {
      model.blur("currentImage", "currentImage");
      setImage();
    }
  }

  @Override
  public void brighten(int scale) {
    if (checkImageInMemory()) {
      model.brighten("currentImage", "currentImage", scale);
      setImage();
    }
  }

  @Override
  public void dither() {
    if (checkImageInMemory()) {
      model.dither("currentImage", "currentImage");
      setImage();
    }
    // TODO has bug, won't apply twice


  }

  @Override
  public void greyscale() {
    if (checkImageInMemory()) {
      model.greyscale("luma-component", "currentImage", "currentImage");
      setImage();
    }
  }

  @Override
  public void horizontalFlip() {
    if (checkImageInMemory()) {
      model.flipHorizontal("currentImage", "currentImage");
      setImage();
    }
  }

  @Override
  public void verticalFlip() {
    if (checkImageInMemory()) {
      model.flipVertical("currentImage", "currentImage");
      setImage();
    }
  }

  @Override
  public void loadImage(String path) {
    try {
      model.loadImage(path, "currentImage");
      setImage();
    } catch (Exception e) {
      view.printGeneralError(e.getMessage());
    }
  }

  @Override
  public void rgbCombine() {
    checkImageInMemory();
    //TODO this one needs lots of work in the view as well
    setImage();
  }

  @Override
  public void rgbSplit() {
    checkImageInMemory();
    try {
      model.rgbSplit("currentImage", "currentImage", "green", "blue");
      setImage();
    } catch (NoSuchElementException e) {
      //TODO throw error in UI
      //ERROR if image not in mem
    }

  }

  @Override
  public void save(String path) {

    if (path.isEmpty()) {
      path = "./img.png";
    }
    if (checkImageInMemory()) {
      try {
        model.saveImage(path, "currentImage");
      } catch (Exception e) {
        // OR error if path file type is wrong
        view.printGeneralError(e.getMessage());
      }
    }

  }

  @Override
  public void sepia() {
    if (checkImageInMemory()) {
      model.sepia("currentImage", "currentImage");
      setImage();
    }

  }

  @Override
  public void sharpen() {
    if (checkImageInMemory()) {
      model.sharpen("currentImage", "currentImage");
      setImage();
    }
  }

  @Override
  public void exit() {
    System.out.println("Exiting application...");
    System.exit(1);
  }

  private void setImage() {
    BufferedImage i = ImageUtil.writeBufferedImage(model.getImage("currentImage"));
    view.setImage(i);
  }

  private boolean checkImageInMemory() {
    try {
      model.getImage("currentImage");
      return true;
    } catch (Exception e) {
      view.printGeneralError("Please load an image.");
      return false;
    }
  }
}
