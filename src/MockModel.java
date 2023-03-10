import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MockModel implements Model {

    private StringBuilder log;
    private final int uniqueCode;

    Set<String> commands = new HashSet<>(Arrays.asList("vertical-flip", "horizontal-flip", "greyscale", "brighten",
            "rgb-split", "rgb-combine", "value", "intensity", "luma"));


    public MockModel(StringBuilder log, int uniqueCode) {
        this.log = log;
        this.uniqueCode = uniqueCode;

    }

    @Override
    public Set<String> getCommands(){return commands;}

    @Override
    public int loadPPMImage(String imagePath, String newImageName) {
        log.append("loadPPMImage: imagePath = " + imagePath +
                " newImageName = " + newImageName + "\n");
        return uniqueCode;
    }

    @Override
    public int savePPMImage(String imagePath, String imageName) {
        log.append("savePPMImage: imagePath = " + imagePath +
                " imageName = " + imageName + "\n");
        return uniqueCode;
    }

    @Override
    public int getRedComponent(String currentImageName, String newImageName) {
        log.append("getRedScaleImage: currentImageName = " + currentImageName + " newImageName = "
                + newImageName + "\n");
        return uniqueCode;
    }

    @Override
    public int getGreenComponent(String currentImageName, String newImageName) {
        log.append("getGreenScaleImage: currentImageName = " + currentImageName + " newImageName = "
                + newImageName + "\n");
        return uniqueCode;
    }

    @Override
    public int getBlueComponent(String currentImageName, String newImageName) {
        log.append("getBlueScaleImage: currentImageName = " + currentImageName + " newImageName = "
                + newImageName + "\n");
        return uniqueCode;
    }

    @Override
    public int flipHorizontal(String currentImageName, String newImageName) {
        log.append("flipHorizontal: currentImageName = " + currentImageName + " newImageName = "
                + newImageName + "\n");
        return uniqueCode;
    }

    @Override
    public int flipVertical(String currentImageName, String newImageName) {
        log.append("flipVertical: currentImageName = " + currentImageName + " newImageName = "
                + newImageName + "\n");
        return uniqueCode;
    }

    @Override
    public int getValueImage(String currentImageName, String newImageName) {
        log.append("getValueImage: currentImageName = " + currentImageName + " newImageName = "
                + newImageName + "\n");
        return uniqueCode;
    }

    @Override
    public int getIntensityImage(String currentImageName, String newImageName) {
        log.append("getIntensityImage: currentImageName = " + currentImageName + " newImageName = "
                + newImageName + "\n");
        return uniqueCode;
    }

    @Override
    public int getLumaImage(String currentImageName, String newImageName) {
        log.append("getLumaImage: currentImageName = " + currentImageName + " newImageName = "
                + newImageName + "\n");
        return uniqueCode;
    }

    @Override
    public int brighten(String currentImageName, String newImageName, int scale) {
        log.append("brighten: currentImageName = " + currentImageName + " newImageName = "
                + newImageName + " scale = " + scale + "\n");
        return uniqueCode;
    }

    @Override
    public int rgbSplit(String currentImageName, String redImageName, String greenImageName, String blueImageName) {
        log.append("rgbSplit: currentImageName = " + currentImageName + " redImageName = " + redImageName
                + " greenImageName = " + greenImageName + " blueImageName = " + blueImageName + "\n");
        return uniqueCode;
    }

    @Override
    public int rgbCombine(String newImageName, String rImageName, String gImageName, String bImageName) {
        log.append("rgbCombine: newImageName = " + newImageName + " redImageName = " + rImageName
            + " greenImageName = " + gImageName + " blueImageName = " + bImageName + "\n");
        return uniqueCode;
    }

    @Override
    public int greyscale(String greyScaleComponent, String imageName, String newImageName) {
        log.append("greyscale: greyScaleComponent = " + greyScaleComponent + " ImageName = "
            + imageName + " newImageName = " + newImageName +"\n");
        return uniqueCode;
    }
}
