
import java.awt.*;
import java.awt.geom.*;

/**
 * A circle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0.  (15 July 2000) 
 */

public class Circle extends Symbol{

    public static final double PI=3.1416;
    


    public Circle(){
        diameter = 20;
        xPosition = 10;
        yPosition = 90;
        color = "green";
        isVisible = false;
    }



    public void draw(){
        if(isVisible) {
    
            canvas.draw(this, color, 
                new Ellipse2D.Double(xPosition, yPosition, 
                diameter, diameter));
            canvas.wait(10);
        }
    }

    public  void erase(){
        if(isVisible) {
            canvas.erase(this);
        }
    }


 
    /**
     * Change the size.
     * @param newDiameter the new size (in pixels). Size must be >=0.
     */
    public void changeSize(int newDiameter){
        erase();
        diameter = newDiameter;
        draw();
    }




}
