import java.util.Arrays;

public class SimplePPMImage extends AbstractPPMImage{


    private final int[][] component;


    public SimplePPMImage(String name, int width, int height, int[][] component){
        super(name, width, height);
        this.component = component;
    }


    public int getIndex(int i, int j){
        return component[i][j];
    }

    public void setIndex(int i, int j, int value){
        this.component[i][j] = value;
    }

    @Override
    public boolean isSimplePPMImage(){
        return true;
    }

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }

        if (!(o instanceof SimplePPMImage)) {
            return false;
        }

        SimplePPMImage c = (SimplePPMImage) o;

        if(c.getHeight() == this.getHeight() && c.getWidth() == this.getWidth() &&
                Arrays.equals(c.component, this.component)){
            return true;
        }

        return false;
    }


}
