public class Cipher {
    private final String key;
    private TextProcessor textProcessor;

    public Cipher(String key) {
        this.key = key.toLowerCase();
        this.textProcessor = null;
    }

    public String encrypt(String inputFileName, String outputFileName) {
        textProcessor = new TextProcessor(inputFileName, outputFileName);
        String text = textProcessor.readFile();

        char[][] table = createTable();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            sb.append(table[text.charAt(i) - 97][key.charAt(i % key.length()) - 97]);
            if ((i + 1) % 50 == 0) sb.append("\n");
            else if ((i + 1) % 5 == 0) sb.append(" ");
        }

        textProcessor.writeFile(sb.toString());
        return sb.toString();
    }

    public String decrypt(String inputFileName, String outputFileName) {
        textProcessor = new TextProcessor(inputFileName, outputFileName);
        String text = textProcessor.readFile();
        //Next: decrypt implementation

        return "";
    }

    public static char[][] createTable() {
        char[][] table = new char[26][26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                table[i][j % 26] = (char) ((j + i) % 26 + 97);
            }
        }

        return table;
    }

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
