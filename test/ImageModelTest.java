import ime.model.Image;
import ime.model.ImageModel;
import ime.model.Model;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Class to test the IME.model.PPMModel.
 */
public class ImageModelTest {


  Model m;


  @Before
  public void setUp() {
    m = new ImageModel();

    try {
      m.loadImage("test/donut.ppm", "donuts");
    } catch (Exception ignored) {
    }
  }


  @Test
  public void testFlipHorizontal() {
    m.flipHorizontal("donuts", "donuts-hflipped");
    Image i = m.getImage("donuts-hflipped");
    Image orig = m.getImage("donuts");
    assertArrayEquals(flipArrayHorizontal(orig, orig.getRedComponent()), i.getRedComponent());
    assertArrayEquals(flipArrayHorizontal(orig, orig.getBlueComponent()), i.getBlueComponent());
    assertArrayEquals(flipArrayHorizontal(orig, orig.getGreenComponent()), i.getGreenComponent());
    assertThrows(NoSuchElementException.class, ()
        -> m.flipHorizontal("invalid-donuts", "donuts-hflipped"));
  }

  @Test
  public void testFlipVertical() {
    m.flipVertical("donuts", "donuts-vflipped");
    Image i = m.getImage("donuts-vflipped");
    Image orig = m.getImage("donuts");
    assertArrayEquals(flipArrayVertical(orig, orig.getRedComponent()), i.getRedComponent());
    assertArrayEquals(flipArrayVertical(orig, orig.getBlueComponent()), i.getBlueComponent());
    assertArrayEquals(flipArrayVertical(orig, orig.getGreenComponent()), i.getGreenComponent());
    assertThrows(NoSuchElementException.class, ()
        -> m.flipVertical("invalid-donuts", "donuts-vflipped"));
  }

  @Test
  public void testGetValueImage() {
    m.getValueImage("donuts", "donuts-value");
    Image i = m.getImage("donuts-value");
    assertArrayEquals(valueArray(m.getImage("donuts")), i.getBlueComponent());
    assertArrayEquals(valueArray(m.getImage("donuts")), i.getGreenComponent());
    assertArrayEquals(valueArray(m.getImage("donuts")), i.getRedComponent());
    assertThrows(NoSuchElementException.class, ()
        -> m.getValueImage("invalid-donuts", "donuts-value"));
    assertTrue(checkCeilAndFloorVals(i.getGreenComponent()));
  }

  @Test
  public void testGetIntensityImage() {
    m.getIntensityImage("donuts", "donuts-intensity");
    Image i = m.getImage("donuts-intensity");
    assertArrayEquals(intensityArray(m.getImage("donuts")), i.getBlueComponent());
    assertArrayEquals(intensityArray(m.getImage("donuts")), i.getGreenComponent());
    assertArrayEquals(intensityArray(m.getImage("donuts")), i.getRedComponent());
    assertThrows(NoSuchElementException.class, ()
        -> m.getIntensityImage("invalid-donuts", "donuts-intensity"));
    assertTrue(checkCeilAndFloorVals(i.getGreenComponent()));
  }

  @Test
  public void testGetLumaImage() {
    m.getLumaImage("donuts", "donuts-luma");
    Image i = m.getImage("donuts-luma");
    assertArrayEquals(lumaArray(m.getImage("donuts")), i.getBlueComponent());
    assertArrayEquals(lumaArray(m.getImage("donuts")), i.getGreenComponent());
    assertArrayEquals(lumaArray(m.getImage("donuts")), i.getRedComponent());
    assertThrows(NoSuchElementException.class, ()
        -> m.getLumaImage("invalid-donuts", "donuts-luma"));
    assertTrue(checkCeilAndFloorVals(i.getGreenComponent()));
  }

  @Test
  public void testBrighten() {
    m.brighten("donuts", "donuts-brighten-by-10", 10);
    Image i = m.getImage("donuts-brighten-by-10");
    Image orig = m.getImage("donuts");
    assertArrayEquals(brightenArray(orig, orig.getRedComponent(), 10), i.getRedComponent());
    assertArrayEquals(brightenArray(orig, orig.getBlueComponent(), 10), i.getBlueComponent());
    assertArrayEquals(brightenArray(orig, orig.getGreenComponent(), 10), i.getGreenComponent());
    assertThrows(NoSuchElementException.class, ()
        -> m.flipVertical("invalid-donuts", "donuts-vflipped"));
    //TODO add test for inputting NotANumber
    assertTrue(checkCeilAndFloorVals(i.getGreenComponent()));
    assertTrue(checkCeilAndFloorVals(i.getRedComponent()));
    assertTrue(checkCeilAndFloorVals(i.getBlueComponent()));
  }

  @Test
  public void testGetRedComponent() {
    assertThrows(NoSuchElementException.class, ()
        -> m.getRedComponent("invalid-donuts", "donuts-red"));
  }

