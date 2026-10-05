package domain;
import shapes.*;

 
public class Symbol
{
    protected int width = 20;
    protected int height = 20;
    protected int diameter = 20;
    protected Figure shape;
    
    public Symbol(Figure shape){
        this.shape = shape;
        shape.changeSize(height,width);
    }
    public Figure getShape(){
        return shape;
    }
    public void changeSize(int newHeight, int newWidth){
        if (shape instanceof Rectangle || shape instanceof Triangle) {
            shape.changeSize(newHeight, newWidth);
            width = newWidth;
            height = newHeight;
        } 

    }
    
    public void changeSize( int newDiameter){
        if (shape instanceof Circle){
            shape.changeSize(newDiameter);
            diameter = newDiameter;
        }

    }
    
    public boolean isVisible(){
        return shape.isVisible();
    }
    
    public void changeColor(String color){
        shape.changeColor(color);
    }
    
    public void moveHorizontal(int distance){
        shape.moveHorizontal(distance);
        
    }
    
    public void selectedMode(){
        shape.makeVisible();
    }
    public void moveVertical(int distance){
        shape.moveVertical(distance);
        
    }
    
    public void makeVisible(){
        shape.makeVisible();
    }
    
    public void makeInvisible(){
        shape.makeInvisible();
    }
    public String getColor(){
        return shape.getColor();
    }
    
}
