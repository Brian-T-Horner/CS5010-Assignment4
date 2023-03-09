import java.io.Reader;
import java.io.StringReader;
import org.junit.Test;

public class ModelControllerTest {

  @Test
  public void testReadPPMImage() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader();
    ControllerImp controllerTest = new Controller(in, out);
    StringBuilder log = new StringBuilder();

    controllerTest.run(new ModelImageUtil(log, 3390));
  }

  @Test
  public void testWritePPMImage() {}

  @Test
  public void testRedScaleImage() {}

  @Test
  public void testGreenScaleImage() {}

  @Test
  public void testBlueScaleImage() {
  }

  @Test
  public void testFlipHorizontal() {}

  @Test
  public void testFlipVertical() {}

  @Test
  public void testValueImage() {}

  @Test
  public void testIntensityImage() {}


  @Test
  public void testLumaImage() {}

  @Test
  public void testBrighten() {}

  @Test
  public void testWriteBufferedImage() {}

}
