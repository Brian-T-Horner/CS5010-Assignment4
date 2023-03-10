import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ModelControllerTest {


  @Test
  public void testLoadPPMImage() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/koala.ppm koala");
    ControllerImp controllerTest = new Controller(in, out);
    StringBuilder log = new StringBuilder();
    assertEquals(3390, controllerTest.run(new MockModel(log, 3390)));
    assertEquals("loadPPMImage: imagePath = images/koala.ppm " +
            "newImageName = koala\n", log.toString());

  }

  @Test
  public void testSavePPMImage() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("save images/koala-brighter.ppm koala-brighter");
    ControllerImp controllerTest = new Controller(in, out);
    StringBuilder log = new StringBuilder();
    assertEquals(4537,controllerTest.run(new MockModel(log, 4537)));
    assertEquals("savePPMImage: imagePath = images/koala-brighter.ppm " +
            "imageName = koala-brighter\n", log.toString());
  }

  @Test
  public void testFlipHorizontal() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("horizontal-flip koala-vertical koala-vertical-horizontal");
    ControllerImp controllerTest = new Controller(in, out);
    StringBuilder log = new StringBuilder();
    assertEquals(7362,controllerTest.run(new MockModel(log, 7362)));
    assertEquals("flipHorizontal: currentImageName = koala-vertical " +
            "newImageName = koala-vertical-horizontal\n", log.toString());
  }




  @Test
  public void testFlipVertical() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("vertical-flip koala koala-vertical");
    ControllerImp controllerTest = new Controller(in, out);
    StringBuilder log = new StringBuilder();
    assertEquals(999,controllerTest.run(new MockModel(log, 999)));
    assertEquals("flipVertical: currentImageName = koala " +
            "newImageName = koala-vertical\n", log.toString());
  }

  @Test
  public void testValueImage() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("value koala koala-value");
    ControllerImp controllerTest = new Controller(in, out);
    StringBuilder log = new StringBuilder();
    assertEquals(1432, controllerTest.run(new MockModel(log, 1432)));
    assertEquals("getValueImage: currentImageName = koala " +
            "newImageName = koala-value\n", log.toString());
  }

  @Test
  public void testIntensityImage() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("intensity koala koala-intensity");
    ControllerImp controllerTest = new Controller(in, out);
    StringBuilder log = new StringBuilder();
    assertEquals(33332, controllerTest.run(new MockModel(log, 33332)));
    assertEquals("getIntensityImage: currentImageName = koala " +
            "newImageName = koala-intensity\n", log.toString());
  }


  @Test
  public void testLumaImage() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("luma koala koala-luma");
    ControllerImp controllerTest = new Controller(in, out);
    StringBuilder log = new StringBuilder();
    assertEquals(9191, controllerTest.run(new MockModel(log, 9191)));
    assertEquals("getLumaImage: currentImageName = koala " +
            "newImageName = koala-luma\n", log.toString());
  }

  @Test
  public void testBrighten() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("brighten 50 koala koala-brighten");
    ControllerImp controllerTest = new Controller(in, out);
    StringBuilder log = new StringBuilder();
    assertEquals(34342, controllerTest.run(new MockModel(log, 34342)));
    assertEquals("brighten: currentImageName = koala " +
            "newImageName = koala-brighten scale = 50\n", log.toString());
  }

  @Test
  public void testRGBSplit() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("rgb-split koala koala-red koala-green koala-blue");
    ControllerImp controllerTest = new Controller(in, out);
    StringBuilder log = new StringBuilder();
    assertEquals(6767, controllerTest.run(new MockModel(log, 6767)));
    assertEquals("rgbSplit: currentImageName = koala " +
            "redImageName = koala-red greenImageName = koala-green blueImageName = koala-blue\n", log.toString());
  }

  @Test
  public void testRGBCombine() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("rgb-combine koala-red-tint koala-red koala-green koala-blue");
    ControllerImp controllerTest = new Controller(in, out);
    StringBuilder log = new StringBuilder();
    assertEquals(42131, controllerTest.run(new MockModel(log, 42131)));
    assertEquals("rgbCombine: newImageName = koala-red-tint " +
        "redImageName = koala-red greenImageName = koala-green blueImageName = koala-blue\n",
        log.toString());
  }

  @Test
  public void testGreyScale() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("greyscale value-component koala koala-greyscale");
    ControllerImp controllerTest = new Controller(in, out);
    StringBuilder log = new StringBuilder();
    assertEquals(754329, controllerTest.run(new MockModel(log, 754329)));
    assertEquals("greyscale: greyScaleComponent = value-component " +
            "imageName = koala newImageName = koala-greyscale\n",
        log.toString());
  }

}
