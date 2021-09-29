package coc.cs282.mattsmith.huffmancodes;

/**
 * A priority queue of character trees. The priority is the tree's frequency.
 * This priority queue is implemented as a linked list of PriorityQueueNodes.
 * This solution was easier than trying to implement array wrap-around.
 *
 * @author Matthew Smith
 */
public class PriorityQueue { 

    private PriorityQueueNode first;
    private int elements;

    public PriorityQueue() {
        first = null;
        elements = 0;
    }
    
    public boolean isEmpty() {
        return size() == 0;
    }
    
    public int size() {
        return elements;
    }
    
    public void addTree(Tree tree) {
        PriorityQueueNode nodeToInsert = new PriorityQueueNode(tree);
        elements++;
        
        // Quick insert first node
        if (first == null) {
            first = nodeToInsert;
            return;
        }
        
        int frequencyToInsert = tree.getFrequency();
        
        PriorityQueueNode current = first;
        PriorityQueueNode parentOfCurrent = null;
        
        boolean hasParent = false;
        
        // While we have a node available,
        while (current != null) {
           
            // If frequency is greater, skip this node and adjust for next node
            if (frequencyToInsert > current.getTree().getFrequency()) {
                parentOfCurrent = current;
                current = current.getNext();
                
                hasParent = true;
                
                continue;
            }
            
            // We want to insert this node now
            
            if (!hasParent) { // If no parent, insert at front (first)
                nodeToInsert.setNext(first);
                first = nodeToInsert;
            } else { // If parent, insert between parent and current
                nodeToInsert.setNext(current);
                parentOfCurrent.setNext(nodeToInsert);
            }
            
            // We have inserted, exit out of the method
            
            return;
        }
        
        // If did not already insert, then this value needs to be inserted at end
        parentOfCurrent.setNext(nodeToInsert);
    }
    
    // Equivalent to removeFirst, since this is a queue
    public Tree removeTree() {
        if (first == null) {
            return null;
        }
        
        // Store tree to from first node
        Tree removedTree = first.getTree();
        
        // New first node is at the front of the list
        first = first.getNext();
        
        elements--;
        
        return removedTree;
    }
    
}
