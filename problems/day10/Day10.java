import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;


public class Day10 {
    
    private static List<String> fileLines = readFile("day10_input.txt");
    private static List<Integer> startingCoords = getStartingCoord(fileLines);
    private static int furthestDistance = getFurthestDistance(startingCoords, fileLines);

    public static void main(String[] args) {
        System.out.println(furthestDistance); // Part 1: 6907
    }

    private static List<String> readFile(String fileName) {
        List<String> fileLines = new ArrayList<>();
        try (Scanner reader = new Scanner(new File(fileName))) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                fileLines.add(line);
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Found not found");
            fnfe.printStackTrace();
        }
        return fileLines;
    } 
    
    private static List<Integer> getStartingCoord(List<String> fileLines) {
        for (int i = 0; i < fileLines.size(); i++) {
            String line = fileLines.get(i);
            for (int j = 0; j < fileLines.get(0).length(); j++) {
                char curChar = line.charAt(j);
                if (curChar == 'S') {
                    List<Integer> start = Arrays.asList(i, j, 0);
                    return start;
                }
            }
        }
        return Arrays.asList(0, 0, 0);
    }

    private static int getFurthestDistance(List<Integer> startingCoordinates, List<String> fileLines) {
        Queue<List<Integer>> q = new LinkedList<>();
        Set<List<Integer>> visited = new HashSet<>();
        int furthestDistance = 0;
        q.add(startingCoordinates);

        while (!q.isEmpty()) {
            List<Integer> curPosition = q.poll();
            List<Integer> coord = Arrays.asList(curPosition.get(0), curPosition.get(1));
            if (visited.contains(coord)) {
                furthestDistance = Math.max(furthestDistance, curPosition.get(2));
                continue;
            }
            visited.add(coord);
            List<List<Integer>> nextPositions = getNextPositions(curPosition, fileLines);
            for (List<Integer> nextPosition : nextPositions) {
                if (visited.contains(Arrays.asList(nextPosition.get(0), nextPosition.get(1)))) {
                    furthestDistance = Math.max(furthestDistance, nextPosition.get(2));
                    continue;
                }
                q.add(nextPosition);

            }
        }
        return furthestDistance - 1;
    }

    private static List<List<Integer>> getNextPositions(List<Integer> coords, List<String> fileLines) {
        Set<Character> top = new HashSet<>(Arrays.asList('7', '|', 'F'));
        Set<Character> bottom = new HashSet<>(Arrays.asList('J', '|', 'L'));
        Set<Character> left = new HashSet<>(Arrays.asList('-', 'F', 'L'));
        Set<Character> right = new HashSet<>(Arrays.asList('-', 'J', '7'));        

        List<List<Integer>> nextPositions = new ArrayList<>();
        int row = coords.get(0), col = coords.get(1), pos = coords.get(2);
        if (row - 1 >= 0 && top.contains(fileLines.get(row - 1).charAt(col))) {
            List<Integer> pos1 = Arrays.asList(row - 1, col, pos + 1);
            nextPositions.add(pos1);
        }
        if (row + 1 < fileLines.size() && bottom.contains(fileLines.get(row + 1).charAt(col))) {
            List<Integer> pos2 = Arrays.asList(row + 1, col, pos + 1);
            nextPositions.add(pos2);
        }
        if (col - 1 >= 0 && left.contains(fileLines.get(row).charAt(col - 1))) {
            List<Integer> pos3 = Arrays.asList(row, col - 1, pos + 1);
            nextPositions.add(pos3);
        }
        if (col + 1 < fileLines.get(0).length() && right.contains(fileLines.get(row).charAt(col + 1))) {
            List<Integer> pos4 = Arrays.asList(row, col + 1, pos + 1);
            nextPositions.add(pos4);
        }
        return nextPositions;
    }
}
