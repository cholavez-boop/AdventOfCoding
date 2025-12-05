import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AoC_2025_02 {
    public static Long sum = 0L;
    public static void main(String[] args) {
        File myObj = new File("/Users/Lorenzo Galvez/Documents/Work Files/JustCoding/AdventOfCoding/AoCInputs/AOC2025_02.txt");
        // File myObj = new File("D:/Real Life/Oracle/NonWork/AoC_2025/AdventOfCoding/AoCInputs/AOC2025_02.txt");

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
        // if (startLen % 2 == 0 || endLen % 2 == 0 || startLen != endLen) {
            // if (startLen % 2 != 0) {
            //     fullRange[0] = newStart(startLen);
            // }
            // if (endLen % 2 != 0) {
            //     fullRange[1] = newEnd(endLen);
            // }
        Long start = Long.parseLong(fullRange[0]);
        Long end = Long.parseLong(fullRange[1]);
        Long val = 0L;
        String section = null;

        if (startLen == 1) {
            fullRange[0] = "10";
        }
        if (startLen == 2) {
            section = String.valueOf(fullRange[0].charAt(0));
        }

        String half = startLen > 2 ? fullRange[0].substring(0, startLen/2) : String.valueOf(fullRange[0].charAt(0));
        System.out.println(fullRange[0] + " to " + fullRange[1] + " (" + half + ")");
        int temp = Integer.parseInt(half);
        while (val < end) {
            String sTemp = String.valueOf(temp);
            StringBuilder newVal = new StringBuilder(sTemp);
            newVal.append(sTemp);
            val = Long.parseLong(newVal.toString());
            if (val <= end && val >= start) {
                System.out.println(val + " < " + end);
                sum += val;
            }
            temp++;
        }
    }

    // public static String newStart(int stringLength) {
    //     StringBuilder ns = new StringBuilder("1");
    //     for (int s = 0; s < stringLength; s++) {
    //         ns.append("0");
    //     }
    //     return ns.toString();
    // }

    // public static String newEnd(int stringLength) {
    //     StringBuilder ne = new StringBuilder();
    //     for (int e = 0; e < stringLength-1; e++) {
    //         ne.append("9");
    //     }
    //     return ne.toString();
    // }

    /*
     * RIGHT
     * 35367539282 PART 1
     * 
     * WRONG
     * 39918681190 TOO HIGH
     */
}
