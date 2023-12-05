import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Day4 {

    public static void main(String[] args) {
        
    }

    public static List<String> giveFile(String fileName) {
        List<String> file = new ArrayList<>();
        try {
            Scanner reader = new Scanner(new File(fileName));
            while(reader.hasNextLine()) {
                String data = reader.nextLine();
                file.add(data);
            }
            reader.close();
        } catch(FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        return file;
    }
}
