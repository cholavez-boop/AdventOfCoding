import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AoC_2024_01 {
    public static void main(String[] args) {
        File myObj = new File("/Users/Lorenzo Galvez/Documents/Work Files/JustCoding/AdventOfCoding/AoCInputs/AOC2024_01.txt");

        try (Scanner myReader = new Scanner(myObj)) {
            List<Integer> leftNums = new ArrayList<>();
            List<Integer> rightNums = new ArrayList<>();
            boolean first = true;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] sNums = data.split("\s");
                for (String s : sNums) {
                    if (s != "") {
                        if (first) {
                            leftNums.add(Integer.valueOf(s));
                        } else {
                            rightNums.add(Integer.valueOf(s));
                        }
                        first = !first;
                    }
                }
            }
            /*
            //----------------------------------------Part 1 Solution
            leftNums.sort(null);
            rightNums.sort(null);
            //System.out.println(leftNums);
            //System.out.println(rightNums);

            //List<Integer> diffs = new ArrayList<>();
            int dist = 0;
            for (int i = 0; i < leftNums.size(); i++) {
                //diffs.add(
                dist += Math.abs(leftNums.get(i) - rightNums.get(i));
            }

            //System.out.println(diffs);
            System.out.println(dist);
            */

            //----------------------------------------Part 2 Solution
            HashMap<Integer, Integer> repeats = new HashMap<Integer, Integer>();
            //(ID, times found)
            HashMap<Integer, Integer> multiplier = new HashMap<Integer, Integer>();
            //(ID, multiplier)

            for (int i = 0; i < leftNums.size(); i++) {
                int key = leftNums.get(i);
                if (repeats.containsKey(key)) {
                    if (!multiplier.containsKey(key)) {
                        multiplier.put(key, 1);
                    }
                    multiplier.replace(key, repeats.get(key)+1);
                } else {
                    int times = 0;
                    while (rightNums.contains(key)) {
                        times++;
                        rightNums.remove(rightNums.indexOf(key));
                    }
                    if (times > 0 ) {
                        repeats.put(key, times);
                    }
                }
            }

            //System.out.println(repeats);
            //System.out.println(multiplier);
            int simScore = 0;
            for (int ki : repeats.keySet()) {
                simScore += ki * repeats.get(ki);
            }
            System.out.println(simScore);

        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }
    }
}
