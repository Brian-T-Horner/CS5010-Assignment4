package ime.control;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

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
    setChart();
  }

  @Override
  public void brighten(int scale) {
    checkImageInMemory();
    model.brighten("currentImage","currentImage",scale);
    setImage();
    setChart();
  }

  @Override
  public void dither() {
    checkImageInMemory();
    model.dither("currentImage","currentImage");
    setImage();
    setChart();
  }

  @Override
  public void greyscale() {
    checkImageInMemory();
    model.greyscale("luma-component","currentImage","currentImage");
    setImage();
    setChart();
  }

  @Override
  public void horizontalFlip() {
    checkImageInMemory();
    model.flipHorizontal("currentImage","currentImage");
    setImage();
    setChart();
  }

  @Override
  public void verticalFlip() {
    checkImageInMemory();
    model.flipVertical("currentImage","currentImage");
    setImage();
    setChart();
  }

  @Override
  public void loadImage(String path) {
    try {
      model.loadImage(path,"currentImage");
      setImage();
      setChart();
    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
    }


  }

  @Override
  public void rgbCombine() {
    checkImageInMemory();
    //TODO this one needs lots of work in the view as well
    setImage();
    setChart();
  }

  @Override
  public void rgbSplit() {
    checkImageInMemory();
    model.rgbSplit("currentImage","currentImage","green","blue");
    setImage();
    setChart();
  }

  @Override
  public void save(String path) {

    checkImageInMemory();

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
    checkImageInMemory();
    model.sepia("currentImage","currentImage");
    setImage();
    setChart();;
  }

  @Override
  public void sharpen() {
    checkImageInMemory();
    model.sharpen("currentImage","currentImage");
    setImage();
    setChart();
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

  private void setChart() {


    // get intensity
    model.getIntensityImage("currentImage", "intensity-image-chart");
    Image intensity = model.getImage("intensity-image-chart");
    Image current = model.getImage("currentImage");

    if(current.getRedComponent().equals(current.getGreenComponent())
        && current.getGreenComponent().equals(current.getBlueComponent())
        && current.getBlueComponent().equals(current.getRedComponent())) {
      view.updateGreyChartPanel(intensity.getRedComponent());
    } else {
      view.updateColoredChartPanel(current.getRedComponent(), current.getGreenComponent(),
          current.getBlueComponent(), intensity.getRedComponent());
    }
    // check if rgb is equal

    // if it is setChartGreyScale

    // if it is not setChart Normal
    // get red component
    // get green component
    // get blue component


  }

  private void checkImageInMemory() {
    model.getImage("currentImage");
  }
}
