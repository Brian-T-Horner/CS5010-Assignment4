
public interface PPMImageModelInter {

    int getRedscaleImage(String name);

    int getGreenscaleImage(String name);

    int getBluescaleImage(String name);


    int flipHorizontal(String name);

    int flipVertical(String name);

    int getValueImage(String name);

    int getIntensityImage(String name);

    int getLumaImage(String name);

    int brighten(String name, int scale);


    int writeBufferedImage();


}
