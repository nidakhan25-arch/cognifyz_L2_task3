import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileEncryptionDecryption {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Enter 'E' for Encryption or 'D' for Decryption: ");
        String choice = scanner.nextLine().toUpperCase();


        System.out.print("Enter the file path: ");
        String filePath = scanner.nextLine();

        System.out.print("Enter the shift key (a number): ");
        int shift = scanner.nextInt();


        if (choice.equals("E")) {
            encryptFile(filePath, shift);
        } else if (choice.equals("D")) {
            decryptFile(filePath, shift);
        } else {
            System.out.println("Invalid choice! Please enter 'E' or 'D'.");
        }
    }


    public static void encryptFile(String filePath, int shift) {
        String content = readFile(filePath);
        if (content != null) {
            String encryptedContent = caesarCipher(content, shift);
            writeFile(filePath + "_encrypted.txt", encryptedContent);
            System.out.println("File encrypted successfully.");
        }
    }

    public static void decryptFile(String filePath, int shift) {
        String content = readFile(filePath);
        if (content != null) {
            String decryptedContent = caesarCipher(content, -shift);
            writeFile(filePath + "_decrypted.txt", decryptedContent);
            System.out.println("File decrypted successfully.");
        }
    }


    public static String readFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try {
            Scanner fileScanner = new Scanner(new File(filePath));
            while (fileScanner.hasNextLine()) {
                content.append(fileScanner.nextLine()).append("\n");
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
            return null;
        }
        return content.toString();
    }


    public static void writeFile(String filePath, String content) {
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
        }
    }


    public static String caesarCipher(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                ch = (char) ((ch - base + shift + 26) % 26 + base);
            }
            result.append(ch);
        }

        return result.toString();
    }
}
