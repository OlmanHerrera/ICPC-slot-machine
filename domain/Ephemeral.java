package domain;
import shapes.*;
public class Ephemeral extends Symbol
{
    public Ephemeral(Figure shape){
        super(shape);
        
    }
    
    @Override
    public void selectedMode(){
        if (shape instanceof Rectangle){
            height = height -5;
            width = width -5;
            shape.changeSize(height, width);
            shape.moveHorizontal(2);
            shape.moveVertical(2);
            shape.makeVisible();
        }
        else if (shape instanceof Triangle){
            height = height -5;
            width = width -5;
            shape.changeSize(height, width);
            shape.moveHorizontal(-1);
            shape.moveVertical(2);
            shape.makeVisible();
        }
        
        else if(shape instanceof Circle){
            diameter = diameter - 5;
            shape.changeSize(diameter);
            shape.moveHorizontal(2);
            shape.moveVertical(2);
            shape.makeVisible();
            
        }
    }


 
}