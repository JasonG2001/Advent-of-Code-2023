import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Day8 {

    private static Map<String, String[]> map = new HashMap<>();

    public static void main(String[] args) {
        String instructions = getInstructions("day8_input.txt");
        int steps = countSteps("AAA", "ZZZ", instructions);
        System.out.println(steps); // Part 1: 21797
    }

    private static String getInstructions(String fileName) {
        String instructions = "";
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
        return instructions;
    }

    private static void addToMap(String data) {
        String[] keyValues = data.split("=");
        String key = keyValues[0].strip();
        String[] value = {keyValues[1].strip().substring(1, 4), keyValues[1].strip().substring(6, 9)};
        map.put(key, value);
    }

    private static int countSteps(String start, String end, String instructions) {
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
}
