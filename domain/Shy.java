package domain;
import shapes.*;

public class Shy extends Symbol
{
    
    public Shy(Figure shape){
        super(shape);
        
    }
    @Override
    public void selectedMode(){
        shape.makeInvisible();
    }



 
}