import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Window - Prop that does nothing meaningful 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class window extends Actor
{
    /**
     * Act - Dose Nothing
     * 
     */
    public void act() 
    {
        // Add your action code here.
    }    
    
    /**
     * Gets and scales the window image
     * 
     */
    public window(){
        GreenfootImage img = getImage();
        img.scale(340, 280);
    }
}
