import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Day5 {
    private static Map<String, Map<Long[], Long>> map = readFile("day5_input.txt");
    private static List<Long> seeds = getSeeds("day5_input.txt");        
    private static String[] mappingOrder = {"seed-to-soil", "soil-to-fertilizer", "fertilizer-to-water", "water-to-light", "light-to-temperature", "temperature-to-humidity", "humidity-to-location"};
    private static long minLocation = getLowestLocation(seeds, map);

    public static void main(String[] args) {
        System.out.println(minLocation); // Part 1: 88151870
    }

    public static Map<String, Map<Long[], Long>> readFile(String fileName) {
        Map<String, Map<Long[], Long>> allMaps = new HashMap<>();
        try {
            Scanner reader = new Scanner(new File(fileName));
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                if (data.contains("map")) {
                    Map<Long[], Long> curMap = new HashMap<>();
                    String mapName = data.split(" ")[0];
                    data = reader.nextLine();
                    while (data != "") {
                        curMap = convertToMap(curMap, data);
                        if (reader.hasNextLine()) {
                            data = reader.nextLine();
                        } else {
                            break;
                        }
                    }
                    allMaps.put(mapName, curMap); 
                }
            }
            reader.close();
        } catch(FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        return allMaps;
    }

    public static Map<Long[], Long> convertToMap(Map<Long[], Long> map, String data) {
        String[] sepData = data.split(" ");
        int FIRST = 0, SECOND = 1, THIRD = 2;
        long destination = Long.parseLong(sepData[FIRST]);
        long start = Long.parseLong(sepData[SECOND]);
        long range = Long.parseLong(sepData[THIRD]);
        map.put(new Long[]{start, start + range - 1}, destination);
        return map;
    }

    public static List<Long> getSeeds(String fileName) {
        List<Long> seeds = new ArrayList<>();
        try {
            Scanner reader = new Scanner(new File(fileName));
            String[] seedsStr = reader.nextLine().split(":")[1].strip().split(" ");
            for (String seed : seedsStr) {
                seeds.add(Long.parseLong(seed));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        return seeds;
    }

    public static long getLowestLocation(List<Long> seeds, Map<String, Map<Long[], Long>> map) {
        long minLocation = Long.MAX_VALUE;
        for (Long seed : seeds) {    
            minLocation = Math.min(getLocation(seed, map), minLocation);
        }
        return minLocation;
    }

    public static long getLocation(Long seed, Map<String, Map<Long[], Long>> map) {
        for (String converter : mappingOrder) {
            Map<Long[], Long> value = map.get(converter);
            seed = convert(value, seed);
        }
        return seed;
    }

    public static long convert(Map<Long[], Long> map, long key) {
        Long[] startRange;
        Long end;
        for (Map.Entry<Long[], Long> entry : map.entrySet()) {
            startRange = entry.getKey();
            end = entry.getValue();
            if (key >= startRange[0] && key <= startRange[1]) {
                return key - startRange[0] + end;
            }
        }
        return key;
    }
}
