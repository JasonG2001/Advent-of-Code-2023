import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day3 {

    public static void main(String[] args) {
        List<String> file = readFile("day3_input.txt");
        int summ = sumPartNumbers(file);
        System.out.println(summ); // Part 1: 550064
        int sumOfRatios = sumAllGearRatios(file);
        System.out.println(sumOfRatios);
    }

    public static List<String> readFile(String fileName) {
        List<String> lines = new ArrayList<String>();
        try {
            Scanner reader = new Scanner(new File(fileName));
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                lines.add(data);
            }
            reader.close();
        } catch(FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        return lines;
    }

    public static int sumPartNumbers(List<String> lines) {
        int res = 0;
        for (int rowNum = 0; rowNum < lines.size(); rowNum++) {
            res += sumPartNumbersPerLine(rowNum, lines.get(rowNum), lines);
        }
        return res;
    }

    public static int sumPartNumbersPerLine(int rowNum, String row, List<String> lines) {
        int res = 0;
        char curChar;
        for (int i = 0; i < row.length(); i++) {
            curChar = lines.get(rowNum).charAt(i);
            if (Character.isDigit(curChar)) {
                String num = getNum(row, i);
                if (isPartNum(rowNum, i, num.length(), lines)) {
                    res += Integer.parseInt(num);
                }
                i += num.length();
            }
        }
        return res;
    }

    public static String getNum(String line, int firstIndex) {
        String num = "";
        while (firstIndex < line.length() && Character.isDigit(line.charAt(firstIndex))) {
            num += line.charAt(firstIndex);
            firstIndex++;
        }
        return num;
    }

    public static boolean isPartNum(int rowNum, int colNum, int numLen, List<String> lines) {
        int rowStart = rowNum - 1, rowEnd = rowNum + 1, colStart = colNum - 1, colEnd = colNum + numLen;
        for (int row = rowStart; row <= rowEnd; row++) {
            for (int col = colStart; col <= colEnd; col++) {
                if (row >= 0 && row < lines.size() && col >= 0 && col < lines.get(0).length() && lines.get(row).charAt(col) != '.' && !Character.isDigit(lines.get(row).charAt(col))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int sumAllGearRatios(List<String> lines) {
        int res = 0;
        for (int rowNum = 0; rowNum < lines.size(); rowNum++) {
            for (int colNum = 0; colNum < lines.get(0).length(); colNum++) {
                if (lines.get(rowNum).charAt(colNum) == '*') {
                    res += getGearRatio(rowNum, colNum, lines);
                }
            }
        }
        return res;
    }

    public static int getGearRatio(int rowNum, int colNum, List<String> lines) {
        int rowStart = rowNum - 1, rowEnd = rowNum + 1, colStart = colNum - 1, colEnd = colNum + 1;
        List<Integer> gears = new ArrayList<>();
        for (int row = rowStart; row <= rowEnd; row++) {
            boolean prevIsNum = false;
            for (int col = colStart; col <= colEnd; col++) {
                if (row >= 0 && row < lines.size() && col >= 0 && col < lines.get(0).length()) {
                    char curChar = lines.get(row).charAt(col);
                    if (Character.isDigit(curChar)) {
                        if (!prevIsNum) {
                            int num = getNum(col, lines.get(row));
                            gears.add(num);
                        }
                        prevIsNum = true;
                    } else {
                        prevIsNum = false;
                    }
                }
            }
        }
        if (gears.size() == 2) {
            return gears.get(0) * gears.get(1);
        }
        return 0;
    }

    public static int getNum(int colNum, String line) {
        String num = String.valueOf((line.charAt(colNum)));
        int p1 = colNum - 1, p2 = colNum + 1;
        while(Character.isDigit(line.charAt(p1))) {
            if (Character.isDigit(line.charAt(p1))) {
                num = String.valueOf(line.charAt(p1)) + num;
                if (p1 == 0) {
                    break;
                }
                p1--;
            }
        }
        while(Character.isDigit(line.charAt(p2))) {
            if (Character.isDigit(line.charAt(p2))) {
                num += String.valueOf(line.charAt(p2));
                if (p2 == line.length() - 1) {
                    break;
                }
                p2++;
            }
        }
        return Integer.parseInt(num);
    }
}

