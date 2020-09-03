import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TeddyBear here.
 * 
 * Class represents the child's teddy bear. The class can 
 * influence the child’s behavior base on the braveFactor
 * property.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TeddyBear extends Actor
{
    
    GreenfootSound sound = new GreenfootSound("squeeze_toy.wav");// teddy bear sound
    GreenfootImage teddyBear;// bear image
    
    /*gauge that represents how much influence the bear has on the child
     * the higher the factor the more influence the bear has on the child
     */
    double braveFactor = 1.0;
    /**
     * 
     */
    public void act() 
    {
        // Add your action code here.
    }    
    
           
    /**
     * Creates and scales teddy bear image and inserts transparent image.
     * 
     */
    public TeddyBear(){
        teddyBear = new GreenfootImage("teddy-bear2.png");
        teddyBear.scale(190,200);
        removeImage();
    }
    
    /**
     * Sets bear image
     * 
     */
    public void setTeddyImage(){
        setImage(teddyBear);
    }  
    
    /**
     * Plays sound byte if play is true
     * 
     */
    public void playSound(boolean play){
        if(play){
            sound.play();
        
        }
    }
    
    /**
     * Removes bear image and replace it with a scaled transparent image
     * 
     */
    public void  removeImage(){
        GreenfootImage background = new GreenfootImage("Transparency.png");
        background.scale(390,315);
        setImage(background);
    }
    
    
    public double getFactor(){
        return braveFactor;
    }
    
    /**
     * Decreses braveFactor by 0.1 as long as its greater then 0.
     * 
     * 
     */
    public void decreaseFactor(){
        if(braveFactor > 0){
            braveFactor -= 0.1;
        }
        
    }
    
    /**
     * Increses braveFactor by 0.1 as long as its less then 1
     * 
     */
    public void increaseFactor(){
        if(braveFactor < 1){
            braveFactor += 0.1;
        }
    }
}   