  @Test
  public void testGetGreenComponent() {
    assertThrows(NoSuchElementException.class, ()
        -> m.getGreenComponent("invalid-donuts", "donuts-red"));
  }

  @Test
  public void testGetBlueComponent() {
    assertThrows(NoSuchElementException.class, ()
        -> m.getBlueComponent("invalid-donuts", "donuts-red"));
  }

  @Test
  public void testRgbSplit() {
    m.rgbSplit("donuts", "donuts-red",
            "donuts-green", "donuts-blue");
    Image red = m.getImage("donuts-red");
    Image green = m.getImage("donuts-green");
    Image blue = m.getImage("donuts-blue");
    Image orig = m.getImage("donuts");
    assertArrayEquals(orig.getRedComponent(), red.getRedComponent());
    assertArrayEquals(orig.getRedComponent(), red.getGreenComponent());
    assertArrayEquals(orig.getRedComponent(), red.getBlueComponent());

    assertArrayEquals(orig.getGreenComponent(), green.getRedComponent());
    assertArrayEquals(orig.getGreenComponent(), green.getGreenComponent());
    assertArrayEquals(orig.getGreenComponent(), green.getBlueComponent());

    assertArrayEquals(orig.getBlueComponent(), blue.getRedComponent());
    assertArrayEquals(orig.getBlueComponent(), blue.getGreenComponent());
    assertArrayEquals(orig.getBlueComponent(), blue.getBlueComponent());
    assertThrows(NoSuchElementException.class, () -> m.rgbSplit("invalid-donuts",
            "donuts-red", "donuts-green", "donuts-blue"));
  }

  @Test
  public void testRgbCombine() {
    m.rgbSplit("donuts", "donuts-red",
            "donuts-green", "donuts-blue");
    Image red = m.getImage("donuts-red");
    Image green = m.getImage("donuts-green");
    Image blue = m.getImage("donuts-blue");

    m.rgbCombine("donuts-new", "donuts-red",
            "donuts-green", "donuts-blue");

    Image newImg = m.getImage("donuts-new");

    assertArrayEquals(red.getRedComponent(), newImg.getRedComponent());
    assertArrayEquals(green.getGreenComponent(), newImg.getGreenComponent());
    assertArrayEquals(blue.getBlueComponent(), newImg.getBlueComponent());
    assertThrows(NoSuchElementException.class, () -> m.rgbCombine("donuts-new",
            "invalid-donuts-red", "donuts-green", "donuts-blue"));
    assertThrows(NoSuchElementException.class, () -> m.rgbCombine("donuts-new",
            "donuts-red", "invalid-donuts-green", "donuts-blue"));
    assertThrows(NoSuchElementException.class, () -> m.rgbCombine("donuts-new",
            "donuts-red", "donuts-green", "invalid-donuts-blue"));
  }

  @Test
  public void testGreyscale() {
    m.greyscale("red-component", "donuts",
            "donuts-red");
    m.greyscale("green-component", "donuts",
            "donuts-green");
    m.greyscale("blue-component", "donuts",
            "donuts-blue");

    m.greyscale("value-component", "donuts",
            "donuts-value");
    m.greyscale("intensity-component", "donuts",
            "donuts-intensity");
    m.greyscale("luma-component", "donuts",
            "donuts-luma");

    Image red = m.getImage("donuts-red");
    Image green = m.getImage("donuts-green");
    Image blue = m.getImage("donuts-blue");
    Image value = m.getImage("donuts-value");
    Image intensity = m.getImage("donuts-intensity");
    Image luma = m.getImage("donuts-luma");
    Image orig = m.getImage("donuts");

    m.getValueImage("donuts", "donuts-value");

    m.getIntensityImage("donuts", "donuts-intensity");

    m.getLumaImage("donuts", "donuts-luma");
    Image lumaExpected = m.getImage("donuts-luma");

    Image valueExpected = m.getImage("donuts-value");

    Image intensityExpected = m.getImage("donuts-intensity");

    assertArrayEquals(red.getRedComponent(), orig.getRedComponent());
    assertArrayEquals(green.getGreenComponent(), orig.getGreenComponent());
    assertArrayEquals(blue.getBlueComponent(), orig.getBlueComponent());

    assertArrayEquals(lumaExpected.getGreenComponent(), luma.getGreenComponent());
    assertArrayEquals(valueExpected.getGreenComponent(), value.getGreenComponent());
    assertArrayEquals(intensityExpected.getGreenComponent(), intensity.getGreenComponent());
    assertThrows(IllegalArgumentException.class, ()
        -> m.greyscale("invalid-component", "donuts",
        "invalid-greyscale"));
  }

