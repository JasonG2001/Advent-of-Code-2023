import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Day2 {
    
    public static void main(String[] args) {
        readFileIn("day2_input.txt");
    }

    public static Map<Integer, String> readFileIn(String fileName) {
        Map<Integer, String> lines = new HashMap<Integer, String>();
        try{
            File file = new File(fileName);
            Scanner reader = new Scanner(file);

            while(reader.hasNextLine()) {
                String[] data = reader.nextLine().split(":");
                int gameNumber = Integer.parseInt(data[0].split(" ")[1]);
                lines.put(gameNumber, data[1].strip());
                System.out.println(gameNumber);
                System.out.println(data[1].strip());
            }
            reader.close();
        } catch(FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        return lines;
    }

    public static boolean isPossible(String colorsPerRound, int red, int green, int blue) {
        String[] colorsCombo = colorsPerRound.split(",");
        int count;
        String color;
        for (String colorCombo : colorsCombo) {
            String[] countWithColor = colorCombo.strip().split(" ");
            count = Integer.parseInt(countWithColor[0]);
            color = countWithColor[1];
            if ((color == "red" && red < count) || (color == "green" && green < count) || (color == "blue" && blue < count)) {
                return false;
            }
        }
        return true;
    }
}
