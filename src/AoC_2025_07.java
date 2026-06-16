import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AoC_2025_07 {
    public static void main(String[] args) {
        // File myObj = new File("/Users/Lorenzo Galvez/Documents/Work Files/JustCoding/AdventOfCoding/AoCInputs/AOC2025_07.txt");
        File myObj = new File("D:/Real Life/Oracle/NonWork/AoC_2025/AdventOfCoding/AoCInputs/AOC2025_07.txt");

        ArrayList<String> map = new ArrayList<>();
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                map.add(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }

        splits(map);

        // for (String s : map){
        //     System.out.println(s);
        // }
    }

    public static void splits(ArrayList<String> map) {
        int numSplits = 0;
        int startPoint = map.get(0).indexOf("S");
        ArrayList<Integer> beam = new ArrayList<>();
        beam.add(startPoint);
        ArrayList<Long> timeLines = new ArrayList<>();
        for (int t = 0; t < map.get(0).length(); t++) {
            if (t == startPoint){
                timeLines.add(1L);
            } else {
                timeLines.add(0L);
            }
        }

        for (String row : map){
            if(!row.contains("^")) {continue;}
            ArrayList<Integer> toRemove = new ArrayList<>();
            ArrayList<Integer> toAdd = new ArrayList<>();

            for (int i : beam) {
                if (row.charAt(i) == '^') {
                    numSplits++;
                    toRemove.add(i);
                    toAdd.add(i-1);
                    toAdd.add(i+1);
                    Long temp = timeLines.get(i);
                    timeLines.set(i, 0L);
                    timeLines.set(i+1, timeLines.get(i+1)+temp);
                    timeLines.set(i-1, timeLines.get(i-1)+temp);
                }
            }

            beam.removeAll(toRemove);
            for (int i : toAdd) {
                if (!beam.contains(i)) {
                    beam.add(i);
                }
            }
            toRemove.clear();
            toAdd.clear();
        }

        System.out.println(timeLines);

        Long ans = 0L;
        for (Long num : timeLines) {
            ans += num;
        }

        System.out.println(numSplits + "\n" + ans);
    }

    // 1148909128 LOW
    // 15093663987272 CORRECT
}
