import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BedRoom extends World
{
    
    Lightning lightning = new Lightning();// lightning gif
    Monster monster = new Monster("Monster.wav", "monster-cat.png");// monster image
    TeddyBear teddy = new TeddyBear();// teddy bear image
    Counter braveMeter = new Counter();//Tracks how scared the child gets after each iteration
    
    //buttons and background image
    Button monsterBt = new Button("monsterBtn.png");
    Button teddyBt = new Button("teddyBtn.png");
    Button effectOffBt = new Button("effectBtn.png");
    GreenfootImage background = new GreenfootImage("dark-background.jpg");
    
    double [][] neuroNet = new double[2][1];// 2d array that represents one neruon that accepts two inputs
    double [][] neuroNetCopy =  new double[2][1];// copy of the neuroNet.
    
    double threshold = 0;// neruon threshold
    double [] input = {1,0};// two input neruons where input[0] is monster input and input[1] is teddy input
    
    double forgetFactor = 0.01;
    double learnFactor = 0.2;
    
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public BedRoom()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        
        // Sets world background
        background.scale(600,400);
        setBackground(background);
        
        threshold = initializeNeuroNet(neuroNet);// Initialize the neuroNet and threshold with random values.
        neuroNetCopy = copyArray(neuroNet);// Makes a copy of neuroNet and stores in neuroNetCopy.
        
        // Code bellow adds objects to the world
        addObject(lightning, getWidth()/2, 150);
        addObject(monster, getWidth()/2, 150);
        addObject(teddy,550, 280); 
        addObject(new window(), getWidth()/2, 150);
        addObject(monsterBt, 70, 120);
        addObject(teddyBt,70, 200);
        addObject(effectOffBt,70, 300);
        addObject(braveMeter, 40, 30);
    }
    
   public void act(){
        
          /*This checks if the monsterBt is pressed. If isClicked() returns true then monsterBt was presed
           * twice, so the monster image is removed and setClick() is given false arg. If isClicked() 
           * returns false then first time monsterBt was clicked, so set monster image, give setClick()
           * true arg and play lightning gif. At this point we check if teddyBt.isClicked() returns
           * true.
           * 
           * If true then both teddy and monster factors will increase(teddy will gain influence while 
           * monster loses influence). If false both teddy and monster factors will decrease(monster will  
           * gain influence while teddy loses influence). These updated factors will be stored in input
           * array. Finally neuronOutput() will be called.
           * 
           * effectOffBt.isClicked() determines if effects(lightning, monster laugh, teddy sound) 
           * are turned on.
           * 
           */
       if(Greenfoot.mouseClicked(monsterBt)){
           if(monsterBt.isClicked()){
               monster.removeImage();
               monsterBt.setClick(false);               
            } 
           
           else{
               
              if(effectOffBt.isClicked()){
                 monster.playSound(false);
                 lightning.playGif(false);
              }
                
               else{
                 monster.playSound(true);
                 lightning.playGif(true);
              }
               
              monster.setMonsterImage();
              monsterBt.setClick(true);
               
              if(teddyBt.isClicked()){
                monster.increaseFactor();
                
                teddy.increaseFactor();
                
                input[0] = monster.getFactor();
                input[1] = teddy.getFactor();
              }
              
              else{
                monster.decreaseFactor();
                
                teddy.decreaseFactor();
                
                input[0] = monster.getFactor();
                input[1] = teddy.getFactor();
              }
               
               neuronOutput(neuroNet, neuroNetCopy, threshold, input);
            }
       }
       
       /*This checks if the teddyBt is pressed. If isClicked() returns true then teddyBt was presed
        * twice, so the bear image is removed and setClick() is given false arg. If isClicked() 
        * returns false then first time teddyBt was clicked, so set bear image and give setClick()
        * true arg.
        * 
        * effectOffBt.isClicked() determines if effects are turned on.
       */
       if(Greenfoot.mouseClicked(teddyBt)){
           if(teddyBt.isClicked()){
               teddy.removeImage();
               teddyBt.setClick(false);
            } 
           
           else{
               if(effectOffBt.isClicked()){
                 teddy.playSound(false);    
               }
                
                else{
                 teddy.playSound(true);
                }
               
               teddy.setTeddyImage();  
               teddyBt.setClick(true);             
            }
       }
       
       /*Determines if effects are turned on.*/
       if(Greenfoot.mouseClicked(effectOffBt)){
           if(effectOffBt.isClicked()){
               effectOffBt.setClick(false);
           }
            
           else{
             monster.playSound(false);
             lightning.playGif(false);
             teddy.playSound(false);
             effectOffBt.setClick(true);
           }
        }
    }
    
    /* Initialize neuro network and threshold with random values between 0.0(incl) and 1.0(excl) 
     */
    public double initializeNeuroNet(double[][] w){
        
        double theta = Math.random();
        for(int row = 0; row < w.length; row++){
            for(int col = 0; col < w[row].length; col++){
                w[row][col] = Math.random();
            }
        }
   
        return theta;
    }
    
    /*Produces the output of neuro net and updates weights.
     * w: neruoNet
     * tempW: neruoNetCopy
     * theta: threshold
     * x: input
     */
    private void neuronOutput(double[][] w, double[][] tempW, double theta, double [] x){
        double lambda = learnFactor/forgetFactor;
        double sum =  (w[0][0] * x[0] - theta) + (w[1][0] * x[1] - theta);// takes the sum of both weights
        
        double y = stepFunction(sum);// returns result of output neruon
        
        /* If y is 1 (teddy bear has more influence) then braveMeter will increase by 2 else it
         * decreases by 2( monster has more influence). The highter the braveMeter is the less
         * afraid the child is of the monster. The meters range is [-100 ,200].
         */
        if(y == 1){
            if(braveMeter.getValue() != 200){
                braveMeter.add(2);
            }
        }
        
        else{
            if(braveMeter.getValue() != -100){
                braveMeter.add(-2);
            }
        }
        
        double[][] transX = tranpose(x);// tranposes x
        matrixMultipication(transX, lambda);// multiplies tarnsX with lambda
        
        double [][]w2 = additionSubtractMatrix(transX, tempW, 1);// subtracts transX and tempW
        
        double[][] deltaW = matrixMultipication(w2,forgetFactor);//multiplies w2 with forgetFactor
        
        additionSubtractMatrix(w, deltaW, 0);// updates w by adding deltaW
        
        tempW = copyArray(w);// copies state of w
        
        
    }
    
    
    /*
     * Copies and returns array w
    */
    private double[][] copyArray(double[][]w){
        double[][] tempW = new double[w.length][w[0].length];
        
        for(int row = 0; row < w.length; row++){
            for(int col = 0; col < w[row].length; col++){
                tempW[row][col] = w[row][col];
            }
        }
        return tempW;
    }
     
    /*
     * Multiplies an array with a scalar
    */
    private double[][] matrixMultipication(double[][] w, double scalar ){
       for(int row = 0; row < w.length; row++){
            for(int col = 0; col < w[row].length; col++){
                w[row][col] *= scalar;
            }
        } 
       return w;
    }
    
    private int stepFunction(double num){
        if(num > 0){
            return 1;
        }
         else{
            return 0;
        }
    }
    
    /*
     * Tranposes and returns array X
    */
      private double[][] tranpose(double[] x){
        double[][] transX = new double[x.length][1];
        
        for(int row = 0; row < transX.length; row++){
            for(int col = 0; col < transX[row].length; col++){
                transX[row][col] = x[col];
            }
        }
        
        return transX;
    }
    
    /*
     * Preforms matrix addition(if operatorController = 0) or subtraction
    */
   private double[][] additionSubtractMatrix(double[][] w, double [][] x, int operatorController){
        if(operatorController == 0){
        
          for(int row = 0; row < w.length; row++){
            for(int col = 0; col < w[row].length; col++){
                
                /* This if block insures the weights in the net can't become negative(this is where
                 * weight adjustment occurs). If adding the weights(from w and deltaW) will produce
                 * a negative number then the addition will be skiped.
                 * 
                 */
                if( w[row][col] + x[row][col] < 0){
                    continue;
                }
                else{
                    w[row][col] += x[row][col];
                }
                
                
           }   
          
         }

       }
       
        else{
           for(int row = 0; row < w.length; row++){
            for(int col = 0; col < w[row].length; col++){
                w[row][col] -= x[row][col];
            }
          
         }
        }
        
       return w;
    }
}
