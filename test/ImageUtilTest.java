import org.junit.Test;
import java.io.File;
import java.io.IOException;

import IME.ImageUtil;
import IME.model.Image;

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
  public void writeToPPMFile() {
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