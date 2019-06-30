public class ListNode
{
    private Point data;
    private ListNode next;
    private ListNode previous;
     /* Default Constructor */
    public ListNode(){
        next = null; 
        previous = null; 
        data = null; 
    }

    /* Constructor */
    public ListNode(Point p, ListNode front, ListNode back)
    {
        this.next = front;
        this.previous = back;
        this.data = p;
    }

    /* Sets what the node points ahead to */
    public void setLinkNext(ListNode n)
    {
        next = n;
    }

    /* Sets what the node points behind to */
    public void setLinkPrev(ListNode p)
    {
        previous = p;
    }    

    /* Gets what the node points ahead to */
    public ListNode getLinkNext()
    {
        return next;
    }

    /* Gets what the node points behind to */
    public ListNode getLinkPrev()
    {
        return previous;
    }

    /* Sets the nodes data (Point) */
    public void setData(Point d)
    {
        data = d;
    }

    /* Gets the nodes data (Point) */
    public Point getData()
    {
        return data;
    }

}