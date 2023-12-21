import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Day11 {

    public static void main(String[] args) {
        List<String> fileLines = readFile("day11_input.txt");
        List<List<Integer>> allHashTagCoords = getAllCoords(fileLines);
        List<Integer> emptyRows = getEmptyRows(fileLines);
        List<Integer> emptyCols = getEmptyCol(fileLines);

        List<List<Long>> alteredCoordsP1 = alterAllCoords(allHashTagCoords, emptyRows, emptyCols, 1);
        long sumOfDistancesP1 = getSumOfAllDistances(alteredCoordsP1);
        System.out.println(sumOfDistancesP1); // Part 1: 10422930
        
        List<List<Long>> alteredCoordsP2 = alterAllCoords(allHashTagCoords, emptyRows, emptyCols, 999999);
        long sumOfDistancesP2 = getSumOfAllDistances(alteredCoordsP2);
        System.out.println(sumOfDistancesP2); // Part 2: 699909023130
    }

    public static List<String> readFile(String fileName) {
        List<String> fileLines = new ArrayList<>();
        try (Scanner reader = new Scanner(new File(fileName))) {
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                fileLines.add(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        return fileLines;
    }

    public static List<List<Integer>> getAllCoords(List<String> fileLines) {
        List<List<Integer>> allCoords = new ArrayList<>();
        for (int i = 0; i < fileLines.size(); i++) {
            for (int j = 0; j < fileLines.get(0).length(); j++) {
                if (fileLines.get(i).charAt(j) == '#') {
                    allCoords.add(Arrays.asList(i, j));
                }
            }
        }
        return allCoords;
    }

    public static List<List<Long>> alterAllCoords(List<List<Integer>> originalCoords, List<Integer> rowNumbers, List<Integer> colNumbers, int additionalSpace) {
        List<List<Long>> alteredCoords = new ArrayList<>();
        for (int i = 0; i < originalCoords.size(); i++) {
            List<Long> alteredCoord = alterCoords(originalCoords.get(i), rowNumbers, colNumbers, additionalSpace);
            alteredCoords.add(alteredCoord);
        }
        return alteredCoords;
    }

    public static List<Long> alterCoords(List<Integer> originalCoord, List<Integer> rowNumbers, List<Integer> colNumbers, int additionalSpace) {
        final int X = 0, Y = 1;
        long x = originalCoord.get(X), y = originalCoord.get(Y);
        for (int rowNumber : rowNumbers) {
            if (rowNumber < originalCoord.get(X)) {
                x += additionalSpace;
            }
        }
        for (int colNumber : colNumbers) {
            if (colNumber < originalCoord.get(Y)) {
                y += additionalSpace;
            }
        }
        return Arrays.asList(x, y);
    }

    public static List<Integer> getEmptyRows(List<String> fileLines) {
        List<Integer> emptyRows = new ArrayList<>();
        for (int i = 0; i < fileLines.size(); i++) {
            boolean isEmpty = true;
            for (int j = 0; j < fileLines.get(0).length(); j++) {
                if (fileLines.get(i).charAt(j) == '#') {
                    isEmpty = false;
                    break;
                }
            }
            if (isEmpty) {
                emptyRows.add(i);
            }
        }
        return emptyRows;
    }

    public static List<Integer> getEmptyCol(List<String> fileLines) {
        List<Integer> emptyCols = new ArrayList<>();
        for (int i = 0; i < fileLines.get(0).length(); i++) {
            boolean isEmpty = true;
            for (int j = 0; j < fileLines.size(); j++) {
                if (fileLines.get(j).charAt(i) == '#') {
                    isEmpty = false;
                    break;
                }
            }
            if (isEmpty) {
                emptyCols.add(i);
            }
        }
        return emptyCols;
    }


    public static long getSumOfAllDistances(List<List<Long>> hashTagCoordinates) {
        long summ = 0;
        for (int i = 0; i < hashTagCoordinates.size(); i++) {
            for (int j = i + 1; j < hashTagCoordinates.size(); j++) {
                summ += getDistance(hashTagCoordinates.get(i), hashTagCoordinates.get(j));
            }
        }
        return summ;
    } 

    public static long getDistance(List<Long> coord1, List<Long> coord2) {
        final int X = 0, Y = 1;
        return Math.abs(coord2.get(Y) - coord1.get(Y)) + Math.abs(coord2.get(X) - coord1.get(X));
    }
}
