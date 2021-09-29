package coc.cs282.mattsmith.huffmancodes;

import java.util.Scanner;

/**
 * The app to run the HuffmanCodes project.
 * 
 * 1. Accepts a valid input from the user.
 * 2. Creates an instance of the HuffmanTree class.
 * 3. Encodes the message string to binary.
 * 4. Decodes the binary back to the original message string.
 * 
 * Due date: 9/29/2021
 * @author Matthew Smith
 */
public class HuffmanCodesApp {

    public static void main(String[] args) {
        System.out.println("Enter a message (a-z, A-Z, ' ', lf): ");
        
        Scanner scanner = new Scanner(System.in);
        String message = getValidMessage(scanner);
        
        // Scanner#nextLine appears to consume the line feed, when it  
        // should be included (as we literally enter one for the scanner each time)
        if (message.charAt(message.length() - 1) != '\n') {
            message = message + '\n'; // Add the line feed back
        }

        HuffmanTree huffmanTree = new HuffmanTree(message);

        String encodedBinaryMessage = huffmanTree.encodeToBinary();
        String decodedMessageString = huffmanTree.decodeFromBinary(encodedBinaryMessage);

        System.out.println("Encoded to binary: " + encodedBinaryMessage);
        System.out.println("Decoded from binary: " + decodedMessageString);
    }
    
    // Check for invalid characters and recursively call until no invalid characters
    // Needed, otherwise the program will error on an invalid character
    private static String getValidMessage(Scanner scanner) {
        String message = scanner.nextLine();
        
        boolean hasInvalidCharacters = false;
        
        // If there is an invalid character, set boolean and break out of loop
        for (char c : message.toCharArray()) {
            if (!CharacterConverter.isValidCharacter(c)) {
                hasInvalidCharacters = true;
                break;
            }
        }
        
        // If there was any invalid character, prompt for another message and get it
        if (hasInvalidCharacters) {
            System.out.println("Invalid characters (only a-z, A-Z, space, and lf). Enter another: ");
            return getValidMessage(scanner);
        }
        
        // No invalid characters, return the message inputted
        return message;
    }

}
