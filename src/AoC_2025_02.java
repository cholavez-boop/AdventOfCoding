import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AoC_2025_02 {
    public static Long sum = 0L;
    public static void main(String[] args) {
        // File myObj = new File("/Users/Lorenzo Galvez/Documents/Work Files/JustCoding/AdventOfCoding/AoCInputs/AOC2025_02.txt");
        File myObj = new File("D:/Real Life/Oracle/NonWork/AoC_2025/AdventOfCoding/AoCInputs/AOC2025_02.txt");

        ArrayList<String> numRanges = new ArrayList<>();
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] ranges = data.split(",");
                for (int i = 0; i < ranges.length; i++) {
                    numRanges.add(ranges[i]);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }

        for (String range : numRanges) {
            checkNum(range);
        }

        System.out.println(sum);
    }

    public static void checkNum(String range) {
        String[] fullRange = range.split("-");
        Long start = Long.parseLong(fullRange[0]);
        Long end = Long.parseLong(fullRange[1]);
        for (Long i = start; i <= end; i++) {
            String numStr = String.valueOf(i);
            int len = numStr.length();
            boolean valid = false;
            for (int secLen = 1; secLen <= len / 2; secLen++) {
                if (len % secLen == 0) {
                    String section = numStr.substring(0, secLen);
                    StringBuilder builtNum = new StringBuilder();
                    int repeatTimes = len / secLen;
                    for (int j = 0; j < repeatTimes; j++) {
                        builtNum.append(section);
                    }
                    if (builtNum.toString().equals(numStr)) {
                        valid = true;
                        break;
                    }
                }
            }
            if (valid) {
                sum += i;
            }
        }
        
    }

    // public static void checkNum(String range) {
    //     String[] fullRange = range.split("-");
    //     int endLen = fullRange[1].length();
    //     Long start = Long.parseLong(fullRange[0]);
    //     Long end = Long.parseLong(fullRange[1]);
    //     int secLen = 1;
    //     ArrayList<Long> validNums = new ArrayList<>();
    //     do {
    //         String section = fullRange[0].substring(0, secLen);
    //         Long val = 0L;
    //         while (val <= end) {
    //             val = generateLong(section, fullRange[0].length());
    //             if (val >= start && val <= end && !validNums.contains(val)) {
    //                 System.out.println(range + " " + val);
    //                 validNums.add(val);
    //             }
    //             section = String.valueOf(Integer.parseInt(section) + 1);
    //         }
    //         secLen++;
    //     } while (secLen <= endLen/2);
    //     for (Long validNum : validNums) {
    //         sum += validNum;
    //     }
    // }

    // public static Long generateLong(String section, int goalLen) {
    //     StringBuilder newSec = new StringBuilder(section);
    //     do {
    //         newSec.append(section);
    //     } while (newSec.toString().length() < goalLen);
    //     return Long.parseLong(newSec.toString());
    // }

    /*
     * RIGHT
     * 35367539282 PART 1
     * 
     * WRONG
     * 39918681190 PART 1 TOO HIGH
     * 45753469068 PART 2 TOO LOW
     * 45753465280 Part 2 TOO LOW
     * 45971524360 PART 2 TOO HIGH
     */
}
