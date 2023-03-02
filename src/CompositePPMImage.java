import java.awt.image.BufferedImage;

public class CompositePPMImage extends AbstractPPMImage{

    private final PPMImage redComponent;

    private final PPMImage greenComponent;

    private final PPMImage blueComponent;


    public CompositePPMImage(String name, int width, int height, PPMImage red,
                             PPMImage blue, PPMImage green){
        super(name, width, height);
        this.redComponent = red;
        this.greenComponent = green;
        this.blueComponent = blue;
    }


    @Override
    public boolean isCompositePPMImage(){
        return true;
    }

    @Override
    public PPMImage getRedscaleImage() {
        return this.redComponent;
    }

    @Override
    public PPMImage getGreenscaleImage(){
      return this.greenComponent;
    }

    @Override
    public PPMImage getBluescaleImage() {
        return this.blueComponent;
    }

    @Override
    public PPMImage flipHorizontal() {
        return null;
    }

    @Override
    public PPMImage flipVertical() {
        return null;
    }

    @Override
    public PPMImage getValueImage() {
        return null;
    }

    @Override
    public PPMImage getIntensityImage() {
        return null;
    }

    @Override
    public PPMImage getLumaImage() {
        return null;
    }

    @Override
    public PPMImage greyscale() {
        return null;
    }

    @Override
    public PPMImage brighten() {
        return null;
    }

    @Override
    public BufferedImage writePPM() {
        return null;
    }

    @Override
    public int getIndex(int i, int j) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("composite image has no individual indices");
    }


    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }

        if(!(o instanceof CompositePPMImage)){
            return false;
        }

        CompositePPMImage c = (CompositePPMImage) o;

        if(c.getWidth() == this.getWidth() && c.getHeight() == this.getHeight() &&
                c.blueComponent.equals(blueComponent) && c.greenComponent.equals(greenComponent) &&
                c.redComponent.equals(redComponent)){
            return true;
        }

        return false;

    }







}