  @Test
  public void testLoadImage() {
    try {
      m.loadImage("test/donut.ppm", "donut-1");
    } catch (Exception e) {
      fail("ppm file could not be loaded");
    }

    try {
      m.loadImage("test/donut.png", "donut-2");
    } catch (Exception e) {
      fail("png file could not be loaded");
    }

    try {
      m.loadImage("test/donut.bmp", "donut-3");
    } catch (Exception e) {
      fail("bmp file could not be loaded");
    }

    try {
      m.loadImage("test/donut.jpg", "donut-4");
    } catch (Exception e) {
      fail("jpg file could not be loaded");
    }

    assertNotNull(m.getImage("donut-1"));
    assertNotNull(m.getImage("donut-2"));
    assertNotNull(m.getImage("donut-3"));
    assertNotNull(m.getImage("donut-4"));
    assertThrows(IllegalArgumentException.class, ()
        -> m.loadImage("invalid/path", "donut"));
    assertThrows(FileNotFoundException.class, ()
        -> m.loadImage("invalid/path.ppm", "donut"));
  }

  @Test
  public void testSaveImage() {
    try {
      m.loadImage("test/donut.ppm", "donut-1");
    } catch (Exception e) {
      fail("Error in testSaveImage because loadImage fails");
    }

    try {
      m.saveImage("test/donut.ppm", "donut-1");
    } catch (Exception e) {
      fail("ppm file could not be saved");
    }

    try {
      m.saveImage("test/donut.png", "donut-1");
    } catch (Exception e) {
      fail("png file could not be saved");
    }

    try {
      m.saveImage("test/donut.bmp", "donut-1");
    } catch (Exception e) {
      fail("bmp file could not be saved");
    }

    try {
      m.saveImage("test/donut.jpg", "donut-1");
    } catch (Exception e) {
      fail("jpg file could not be saved");
    }

    assertThrows(IOException.class, ()
        -> m.saveImage("invalid-path", "donut-1"));
    assertThrows(NoSuchElementException.class, ()
        -> m.saveImage("test/test.ppm", "invalid-donut"));
  }

  private int[][] flipArrayHorizontal(Image img, int[][] arr) {
    int temp;
    int height = img.getHeight();
    int width = img.getWidth();
    int[][] flippedH = new int[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width / 2; j++) {
        temp = arr[i][width - j - 1];
        flippedH[i][width - j - 1] = arr[i][j];
        flippedH[i][j] = temp;
      }
    }

    return flippedH;
  }

  private int[][] flipArrayVertical(Image img, int[][] arr) {
    int temp;
    int height = img.getHeight();
    int width = img.getWidth();
    int[][] flippedV = new int[height][width];
    for (int i = 0; i < height / 2; i++) {
      for (int j = 0; j < width; j++) {
        temp = arr[i][j];
        flippedV[i][j] = arr[height - 1 - i][j];
        flippedV[height - 1 - i][j] = temp;
      }
    }
    return flippedV;
  }


  private int[][] brightenArray(Image img, int[][] arr, int scale) {
    int height = img.getHeight();
    int width = img.getWidth();
    int[][] brightened = new int[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int val = arr[i][j] + scale;
        if (val > 255) {
          val = 255;
        } else if (val < 0) {
          val = 0;
        }
        brightened[i][j] = val;
      }
    }
    return brightened;
  }

  private int[][] valueArray(Image img) {
    int width = img.getWidth();
    int height = img.getHeight();
    int[][] value = new int[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int redValue = img.getRedComponent()[i][j];
        int greenValue = img.getGreenComponent()[i][j];
        int blueValue = img.getBlueComponent()[i][j];
        int max = Math.max(redValue, greenValue);
        max = Math.max(max, blueValue);
        value[i][j] = max;
      }
    }
    return value;
  }

  private int[][] intensityArray(Image img) {
    int width = img.getWidth();
    int height = img.getHeight();
    int[][] intensity = new int[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int redValue = img.getRedComponent()[i][j];
        int greenValue = img.getGreenComponent()[i][j];
        int blueValue = img.getBlueComponent()[i][j];
        int intensityValue = (int) Math.ceil((double) (redValue + greenValue + blueValue) / 3);
        if (intensityValue > 255) {
          intensityValue = 255;
        } else if (intensityValue < 0) {
          intensityValue = 0;
        }
        intensity[i][j] = intensityValue;
      }
    }
    return intensity;
  }

  private int[][] lumaArray(Image img) {
    int width = img.getWidth();
    int height = img.getHeight();
    int[][] luma = new int[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int redValue = img.getRedComponent()[i][j];
        int greenValue = img.getGreenComponent()[i][j];
        int blueValue = img.getBlueComponent()[i][j];
        int lumaValue = (int) Math.ceil(0.2126 * redValue + 0.7152
                * greenValue + 0.0722 * blueValue);
        if (lumaValue > 255) {
          lumaValue = 255;
        } else if (lumaValue < 0) {
          lumaValue = 0;
        }
        luma[i][j] = lumaValue;
      }
    }
    return luma;
  }

  private boolean checkCeilAndFloorVals(int[][] arr) {
    for (int[] ints : arr) {
      for (int j = 0; j < arr[0].length; j++) {
        if (ints[j] > 255 || ints[j] < 0) {
          return false;
        }
      }
    }
    return true;
  }
}