import java.io.*;

public class IndiaWordCounter {
    public static void main(String[] args) {
        // Change the file path to your actual file location
        String filePath = "C:\\Users\\Hp\\eclipse-workspace\\13_Nov_Weekly_Assignment\\input"; 

        int count = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\W+");
                
                for (String word : words) {
                    if (word.equalsIgnoreCase("India")) {
                        count++;
                    }
                }
            }

            reader.close();

            System.out.println("The word 'India' appears " + count + " times in the file.");

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}
