import ime.control.Controller;
import ime.control.ImageController;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Class to test the IME.control.Controller in isolation with a MockModel.
 */
public class ModelControllerTest {


  /**
   * Method to test the controller to loadPPMImage method.
   */
  @Test
  public void testLoadPPMImage() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/koala.ppm koala\nquit");
    Controller controllerTest = new ImageController(in, out);
    StringBuilder log = new StringBuilder();
    controllerTest.run(new MockModel(log, 3390));
    assertEquals("loadPPMImage: imagePath = images/koala.ppm "
        + "newImageName = koala uniqueCode = 3390\n", log.toString());
    //TODO test .bmp, .jpg, .png file inputs
  }

  /**
   * Method to test the controller to savePPMImage method.
   */
  @Test
  public void testSavePPMImage() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("save images/koala-brighter.ppm koala-brighter");
    Controller controllerTest = new ImageController(in, out);
    StringBuilder log = new StringBuilder();
    controllerTest.run(new MockModel(log, 4537));
    assertEquals("savePPMImage: imagePath = images/koala-brighter.ppm "
        + "imageName = koala-brighter uniqueCode = 4537\n", log.toString());
    //TODO test .bmp, .jpg, .png file outputs
  }

  /**
   * Method to test the controller to flipHorizontal method.
   */
  @Test
  public void testFlipHorizontal() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("horizontal-flip koala-vertical koala-vertical-horizontal");
    Controller controllerTest = new ImageController(in, out);
    StringBuilder log = new StringBuilder();
    controllerTest.run(new MockModel(log, 7362));
    assertEquals("flipHorizontal: currentImageName = koala-vertical "
        + "newImageName = koala-vertical-horizontal uniqueCode = 7362\n", log.toString());
  }

  /**
   * Method to test the controller to flipVertical method.
   */
  @Test
  public void testFlipVertical() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("vertical-flip koala koala-vertical");
    Controller controllerTest = new ImageController(in, out);
    StringBuilder log = new StringBuilder();
    controllerTest.run(new MockModel(log, 999));
    assertEquals("flipVertical: currentImageName = koala "
        + "newImageName = koala-vertical uniqueCode = 999\n", log.toString());
  }

  /**
   * Method to test the controller to valueImage method.
   */
  @Test
  public void testValueImage() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("value koala koala-value");
    Controller controllerTest = new ImageController(in, out);
    StringBuilder log = new StringBuilder();
    controllerTest.run(new MockModel(log, 1432));
    assertEquals("getValueImage: currentImageName = koala "
        + "newImageName = koala-value uniqueCode = 1432\n", log.toString());
  }

  /**
   * Method to test the controller to intensityImage method.
   */
  @Test
  public void testIntensityImage() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("intensity koala koala-intensity");
    Controller controllerTest = new ImageController(in, out);
    StringBuilder log = new StringBuilder();
    controllerTest.run(new MockModel(log, 33332));
    assertEquals("getIntensityImage: currentImageName = koala "
        + "newImageName = koala-intensity uniqueCode = 33332\n", log.toString());
  }


  /**
   * Method to test the controller to lumaImage method.
   */
  @Test
  public void testLumaImage() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("luma koala koala-luma");
    Controller controllerTest = new ImageController(in, out);
    StringBuilder log = new StringBuilder();
    controllerTest.run(new MockModel(log, 9191));
    assertEquals("getLumaImage: currentImageName = koala "
        + "newImageName = koala-luma uniqueCode = 9191\n", log.toString());
  }

  /**
   * Method to test the controller to brighten method.
   */
  @Test
  public void testBrighten() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("brighten 50 koala koala-brighten");
    Controller controllerTest = new ImageController(in, out);
    StringBuilder log = new StringBuilder();
    controllerTest.run(new MockModel(log, 34342));
    assertEquals("brighten: currentImageName = koala "
        + "newImageName = koala-brighten scale = 50 uniqueCode = 34342\n", log.toString());
  }

  /**
   * Method to test the controller to brighten (darken) method.
   */
  @Test
  public void testDarken() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("brighten -50 koala koala-brighten");
    Controller controllerTest = new ImageController(in, out);
    StringBuilder log = new StringBuilder();
    controllerTest.run(new MockModel(log, 3754));
    assertEquals("brighten: currentImageName = koala "
        + "newImageName = koala-brighten scale = -50 uniqueCode = 3754\n", log.toString());
  }

  /**
   * Method to test the controller to rgbSplit method.
   */
  @Test
  public void testRGBSplit() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("rgb-split koala koala-red koala-green koala-blue");
    Controller controllerTest = new ImageController(in, out);
    StringBuilder log = new StringBuilder();
    controllerTest.run(new MockModel(log, 6767));
    assertEquals("rgbSplit: currentImageName = koala "
        + "redImageName = koala-red greenImageName = koala-green blueImageName "
        + "= koala-blue uniqueCode = 6767\n", log.toString());
  }

  /**
   * Method to test the controller to rgbCombine method.
   */
  @Test
  public void testRGBCombine() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("rgb-combine koala-red-tint koala-red koala-green koala-blue");
    Controller controllerTest = new ImageController(in, out);
    StringBuilder log = new StringBuilder();
    controllerTest.run(new MockModel(log, 42131));
    assertEquals("rgbCombine: newImageName = koala-red-tint "
            + "redImageName = koala-red greenImageName = koala-green blueImageName "
            + "= koala-blue uniqueCode = 42131\n", log.toString());
  }

  /**
   * Method to test the controller to greyScale method.
   */
  @Test
  public void testGreyScale() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("greyscale value-component koala koala-greyscale");
    Controller controllerTest = new ImageController(in, out);
    StringBuilder log = new StringBuilder();
    controllerTest.run(new MockModel(log, 754329));
    assertEquals("greyscale: greyScaleComponent = value-component "
            + "imageName = koala newImageName = koala-greyscale uniqueCode = 754329\n",
        log.toString());
  }

  /**
   * Method to test the controller to dither method.
   */
  @Test
  public void testDither() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("dither koala dither-koala");
    Controller controllerTest = new ImageController(in, out);
    StringBuilder log = new StringBuilder();
    controllerTest.run(new MockModel(log, 645));
    assertEquals("dither: imageName = koala newImageName " +
            "= dither-koala uniqueCode = 645\n", log.toString());

  }

  /**
   * Method to test the controller to sepia method.
   */
  @Test
  public void testSepia() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("sepia koala sepia-koala");
    Controller controllerTest = new ImageController(in, out);
    StringBuilder log = new StringBuilder();
    controllerTest.run(new MockModel(log, 64));
    assertEquals("sepia: imageName = koala newImageName " +
            "= sepia-koala uniqueCode = 64\n", log.toString());

  }


  /**
   * Method to test the controller to blur method.
   */
  @Test
  public void testBlur() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("blur koala blur-koala");
    Controller controllerTest = new ImageController(in, out);
    StringBuilder log = new StringBuilder();
    controllerTest.run(new MockModel(log, 99));
    assertEquals("blur: imageName = koala newImageName " +
            "= blur-koala uniqueCode = 99\n", log.toString());

  }

  /**
   * Method to test the controller to sharpen method.
   */
  @Test
  public void testSharpen() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("sharpen koala sharpen-koala");
    Controller controllerTest = new ImageController(in, out);
    StringBuilder log = new StringBuilder();
    controllerTest.run(new MockModel(log, 346324));
    assertEquals("sharpen: imageName = koala newImageName " +
            "= sharpen-koala uniqueCode = 346324\n", log.toString());

  }

  /**
   * Method to test the controller to matrixGreyscale method.
   */
  @Test
  public void testMatrixGreyscale() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("greyscale koala matrixGreyscale-koala");
    Controller controllerTest = new ImageController(in, out);
    StringBuilder log = new StringBuilder();
    controllerTest.run(new MockModel(log, 3241));
    assertEquals("matrixGreyscale: imageName = koala newImageName " +
            "= matrixGreyscale-koala uniqueCode = 3241\n", log.toString());

  }

}
