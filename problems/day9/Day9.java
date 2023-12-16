import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class Day9 {

    private static String fileName = "day9_input.txt";
    private static List<String> fileLines = readFile(fileName);
    private static long summ = sumAllNums(fileLines);

    public static void main(String[] args) {
        System.out.println(summ); // Part 1: 1782868781
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

    public static long sumAllNums(List<String> fileLines) {
        long summ = 0;
        for (String line : fileLines) {
            long[] convertedArray = convertToArrayOfLongs(line);
            summ += getNextNum(convertedArray);
        }
        return summ;
    }

    public static long[] convertToArrayOfLongs(String history) {
        String[] historyArray = history.split(" ");
        long[] convertedArray = new long[historyArray.length];
        for (int i = 0; i < historyArray.length; i++) {
            convertedArray[i] = Long.parseLong(historyArray[i]);
        }
        return convertedArray;
    }

    public static long getNextNum(long[] history) {
        int length = history.length;
        Set<Long> historySet = new HashSet<>();
        for (long value : history) {
            historySet.add(value);
        }

        if (historySet.size() == 1) {
            return history[0];
        }

        long[] nextSequence = new long[length - 1];
        for (int i = 0; i < nextSequence.length; i++) {
            nextSequence[i] = history[i+1] - history[i];
        }
        return history[length - 1] + getNextNum(nextSequence);
    }
}
