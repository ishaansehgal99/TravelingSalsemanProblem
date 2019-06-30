PROJECT TITLE: Traveling Salesperson Problem
PURPOSE OF PROJECT: Implement two greedy heuristics in the attempt to find
an optimal solution to the Traveling Salesperson Problem
DATE: 12/14/2017
AUTHOR: Jenica Baron & Ishaan Sehgal

DATA STRUCTURE DESCRIPTION: 
The underlying data structure of this project was a Doubly Circular Linked-List. Why use such a data structure you ask? Well…

ADVANTAGES:
- List can be traversed from both head to tail and from tail to head. In fact the entire List can be traversed from any starting node. 
- All nodes have a node before and after them. This makes referring to particular nodes easier. 
- The start and end nodes of the list can be reached with one operation.
- Accessing nodes (transversals) & Searching for nodes takes O(N)
- Inserting & deleting nodes takes O(1) 

DISADVANTAGES: 
- List takes up more memory. Each node requires both a next and previous pointer. 
- Requires much more setup. If implemented poorly, finding the start and end nodes is difficult because there is no NULL’s to mark beginning & end. 


With the data structure all setup, implementing the algorithms was rather fun & straightforward…

ALGORITHM DESCRIPTIONS: 

insertNearest()
The insertNearest() function first checks to see if there is at least two points in the tour then it will try to find the implement the NearestNeighbor heuristic. It does this by finding the node in the Tour closes to the point P. Then it stores the index (position) of this node. At the end of the for-loop (after all the nodes in the Tour have been compared), the point P is added as a node into the tour. The linked-list class takes care of setting the inserted node’s previous and next links. Complexity: O(N^2)

insertSmallest()
A mistake I had made initially was directly inserting a point into the Tour, performing the algorithm and then deleting the point. This constant insertion and deletion was happening thousands of times! And it made the complexity of my insertSmallest() algorithm O(N^3)! Figuring out how to fix this problem made this assignment really enjoyable.  

The algorithm works by first calling tour.distance() which contains the distance between all current points in the tour. (Basically the distance() method found in the tour class except it saves time because it doesn’t require list transversal). Then it adds tour.distance() to the distance between the current node in the list (nodeIterator) to point p. Then it adds the distance between point p to the node after the current node (nodeIterator.getLinkNext()). Then it subtracts the distance between the two nodes (nodeIterator & nodeIterator.getLinkNext()). This calculates the new distance of the tour.  The lowest distance calculated has its index saved and the point P added to the tour as a node. This whole processes doesn’t require the point to be directly added to the tour rather simply “referred” to as point P. This means the complexity of the algorithm is O(N^2). 

