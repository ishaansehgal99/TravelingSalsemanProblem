import java.util.*;
import java.awt.Graphics;

/**
 * This class is a specialized Linked List of Points that represents a
 * Tour of locations attempting to solve the Traveling Salesperson Problem
 * 
 * @author
 * @version
 */
@SuppressWarnings("unchecked")
public class Tour implements TourInterface
{
    // instance variables
    LinkedList tour;

    // constructor
    public Tour()
    {
        tour = new LinkedList(); 
    }

    //return the number of points (nodes) in the list   
    public int size()
    {
        return tour.getSize(); 
    }

    // append Point p to the end of the list
    public void add(Point p)
    {
        tour.insertAtEnd(p); 
    } 

    // print every node in the list 
    public void print()
    {   
        tour.printList();
    }

    // draw the tour using the given graphics context
    public void draw(Graphics g)
    {
        ListNode ptr = tour.getStartNode(); 
        for(int i = 0; i < size(); i++){
            g.fillOval((int)(ptr.getData().getX() + 0.5), (int)(ptr.getData().getY() + 0.5), 5, 5);
            g.drawLine(
                //Points below have been rounded to nearest integer...
                (int)(ptr.getData().getX() + 0.5), //Starting Point X-Coordinate
                (int)(ptr.getData().getY() + 0.5), //Starting Point Y-Coordinate
                (int)(ptr.getLinkNext().getData().getX() + 0.5), //Ending Point X-Coordinate
                (int)(ptr.getLinkNext().getData().getY() + 0.5)); //Ending Point Y-Coordinate
            ptr = ptr.getLinkNext();
        }

    }
    //calculate the distance of the Tour, but summing up the distance between adjacent points
    //NOTE p.distance(p2) gives the distance where p and p2 are of type Point
    public double distance()
    {
        ListNode ptr = tour.getStartNode(); 
        double sum = 0.0; 
        for(int i = 0; i < size(); i++){
            sum += ptr.getData().distance(ptr.getLinkNext().getData()); 
            ptr = ptr.getLinkNext();
        }
        return sum;
    }

    // add Point p to the list according to the NearestNeighbor heuristic
    public void insertNearest(Point p)
    {   

        if(size() > 1){
            ListNode nodeIterator = tour.getStartNode(); //Node will interate through Tour (Doubly Circular Linked-List)
            double lowestDistance = Double.POSITIVE_INFINITY; //Arbitrary high # so that if-statement is entered upon first loop                           
            double currentDistance = lowestDistance; 
            int index = 0; //Will store the position of the lowestDistance

            for(int i = 0; i < size(); i++){
                currentDistance = nodeIterator.getData().distance(p);
                if(currentDistance < lowestDistance){
                    lowestDistance = currentDistance; 
                    index = i; 
                }
                nodeIterator = nodeIterator.getLinkNext();
            }
            tour.addAtPos(p, index + 1); 
            return;
        }  
        add(p); //If there is 0 or 1 point in the tour... Add the point to the end of the tour.
    }
    // add Point p to the list according to the InsertSmallest heuristic
    public void insertSmallest(Point p)
    { 

        if(size() > 2){                             
            ListNode nodeIterator = tour.getStartNode(); //Node will interate through Tour (Doubly Circular Linked-List)
            double lowestDistance = Double.POSITIVE_INFINITY; //Arbitrary high # so that if-statement is entered upon first loop 
            double currentDistance = lowestDistance; 
            int index = 0; //Will store the position of the lowestDistance

            for(int i = 1; i < size() + 1; i++){ 
                /*Without directly adding the point P into the Linked-List (because that would require an unecceasry transveral of 
                Linked-list which is O(N)), simply refer to the point P. Get the distance from the current node in the Tour 
                Linked-List (nodeIterator) to point P and then add this to the distance from point P to the next node 
                (nodeIterator.getLinkNext()) and then subtract the distance between the two nodes 
                (nodeIterator & nodeIterator.getLinkNext()) to obtain your current distance. */

                currentDistance = tour.getDistance() + 
                nodeIterator.getData().distance(p) + 
                p.distance(nodeIterator.getLinkNext().getData()) - 
                nodeIterator.getData().distance(nodeIterator.getLinkNext().getData());
                
                if((currentDistance < lowestDistance)){
                    lowestDistance = currentDistance; 
                    index = i; 
                }
                nodeIterator = nodeIterator.getLinkNext(); 
            }
            tour.addAtPos(p, index + 1);
            return; 
        }
        /*If there are either 0, 1, or 2 points currently in the tour...It doesn't matter where you add the next point 
         * the cumulative distance of the tour (a triangle) remains the same. So add the point to the end of the tour...*/
        add(p); 
    }
}
