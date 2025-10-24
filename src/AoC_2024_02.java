import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AoC_2024_02 {
    public static void main(String[] args) {
        File myObj = new File("/Users/Lorenzo Galvez/Documents/Work Files/JustCoding/AdventOfCoding/AoCInputs/AOC2024_02.txt");

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
        boolean incre = true;
        boolean skip = false;
        int safes = 0;
        for (int j = 0; j < diffLevels.size(); j++) {
            for (int k = 1; k < diffLevels.get(j).size(); k++) {
                int prev = diffLevels.get(j).get(k-1);
                int now = diffLevels.get(j).get(k);
                if (Math.abs(now - prev) < 4 && now != prev) {
                    if (k == 1) {
                        incre = checkInc(prev, now);
                    } else {
                        if (incre != checkInc(prev, now)) {
                            skip = true;
                            break;
                        }
                    }
                } else {
                    skip = true;
                    break;
                }
            }
            if (skip) {
                skip = false;
                continue;
            }
            safes++;
        }
        System.out.println(safes);
    }

    public static boolean checkInc(int a, int b) {
        return a < b;
    } 
}
