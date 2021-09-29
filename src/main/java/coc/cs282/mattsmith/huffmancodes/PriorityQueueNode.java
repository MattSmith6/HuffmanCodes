package coc.cs282.mattsmith.huffmancodes;

/**
 * The node for the linked list representation of a PriorityQueue.
 * Contains a tree and a reference to the next node in the list.
 * 
 * @author Matthew Smith
 */
public class PriorityQueueNode {
    
    private final Tree tree;
    
    private PriorityQueueNode next;
    
    public PriorityQueueNode(Tree tree) {
        this.tree = tree;
    }
    
    public Tree getTree() {
        return tree;
    }
    
    public PriorityQueueNode getNext() {
        return next;
    }
    
    public boolean hasNext() {
        return next != null;
    }
    
    public void setNext(PriorityQueueNode next) {
        this.next = next;
    }
}
