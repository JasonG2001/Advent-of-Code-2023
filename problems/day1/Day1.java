import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day1 {
    
    public static void main(String[] args) {
        List<String> lines = readFile("day1_input.txt");
        int summ = sumAllEndNumbers(lines);
        System.out.println(summ);
    }

    public static List<String> readFile(String fileName) {
        List<String> lines = new ArrayList<>();
        try {
            File file = new File(fileName); 
            Scanner reader = new Scanner (file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                lines.add(data);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No file found");
            e.printStackTrace();
        }        
        return lines;    
    }

    public static int sumAllEndNumbers(List<String> lines) {
        int res = 0;
        for (String s : lines) {
            res += sumEndNumbers(s);
        }
        return res;
    }

    public static int sumEndNumbers(String line) {
        int res = 0;
        int p1 = 0, p2 = line.length() - 1;
        char leftChar = line.charAt(p1), rightChar = line.charAt(p2);
        while (!Character.isDigit(leftChar) || !Character.isDigit(rightChar)) {
            leftChar = line.charAt(p1);
            rightChar = line.charAt(p2);
            if (!Character.isDigit(leftChar)) {
                p1++;
            }
            if (!Character.isDigit(rightChar)) {
                p2--;
            }
        }
        res += Integer.parseInt(String.valueOf(line.charAt(p1)) + String.valueOf(line.charAt(p2)));
        return res;

    }
}
