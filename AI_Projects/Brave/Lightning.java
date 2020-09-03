import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class Lightning here.
 * 
 * Class controls how and when the lightning gif is played
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lightning extends Actor
{
    private GifImage gif;// lighning gif
    private List<GreenfootImage> list;// stores an array of lighning images
    private GreenfootImage imgOne;// stores first image of lighning gif
    private GreenfootImage blackBackground;
    private boolean notImgOne = false;// checks if the first lighning image is returned
    private boolean play = false;// determines if gif will play
    
   /**
     * Act - If play is true then it will cycle through lightning images once
     */
    public void act() 
    {
        
        
        if(play){
           cycleThroughImages();
        }
        
    }    
  
   /**
     * Creates lightning gif and scales them 
     * Sets a black-background and stores the first lightning image 
     * into imgOne.
     *
     * 
     */
    public Lightning(){
        gif = new GifImage("lightning gif2.gif");
        gif = scaleGif(gif);
        list = gif.getImages();
        imgOne = list.get(0);
        blackBackground = new GreenfootImage("black-background.jpg");
        blackBackground.scale(300,230);
        setImage(blackBackground);
    }
    
   /**
     * Set value of play. This determines if the gif plays
     * 
     * 
     */
    public void playGif(boolean p){
       play = p;                    
    }
    
    
   /**
     * Loops through the gif image once.
     * This insures the lightning gif only plays once
     * 
     */
    public void cycleThroughImages(){
        setImage(gif.getCurrentImage());// loops through lightning images. this will
        
        /*
         * This if block checks if the current image is the first image.
         * (gif.getCurrentImage() != imgOne) returns true if current image 
         * equals imgOne (first image). If it returns true then it won't equal
         * notImgOne, which is false, and execute the line that negates notImgOne.
         * 
         * The next block executes if !notImgOne is false. Replaces gif with black
         * background and sets play to false
         */
        if( (gif.getCurrentImage() != imgOne) != notImgOne){
            notImgOne = !notImgOne;
        
            if(!notImgOne){
                setImage(blackBackground);
                play = false;
                return;
            }
        }
    }
    
    
   /**
     * Scales each gif image
     * 
     * 
     */
    public GifImage scaleGif(GifImage gif){
        for(GreenfootImage gifScale: gif.getImages()){
            gifScale.scale(300,230);
        }
        return gif;
    }
    
}
