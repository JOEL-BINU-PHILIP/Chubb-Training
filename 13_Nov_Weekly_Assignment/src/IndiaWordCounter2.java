import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class IndiaWordCounter2 {
    public static void main(String[] args) throws Exception {

        List<String> lines = Files.readAllLines(Paths.get("data.txt"));

        var wordsStream = lines.stream().map(a->a.toLowerCase()).map(line -> line.split("[^a-z]+"));   
        
        long count = wordsStream
                .flatMap(Arrays::stream)
                .filter(word -> word.equals("india"))
                .count();

        System.out.println("Total occurrences of 'India': " + count);
    }
}