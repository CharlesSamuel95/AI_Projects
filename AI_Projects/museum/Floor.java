import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Wall here.
 * 
 * Each Floor will have a score and reference to their parent Floor. The class consist of
 * getters and setters to access steps, heuristic, score and parentFloor. All Floor's
 * are sorted by their score in ascending order.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Floor extends Actor implements Comparable<Floor>
{
    private int steps = 0;// number of steps needed to reach starting floor node
    private int heuristic = 0;// number of steps needed to reach art's location
    private int score = 0;// setps + heuristic

    private Floor parentFloor;
    /**
     * Act - do whatever the Wall wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    

    public Floor getParent(){
        return parentFloor;
    }

    public int getSteps(){
        return steps;
    }

    public int getHeuristic(){
        return heuristic;
    }

    public int getScore(){
        return score;
    }

    public void setParent(Floor parent){
        parentFloor = parent;
    }

    public void setSteps(int steps){
        this.steps = steps;
    }

    public void setHeuristic(int heuristic){
        this.heuristic = heuristic;
    }

    public void setScore(int steps, int heuristic){
        score = steps + heuristic;
    }

    public int compareTo(Floor floor){
        return this.getScore() - floor.getScore();
    }
}
