import static java.lang.Math.floorMod;

/**
 * @author Yannick Brenning
 * The {@code Cipher} class allows for encryption and
 * decryption of a {@code String} using the Vigenere-Cipher
 */
public class Cipher {
    private final String key;

    /**
     * Constructor for a {@code Cipher} object.
     * @param key Necessary for encryption/decryption
     */
    public Cipher(String key) {
        this.key = key.toLowerCase();
    }

    /**
     * Encrypts text from a given file using the current objects
     * {@code key} paramater and the Vigenere-Cipher.
     * @param inputFileName Name of the file to read original text from
     * @param outputFileName Name of file to write encrypted text to
     * @return encrypted text as {@code String}
     */
    public String encrypt(String inputFileName, String outputFileName) {
        TextProcessor textProcessor = new TextProcessor(inputFileName, outputFileName);
        String text = textProcessor.readFile();

        char[][] table = createTable();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            sb.append(table[text.charAt(i) - 97][key.charAt(i % key.length()) - 97]);
            if ((i + 1) % 50 == 0) sb.append("\n");
            else if ((i + 1) % 5 == 0) sb.append(" ");
        }

        sb.append("\n");
        textProcessor.writeFile(sb.toString());
        return sb.toString();
    }

    /**
     * Decrypts a text from a given file using the current objects
     * {@code key} parameter and the Vigenere-Cipher.
     * @param inputFileName Name of the file to read encrypted text from
     * @param outputFileName Name of the file to write decrypted text to
     * @return decrypted text as {@code String}
     */
    public String decrypt(String inputFileName, String outputFileName) {
        TextProcessor textProcessor = new TextProcessor(inputFileName, outputFileName);
        String text = textProcessor.readFile();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            int currChar = floorMod((text.charAt(i) - 97) - (key.charAt(i % key.length()) - 97), 26);
            sb.append((char) (currChar + 97));

            if ((i + 1) % 50 == 0) sb.append("\n");
        }

        sb.append("\n");
        textProcessor.writeFile(sb.toString());
        return sb.toString();
    }

    /**
     * Creates a Vigenere-Table using a 2D array
     * @return Vigenere-table as a 2-Dimensional array of {@code char}s
     */
    public static char[][] createTable() {
        char[][] table = new char[26][26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                table[i][j % 26] = (char) ((j + i) % 26 + 97);
            }
        }

        return table;
    }

    /**
     * Prints a Vigenere-Table onto the console.
     */
    public static void printTable() {
        char[][] output = createTable();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                System.out.print(output[i][j]);
            }
            System.out.println();
        }

        System.out.println();
    }
}
