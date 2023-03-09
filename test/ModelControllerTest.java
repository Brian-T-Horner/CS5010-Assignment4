import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.junit.Test;

public class ModelControllerTest {


//  Test
//  public void testGo() throws Exception {
//    StringBuffer out = new StringBuffer();
//    Reader in = new StringReader("+ 3 4 + 8 9 q");
//    CalcController controller6 = new Controller6(in, out);
//    StringBuilder log = new StringBuilder(); //log for mock model
//    controller5.go(new MockModel(log,1234321));
//    assertEquals("Input: 3 4\nInput: 8 9\n", log.toString()); //inputs reached the model correctly
//    assertEquals("1234321\n1234321\n",out.toString()); //output of model transmitted correctly
//  }
  @Test
  public void testReadPPMImage() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("brighten 10 koala koala-brighter");
    ControllerImp controllerTest = new Controller(in, out);
    StringBuilder log = new StringBuilder();


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
