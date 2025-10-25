import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AoC_2024_02 {
    public static void main(String[] args) {
        //File myObj = new File("/Users/Lorenzo Galvez/Documents/Work Files/JustCoding/AdventOfCoding/AoCInputs/AOC2024_02.txt");
        File myObj = new File("D:/Real Life/Oracle/NonWork/AoC_2025/AdventOfCoding/AoCInputs/AOC2024_02.txt");

        ArrayList<ArrayList<Integer>> diffLevels = new ArrayList<ArrayList<Integer>>();
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] sNums = data.split("\s");
                ArrayList<Integer> levels = new ArrayList<Integer>();
                for (int i = 0; i < sNums.length; i++) {
                    if (sNums[i] != "") {
                        levels.add(Integer.valueOf(sNums[i]));
                    }
                }
                diffLevels.add(levels);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }

        //System.out.println(diffLevels);
        int safes = 0;
        for (int j = 0; j < diffLevels.size(); j++) {
            if (!checkSafe(diffLevels.get(j))) {
                if (!checkSafeWithDamp(diffLevels.get(j))) {
                    continue;
                }
            }
            safes++;
        }
        System.out.println(safes);  
    }

    //Part 1
    public static boolean checkSafe(ArrayList<Integer> levels) {
        boolean incre = true;
        boolean safe = true;
        for (int k = 1; k < levels.size(); k++) {
            int prev = levels.get(k-1);
            int now = levels.get(k);
            if (Math.abs(now - prev) < 4 && now != prev) {
                if (k == 1) {
                    incre = checkInc(prev, now);
                } else {
                    if (incre != checkInc(prev, now)) {
                        safe = false;
                        break;
                    }
                }
            } else {
                safe = false;
                break;
            }
        }
        return safe;
    }

    public static boolean checkInc(int a, int b) {
        return a < b;
    }

    //Part 2
    public static boolean checkSafeWithDamp(ArrayList<Integer> levels) {
        for (int a = 0; a < levels.size(); a++) {
            ArrayList<Integer> modifiedLevels = new ArrayList<Integer>(levels);
            modifiedLevels.remove(a);
            if (checkSafe(modifiedLevels)) {
                return true;
            }
        }
        return false;
    }
}
