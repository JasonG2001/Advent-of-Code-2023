import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Day2 {
    
    public static void main(String[] args) {
        Map<Integer, String> games = readFileIn("day2_input.txt");
        int sumAllIds = sumIds(games, 12, 13, 14);
        System.out.println(sumAllIds); // Part 1: 2162
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
            }
            reader.close();
        } catch(FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        return lines;
    }

    public static int sumIds(Map<Integer, String> games, int red, int green, int blue) {
        int summ = 0;
        String colorsPerGame;
        int gameID;
        String allRounds;
        for (Map.Entry<Integer, String> game : games.entrySet()) {
            gameID = game.getKey();
            allRounds = game.getValue();
            if (isAllRoundsPossible(allRounds, red, green, blue)) {
                summ += gameID;
            }
        }
        return summ;
    }

    public static boolean isAllRoundsPossible(String allRounds, int red, int green, int blue) {
        String[] allRoundsArr = allRounds.split(";");
        for (String round : allRoundsArr) {
            if (!isPossible(round.strip(), red, green, blue)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPossible(String colorsPerRound, int red, int green, int blue) {
        String[] colorsCombo = colorsPerRound.split(",");
        int count;
        String color;
        for (String colorCombo : colorsCombo) {
            String[] countWithColor = colorCombo.strip().split(" ");
            count = Integer.parseInt(countWithColor[0]);
            color = countWithColor[1];
            if ((color.equals("red") && red < count) || (color.equals("green") && green < count) || (color.equals("blue") && blue < count)) {
                return false;
            }
        }
        return true;
    }
}
