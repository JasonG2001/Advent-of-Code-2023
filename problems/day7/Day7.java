import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;


public class Day7 {

    private static Queue<String> q = new PriorityQueue<>(new HandComparator());
    private static Map<String, Integer> bids = new HashMap<>();

    public static void main(String[] args) {
        populateStructures("day7_input.txt");
        long totalWinnings = getTotalWinnings();
        System.out.println(totalWinnings); // Part 1: 241344943 
                                           // Part 2: 243101568
    }
    
    public static void populateStructures(String fileName) {
        try (Scanner reader = new Scanner(new File(fileName))) {
            while (reader.hasNextLine()) {
                String[] line = reader.nextLine().split(" ");
                String hand = line[0];
                String bid = line[1];
                q.add(hand);
                bids.put(hand, Integer.parseInt(bid));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    public static long getTotalWinnings() {
        long totalWinnings = 0;
        int totalHands = bids.size();
        while (!q.isEmpty()) {
            String hand = q.poll();
            totalWinnings += totalHands * bids.get(hand);
            totalHands--;
        }
        return totalWinnings;
    }
}
