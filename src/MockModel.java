public class MockModel implements Model {

    private StringBuilder log;
    private final int uniqueCode;


    public MockModel(StringBuilder log, int uniqueCode) {
        this.log = log;
        this.uniqueCode = uniqueCode;

    }
    @Override
    public int getRedscaleImage(String currentImageName, String newImageName) {
        log.append("getRedScaleImage: currentImageName = " + currentImageName + " newImageName = "
                + newImageName + "\n");
        return uniqueCode;
    }

    @Override
    public int getGreenscaleImage(String currentImageName, String newImageName) {
        log.append("getGreenScaleImage: currentImageName = " + currentImageName + " newImageName = "
                + newImageName + "\n");
        return uniqueCode;
    }

    @Override
    public int getBluescaleImage(String currentImageName, String newImageName) {
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
}
