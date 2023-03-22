import org.junit.Test;
import java.io.File;
import java.io.IOException;

import ime.ImageUtil;
import ime.model.Image;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

/**
 * Test class for ImageUtil.
 */
public class ImageUtilTest {

  @Test
  public void testReadIntoPPMImage() {
    assertNotNull(ImageUtil.loadPPMImage("res/donut.ppm"));
    assertNull(ImageUtil.loadPPMImage("invalid-path"));
  }


  @Test
  public void testLoadImage() {
    // TODO test that works with all image types
    assertNotNull(ImageUtil.loadImage("images/koala-vertical.png"));
    // TODO add .bmp and .jpg
    assertNull(ImageUtil.loadImage("invalid-path"));
  }


  @Test
  public void testwriteBufferedImage() {
    // TODO make sure image is the same
    // no other checks needed
  }


  @Test
  public void testReadBufferedImage() {
    //TODO
    // make sure image is the same
    // no other checks needed
  }


  @Test
  public void testSaveImage() {
    //TODO test that save works with all image types
    // invalid path returns null
    // test that file actually is created
  }





  @Test
  public void testWriteToPPMFile() {
    Image i = null;

    try {
      i = ImageUtil.loadPPMImage("res/donut.ppm");
    } catch (Exception ignored) {
    }
    assertNotNull(i);
    try {
      ImageUtil.writeToPPMFile(i, "test.ppm");
    } catch (Exception ignored) {
    }
    File file = new File("test.ppm");
    file.deleteOnExit();
    assertTrue(file.exists());
    Image finalI = i;
    assertThrows(IOException.class, () -> ImageUtil.writeToPPMFile(finalI, "test"));
  }
}