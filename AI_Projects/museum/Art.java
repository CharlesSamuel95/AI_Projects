import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Art here.
 * 
 * When this object is clicked it calls the aStar method.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Art extends Actor
{
    /**
     * When clicked it calls the aStar method
     */
    public void act() 
    {
        // Add your action code here.
        if(Greenfoot.mouseClicked(this)){
            Museum world =  (Museum) getWorld();
            world.aStar(this);
        }
    }
    
    /**
     * Scales and sets image
     */
    public  Art(){
        GreenfootImage img = getImage();
        img.scale(50,50);
        setImage(img);
    }
}
