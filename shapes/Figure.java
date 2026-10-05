package shapes;

public abstract class Figure
{

    
    public abstract void moveHorizontal(int distance);
    
    public abstract void moveVertical(int distance);
    
    public abstract void makeVisible();
    
    public abstract void makeInvisible();
    
    public void changeSize(int newHeight, int newWidth){
        return;
    }
    public void changeSize(int newDiameter){
        return;
    }
    
    public abstract void changeColor(String color);

    public abstract boolean isVisible();
    
    public abstract String getColor();

}