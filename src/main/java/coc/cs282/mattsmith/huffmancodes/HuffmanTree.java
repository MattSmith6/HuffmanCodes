package coc.cs282.mattsmith.huffmancodes;

/**
 * The class that generates the frequency table, Huffman Tree, and code table.
 * 
 * @author Matthew Smith
 */
public class HuffmanTree {
    
    // Message to encode/decode, given through the constructor
    private final String message;
    
    // The frequency for each character, dependent on the array index
    private final int[] characterFrequencies;
    
    // The generated huffman tree
    private Tree huffmanTree;
    
    // The code table for each character, depedent on the array index
    private final String[] codeTable;
    
    public HuffmanTree(String message) {
        this.message = message;
        this.characterFrequencies = new int[CharacterConverter.getNumOfCharacters()];
        this.codeTable = new String[CharacterConverter.getNumOfCharacters()];
        
        // Add the frequency for each character to an array
        populateFrequencyArray();

        // Create the huffman tree given the frequency table
        createHuffmanTree();

        // Create the code table from the huffman tree
        createCodeTable();
    }
    
    private void populateFrequencyArray() {
        // Add 1 to the character's frequency when it shows up in the message string
        for (char c : message.toCharArray()) {
            int arrayIndex = CharacterConverter.charToInt(c);
            int frequency = characterFrequencies[arrayIndex];
            
            characterFrequencies[arrayIndex] = frequency + 1;
        }
    }
    
    private void createHuffmanTree() {
        PriorityQueue priorityQueue = new PriorityQueue();

        // Insert characters into the priority queue
        for (int i = 0; i < characterFrequencies.length; i++) {
            int frequency = characterFrequencies[i];

            // If the character does not show up in the message, skip it
            if (frequency == 0) {
                continue;
            }

            // Get the current character from slot in the index
            char current = CharacterConverter.intToChar(i);

            // Create new node and tree for this character and its frequency
            TreeNode node = new TreeNode(current, frequency);
            Tree tree = new Tree(node);

            // Insert new tree into the queue
            priorityQueue.addTree(tree);
        }

        // While there are more than one trees in the priority queue,
        while (priorityQueue.size() > 1) {

            // Create a new tree with a null character (merge node)
            TreeNode mergeNode = new TreeNode(null, 0);
            Tree mergeTree = new Tree(mergeNode);

            // First tree in queue will be the left node of merge node
            Tree left = priorityQueue.removeTree();
            mergeNode.setLeft(left.getRoot());

            // Second tree in queue will be the right node of merge node
            Tree right = priorityQueue.removeTree();
            mergeNode.setRight(right.getRoot());

            // Add the new tree to the queue
            priorityQueue.addTree(mergeTree);
        }
        
        // The only remaining node will be the completed huffman tree
        huffmanTree = priorityQueue.removeTree();
    }
    
    private void createCodeTable() {
        // Use recursive call to generate codes for entire tree
        createCode(huffmanTree.getRoot(), "");
    }
    
    private void createCode(TreeNode node, String currentBits) {
        // If this node does not exist, exit (base condition)
        if (node == null) {
            return;
        }

        // If the character is not null (not a merged node),
        if (node.getCharacter() != null) {
            
            // Use the current bits to insert this code into the table
            int arrayIndex = CharacterConverter.charToInt(node.getCharacter());
            codeTable[arrayIndex] = currentBits;

            return;
        }

        // If this node does not have a character (merged node), do recursion
        
        // Left trees are '0'
        createCode(node.getLeft(), currentBits + "0");

        // Right trees are '1'
        createCode(node.getRight(), currentBits + "1");
    }
    
    public String encodeToBinary() {
        StringBuilder stringBuilder = new StringBuilder();

        // For every character, find it in the code table and separate it with a space
        for (char c : message.toCharArray()) {
            int index = CharacterConverter.charToInt(c);
            String characterBits = codeTable[index];
            
            stringBuilder.append(characterBits);
        }

        // Return the full string, with the whitespace at the end removed
        return stringBuilder.toString().trim();
    }

    public String decodeFromBinary(String binary) {
        StringBuilder stringBuilder = new StringBuilder();

        TreeNode currentNode = huffmanTree.getRoot();

        // For each code in the array, find the character from the tree
        for (char bit : binary.toCharArray()) {
            // Move the current node in the direction of the bit
            if (bit == '0') {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }

            // If the character for this node exists (is not blank), we found a character
            // Append it to the message and then reset the current node back to the root
            if (currentNode.getCharacter() != null) {
                stringBuilder.append(currentNode.getCharacter());
                currentNode = huffmanTree.getRoot();
            }
        }

        // Return the string of characters in the original message
        return stringBuilder.toString();
    }

    private char getCharacterFromBinary(String binary) {
        TreeNode currentNode = huffmanTree.getRoot();

        // For each bit in the binary string, travel left if '0', or right if '1'
        for (char bit : binary.toCharArray()) {
            if (bit == '0') {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }

        // We are at the specified node, so print its character
        return currentNode.getCharacter();
    }
    
}
