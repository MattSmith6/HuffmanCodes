package coc.cs282.mattsmith.huffmancodes;

/**
 * A class designed to convert the characters for this project
 * ('a'-'z', 'A'-'Z', ' ', and '\n') into integers, and those integers
 * back into their respective characters.
 * 
 * These integers are used as indices for arrays in the HuffmanTree class:
 * 'a'-'z':  0-25
 * 'A'-'Z': 26-51
 *     ' ':    52
 *    '\n':    53
 * 
 * If an invalid character is in the message, this program *will* break.
 * 
 * @author Matthew Smith
 */
public class CharacterConverter {
    
    // Constant from the representing 'a'-'z', 'A'-'Z', ' ', and '\n' characters
    private static final int NUMBER_OF_ALLOWED_CHARACTERS = 26 + 26 + 2;

    // Constants for handling character <-> int conversions for array indices
    private static final int LOWERCASE_OFFSET = 0;
    private static final int UPPERCASE_OFFSET = 26;
    private static final int SPACE_INDEX = NUMBER_OF_ALLOWED_CHARACTERS - 2;
    private static final int LINEFEED_INDEX = NUMBER_OF_ALLOWED_CHARACTERS - 1;
    
    public static int getNumOfCharacters() {
        return NUMBER_OF_ALLOWED_CHARACTERS;
    }

    // Method used to find the integer (for an array index) for a specific character
    public static int charToInt(char c) {
        if (c == ' ') {
            return SPACE_INDEX;
        } else if (c == '\n') {
            return LINEFEED_INDEX;
        }

        int lowerCaseSubtraction = c - 'a';
        int upperCaseSubtraction = c - 'A';

        // Math for characters in each category, -1 if not found
        if (lowerCaseSubtraction >= 0 && lowerCaseSubtraction < 26) {
            return lowerCaseSubtraction + LOWERCASE_OFFSET;
        } else if (upperCaseSubtraction >= 0 && upperCaseSubtraction < 26) {
            return upperCaseSubtraction + UPPERCASE_OFFSET;
        } else {
            return -1;
        }
    }
    
    // Method used to determine if the character is allowed in the tree
    public static boolean isValidCharacter(char c) {
        return charToInt(c) != -1;
    }

    // Method used to find the character (from an array index) for a specific character
    public static char intToChar(int i) {
        // Math for each character in a category, null character if not found
        if (i == SPACE_INDEX) {
            return ' ';
        } else if (i == LINEFEED_INDEX) {
            return '\n';
        } else if (i < SPACE_INDEX && i >= UPPERCASE_OFFSET) {
            return (char) ('A' + i - UPPERCASE_OFFSET);
        } else if (i < UPPERCASE_OFFSET && i >= LOWERCASE_OFFSET) {
            return (char) ('a' + i - LOWERCASE_OFFSET);
        } else {
            return '\0';
        }
    }
    
}
