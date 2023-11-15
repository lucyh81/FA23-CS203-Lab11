import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

public class Encrypter {

    private int shift;
    private String encrypted;

    /**
     * Default Constructor
     */
    public Encrypter() {
        this.shift = 1;
        this.encrypted = "";
    }

    /**
     * Non-default Constructor
     * @param s - custom shift amount
     */
    public Encrypter(int s) {
        this.shift = s;
        this.encrypted = "";
    }

    /**
     * Encrypts the content of a file and writes the result to another file.
     *
     * @param inputFilePath      the path to the file containing the text to be encrypted
     * @param encryptedFilePath the path to the file where the encrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void encrypt(String inputFilePath, String encryptedFilePath) throws Exception {
        //TODO: Call the read method, encrypt the file contents, and then write to new file
        try(Scanner fileScanner = new Scanner(Paths.get(inputFilePath))){
            StringBuilder content = new StringBuilder();
            while(fileScanner.hasNextLine()){
                String text = fileScanner.nextLine();
                String encryptedText = encryptText(text);
                content.append(encryptedText).append("\n");
            }
            writeFile(content.toString(), encryptedFilePath);
        } catch(IOException e) {
            System.out.println("Error; " +e.toString());
        }
    }
    private String encryptText(String text) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char originalChar = text.charAt(i);
            char encryptedChar = (char) (originalChar + shift);
            result.append(encryptedChar);
        }

        return result.toString();
    }

    /**
     * Decrypts the content of an encrypted file and writes the result to another file.
     *
     * @param messageFilePath    the path to the file containing the encrypted text
     * @param decryptedFilePath the path to the file where the decrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void decrypt(String messageFilePath, String decryptedFilePath) throws Exception {
        //TODO: Call the read method, decrypt the file contents, and then write to new file
        try (Scanner fileScanner = new Scanner(Paths.get(messageFilePath))){
            StringBuilder content = new StringBuilder();
            while (fileScanner.hasNextLine()){
                String encryptedText = fileScanner.nextLine();
                String decryptedText = decryptedText(encryptedText);
                content.append(decryptedText).append("\n");
            }
            writeFile(content.toString(), decryptedFilePath);
        }catch (IOException e){
            System.out.println("Error: " + e.toString());
        }
    }
     private String decryptedText(String encryptedText) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < encryptedText.length(); i++) {
            char encryptedChar = encryptedText.charAt(i);
            char decryptedChar = (char) (encryptedChar - shift);
            result.append(decryptedChar);
        }

        return result.toString();
    }

    /**
     * Reads the content of a file and returns it as a string.
     *
     * @param filePath the path to the file to be read
     * @return the content of the file as a string
     * @throws Exception if an error occurs while reading the file
     */
    private static String readFile(String filePath) throws Exception {
        String message = "";
        //TODO: Read file from filePath
        for (String line:Files.readAllLines(Paths.get(filePath))){
            message+=line +"\n";
        }
        return message;
    }

    /**
     * Writes data to a file.
     *
     * @param data     the data to be written to the file
     * @param filePath the path to the file where the data will be written
     */
    private static void writeFile(String data, String filePath) {
        //TODO: Write to filePath
       try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
           writer.println(data);
    }catch(IOException e){
           System.out.println("Error: "+e.toString());
       }
    }

    /**
     * Returns a string representation of the encrypted text.
     *
     * @return the encrypted text
     */
    @Override
    public String toString() {
        return encrypted;
    }
    fileScanner.close();
}
