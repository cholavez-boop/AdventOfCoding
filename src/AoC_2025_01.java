import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AoC_2025_01 {

    public static int dialNum = 50;
    public static int numZ = 0;
    public static int zz = 0;
    public static void main(String[] args) {
        // File myObj = new File("/Users/Lorenzo Galvez/Documents/Work Files/JustCoding/AdventOfCoding/AoCInputs/AOC2025_01.txt");
        File myObj = new File("D:/Real Life/Oracle/NonWork/AoC_2025/AdventOfCoding/AoCInputs/AOC2025_01.txt");

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

        // ArrayList<String> smaller = new ArrayList<>();
        // for (int i = 0; i < 10; i++) {
        //     smaller.add(inputMap.get(i));
        // }

        // System.out.println("Total Lines Read: " + inputMap.size());
        for (String instruction : inputMap) {
            boolean right = instruction.charAt(0) == 'R';
            String num = instruction.substring(1);
            if (num.length() > 2) {
                num = num.substring(num.length()-2);
                numZ += Integer.parseInt(instruction.substring(1, instruction.length()-2));
                // int hundo = Integer.parseInt(instruction.substring(1, instruction.length()-2));
                // numZ += hundo;
                // System.out.println(instruction + " " + hundo + " " + num);
            }
            int val = Integer.parseInt(num);
            // System.out.println("----------\nCurrVal:" + dialNum + "\nRotate: " + instruction);
            dialNum(right, val);
            if (dialNum == 0) {
                zz++;
            }
            // System.out.println("Current Dial Number: " + dialNum + "\nZeroes: " + numZ);
        }

        System.out.println("Times Zeroed: " + numZ + " " + zz);
    }

    public static void dialNum(boolean right, int val) {
        if (right) {
            dialNum += val;
            if (dialNum > 99) {
                numZ++;
                dialNum -= 100;
            }
        } else {
            dialNum -= val;
            if (dialNum <= 0) {
                numZ++;
                if (dialNum < 0) {
                    dialNum += 100;
                }
            }
        }
    }
}
