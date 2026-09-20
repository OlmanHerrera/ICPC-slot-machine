
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
    private int MAX_SIZE = 3;
    private List<Symbol> symbols = Arrays.asList(new Symbol[MAX_SIZE]);
    private boolean placed;
    private boolean ok; 
    private Random random = new Random();
    private int currentPos;
    
    private Symbol currentSymbol;
    public Wheel()
    {
        this.changeColor("white");
        placed = false;
    
    }
    
    
    public void addSymbol(int pos, String color){
        
        if (!placed || symbolShape(color)  == null){
            setOk(false);
            System.out.println("pendejo");
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

            if (color == "red"){
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
    
    public Symbol spin(){

        int numPos = random.nextInt(10,21);
        int pos = 0; 
        for (int i = 0; i <= numPos; i++){
            pos++;
            if (pos == MAX_SIZE){
                pos -= MAX_SIZE;
            }
            Symbol s = symbols.get(pos);
            if (s != null){
                currentPos = pos;
                symbols.get(currentPos).makeVisible();
                try{
                    Thread.sleep(50);
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }

            }
            symbols.get(currentPos).makeInvisible();
                           
        }
        symbols.get(currentPos).makeVisible();
        return symbols.get(currentPos);
        
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
            setOk(true);
        }
        setOk(false);
    }
    
    public void setOk(boolean state){
        ok = state;
    }
    
    private Symbol symbolShape(String color){
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
    
    public void makeSymbolsVisible(){
        for (Symbol s : symbols){
            if (s != null){
                s.makeVisible();
            }
        }
    
    }
    
    public void setPlaced(boolean moved){
        placed = moved; 
    }
    
    public boolean getPlaced(){
        return placed;
    }
}
