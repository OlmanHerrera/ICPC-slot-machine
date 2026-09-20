
/**
 * Write a description of class Figure here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Symbol
{
    protected Canvas canvas = Canvas.getCanvas();
    protected int height;
    protected int width;
    protected int diameter;
    protected int xPosition;
    protected int yPosition;
    protected String color;
    protected boolean isVisible;
    
    public Symbol(){
        
    }

    public int[] getPosition() { 
        return new int[]{ xPosition, yPosition }; 
    }


    public void makeVisible(){
        isVisible = true;
        draw();
    }
    

    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    public abstract void draw();
    
    public abstract void erase();
    
    /**
     * Move the circle a few pixels to the right.
     */
    public void moveRight(){
        moveHorizontal(20);
    }

    /**
     * Move the circle a few pixels to the left.
     */
    public void moveLeft(){
        moveHorizontal(-20);
    }

    /**
     * Move the circle a few pixels up.
     */
    public void moveUp(){
        moveVertical(-20);
    }

    /**
     * Move the circle a few pixels down.
     */
    public void moveDown(){
        moveVertical(20);
    }

    /**
     * Move the circle horizontally.
     * @param distance the desired distance in pixels
     */
    public void moveHorizontal(int distance){
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the circle vertically.
     * @param distance the desired distance in pixels
     */
    public void moveVertical(int distance){
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Slowly move the circle horizontally.
     * @param distance the desired distance in pixels
     */
    public void slowMoveHorizontal(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            xPosition += delta;
            draw();
        }
    }

    /**
     * Slowly move the circle vertically
     * @param distance the desired distance in pixels
     */
    public void slowMoveVertical(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        }else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            yPosition += delta;
            draw();
        }
    }
    
    public String getColor(){
        return color;
    }
    
    public boolean isVisible(){
        return isVisible;
    }

    /**
     * Change the color. 
     * @param color the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }
    public void updateSize(){
        width = 20;
        height = 20;
    }



}