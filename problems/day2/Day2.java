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

        int totalPower = getTotalPower(games);
        System.out.println(totalPower); // Part 2: 72513
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

    public static int getTotalPower(Map<Integer, String> games) {
        int totalPower = 0;
        for (String gameColors : games.values()) {
            totalPower += getTotalPowerPerGame(gameColors);
        }
        return totalPower;
    }

    public static int getTotalPowerPerGame(String gameColors) {
        int[] maxColors = {0, 0, 0}; // rgb
        String[] gameColorsArr = gameColors.split(";");
        for (String roundColors : gameColorsArr) {
            String[] roundColorsArr = roundColors.strip().split(",");
            int[] roundColorCount = getRoundColorCount(roundColorsArr);
            for (int i = 0; i < maxColors.length; i++) {
                maxColors[i] = Math.max(maxColors[i], roundColorCount[i]);
            }
        }
        return maxColors[0] * maxColors[1] * maxColors[2];
    }

    public static int[] getRoundColorCount(String[] roundColors) {
        final int NUM_OF_COLORS = 3, RED_INDEX = 0, GREEN_INDEX = 1, BLUE_INDEX = 2;
        int[] colors = new int[NUM_OF_COLORS];
        int count;
        String color;
        for (String countWithColor : roundColors) {
            String[] countWithColorArr = countWithColor.strip().split(" ");
            count = Integer.parseInt(countWithColorArr[0]);
            color = countWithColorArr[1];
            if (color.equals("red")) {
                colors[RED_INDEX] = count;
            } else if (color.equals("green")) {
                colors[GREEN_INDEX] = count;
            } else {
                colors[BLUE_INDEX] = count;
            }
        }
        return colors;
    }
}
