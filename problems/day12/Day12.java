import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day12 {

    public static void main(String[] args) {
        List<String> fileLines = readFile("day12_input.txt");
        int totalSum = sumAllCombinations(fileLines);
        System.out.println(totalSum);

        int c = getCombinationsCount("#.?.#", Arrays.asList(1));
        System.out.println(c);
    }

    public static List<String> readFile(String fileName) {
        List<String> fileLines = new ArrayList<>();
        try (Scanner reader = new Scanner(new File(fileName))) {
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                fileLines.add(data);
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found");
            fnfe.printStackTrace();
        }
        return fileLines;
    }

    public static int sumAllCombinations(List<String> fileLines) {
        int totalSum = 0;
        for (String line : fileLines) {
            String[] springsAndCounts = line.split(" ");
            String springs = springsAndCounts[0];
            List<Integer> counts = convertToInts(springsAndCounts[1]);
            totalSum += getCombinationsCount(springs, counts);
        }
        return totalSum;
    }

    public static List<Integer> convertToInts(String counts) {
        List<Integer> res = new ArrayList<>();
        for (String num : counts.split(",")) {
            res.add(Integer.parseInt(num));
        }
        return res;
    }

    public static int getCombinationsCount(String springs, List<Integer> counts) {

        if (counts.size() == 1) {
            if (springs.length() < counts.get(0)) {
                return 0;
            } else if (springs.substring(0, counts.get(0)).contains(".")) {
                return getCombinationsCount(springs.substring(1), counts);
            } else if (springs.charAt(0) == '#') { 
                return springs.substring(counts.get(0)).contains("#") ? 
                    0 : 1;
            } else if (springs.substring(0, counts.get(0)).contains("#")) {
                return springs.substring(counts.get(0)).contains("#") ? 
                    getCombinationsCount(springs.substring(1), counts) : 
                    1 + getCombinationsCount(springs.substring(1), counts);
            // all ?
            } else if (springs.substring(counts.get(0)).contains("#")) {
                return getCombinationsCount(springs.substring(1), counts);
            } else {
                return 1 + getCombinationsCount(springs.substring(1), counts);
            }
        } else if (springs.length() <= counts.get(0)) {
            return 0;
        } else if (springs.substring(0, counts.get(0)).contains(".") || springs.charAt(counts.get(0)) == '#') {
            return getCombinationsCount(springs.substring(1), counts);
        } else {
            return getCombinationsCount(springs.substring(counts.get(0) + 1), counts.subList(1, counts.size())) + 
                getCombinationsCount(springs.substring(1), counts);
        }
    }
}
