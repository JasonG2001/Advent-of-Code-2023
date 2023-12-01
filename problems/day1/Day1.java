import java.util.File;
import java.util.FileNotFoundException;
import java.util.Scanner;

public class Day1 {
    
    public static void main(String[] args) {
        try {
            File file = new File("day1_input.txt"); 
            Scanner reader = new Scanner (file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                System.out.println(data);
            }
            reader.close();
        } catch (FileNotFoundException) {
            System.out.println("No file found");
        }        
    }
}
