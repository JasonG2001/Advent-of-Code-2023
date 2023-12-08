import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Day5 {

    public static void main(String[] args) {
        System.out.println(readFile("day5_input.txt"));
    }

    public static Map<String, Map<Long[], Long[]>> readFile(String fileName) {
        Map<String, Map<Long[], Long[]>> allMaps = new HashMap<>();
        try {
            Scanner reader = new Scanner(new File(fileName));
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                if (data.contains("map")) {
                    Map<Long[], Long[]> curMap = new HashMap<>();
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

    public static Map<Long[], Long[]> convertToMap(Map<Long[], Long[]> map, String data) {
        String[] sepData = data.split(" ");
        int FIRST = 0, SECOND = 1, THIRD = 2;
        long destination = Long.parseLong(sepData[FIRST]);
        long start = Long.parseLong(sepData[SECOND]);
        long range = Long.parseLong(sepData[THIRD]);
        map.put(new Long[]{start, start + range - 1}, new Long[]{destination + range - 1});
        return map;
    }
}
