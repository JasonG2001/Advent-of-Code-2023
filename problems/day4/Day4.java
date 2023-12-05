import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class Day4 {

    public static void main(String[] args) {
        List<String> file = readFile("day4_input.txt");
        int sumAllPoints = sumScratchCardPoints(file);
        System.out.println(sumAllPoints); // Part 1: 18519
    }

    public static List<String> readFile(String fileName) {
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

    public static int sumScratchCardPoints(List<String> file) {
        String[] numbers;
        int totalSum = 0;
        for (String line : file) {
            numbers = line.split(":")[1].split("\\|");
            totalSum += getPointsWorth(numbers);
        }
        return totalSum;
    }

    public static int getPointsWorth(String[] numbers) {
        String[] ourNumbers = numbers[1].strip().split("\\s+");
        String[] winningNumbers = numbers[0].strip().split("\\s+");
        Set<Integer> ourNumbersSet = new HashSet<>();
        int matches = 0;
        for (String ourNumber : ourNumbers) {
            if (!ourNumber.equals("")) {
                ourNumbersSet.add(Integer.parseInt(ourNumber));
            }
        }
        for (String winningNumber : winningNumbers) {
            if (ourNumbersSet.contains(Integer.parseInt(winningNumber))) {
                matches++;
            }
        }
        return (int) Math.pow(2, matches - 1);
    }
}
