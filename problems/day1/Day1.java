import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day1 {
    
    public static void main(String[] args) {
        List<String> lines = readFile("day1_input.txt");
        int summ = sumAllEndNumbers(lines);
        System.out.println(summ); // Part1: 55108, Part 2: 56324
    }

    public static List<String> readFile(String fileName) {
        List<String> lines = new ArrayList<>();
        try {
            File file = new File(fileName); 
            Scanner reader = new Scanner (file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                lines.add(data);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No file found");
            e.printStackTrace();
        }        
        return lines;    
    }

    public static int sumAllEndNumbers(List<String> lines) {
        int res = 0;
        for (String s : lines) {
            res += sumEndNumbers(s);
        }
        return res;
    }

    public static Map<String, Character> convertNumbers() {
        Map<String, Character> converter = new HashMap<>() {{
            put("one", '1');
            put("two", '2');
            put("three", '3');
            put("four", '4');
            put("five", '5');
            put("six", '6');
            put("seven", '7');
            put("eight", '8');
            put("nine", '9');
        }};
        return converter;
    }

    public static char containsNumber(String s) {
        Map<String, Character> converter = convertNumbers();
        for (Map.Entry<String, Character> entry : converter.entrySet()) {
            if (s.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return ' ';
    }

    public static int sumEndNumbers(String line) {
        int res = 0;
        int p1 = 0, p2 = line.length() - 1;
        boolean leftFound = false, rightFound = false;
        char leftChar, rightChar;
        char leftNum = ' ', rightNum = ' ';
        while (!leftFound || !rightFound) {
            leftChar = line.charAt(p1);
            rightChar = line.charAt(p2);
            if (!leftFound) {
                if (Character.isDigit(leftChar)) {
                    leftFound = true;
                    leftNum = leftChar;
                } else if (containsNumber(line.substring(0, p1 + 1)) != ' ') {
                    leftFound = true;
                    leftNum = containsNumber(line.substring(0, p1 + 1));
                } else {
                    p1++;
                }
            }
            if (!rightFound) {
                if (Character.isDigit(rightChar)) {
                    rightFound = true;
                    rightNum = rightChar;
                } else if (containsNumber(line.substring(p2)) != ' ') {
                    rightFound = true;
                    rightNum = containsNumber(line.substring(p2));
                } else {
                    p2--;
                }
            }
        }
        res += Integer.parseInt(String.valueOf(leftNum) + String.valueOf(rightNum));
        return res;
    }
}
