import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Write a description of class SlotMachine here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SlotMachine {
    private static int MAX_SIZE = 6;
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
    private Random random = new Random();
    private String[] colours = {"red", "yellow", "blue", "green", "magenta", "black"};
    
    public SlotMachine(){
        updateShape();
    }
    
    public SlotMachine(int n){
        if (n < 1 || n > MAX_SIZE){
            setOk(false);
            return;
        }
        updateShape();
        for (int i = 0; i <= n; i++){
            addWheel(i);
        }
        int idx;
        for (int pos = 1; pos <= n; pos++){
            idx = random.nextInt(colours.length);
            String color = colours[idx];
            addSymbol(pos,color);
            while( new HashSet<>(configuration.values()).size() != pos){
                idx = random.nextInt(colours.length);
                color = colours[idx];
                addSymbol(pos,color);
            }
 
        }
        makeVisible();
    }
    
    
    private void updateShape(){
        shape.changeSize(200,20);
        shape.moveVertical(-40);
        shape.changeColor("blue");
        base.moveHorizontal(10);
        base.moveVertical(120);
        base.changeSize(10,60);
        base.changeColor("blue");
        updateSpinner();
    }
    
    private void updateSpinner(){
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

    }
    
    public void delSymbol(int pos){
        for (int i = 0; i < currentIndex; i++){
            Wheel w = wheels.get(i);
            w.delSymbol(pos);
            configuration.remove(pos);
            sequence.set(i, w.getShapeCurrentPos());
        }
        makeVisible();
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
    public void placeSymbol(int wheel, String color){
        if (wheel >= currentIndex){
            setOk(false);
            return;
        }
        if (wheels.get(wheel-1) == null || wheels.get(0) == null){
            setOk(false);
            return;
        }
        setOk(true);
        return;
    }
    public void spin(int wheel,int steps){
        if (wheel > currentIndex){
            setOk(false);
            return;
        }
        if (wheels.get(wheel-1).isLocked()){
            setOk(false);
            return;
        }
        if (currentIndex ==0 || configuration.size() < 2){
            setOk(false);
            return;
        }
        if (1<= wheel && wheel <= currentIndex){
            Wheel w  = wheels.get(wheel-1);
            Symbol obtained = w.spinSteps(steps);
            sequence.set(wheel-1, obtained);
        }

    }
    
    public String[] symbols(){
        String[] symbols = configuration.values().toArray(new String[0]);
        return symbols;
    }
    
    public int distinctSymbols(){
        if (configuration.size()<1){
            setOk(false);
            return -1;
            
        }
        ArrayList<String> seen = new ArrayList<>();
        for (int i = 0; i < currentIndex; i++){
            
            if (!seen.contains(sequence.get(i).getColor())){
                seen.add(sequence.get(i).getColor());
            }

        }

        return seen.size();
    }
    public String[] configuration(){
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i < currentIndex; i++){
            if (sequence.get(i) == null){
                break;
            }
            temp.add(sequence.get(i).getColor());
            
        }
        System.out.println("Configuration: "+ temp);
        return temp.toArray(new String[0]);
        
        
    }
    public void delWheel(int pos){

        if (wheels.get(pos-1) == null ){
            setOk(false);
            return;
        }
        
        if (wheels.get(pos-1).isLocked()){
            setOk(false);
            return;
        }

        moveSpinner(-60);
        Wheel temp;
        wheels.get(pos-1).makeInvisible();
        wheels.get(pos-1).clear();
        wheels.set(pos-1,null);
        sequence.set(pos-1, null);
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
    private void moveSpinner(int distance){
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

    }
    
    public void lock(int wheel){
        if (wheel >= currentIndex){
            setOk(false);
            return;
        }
        if ( wheel < 1 || wheel > MAX_SIZE){
            setOk(false);
            return;
        }
        
        if (wheels.get(wheel-1) != null){
            wheels.get(wheel-1).setLock(true);
            setOk(true);
        }
        setOk(false);
    }
    
    public void unlock(int wheel){
        if (wheel >= currentIndex){
            setOk(false);
            return;
        }
        if ( wheel < 1 || wheel > MAX_SIZE){
            setOk(false);
            return;
        }
        if (wheels.get(wheel-1) != null && wheels.get(wheel-1).isLocked()){
            wheels.get(wheel-1).setLock(false);
            setOk(true);
        }
        setOk(false);
    }
    
    
    public void swap(int wheel1, int wheel2){
        if ( wheel1 < 1 || wheel1 > MAX_SIZE || wheel2 < 1 || wheel2 > MAX_SIZE){
            setOk(false);
            return;
        }

        if (wheels.get(wheel1-1) == null || wheels.get(wheel2-1) == null){
            setOk(false);
            return;
        }
        if (wheels.get(wheel1-1).isLocked() || wheels.get(wheel2-1).isLocked()){
            setOk(false);
            return;
        }
        if (wheels.get(wheel1-1).isLocked() || wheels.get(wheel2-1).isLocked()){
            setOk(false);
            return;
        }
        Wheel w1 = wheels.get(wheel1-1);
        Wheel w2= wheels.get(wheel2-1);
        int[] p2  = w2.getPosition();
        int[] p1 = w1.getPosition();
        int distance = p2[0]-p1[0];
        w1.moveHorizontal(distance);
        w1.adjustSymbols(distance);
        w2.moveHorizontal(-distance);
        w2.adjustSymbols(-distance);
        wheels.set(wheel1-1, w2);
        wheels.set(wheel2-1,w1);
        
        if (configuration.size() >= 1){
            sequence.set(wheel1-1, w2.getShapeCurrentPos());
            sequence.set(wheel2-1,w1.getShapeCurrentPos());
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
            if (!w.isLocked()){
                Symbol obtained = w.spin();
                sequence.set(i,obtained);
            }

        }
    }
    
    public void spin(int pos){
        if (pos > currentIndex){
            setOk(false);
            return;
        }
        if (wheels.get(pos-1).isLocked()){
            setOk(false);
            return;
        }
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
        if (configuration.size()<1){
            setOk(false);
            return false;
        }
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
    private void setOk(boolean state){
        ok = state; 
    }
}
