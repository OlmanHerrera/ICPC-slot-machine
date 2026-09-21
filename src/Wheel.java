
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.lang.Thread;


/**
 * Write a description of class Wheel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wheel extends Rectangle
{
    private int MAX_SIZE = 6;
    private List<Symbol> symbols = Arrays.asList(new Symbol[MAX_SIZE]);
    private boolean placed;
    private boolean ok; 
    private Random random = new Random();
    private int currentPos = -1;
    private boolean locked = false;
    private Symbol currentSymbol;
    public Wheel()
    {
        this.changeColor("white");
        placed = false;
    
    }
    
    
    public void addSymbol(int pos, String color){
        
        if (!placed || symbolShape(color)  == null){
            setOk(false);
            return;
        }
        currentPos = pos-1;
        int[] position = this.getPosition();
        if (1<=pos && pos <= MAX_SIZE){
            Symbol s = symbolShape(color);
            if (currentSymbol != null){
                currentSymbol.makeInvisible();
            }
            currentSymbol = s;

            if (color == "red" || color == "green" || color == "yellow"){
                symbols.set(pos-1,s);
                s.updateSize();
                s.moveHorizontal(position[0]-20);
                s.moveVertical(30);

                setOk(true);
                return;
            }
            s.moveHorizontal(position[0]);
            s.moveVertical(30);
 
            symbols.set(pos-1, s);
            setOk(true);
        }
        setOk(false);
    }
    
    
    public Symbol spinSteps(int steps){
        if (currentPos == -1){
            setOk(false);
            return null;
        }
        Symbol s = symbols.get(currentPos);
        s.makeInvisible();
        int pos = currentPos; 
        for (int i = 0; i < steps; i++){
            pos++;
            if (pos == MAX_SIZE){
                pos -= MAX_SIZE;
            }
            s = symbols.get(pos);
            while (s == null){
                pos++;
                if (pos == MAX_SIZE){
                    pos -= MAX_SIZE;
                }
                s = symbols.get(pos);
            }
            currentPos = pos;
            symbols.get(currentPos).makeVisible();
            try{
                Thread.sleep(500);
            }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
            } 
            symbols.get(currentPos).makeInvisible();
        }
        symbols.get(currentPos).makeVisible();
        return symbols.get(currentPos);
    }
    public Symbol spin(){
        if (currentPos == -1){
            setOk(false);
            return null;
        }
        Symbol s = symbols.get(currentPos);
        s.makeInvisible();
        int numPos = random.nextInt(10,21);
        int pos = currentPos; 
        for (int i = 0; i < numPos; i++){
            
            pos++;
            if (pos == MAX_SIZE){
                pos -= MAX_SIZE;
            }
            s = symbols.get(pos);
            while (s == null){
                pos++;
                if (pos == MAX_SIZE){
                    pos -= MAX_SIZE;
                }
                s = symbols.get(pos);
            }
            currentPos = pos;
            symbols.get(currentPos).makeVisible();
            try{
                Thread.sleep(250);
            }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
            } 
            symbols.get(currentPos).makeInvisible();
        }
        symbols.get(currentPos).makeVisible();
        return symbols.get(currentPos);

        
    }
    public void adjustSymbols(int distance){
        int[] position = this.getPosition();
        for (Symbol s : symbols){
            if (s != null){
                s.makeInvisible();
                s.moveHorizontal(distance);
            }
        }
    }
    
    public void moveSymbolsleft(){
        for (Symbol s:symbols){
            if (s != null){
                if (s.isVisible()){
                    s.makeInvisible();
                }
                s.moveHorizontal(-60);
            }
        }
    }
    
    public Symbol getShapeCurrentPos(){
        return symbols.get(currentPos);
    }
    public void clear(){
        for (Symbol s: symbols){
            if (s != null){
                s.makeInvisible();
            }
        }
    }
    public void delSymbol(int pos){
        if ( 1 <= pos && pos <= MAX_SIZE){
            symbols.get(pos-1).makeInvisible();
            symbols.set(pos-1, null);
            if (currentPos == pos-1){
                for (int i = 0; i < MAX_SIZE; i++){
                    if (symbols.get(i) != null){
                        currentPos = i;
                        break;
                    }
                }
                
            }
            setOk(true);
        }
        setOk(false);
    }
    
    public void setOk(boolean state){
        ok = state;
    }
    
    private Symbol symbolShape(String color){
        if (color == "red" || color == "green" || color == "yellow"){
            Rectangle shape = new Rectangle();
            shape.changeColor(color);
            return shape;
        }
        
        if (color == "blue" || color == "magenta"){
            Circle shape = new Circle();
            shape.changeColor(color);
            return shape;
        }
        if (color == "black"){
            Triangle shape = new Triangle();
            shape.changeColor(color);
            return shape;
        }
        
        else{
            return null;
        }
    }
    
    public void makeSymbolsVisible(){
        for (Symbol s : symbols){
            if (s != null){
                s.makeVisible();
            }
        }
    
    }
    
    public void setLock(boolean state){
        locked = state;
    }
    
    public boolean isLocked(){
        return locked;
    }
    
    public void setPlaced(boolean moved){
        placed = moved; 
    }
    
    public boolean getPlaced(){
        return placed;
    }
}
