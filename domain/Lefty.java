package domain;


/**
 * Write a description of class Lefty here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lefty extends Wheel
{
    private SlotMachine machine;
    private int pos;
    public Lefty(SlotMachine machine, int pos)
    {
        this.machine = machine;
        this.pos = pos;
    }
    
    @Override
    public Symbol spin(){
        Symbol opt = super.spin();
        Symbol left = machine.getCurrentConfiguration(pos-1);
        String color = left.getColor();
        for (Symbol s: symbols){
            if (s.getColor().equals(color)){
                s.makeVisible();
                return s;
            }
        }
        return opt;
    }    

}