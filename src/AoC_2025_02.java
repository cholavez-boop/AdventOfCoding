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
        int startLen = fullRange[0].length();
        int endLen = fullRange[1].length();
        Long start = Long.parseLong(fullRange[0]);
        Long end = Long.parseLong(fullRange[1]);
        int secLen = 1;
        String section = null;
        ArrayList<Long> validNums = new ArrayList<>();
        if (startLen == 1) {
            fullRange[0] = "10";
        }
        do {
            if (secLen == 1) {
                section = String.valueOf(fullRange[0].charAt(0));
            } else {
                section = fullRange[0].substring(0, secLen-1);
            }
            boolean inRange = true;
            while (inRange) {
                StringBuilder newSec = new StringBuilder(section);
                while (newSec.toString().length() < startLen) {
                    newSec.append(section);
                }
                Long val = Long.parseLong(newSec.toString());
                if (val <= end && val >= start && !validNums.contains(val)){
                    System.out.println(range + " " + val);
                    validNums.add(val);
                }
                while (newSec.toString().length() < endLen) {
                    newSec.append(section);
                }
                Long secondVal = Long.parseLong(newSec.toString());
                if (secondVal != val && secondVal <= end && secondVal >= start && !validNums.contains(secondVal)){
                    System.out.println(range + " " + val);
                    validNums.add(val);
                }

                if (val > end || secondVal > end) {
                    inRange = false;
                } else {
                    section = String.valueOf(Integer.parseInt(section) + 1);
                }
            }
            secLen++;
        } while (section.length() <= endLen/2);

        for (Long validNum : validNums) {
            sum += validNum;
        }
    }

    /*
     * RIGHT
     * 35367539282 PART 1
     * 
     * WRONG
     * 39918681190 TOO HIGH
     */
}
