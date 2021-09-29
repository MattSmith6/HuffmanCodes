package coc.cs282.mattsmith.huffmancodes;

/**
 * Represents a binary tree of character nodes, containing a root node 
 * and a method to calculate the frequency of the entire tree.
 *
 * @author Matthew Smith
 */
public class Tree {
    
    private final TreeNode root;
    
    public Tree(TreeNode root) {
        this.root = root;
    }
    
    public TreeNode getRoot() {
        return root;
    }
    
    // Get the frequency of the entire tree
    public int getFrequency() {
        return getFrequencyRecursive(root);
    }

    // Recursive function to get the frequency from the provided node and its sub-nodes
    private int getFrequencyRecursive(TreeNode node) {
        // If this node does not exist, return 0 (base condition)
        if (node == null) {
            return 0;
        }
        
        int frequencies = node.getFrequency();
        
        // Recursively add frequencies of left and right nodes
        frequencies += getFrequencyRecursive(node.getLeft());
        frequencies += getFrequencyRecursive(node.getRight());
        
        return frequencies;
    }
    
}
