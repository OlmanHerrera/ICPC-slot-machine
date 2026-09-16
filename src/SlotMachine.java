import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


/**
 * Write a description of class SlotMachine here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SlotMachine {
    private static int MAX_SIZE = 10;
    private List<Wheel> wheels = Arrays.asList(new Wheel[MAX_SIZE]);
    private ArrayList<Symbol> sequence;
    private Rectangle shape = new Rectangle();
    private int shapeWidth = 20;
    private int shapeHeigth = 160;
    private boolean ok;
    private int currentIndex = 0;
    private Rectangle base = new Rectangle();
    private int baseWidth = 60;
    private int baseHeigth = 10;
    
    public SlotMachine(){
        updateShape();
    }
    
    
    public void updateShape(){
        shape.changeSize(200,20);
        shape.moveVertical(-40);
        shape.changeColor("blue");
        base.moveHorizontal(10);
        base.moveVertical(120);
        base.changeSize(10,60);
        base.changeColor("black");
    }
    
    public void addSymbol(int pos, String color){
        for (int i = 0; i <currentIndex; i++){
            Wheel w = wheels.get(i);
            w.addSymbol(pos, color);
        }
    }
    
    public void delSymbol(int pos){
        for (int i = 0; i < currentIndex; i++){
            Wheel w = wheels.get(i);
            w.delSymbol(pos);
        }
    }
    
    public boolean getOk(){
        return ok;
    }
    public void addWheel(int pos){
        if (pos < 1 || wheels.get(pos-1) != null){
            setOk(false);
            return;
        
        }
        
        if (pos >= 2){
            if (wheels.get(pos-2) == null){
                setOk(false);
                return;
            }
        }

        if (1<= pos && pos <= wheels.size()){
            currentIndex++;
            wheels.set(pos-1, new Wheel());
            reshape();
            setOk(true);
            return; 
        }
        setOk(false);
    
    }
    
    public void deleteWheel(int pos){
        if (wheels.get(pos-1) == null){
            setOk(false);
            return;
        }
        Wheel temp;
        wheels.get(pos-1).makeInvisible();
        wheels.set(pos-1,null);
        for (int i = pos; i < currentIndex;i++ ){
            temp = wheels.get(i);
            wheels.set(i, null);
            wheels.set(i-1, temp);
            temp.moveHorizontal(-60);
        }
        currentIndex--;
        shape.changeSize(shapeHeigth,shapeWidth+60*(currentIndex));
        base.changeSize(baseHeigth,60 + 60*(currentIndex-1));
        makeVisible();


    }
    public void reshape(){
        int length = wheels.size();
        int currentWidth = 40 * currentIndex;
        shape.changeSize(shapeHeigth, shapeWidth + 60*currentIndex);
        base.changeSize(20, 60 + 60*(currentIndex-1));
        for (int i = 0; i <currentIndex; i++){
            Wheel w = wheels.get(i);
            if (w == null){
                return;
            
            }
            boolean placed = w.getPlaced();
            if (!placed){
                w.moveHorizontal(i*40+20*(i+1));
                w.setPlaced(true);
            }
        }
        makeVisible();

    }
    
    public void makeInvisible(){
        shape.makeInvisible();
        base.makeInvisible();
        for (int i = 0; i < currentIndex; i++){
            Wheel w = wheels.get(i);
            w.makeInvisible();
        }
        setOk(true);
    }
    
    public void makeVisible(){
        shape.makeVisible();
        base.makeVisible();
        for (int i = 0; i < currentIndex; i++){
            Wheel w = wheels.get(i);
            w.makeVisible();
        }
        setOk(true);
    }
    public void setOk(boolean state){
        ok = state; 
    }
}
