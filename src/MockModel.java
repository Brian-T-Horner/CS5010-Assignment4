import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MockModel implements Model {

    private StringBuilder log;
    private final int uniqueCode;

    Set<String> commands = new HashSet<>(Arrays.asList("vertical-flip", "horizontal-flip", "greyscale", "brighten",
            "rgb-split", "rgb-combine", "value", "intensity", "luma", "load", "save", "darken"));


    public MockModel(StringBuilder newLog, int newUniqueCode) {
        this.log = newLog;
        this.uniqueCode = newUniqueCode;

    }

    @Override
    public Set<String> getCommands(){return commands;}

    @Override
    public int loadPPMImage(String imagePath, String newImageName) {
        log.append("loadPPMImage: imagePath = ");
        log.append(imagePath);
        log.append(" newImageName = ");
        log.append(newImageName);
        log.append("\n");
        return uniqueCode;
    }

    @Override
    public int savePPMImage(String imagePath, String imageName) {
        log.append("savePPMImage: imagePath = ");
        log.append(imagePath);
        log.append(" imageName = ");
        log.append(imageName);
        log.append("\n");
        return uniqueCode;
    }

    @Override
    public int getRedComponent(String currentImageName, String newImageName) {
        log.append("getRedScaleImage: currentImageName = ");
        log.append(currentImageName);
        log.append(" newImageName = ");
        log.append(newImageName);
        log.append("\n");
        return uniqueCode;
    }

    @Override
    public int getGreenComponent(String currentImageName, String newImageName) {
        log.append("getGreenScaleImage: currentImageName = ");
        log.append(currentImageName);
        log.append(" newImageName = ");
        log.append(newImageName);
        log.append("\n");
        return uniqueCode;
    }

    @Override
    public int getBlueComponent(String currentImageName, String newImageName) {
        log.append("getBlueScaleImage: currentImageName = ");
        log.append(currentImageName);
        log.append(" newImageName = ");
        log.append(newImageName);
        log.append("\n");
        return uniqueCode;
    }

    @Override
    public int flipHorizontal(String currentImageName, String newImageName) {
        log.append("flipHorizontal: currentImageName = ");
        log.append(currentImageName);
        log.append(" newImageName = ");
        log.append(newImageName);
        log.append("\n");
        return uniqueCode;
    }

    @Override
    public int flipVertical(String currentImageName, String newImageName) {
        log.append("flipVertical: currentImageName = ");
        log.append(currentImageName);
        log.append(" newImageName = ");
        log.append(newImageName);
        log.append("\n");
        return uniqueCode;
    }

    @Override
    public int getValueImage(String currentImageName, String newImageName) {
        log.append("getValueImage: currentImageName = ");
        log.append(currentImageName);
        log.append(" newImageName = ");
        log.append(newImageName);
        log.append("\n");
        return uniqueCode;
    }

    @Override
    public int getIntensityImage(String currentImageName, String newImageName) {
        log.append("getIntensityImage: currentImageName = ");
        log.append(currentImageName);
        log.append(" newImageName = ");
        log.append(newImageName);
        log.append("\n");
        return uniqueCode;
    }

    @Override
    public int getLumaImage(String currentImageName, String newImageName) {
        log.append("getLumaImage: currentImageName = ");
        log.append(currentImageName);
        log.append(" newImageName = ");
        log.append(newImageName);
        log.append("\n");
        return uniqueCode;
    }

    @Override
    public int brighten(String currentImageName, String newImageName, int scale) {
        log.append("brighten: currentImageName = ");
        log.append(currentImageName);
        log.append(" newImageName = ");
        log.append(newImageName);
        log.append(" scale = ");
        log.append(scale);
        log.append("\n");
        return uniqueCode;
    }

//    @Override
//    public int darken(String currentImageName, String newImageName, int scale) {
//        log.append("darken: currentImageName = ");
//        log.append(currentImageName);
//        log.append(" newImageName = ");
//        log.append(newImageName);
//        log.append(" scale = ");
//        log.append(scale);
//        log.append("\n");
//        return uniqueCode;
//    }

    @Override
    public int rgbSplit(String currentImageName, String redImageName, String greenImageName, String blueImageName) {
        log.append("rgbSplit: currentImageName = ");
        log.append(currentImageName);
        log.append(" redImageName = ");
        log.append(redImageName);
        log.append(" greenImageName = ");
        log.append(greenImageName);
        log.append(" blueImageName = ");
        log.append(blueImageName);
        log.append("\n");
        return uniqueCode;
    }

    @Override
    public int rgbCombine(String newImageName, String rImageName, String gImageName, String bImageName) {
        log.append("rgbCombine: newImageName = ");
        log.append(newImageName);
        log.append(" redImageName = ");
        log.append(rImageName);
        log.append(" greenImageName = ");
        log.append(gImageName);
        log.append(" blueImageName = ");
        log.append(bImageName);
        log.append("\n");
        return uniqueCode;
    }

    @Override
    public int greyscale(String greyScaleComponent, String imageName, String newImageName) {
        log.append("greyscale: greyScaleComponent = ");
        log.append(greyScaleComponent);
        log.append(" imageName = ");
        log.append(imageName);
        log.append(" newImageName = ");
        log.append(newImageName);
        log.append("\n");
        return uniqueCode;
    }
}
