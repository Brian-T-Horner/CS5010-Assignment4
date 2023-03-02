import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class PPMImageTest {

  int [][] r;
  int [][] g;
  int [][] b;

  Image i1;

  @Before
  public void setUp() {
    r = generateRandomArray(300,300);
    g = generateRandomArray(300,300);
    b = generateRandomArray(300,300);
    i1 = new AbstractPPMImage("image",300,300,r,g,b);
  }

  @Test
  public void testGetRedComponent() {
    Image redscale = i1.getRedscaleImage();
    for(int i=0; i < i1.getWidth();i++) {
      for(int j=0; j < i1.getHeight();j++) {
        assertEquals(redscale.getBlueAtIndex(i,j),redscale.getRedAtIndex(i,j));
        assertEquals(redscale.getGreenAtIndex(i,j),redscale.getRedAtIndex(i,j));
      }
    }
  }

  @Test
  public void testGetGreenComponent() {
    Image greenscale = i1.getRedscaleImage();
    for(int i=0; i < i1.getWidth();i++) {
      for(int j=0; j < i1.getHeight();j++) {
        assertEquals(greenscale.getBlueAtIndex(i,j),greenscale.getGreenAtIndex(i,j));
        assertEquals(greenscale.getRedAtIndex(i,j),greenscale.getGreenAtIndex(i,j));
      }
    }
  }

  @Test
  public void testGetBlueComponent() {
    Image bluescale = i1.getRedscaleImage();
    for(int i=0; i < i1.getWidth();i++) {
      for(int j=0; j < i1.getHeight();j++) {
        assertEquals(bluescale.getGreenAtIndex(i,j),bluescale.getBlueAtIndex(i,j));
        assertEquals(bluescale.getRedAtIndex(i,j),bluescale.getBlueAtIndex(i,j));
      }
    }
  }

  public int [][] generateRandomArray(int width, int height) {
    int [][] randArray = new int[width][height];
    for(int i = 0; i < width; i++) {
      for(int j = 0; j < height; j++) {
        Random rand = new Random();
        randArray[i][j] = rand.nextInt(255 + 1);
      }
    }
    return randArray;
  }
}