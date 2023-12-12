import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Day8 {

    private static Map<String, String[]> map = new HashMap<>();
    private static String instructions = "";
    private static List<String> startingPositions = new ArrayList<>();

    public static void main(String[] args) {
        getInstructions("day8_input.txt");
        int steps = countSteps("AAA", "ZZZ");
        System.out.println(steps); // Part 1: 21797
        populateStartingPositions('A');
        long stepsForAllPositions = countStepsForAllPositions('Z');
        System.out.println(stepsForAllPositions);
    }

    private static void getInstructions(String fileName) {
        try(Scanner reader = new Scanner(new File(fileName))) {
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                if (data.contains("=")) {
                    addToMap(data);
                } else if (data != "") {
                    instructions += data;
                }
            }
        } catch(FileNotFoundException fnfe) {
            System.out.println("File not found");
            fnfe.printStackTrace();
        }
    }

    private static void addToMap(String data) {
        String[] keyValues = data.split("=");
        String key = keyValues[0].strip();
        String[] value = {keyValues[1].strip().substring(1, 4), keyValues[1].strip().substring(6, 9)};
        map.put(key, value);
    }

    private static int countSteps(String start, String end) {
        int position = 0;
        int steps = 0;
        while (!start.equals(end)) {
            if (position == instructions.length()) {
                position = 0;
            }
            char direction = instructions.charAt(position);
            String[] destination = map.get(start);
            start = direction == 'L' ? destination[0] : destination[1];
            position++;
            steps++;
        }
        return steps;
    }

    private static int countStepsToReachFinalChar(char startchar, char endChar)

    private static void populateStartingPositions(char startChar) {
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key.charAt(key.length() - 1) == startChar) {
                startingPositions.add(key);
            }
        }
    }
}
