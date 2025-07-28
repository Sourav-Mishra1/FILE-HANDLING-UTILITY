import java.io.IOException;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
public class FileHandlingUtility {

    // Read file content as a String
    public static String readFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
    }

    // Write content to a file (overwrites if exists)
    public static void writeFile(String filePath, String content) throws IOException {
        Files.write(Paths.get(filePath), content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    // Append content to a file
    public static void appendToFile(String filePath, String content) throws IOException {
        Files.write(Paths.get(filePath), content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    // Check if a file exists
    public static boolean fileExists(String filePath) {
        return Files.exists(Paths.get(filePath));
    }

    // Delete a file
    public static boolean deleteFile(String filePath) throws IOException {
        return Files.deleteIfExists(Paths.get(filePath));
    }

    // Copy file from source to destination
    public static void copyFile(String sourcePath, String destinationPath) throws IOException {
        Files.copy(Paths.get(sourcePath), Paths.get(destinationPath), StandardCopyOption.REPLACE_EXISTING);
    }

    // Main method to test the utility
    public static void main(String[] args) {
        String filePath = "sample.txt";
        String copyPath = "sample_copy.txt";

        try {
            // Write to file
            writeFile(filePath, "Hello from Java File Handling Utility!");

            // Append to file
            appendToFile(filePath, "\nAppending more content...");

            // Read file
            String content = readFile(filePath);
            System.out.println("File Content:\n" + content);

            // Copy file
            copyFile(filePath, copyPath);
            System.out.println("File copied to: " + copyPath);

            // Check existence
            System.out.println("Does 'sample.txt' exist? " + fileExists(filePath));

            // Delete original file
            if (deleteFile(filePath)) {
                System.out.println("Deleted file: " + filePath);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
