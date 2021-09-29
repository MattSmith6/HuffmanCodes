package coc.cs282.mattsmith.huffmancodes;

/**
 * A node to be used for a binary tree. Includes a character and its
 * frequency, as well left and right child nodes.
 *
 * @author Matthew Smith
 */
public class TreeNode {
    
    // The character in the message
    private final Character character;
    
    // The frequency in the message
    private final int frequency;
    
    private TreeNode left;
    private TreeNode right;
    
    public TreeNode(Character character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }
    
    public Character getCharacter() {
        return character;
    }
    
    public int getFrequency() {
        return frequency;
    }
    
    public TreeNode getLeft() {
        return left;
    }
    
    public TreeNode getRight() {
        return right;
    }
    
    public void setLeft(TreeNode left) {
        this.left = left;
    }
    
    public void setRight(TreeNode right) {
        this.right = right;
    }
    
}
