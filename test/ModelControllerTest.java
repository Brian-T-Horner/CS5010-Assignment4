import java.io.Reader;
import java.io.StringReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Class to test the Controller in isolation with a MockModel.
 */
public class ModelControllerTest {


  /**
   * Method to test the controller to loadPPMImage method.
   */
  @Test
  public void testLoadPPMImage() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/koala.ppm koala");
    ControllerImp controllerTest = new Controller(in, out);
    StringBuilder log = new StringBuilder();
    controllerTest.run(new MockModel(log, 3390));
    assertEquals("loadPPMImage: imagePath = images/koala.ppm " +
            "newImageName = koala uniqueCode = 3390\n", log.toString());

  }

  /**
   * Method to test the controller to savePPMImage method.
   */
  @Test
  public void testSavePPMImage() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("save images/koala-brighter.ppm koala-brighter");
    ControllerImp controllerTest = new Controller(in, out);
    StringBuilder log = new StringBuilder();
    controllerTest.run(new MockModel(log, 4537));
    assertEquals("savePPMImage: imagePath = images/koala-brighter.ppm " +
            "imageName = koala-brighter uniqueCode = 4537\n", log.toString());
  }

  /**
   * Method to test the controller to flipHorizontal method.
   */
  @Test
  public void testFlipHorizontal() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("horizontal-flip koala-vertical koala-vertical-horizontal");
    ControllerImp controllerTest = new Controller(in, out);
    StringBuilder log = new StringBuilder();
    controllerTest.run(new MockModel(log, 7362));
    assertEquals("flipHorizontal: currentImageName = koala-vertical " +
            "newImageName = koala-vertical-horizontal uniqueCode = 7362\n", log.toString());
  }

  /**
   * Method to test the controller to flipVertical method.
   */
  @Test
  public void testFlipVertical() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("vertical-flip koala koala-vertical");
    ControllerImp controllerTest = new Controller(in, out);
    StringBuilder log = new StringBuilder();
    controllerTest.run(new MockModel(log, 999));
    assertEquals("flipVertical: currentImageName = koala " +
            "newImageName = koala-vertical uniqueCode = 999\n", log.toString());
  }

  /**
   * Method to test the controller to valueImage method.
   */
  @Test
  public void testValueImage() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("value koala koala-value");
    ControllerImp controllerTest = new Controller(in, out);
    StringBuilder log = new StringBuilder();
    controllerTest.run(new MockModel(log, 1432));
    assertEquals("getValueImage: currentImageName = koala " +
            "newImageName = koala-value uniqueCode = 1432\n", log.toString());
  }

  /**
   * Method to test the controller to intensityImage method.
   */
  @Test
  public void testIntensityImage() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("intensity koala koala-intensity");
    ControllerImp controllerTest = new Controller(in, out);
    StringBuilder log = new StringBuilder();
    controllerTest.run(new MockModel(log, 33332));
    assertEquals("getIntensityImage: currentImageName = koala " +
            "newImageName = koala-intensity uniqueCode = 33332\n", log.toString());
  }


  /**
   * Method to test the controller to lumaImage method.
   */
  @Test
  public void testLumaImage() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("luma koala koala-luma");
    ControllerImp controllerTest = new Controller(in, out);
    StringBuilder log = new StringBuilder();
    controllerTest.run(new MockModel(log, 9191));
    assertEquals("getLumaImage: currentImageName = koala " +
            "newImageName = koala-luma uniqueCode = 9191\n", log.toString());
  }

  /**
   * Method to test the controller to brighten method.
   */
  @Test
  public void testBrighten() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("brighten 50 koala koala-brighten");
    ControllerImp controllerTest = new Controller(in, out);
    StringBuilder log = new StringBuilder();
    controllerTest.run(new MockModel(log, 34342));
    assertEquals("brighten: currentImageName = koala " +
            "newImageName = koala-brighten scale = 50 uniqueCode = 34342\n", log.toString());
  }

  /**
   * Method to test the controller to brighten (darken) method.
   */
  @Test
  public void testDarken() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("brighten -50 koala koala-brighten");
    ControllerImp controllerTest = new Controller(in, out);
    StringBuilder log = new StringBuilder();
    controllerTest.run(new MockModel(log, 3754));
    assertEquals("brighten: currentImageName = koala " +
        "newImageName = koala-brighten scale = -50 uniqueCode = 3754\n", log.toString());
  }

  /**
   * Method to test the controller to rgbSplit method.
   */
  @Test
  public void testRGBSplit() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("rgb-split koala koala-red koala-green koala-blue");
    ControllerImp controllerTest = new Controller(in, out);
    StringBuilder log = new StringBuilder();
    controllerTest.run(new MockModel(log, 6767));
    assertEquals("rgbSplit: currentImageName = koala " +
            "redImageName = koala-red greenImageName = koala-green blueImageName = koala-blue uniqueCode = 6767\n", log.toString());
  }

  /**
   * Method to test the controller to rgbCombine method.
   */
  @Test
  public void testRGBCombine() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("rgb-combine koala-red-tint koala-red koala-green koala-blue");
    ControllerImp controllerTest = new Controller(in, out);
    StringBuilder log = new StringBuilder();
    controllerTest.run(new MockModel(log, 42131));
    assertEquals("rgbCombine: newImageName = koala-red-tint " +
        "redImageName = koala-red greenImageName = koala-green blueImageName = koala-blue uniqueCode = 42131\n",
        log.toString());
  }

  /**
   * Method to test the controller to greyScale method.
   */
  @Test
  public void testGreyScale() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("greyscale value-component koala koala-greyscale");
    ControllerImp controllerTest = new Controller(in, out);
    StringBuilder log = new StringBuilder();
    controllerTest.run(new MockModel(log, 754329));
    assertEquals("greyscale: greyScaleComponent = value-component " +
            "imageName = koala newImageName = koala-greyscale uniqueCode = 754329\n",
        log.toString());
  }

}
