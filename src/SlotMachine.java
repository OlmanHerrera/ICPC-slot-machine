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
    private boolean ok = true;
    private int currentIndex = 0;
    private Rectangle base = new Rectangle();
    private int baseWidth = 60;
    private int baseHeigth = 10;
    private HashMap<Integer,String> configuration = new HashMap<>();
    private Rectangle line1 = new Rectangle();
    private Rectangle line2 = new Rectangle();
    private Circle buttom = new Circle();

    
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
        updateSpinner();
    }
    
    public void updateSpinner(){
        line1.changeSize(40,2);
        line1.changeColor("black");
        line2.changeSize(2,40);
        line2.changeColor("black");
        buttom.changeSize(20);
        buttom.changeColor("red");
        line1.moveVertical(-40);
        line1.moveHorizontal(58);
        line2.moveHorizontal(20);
        buttom.moveHorizontal(70);
        buttom.moveVertical(-50);


    }
    public void addSymbol(int pos, String color){
        if (wheels.get(0) == null){
            setOk(false);
            return;
        }
        configuration.put(pos,color);
        for (int i = 0; i <currentIndex; i++){
            Wheel w = wheels.get(i);
            w.addSymbol(pos, color);
            sequence.set(i, w.getShapeCurrentPos());

        }
        makeVisible();


    }
    
    public void delSymbol(int pos){
        for (int i = 0; i < currentIndex; i++){
            Wheel w = wheels.get(i);
            w.delSymbol(pos);
            configuration.remove(pos);
            sequence.set(pos-1,null);
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

        moveSpinner(-60);
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
    public void moveSpinner(int distance){
        buttom.moveHorizontal(distance);
        line1.moveHorizontal(distance);
        line2.moveHorizontal(distance);
    }
    private void reshape(){
        int length = wheels.size();
        int currentWidth = 40 * currentIndex;
        shape.changeSize(shapeHeigth, shapeWidth + 60*currentIndex);
        moveSpinner(60);
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
        line1.makeInvisible();
        line2.makeInvisible();
        buttom.makeInvisible();
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
        line1.makeVisible();
        line2.makeVisible();
        buttom.makeVisible();
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
        if (wheels.get(0) == null){
            return false;
        }
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
