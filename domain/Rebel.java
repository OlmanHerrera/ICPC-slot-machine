package domain;

public class Rebel extends Wheel
{

    public Rebel()
    {
    
      
    }
    
    @Override
    public boolean isLocked(){
        return true;
    }
    
    @Override
    public void setLock(boolean State){
        return;
    }
    

}