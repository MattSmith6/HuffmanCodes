package coc.cs282.mattsmith.huffmancodes;

/**
 *
 * @author Matthew Smith
 */
public class DefaultHuffmanCodesTest {
    
    // Constant from book
    private static final String TEST_MESSAGE = "SUSIE SAYS IT IS EASY\n";
    
    // Constant from book
    private static final String TEST_CORRECT_BINARY = getDefaultBinary();
    
    public static void main(String[] args) {
        HuffmanTree huffmanTree = new HuffmanTree(TEST_MESSAGE);
        
        String encodedToBinary = huffmanTree.encodeToBinary();
        String decodedFromBinary = huffmanTree.decodeFromBinary(encodedToBinary);
        
        assert(encodedToBinary.equals(TEST_CORRECT_BINARY));
        System.out.println("-----[ PASSED ENCODE TEST ]-----");
        
        assert(decodedFromBinary.equals(TEST_MESSAGE));
        System.out.println("-----[ PASSED DECODE TEST ]-----");
    }
    
    private static String getDefaultBinary() {
        // Copied from the book, with arrow and new line removed, and spaces removed
        return "10 01111 10 110 1111 00 10010 1110 10 00 110 0110 00 110 10 00 1111 010 10 1110 01110".replace(" ", "");
    }
    
}
