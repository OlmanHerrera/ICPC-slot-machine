import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

/**
 * Write a description of class SlotMachine here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SlotMachine {
    private static int MAX_SIZE = 10;
    private List<Wheel> wheels = Arrays.asList(new Wheel[MAX_SIZE]);
    private List<Symbol> sequence = Arrays.asList(new Symbol[MAX_SIZE]);
    private Rectangle shape = new Rectangle();
    private int shapeWidth = 20;
    private int shapeHeigth = 160;
    private boolean ok;
    private int currentIndex = 0;
    private Rectangle base = new Rectangle();
    private int baseWidth = 60;
    private int baseHeigth = 10;
    private HashMap<Integer,String> configuration = new HashMap<>();

    
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
        base.changeColor("blue");
    }
    
    public void addSymbol(int pos, String color){
        for (int i = 0; i <currentIndex; i++){
            Wheel w = wheels.get(i);
            w.addSymbol(pos, color);
            sequence.set(i, w.getShapeCurrentPos());
            configuration.put(pos, color);
        }
    }
    
    public void delSymbol(int pos){
        for (int i = 0; i < currentIndex; i++){
            Wheel w = wheels.get(i);
            w.delSymbol(pos);
            configuration.remove(pos);
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
            Wheel w = wheels.get(pos-1);
            for (Integer valor: configuration.keySet()){
                w.addSymbol(valor,configuration.get(valor));
            }
            sequence.set(pos-1, w.getShapeCurrentPos());
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
        wheels.get(pos-1).clear();
        wheels.set(pos-1,null);
        for (int i = pos; i < currentIndex;i++ ){
            temp = wheels.get(i);
            wheels.set(i, null);
            wheels.set(i-1, temp);
            temp.moveHorizontal(-60);
            temp.moveSymbolsleft();
            if (temp.getShapeCurrentPos() != null){
                sequence.set(i-1,temp.getShapeCurrentPos());
            }
        }
        currentIndex--;
        
        if (currentIndex > 0){
            shape.changeSize(shapeHeigth,shapeWidth+60*(currentIndex));
            base.changeSize(baseHeigth,60 + 60*(currentIndex-1));
            makeVisible();
            return;
        }
        makeInvisible();


    }
    private void reshape(){
        int length = wheels.size();
        int currentWidth = 40 * currentIndex;
        shape.changeSize(shapeHeigth, shapeWidth + 60*currentIndex);
        base.changeSize(10, 60 + 60*(currentIndex-1));
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
    
    
    public void spin(){
        if (currentIndex ==0 ||configuration.size() < 2){
            setOk(false);
            return;
        }
        for (int i = 0; i < currentIndex; i++){
            Wheel w = wheels.get(i);
            Symbol obtained = w.spin();
            sequence.set(i,obtained);
        }

    }
    
    public void spin(int pos){
        if (currentIndex ==0 || configuration.size() < 2){
            setOk(false);
            return;
        }
        if (1<= pos && pos <= currentIndex){
            Wheel w  = wheels.get(pos-1);
            Symbol obtained = w.spin();
            sequence.set(pos-1, obtained);
        }
    }
    
    public void spin(int pos1, int pos2){
        if (currentIndex ==0 || configuration.size() < 2){
            setOk(false);
            return;
        }
        if (1 <= pos1  && pos1 <= currentIndex && 1<= pos2 && pos2 <= currentIndex){
            Wheel w1 = wheels.get(pos1-1);
            Symbol obtained1  = w1.spin();
            sequence.set(pos1-1,obtained1);
            Wheel w2 = wheels.get(pos2-1);
            Symbol obtained2 = w2.spin();
            sequence.set(pos2-1,obtained2);
        }
    }
    
    public void makeInvisible(){
        shape.makeInvisible();
        base.makeInvisible();
        for (int i = 0; i < currentIndex; i++){
            Wheel w = wheels.get(i);
            w.makeInvisible();
            if (sequence.get(i) != null){
                sequence.get(i).makeInvisible();
            
            }
        }
        setOk(true);
    }
    
    public void makeVisible(){
        if (currentIndex == 0){
            setOk(false);
            return;
        }
        shape.makeVisible();
        base.makeVisible();
        for (int i = 0; i < currentIndex; i++){
            Wheel w = wheels.get(i);
            w.makeVisible();
            if (sequence.get(i) != null){
                sequence.get(i).makeVisible();
            
            }

        }
        
        setOk(true);
    }
    public boolean isJackpot(){
        
        for (int i = 0 ; i < currentIndex; i++){

            if (sequence.get(i).getColor() != sequence.get(0).getColor()){
                return false;
            }
        }
        return true;
    }
    public void setOk(boolean state){
        ok = state; 
    }
}
