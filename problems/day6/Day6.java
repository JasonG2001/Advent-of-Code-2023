import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Day6 {
    private static List<List<String>> timesAndDistances = readFile("day6_input.txt");
    private static int allWaysToBeatRecord = getWaysToBeatRecord(timesAndDistances);
    private static long waysToBeatTotalRecord = getWaysToBeatTotalRecord(timesAndDistances);

    public static void main(String[] args) {
        System.out.println(allWaysToBeatRecord); // Part 1: 170000
        System.out.println(waysToBeatTotalRecord); // Part 2: 20537782
    }

    public static List<List<String>> readFile(String fileName) {
        List<List<String>> res = new ArrayList<>();
        try {
            Scanner reader = new Scanner(new File(fileName));
            while (reader.hasNextLine()) {
                String[] data = reader.nextLine().split(":")[1].strip().split("\\s+");
                res.add(Arrays.asList(data));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        return res;
    }

    public static long getWaysToBeatTotalRecord(List<List<String>> timesAndDistances) {
        String time = "", distance = "";
        for (int i = 0; i < timesAndDistances.get(0).size(); i++) {
            time += timesAndDistances.get(0).get(i);
            distance += timesAndDistances.get(1).get(i);
        }
        return getWaysToBeatCertainRecord(Long.parseLong(time), Long.parseLong(distance));
    }

    public static int getWaysToBeatRecord(List<List<String>> timesAndDistances) {
        int waysToBeat = 1;
        List<String> times = timesAndDistances.get(0);
        List<String> distances = timesAndDistances.get(1);
        for (int i = 0; i < times.size(); i++) {
            waysToBeat *= getWaysToBeatCertainRecord(Integer.parseInt(times.get(i)), Integer.parseInt(distances.get(i)));
        }
        return (int) waysToBeat;
    }

    public static long getWaysToBeatCertainRecord(long time, long distance) {
        long p1 = 0, p2 = time, mid;
        long ways = 1;

        while (p1 <= p2) {
            mid = p1 + (p2 - p1) / 2;
            if (calculateDistance(mid, time) > distance) {
                p1 = mid - 1; 
                p2 = mid + 1;
                while (p1 >= 0 && calculateDistance(p1, time) > distance) {
                    ways++;
                    p1--;
                }
                while (p2 <= distance && calculateDistance(p2, time) > distance) {
                    ways++;
                    p2++;
                }
                break;
            } else if (calculateDistance(mid + 1, time) > calculateDistance(mid - 1, time)) {
                p1 = mid + 1;
            } else {
                p2 = mid - 1;
            }
        }
        return ways;
    }

    public static long calculateDistance(long timeHeld, long timeTotal) {
        return timeHeld * (timeTotal - timeHeld);
    } 
}
