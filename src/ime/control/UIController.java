package ime.control;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import ime.ImageUtil;
import ime.model.Image;
import ime.model.Model;
import ime.view.Features;
import ime.view.View;

public class UIController extends AbstractController implements Features {
  /**
   * Contructor for AbstractController.
   *
   * @param model
   * @param view
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
    checkImageInMemory();
    model.blur("currentImage","currentImage");
    setImage();
  }

  @Override
  public void brighten(int scale) {
    checkImageInMemory();
    model.brighten("currentImage","currentImage",scale);
    setImage();
  }

  @Override
  public void dither() {
    checkImageInMemory();
    model.dither("currentImage","currentImage");
    setImage();
  }

  @Override
  public void greyscale() {
    checkImageInMemory();
    model.greyscale("luma-component","currentImage","currentImage");
    setImage();
  }

  @Override
  public void horizontalFlip() {
    checkImageInMemory();
    model.flipHorizontal("currentImage","currentImage");
    setImage();
  }

  @Override
  public void verticalFlip() {
    checkImageInMemory();
    model.flipVertical("currentImage","currentImage");
    setImage();
  }

  @Override
  public void loadImage(String path) {
    try {
      model.loadImage(path,"currentImage");
    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
    }

    setImage();
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
    //TODO
    setImage();
  }

  @Override
  public void save(String path) {

    checkImageInMemory();

//    if(model.getImage("currentImage") == null) {
//      //TODO view throw error
//      return;
//    }

    if(path.isEmpty()) {
      path = "./img.png";
    }
    try {
      model.saveImage(path,"currentImage");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public void sepia() {
    model.sepia("currentImage","currentImage");
    setImage();
  }

  @Override
  public void sharpen() {
    model.sharpen("currentImage","currentImage");
    setImage();
  }

  @Override
  public void exit() {
    System.out.println("Exiting application...");
    System.exit(1);
  }

  private void setImage() {
    BufferedImage i =ImageUtil.writeBufferedImage(model.getImage("currentImage"));
    view.setImage(i);
  }

  private void checkImageInMemory() {
    model.getImage("currentImage");
  }
}
