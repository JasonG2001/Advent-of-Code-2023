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

        int c = getCombinationsCount("?#????..#??????#????", Arrays.asList(2, 2, 1, 9));
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

        int curCount = counts.get(0);
        String curSequence;
        if (counts.size() == 1) {
            if (curCount == springs.length()) {
                curSequence = springs;
            } else if (curCount < springs.length()) {
                curSequence = springs.substring(0, curCount);
            } else {
                return 0;
            }
        } else {
            if (curCount >= springs.length()) {
                return 0;
            } else {
                curSequence = springs.substring(0, curCount);
            }
        }
        
        if (counts.size() == 1) {
            if (curCount > springs.length() || (springs.charAt(0) == '#' 
                        && springs.substring(curCount).contains("#"))) {
                return 0;
            } else if (curSequence.contains(".") 
                    || (springs.substring(curCount).contains("#") && 
                        springs.charAt(0) != '#')) {
                return getCombinationsCount(springs.substring(1), counts);
            } else if (springs.charAt(0) == '#' 
                    && !springs.substring(curCount).contains("#")) {
                return 1;
            } else {
                return 1 + getCombinationsCount(springs.substring(1), counts);
            }
        } else if (curCount >= springs.length() - 1) {
            return 0;
        } else if (springs.charAt(0) == '#') {
            return springs.charAt(curCount) == '#' ? 0 
                : curSequence.contains(".") ? 0 
                : getCombinationsCount(springs.substring(curCount + 1), counts.subList(1, counts.size()));
        } else if (!curSequence.contains(".") 
                && springs.charAt(curCount) != '#') {
            return getCombinationsCount(springs.substring(1), counts) 
                + getCombinationsCount(springs.substring(curCount + 1), counts.subList(1, counts.size()));
        } else {
            return getCombinationsCount(springs.substring(1), counts);
        }
    }
}
