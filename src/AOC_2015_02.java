import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AOC_2015_02 {
    public static void main(String[] args) {
        // File myObj = new File("/Users/Lorenzo Galvez/Documents/Work Files/JustCoding/AdventOfCoding/AoCInputs/AOC2023_12.txt");
        File myObj = new File("D:/Real Life/Oracle/NonWork/AoC_2025/AdventOfCoding/AoCInputs/AOC2015_02.txt");

        //Read File
        ArrayList<ArrayList<Integer>> input = new ArrayList<>();
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String nextLine = myReader.nextLine();
                String[] inputLine = nextLine.split("x");
                ArrayList<Integer> innerInput = new ArrayList<>();
                for (String s : inputLine) {
                    innerInput.add(Integer.parseInt(s));
                }
                input.add(innerInput);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }

        //System.out.println(input);

        /*
        Plan
        find least value (arrange? or get min?)
        sum of 3*least + 2*other1 + 2*other2
        sum of all
        */

        ArrayList<Integer> areaNeeded = new ArrayList<>();
        ArrayList<Integer> ribbons = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            int a = input.get(i).get(0);
            int b = input.get(i).get(1);
            int c = input.get(i).get(2);
            int max = Math.max(a, Math.max(b, c));
            ribbons.add(2 * (a + b + c - max) + a * b * c);

            int areaA = a * b;
            int areaB = b * c;
            int areaC = a * c;
            int least = Math.min(areaA, Math.min(areaB, areaC));
            areaNeeded.add(2 * areaA + 2 * areaB + 2 * areaC + least);
        }

        int sum = 0;
        for (Integer i : areaNeeded) {
            sum += i;
        }
        System.out.println(sum);

        int ribbonSum = 0;
        for (Integer i : ribbons) {
            ribbonSum += i;
        }
        System.out.println(ribbonSum);
    }
}
