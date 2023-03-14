import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class PPMImageTest {

  //test isCompositeImage on composite and simple
  // test getters (basic)
  // test flip: one with even, one with odd (horizontal and vertical)
  // test luma/value/intensity basic, and test that they clamp to upper and lower bounds
  // test buffered image ??? somehow

  Image i1;

  Image i2;

  int[][] ri1;
  int[][] gi1;
  int[][] bi1;

  int[][] ri1Value;

  int[][] ri1Intensity;
  int[][] ri1Luma;
  int[][] ri2;
  int[][] gi2;
  int[][] bi2;

  private int[][] generateRandomArray(int width, int height) {
    int[][] randArray = new int[width][height];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        Random rand = new Random();
        randArray[i][j] = rand.nextInt(255 + 1);
      }
    }
    return randArray;
  }

  @Before
  public void setUp() {
    int height = 300;
    int width = 300;
    ri1 = generateRandomArray(width, height);
    gi1 = generateRandomArray(width, height);
    bi1 = generateRandomArray(width, height);


//    i2 = new PPMImage(width, height, ri1, ri1, ri1);
//
//    i1 = new PPMImage(width, height, ri1, bi1, gi1);
  }


  @Test
  public void testFlipHorizontal() {
  }

  @Test
  public void testFlipVertical() {
  }

  @Test
  public void testGetValueImage() {
  }

  @Test
  public void testGetIntensityImage() {
  }

  @Test
  public void testGetLumaImage() {
  }

  @Test
  public void testBrighten() {

  }
}