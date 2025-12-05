import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AoC_2025_01 {

    public static int dialNum = 50;
    public static int numZ = 0;
    public static int zz = 0;
    public static void main(String[] args) {
        File myObj = new File("/Users/Lorenzo Galvez/Documents/Work Files/JustCoding/AdventOfCoding/AoCInputs/AOC2025_01.txt");
        // File myObj = new File("D:/Real Life/Oracle/NonWork/AoC_2025/AdventOfCoding/AoCInputs/AOC2025_01.txt");

        ArrayList<String> inputMap = new ArrayList<>();
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                inputMap.add(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }

        for (String instruction : inputMap) {
            System.out.println("----------------\n" + dialNum + "\n" + instruction);
            boolean right = instruction.charAt(0) == 'R';
            int val = Integer.parseInt(instruction.substring(1));
            if (val > 99) {
                numZ += val / 100;
                val = val % 100;
            }
            dialNum(right, val);
            if (dialNum == 0) {
                zz++;
            }
            System.out.println(dialNum + "\n" + numZ);
        }

        System.out.println("Times Zeroed: Part 2: " + numZ + " Part 1: " + zz);
    }

    public static void dialNum(boolean right, int val) {
        if (right) {
            dialNum += val;
            if (dialNum > 99) {
                numZ++;
                dialNum -= 100;
            }
        } else {
            int prev = dialNum;
            dialNum -= val;
            if (dialNum <= 0 && prev != 0) {
                numZ++;
            }
            if (dialNum < 0) {
                dialNum += 100;
            }
        }
    }
}
