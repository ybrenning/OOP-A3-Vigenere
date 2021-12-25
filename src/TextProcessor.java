import java.io.*;
import java.util.Scanner;

public class TextProcessor {
    private String inputFileName;
    private String outputFileName;

    public TextProcessor(String inputFileName, String outputFileName) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
    }

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

    public void printFile() {

    }
}
