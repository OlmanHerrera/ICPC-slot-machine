import java.awt.*;

/**
 * A triangle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0  (15 July 2000)
 */

public class Triangle extends Symbol{
    
    public static int VERTICES=3;
    
 

    /**
     * Create a new triangle at default position with default color.
     */
    public Triangle(){
        height = 20;
        width = 20;
        xPosition = 20;
        yPosition = 90;
        color = "yellow";
        isVisible = false;
    }

    /**
     * Change the size to the new size
     * @param newHeight the new height in pixels. newHeight must be >=0.
     * @param newWidht the new width in pixels. newWidht must be >=0.
     */
    public void changeSize(int newHeight, int newWidth) {
        erase();
        height = newHeight;
        width = newWidth;
        draw();
    }
    
   
    /*
     * Draw the triangle with current specifications on screen.
     */
    @Override
    public  void draw(){
        if(isVisible) {
            int[] xpoints = { xPosition, xPosition + (width/2), xPosition - (width/2) };
            int[] ypoints = { yPosition, yPosition + height, yPosition + height };
            canvas.draw(this, color, new Polygon(xpoints, ypoints, 3));
            canvas.wait(10);
        }
    }

    /*
     * Erase the triangle on screen.
     */
    @Override
    public void erase(){
        if(isVisible) {
            canvas.erase(this);
        }
    }
}
