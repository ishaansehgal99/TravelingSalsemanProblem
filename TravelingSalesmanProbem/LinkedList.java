
/**
 * Doubly Circular Linked-List Implementation
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LinkedList
{
    private ListNode start; //Start node
    private ListNode end;   //End node
    public int size;    //Size of list

    private double distance; //Contains distance between points added into the List

    /* Constructor */
    public LinkedList()
    {
        start = null;
        end = null;
        size = 0;
    }

    /* Function to get current distance in List */
    public double getDistance(){
        return distance; 
    }

    /* Function to check if list is empty */
    public boolean isEmpty()
    {
        return start == null;
    }

    /* Function to get size of list */
    public int getSize()
    {
        return size;
    }

    /* Function to get start node of list */
    public ListNode getStartNode(){
        return start; 
    }

    /* Function to get end node of list */
    public ListNode getEndNode(){
        return end; 
    }

    /* Function to insert node at the start of the list */
    public void insertAtStart(Point p)
    {
        ListNode newNode = new ListNode(p, null, null);  //Construct node from Point P
        if (start == null)  //List is empty. 
        {            
            newNode.setLinkNext(newNode);  //Since list is circular both LinkNext & LinkPrev go to the same node. 
            newNode.setLinkPrev(newNode);
            start = newNode;    //Since the newNode is the only node in this list. It is both the start & end.
            end = start;            
        }
        else
        {
            /*Distance from start node to point P is added to distance. Then the newNode is inserted 
            into the list as the new start node. */
            distance += getStartNode().getData().distance(p); 
            newNode.setLinkPrev(end);   //end <------- newNode
            end.setLinkNext(newNode);   //end -------> newNode

            start.setLinkPrev(newNode); //newNode <------- start
            newNode.setLinkNext(start); //newNode -------> start

            start = newNode;        //Start node is now newNode
        }
        size++; 
    }

    /* Function to insert node at the end of the list */
    public void insertAtEnd(Point p)
    {
        ListNode newNode = new ListNode(p, null, null);    //Construct node from Point P    
        if (start == null) //List is empty
        {
            newNode.setLinkNext(newNode); //Since list is circular both LinkNext & LinkPrev go to the same node. 
            newNode.setLinkPrev(newNode);
            start = newNode;  //Since the newNode is the only node in this list. It is both the start & end.
            end = start;
        }
        else
        {
            /*Distance from end node to point P is added to distance. Then the newNode is inserted 
            into the list as the new end node. */
            distance += getEndNode().getData().distance(p); 
            newNode.setLinkPrev(end); //end <------- newNode
            end.setLinkNext(newNode); //end -------> newNode

            start.setLinkPrev(newNode); //newNode <------- start
            newNode.setLinkNext(start); //newNode -------> start

            end = newNode;    //End node is now newNode
        }
        size++;
    }

    /* Function to insert element at certain position */
    public void addAtPos(Point p, int pos)
    {
        ListNode newNode = new ListNode(p, null, null);    //Node to be inserted into list
        if (pos == 1) //If position to be inserted is at start of list, call insertAtStart(Point)
        {
            insertAtStart(p);
            return;
        }            
        ListNode ptr = start;
        for (int i = 2; i <= size; i++)
        {
            if (i == pos)   //If i-value equals pos, we have found spot of insertion. 
            {
                ListNode temp = ptr.getLinkNext();  //Temporary node created. Ahead of spot of insertion.
                ptr.setLinkNext(newNode); // ptr -----> newNode
                newNode.setLinkPrev(ptr); // ptr <----- newNode

                newNode.setLinkNext(temp); // newNode -----> temp (AKA ptr.getLinkNext())
                temp.setLinkPrev(newNode); // newNode <----- temp 
                if(getSize() > 1) //If there is more than 1 node in this list calculate distance
                {
                    /*Distance is equal to the distance between the node to be inserted (newNode)
                    and its previous node. Plus distance between newNode and its next node.
                    Minus the distance between the newNode's previous and next nodes
                    (newNode.getLinkPrev() & newNode.getLinkNext()*/
                    distance += newNode.getLinkPrev().getData().distance(p) + 
                    newNode.getLinkNext().getData().distance(p) - 
                    newNode.getLinkPrev().getData().distance(newNode.getLinkNext().getData());
                }
            }
            ptr = ptr.getLinkNext();   //Keep cycling through list         
        }
        size++ ;
    }

    /* Function to print out the list */
    public void printList(){

        if (size == 0) //If list is empty, there is nothing to print out.
        {
            System.out.print("empty\n");
            return;
        }

        ListNode ptr = start;
        while (ptr != start.getLinkPrev())      //Cycle through list. Has to stop at last node.
        {
            System.out.print(ptr.getData()+ "\n");
            ptr = ptr.getLinkNext();
        }
        System.out.print(ptr.getData()+ "\n");      //Print out last node.

    }
}
