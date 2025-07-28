import java.io.*;
import java.util.Scanner;

public class FileHandlingUtility {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        String fileName;

        do {
            System.out.println("\n===== FILE HANDLING UTILITY =====");
            System.out.println("1. Create a File");
            System.out.println("2. Write to a File");
            System.out.println("3. Read from a File");
            System.out.println("4. Append to a File");
            System.out.println("5. Delete a File");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter file name to create: ");
                    fileName = scanner.nextLine();
                    createFile(fileName);
                    break;
                case 2:
                    System.out.print("Enter file name to write to: ");
                    fileName = scanner.nextLine();
                    System.out.print("Enter content to write: ");
                    String content = scanner.nextLine();
                    writeToFile(fileName, content);
                    break;
                case 3:
                    System.out.print("Enter file name to read: ");
                    fileName = scanner.nextLine();
                    readFromFile(fileName);
                    break;
                case 4:
                    System.out.print("Enter file name to append to: ");
                    fileName = scanner.nextLine();
                    System.out.print("Enter content to append: ");
                    String appendContent = scanner.nextLine();
                    appendToFile(fileName, appendContent);
                    break;
                case 5:
                    System.out.print("Enter file name to delete: ");
                    fileName = scanner.nextLine();
                    deleteFile(fileName);
                    break;
                case 0:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
    }

    public static void createFile(String fileName) {
        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getAbsolutePath());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
        }
    }

    public static void writeToFile(String fileName, String content) {
        try (FileWriter writer = new FileWriter(fileName, false)) {
            writer.write(content);
            System.out.println("Successfully written to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
        }
    }

    public static void readFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("=== File Content ===");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("====================");
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
        }
    }

    public static void appendToFile(String fileName, String content) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(content + "\n");
            System.out.println("Content appended successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while appending to the file.");
        }
    }

    public static void deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.delete()) {
            System.out.println("Deleted the file: " + file.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }
}
