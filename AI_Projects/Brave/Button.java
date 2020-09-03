import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Button: Provides common properties and methodes for all button images 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    private boolean clicked = false;// used to determine if image has been clicked
    /**
     * Act - Dose nothing
     * 
     */
    public void act() 
    {
        // Add your action code here.
    }    
    
   
    /**
     * Constructs and set image
     * 
     */
    public Button(String img){
        setImage(img);
    }
    
    
    /**
     * Checks if image has been clicked
     * 
     */
    public boolean isClicked(){
        return clicked;
    }
    
    /**
     * Changes clicked value
     * 
     */
    public void setClick(boolean hasClicked){
        clicked = hasClicked;
    }
}
