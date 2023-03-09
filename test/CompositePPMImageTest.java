import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import static org.junit.Assert.assertEquals;

public class CompositePPMImageTest {

  int[][] r;
  int[][] g;
  int[][] b;

  PPMImage i1;

  @Before
  public void setUp() {
    r = generateRandomArray(300, 300);
    SimplePPMImage rSimple = new SimplePPMImage("rSimple", 300, 300, r);
    g = generateRandomArray(300, 300);
    SimplePPMImage gSimple = new SimplePPMImage("gSimple", 300, 300, g);
    b = generateRandomArray(300, 300);
    SimplePPMImage bSimple = new SimplePPMImage("bSimple", 300, 300, b);
    i1 = new CompositePPMImage("image", 300, 300, rSimple, bSimple, gSimple);
  }

  @Test
  public void testGetRedscaleImage() {
    PPMImage redscale = i1.getRedscaleImage("");
    for (int i = 0; i < i1.getWidth(); i++) {
      for (int j = 0; j < i1.getHeight(); j++) {
        assertEquals(redscale.getIndex(i, j), i1.getRedscaleImage("").getIndex(i, j));
      }
    }


  }

  @Test
  public void testIOExample() throws IOException {
    PPMImage i2 = ImageUtil.readIntoPPMImage("newfile.ppm", "newFile");

//    i2 = i2.getLumaImage("");
//    ImageUtil.writeToPPMFile(i2,"newfile");
    File f = new File("MyFile4.png");
    ImageIO.write(i2.writeBufferedImage(), "PNG", f);
  }


  @Test
  public void testGetGreenscaleImage() {
    PPMImage greenscale = i1.getGreenscaleImage("");
    for (int i = 0; i < i1.getWidth(); i++) {
      for (int j = 0; j < i1.getHeight(); j++) {
        assertEquals(greenscale.getIndex(i, j), i1.getGreenscaleImage("").getIndex(i, j));
      }
    }
  }

  @Test
  public void testGetBluescaleImage() {
    PPMImage bluescale = i1.getBluescaleImage("");
    for (int i = 0; i < i1.getWidth(); i++) {
      for (int j = 0; j < i1.getHeight(); j++) {
        assertEquals(bluescale.getIndex(i, j), i1.getBluescaleImage("").getIndex(i, j));
      }
    }
  }

  public void testBrighten() {
    PPMImage i2 = i1.brighten("",20);

  }

  public int[][] generateRandomArray(int width, int height) {
    int[][] randArray = new int[width][height];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        Random rand = new Random();
        randArray[i][j] = rand.nextInt(255 + 1);
      }
    }
    return randArray;
  }
}