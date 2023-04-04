package ime.view;

public interface Features {

  void blur();

  void brighten(int scale);

  void dither();

  void greyscale();

  void horizontalFlip();

  void verticalFlip();

  void loadImage(String path);

  void rgbCombine();

  void rgbSplit();

  void save(String path);

  void sepia();

  void sharpen();

  void exit();
}
