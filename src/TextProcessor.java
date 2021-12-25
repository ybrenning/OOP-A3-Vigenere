import java.io.*;
import java.util.Scanner;

/**
 * @author Yannick Brenning
 * The {@code TextProcessor} class reads and writes {@code String}s
 * into and from files specified by the user.
 */
public class TextProcessor {
    private String inputFileName;
    private String outputFileName;

    /**
     * Constructor for a {@code TextProcessor} object.
     * @param inputFileName Name of file to read from
     * @param outputFileName Name of file to write to
     */
    public TextProcessor(String inputFileName, String outputFileName) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
    }

    /**
     * Reads the content of the input file and converts it to a {@code String}
     * usable for encryption/decryption by removing irregular symbols.
     * @return Content of the input file for this {@code TextProcessor} object as a {@code String}
     */
    public String readFile() {
        StringBuilder sb = new StringBuilder();
        File file = new File(inputFileName);

        try (Scanner sc = new Scanner(new FileInputStream(file), "Windows-1252")) {
            while (sc.hasNextLine()) {
                sb.append(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String inputFileContent = sb.toString();

        inputFileContent = inputFileContent.toLowerCase();
        inputFileContent = inputFileContent.replaceAll("ß", "ss");
        inputFileContent = inputFileContent.replaceAll("ä", "ae");
        inputFileContent = inputFileContent.replaceAll("ü", "ue");
        inputFileContent = inputFileContent.replaceAll("ö", "oe");
        inputFileContent = inputFileContent.replaceAll(" ", "");
        inputFileContent = inputFileContent.replaceAll("\\p{Punct}", "");

        return inputFileContent;
    }

    /**
     * Writes a given {@code String} into a new file with the name {@code outputFileName}.
     * @param fileContent Content to write into the output file
     */
    public void writeFile(String fileContent) {
        try {
            File file = new File(outputFileName);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(fileContent);
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
