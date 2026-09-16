
import java.util.Arrays;
import java.util.List;


/**
 * Write a description of class Wheel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wheel extends Rectangle
{
    private int MAX_SIZE = 3;
    private List<Figure> symbols = Arrays.asList(new Figure[MAX_SIZE]);
    private boolean placed;
    private boolean ok; 
    public Wheel()
    {
        this.changeColor("white");
        placed = false;
    
    }
    
    
    public void addSymbol(int pos, String color){
        
        if (!placed){
            setOk(false);
            return;
        }
        if (1<=pos && pos <= MAX_SIZE){
            symbols.set(pos-1, symbolShape(color));
            setOk(true);
        }
        setOk(false);
    }

    
    public void delSymbol(int pos){
        if ( 1 <= pos && pos <= MAX_SIZE){
            symbols.set(pos-1, null);
            setOk(true);
        }
        setOk(false);
    }
    
    public void setOk(boolean state){
        ok = state;
    }
    
    public Figure symbolShape(String color){
        if (color == "red"){
            Rectangle shape = new Rectangle();
            return shape;
        }
        
        if (color == "green"){
            Circle shape = new Circle();
            return shape;
        }
        if (color == "yellow"){
            Triangle shape = new Triangle();
            return shape;
        }
        
        else{
            return null;
        }
    }
    public void setPlaced(boolean moved){
        placed = moved; 
    }
    
    public boolean getPlaced(){
        return placed;
    }
}
