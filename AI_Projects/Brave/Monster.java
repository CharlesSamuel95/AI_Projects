import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Monster here.
 * 
 * Monster the child encounters in bedroom. How much
 * influence the monster has is determined by toleranceFactor
 * property.
 * 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Monster extends Actor
{
    GreenfootSound roar;// monster sound effect
    GreenfootImage monster;// monster image
    
    /*gauge that represents how much influence the monster has on the child
     * the higher the factor the less influence the monster has on the child
       
     */
    
    double toleranceFactor = 1;
    /**
     * Act - do whatever the Monster wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
    
    /**
     * Creates a monster with a roar and scale image
     * 
     */
    public Monster(String monsterRoar, String monsterImg){
        roar = new GreenfootSound(monsterRoar);
        monster = new GreenfootImage(monsterImg);
        monster.scale(300,230);
    }
    
    /**
     * Sets image
     * 
     */
    public void setMonsterImage(){
        setImage(monster);
        
    }
    
    
    /**
     * Plays sound byte if play is true
     * 
     */
    public void playSound(boolean play){
        if(play){
            roar.play();
        
        }
    }
    
    /**
     * Removes monster image and replace with scale black-background
     * 
     */
    public void  removeImage(){
        GreenfootImage background = new GreenfootImage("Transparency.png");
        background.scale(300,230);
        setImage(background);
    }
    
    public double getFactor(){
        return toleranceFactor;
    }
    
    /**
     * Decreases toleranceFactor by 0.1 as long as its greather then 0
     * 
     */
    public void decreaseFactor(){
        if(toleranceFactor > 0){
            toleranceFactor -= 0.1;
        }
    }
    
    /**
     * Increses toleranceFactor by 0.1 as long as its less then 1
     * 
     */
    public void increaseFactor(){
        if(toleranceFactor < 1){
            toleranceFactor += 0.1;
        }
    }
}
