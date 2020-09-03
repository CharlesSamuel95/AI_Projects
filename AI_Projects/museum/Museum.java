import greenfoot.*;  
import java.util.*;
import java.io.*;

/**
 *This class executes the A* algorithm and populates the world with actors.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Museum  extends World
{
    //18 x 13
    //Dimension of the world
    static int WIDTH = 18;
    static int HEIGHT = 13;

    //Bill actor
    Bill bill;

    //Stores dynamic starting location of bill and arts location
    Floor startLocal;
    Floor destFloor;

    //This list is used to get objects from world
    List<Floor> list;

    ArrayList <Floor> openList = new ArrayList<>();
    ArrayList <Floor> closeList = new ArrayList<>();

    /**
     * Constructor for objects of class Museum.
     * Populates the world and sets dimensions.
     */
    public Museum()
    {    
        // Create a new world with 15x15 cells with a cell size of 50x50 pixels.
        super(WIDTH, HEIGHT, 50);
        populate("museum.txt");
    }

    /**
     * Populates world with Wall, Floor, Art, and sets Bill at starting location
     */
    public void populate(String file){

        try{
            Scanner sc = new Scanner(new File(file));
            bill = new Bill();
            int i = 0;
            while(sc.hasNext()){
                String s = sc.next();
                for(int j = 0; j < WIDTH; j++){
                    switch(s.charAt(j)){
                        case '0': 
                        addObject(new Wall(), j, i);
                        break;

                        case '1': addObject(new Floor(), j, i);
                        break;

                        case '2': addObject(new Floor(), j, i);
                        addObject(new Art(), j, i);
                        break;
                    }
                }
                i++;
            }
        }
        catch(Exception e){
        }
        addObject(bill, 9, 11);

        /** Gets floor object bill is standing on and adds to openList and stores in
         *  startLocal (starting location)
         */
        list = getObjectsAt(bill.getX(), bill.getY(), Floor.class);
        openList.add(list.get(0));
        startLocal = list.get(0);
    }

    
    /** The A* method is called when a art object is clcked on. It examines a Floor object
     * with the smallest score and checks the adjacent Floor objects, calls a method to
     * calculate its score, and adds it to openList. It accepts a Art object which will
     * be used to get the Floor object at dest's (destination) location
     */
    public void aStar(Art dest){
        //Will store a Floor with the lowest score 
        Floor lowCostFloor;

        //Gets and stores in destFloor the Floor object that contains the art object
        list = getObjectsAt(dest.getX(), dest.getY(), Floor.class);
        destFloor = list.get(0);

        /**
         * If openList is empty and bill's location is different from startLocal then clear
         * clostList and get the Floor at bill's new location, store in startLocal, and add 
         * to openList. This allows the starting location to be dynamic and resets the state
         * of the two ArrayList (i.e allows A* to reset).
         */
        if(openList.isEmpty()){
            if((startLocal.getX() != bill.getX() ) || 
            (startLocal.getY() != bill.getY()) ){
                closeList.clear();
                list = getObjectsAt(bill.getX(), bill.getY(), Floor.class);
                startLocal = list.get(0);
                openList.add(list.get(0));

            }

        }

        /**
         * While openList is not empty it sorts the list in ascending order by score,
         * stores Floor in lowCostFloor, and sets bills new location to lowCostFloor's.
         * If lowCostFloor location equals destFloor then add lowCostFloor to closeList
         * and end the while loop, else add lowCostFloor check each Floor object at 
         * adjacent to bill's new location by calling checkAdjacentSpace().
         * 
         */
        while(!openList.isEmpty()){
            Collections.sort(openList);
            lowCostFloor = openList.remove(0);
            bill.setLocation(lowCostFloor.getX(), lowCostFloor.getY());

            if((lowCostFloor.getX() == destFloor.getX()) && 
            (lowCostFloor.getY() == destFloor.getY())){
                closeList.add(lowCostFloor);
                //removeObject(dest);
                break;

            }

            else{
                closeList.add(lowCostFloor);

                //Top
                if(lowCostFloor.getY() - 1 >= 0 ){
                    checkAdjacentSpace(getObjectsAt(lowCostFloor.getX(), 
                            lowCostFloor.getY() - 1, Object.class), 
                        lowCostFloor);
                }

                //Top left
                if(lowCostFloor.getX() - 1 >= 0 && lowCostFloor.getY() - 1 >= 0){
                    checkAdjacentSpace(getObjectsAt(lowCostFloor.getX() - 1, 
                            lowCostFloor.getY() - 1, Object.class),
                        lowCostFloor);
                }

                //Left
                if(lowCostFloor.getX() - 1 >= 0){
                    checkAdjacentSpace(getObjectsAt(lowCostFloor.getX() - 1, 
                            lowCostFloor.getY(), Object.class),
                        lowCostFloor);
                }

                //Bottom left
                if(lowCostFloor.getX() - 1 >= 0 && lowCostFloor.getY() + 1 < HEIGHT){
                    checkAdjacentSpace(getObjectsAt(lowCostFloor.getX() - 1, 
                            lowCostFloor.getY() + 1, Object.class),
                        lowCostFloor);
                }

                //Bottom
                if(lowCostFloor.getY() + 1 < HEIGHT){
                    checkAdjacentSpace(getObjectsAt(lowCostFloor.getX(), 
                            lowCostFloor.getY() + 1, Object.class),
                        lowCostFloor);
                }

                //Bottom right
                if(lowCostFloor.getX() + 1 < WIDTH && lowCostFloor.getY() + 1 < HEIGHT){
                    checkAdjacentSpace(getObjectsAt(lowCostFloor.getX() + 1, 
                            lowCostFloor.getY() + 1, Object.class),
                        lowCostFloor);
                }

                //Right
                if(lowCostFloor.getX() + 1 < WIDTH){
                    checkAdjacentSpace(getObjectsAt(lowCostFloor.getX() + 1, 
                            lowCostFloor.getY(), Object.class),
                        lowCostFloor);
                }

                //Top right
                if(lowCostFloor.getX() + 1 < WIDTH && lowCostFloor.getY() - 1 >= 0){
                    checkAdjacentSpace(getObjectsAt(lowCostFloor.getX() + 1, 
                            lowCostFloor.getY() - 1, Object.class),
                        lowCostFloor);
                }

            }

            Greenfoot.delay(1);//delays speed of program
        }

        /**
         * If openList becomes empty (i.e bill couldn't find the art) then the loop ends
         * and a sound will play.
         */
        if(openList.isEmpty()){
            Greenfoot.playSound("sounds/gameOver.wav");
        }

        //Ensures that A* will reset.
        openList.clear();

    }

    /**
     * Checks if the object is a Wall, Floor, or Art. If object.get(0) is an Art object
     * then floorIndex will be 1, else it will be 0. (The method World.getObjectsAt()
     * will return a list of all objects at some location, so I needed to check which index
     * contains the Art object so I could avoid it).
     * 
     * It then checks if object.get(floorIndex) is a Floor object that is not in openList
     * and closeList. If not it sets parentFloor as its parent, calculates its score, and
     * adds to openList.
     */
    public void checkAdjacentSpace(List<Object> object, Floor parentFloor){

        int floorIndex = 0;
        if(object.get(0) instanceof Art){
            floorIndex = 1;
        }

        else{
            floorIndex = 0;
        }

        if(object.get(floorIndex) instanceof Floor){
            if(!openList.contains((Floor) object.get(floorIndex))){
                if(!closeList.contains((Floor) object.get(floorIndex))){
                    Floor ajacentFloor = (Floor) object.get(floorIndex);
                    ajacentFloor.setParent(parentFloor);

                    ajacentFloor.setSteps(calculateSteps(ajacentFloor.getParent(),
                            startLocal));

                    ajacentFloor.setHeuristic(calculateHeuristic(ajacentFloor.getX(), 
                            ajacentFloor.getY(), destFloor));

                    ajacentFloor.setScore(ajacentFloor.getSteps(), 
                        ajacentFloor.getHeuristic());              

                    openList.add(ajacentFloor); 
                }

                else{

                }
            }
        }
    }

    /**
     * The Floor's parent and startLocal is passed. If the parents location is equal to
     * startLocal's, then return 1, else it performs a recursive call that receives
     * the parent Floor's parent and startLocal, and adds 1 to whatever it returns.
     */
    public int calculateSteps(Floor parent, Floor startLocl){
        if( (parent.getX() == startLocl.getX()) && (parent.getY() == startLocl.getY()) ){
            return 1;
        }
        else{
            return 1 + calculateSteps(parent.getParent(), startLocl);
        }
    }

    /**
     * Receives x and y coordinate of a Floor object and the dest Floor. If x and y equal
     * dest location then return 0, else increment/decrement x and y until they equal. 
     * Finally, recursively call method with updated x and y, and dest. Add 1 to whatever
     * the method returns.
     */
    public int calculateHeuristic(int x, int  y, Floor dest){
        if(x == dest.getX() && y == dest.getY()){
            return 0;
        }

        if(x == dest.getX()){
        }

        else if(x > dest.getX()){
            x--;
        }

        else{
            x++;
        }

        if(y == dest.getY()){
        }

        else if(y > dest.getY()){
            y--;
        }

        else{
            y++;
        }

        return 1 + calculateHeuristic(x, y, dest);
    }
}
