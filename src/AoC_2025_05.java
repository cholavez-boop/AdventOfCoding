import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AoC_2025_05 {

    public static void main(String[] args) {
        // File myObj = new File("/Users/Lorenzo Galvez/Documents/Work Files/JustCoding/AdventOfCoding/AoCInputs/AOC2025_05.txt");
        File myObj = new File("D:/Real Life/Oracle/NonWork/AoC_2025/AdventOfCoding/AoCInputs/AOC2025_05.txt");

        ArrayList<String> range = new ArrayList<>();
        ArrayList<Long> food = new ArrayList<>();
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.contains("-")) {
                    range.add(data);
                } else if (!data.isBlank()) {
                    food.add(Long.parseLong(data));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }

        ArrayList<ArrayList<Long>> fresh = refineRanges(freshList(range));
        int count = 0;
        for (Long f : food) {
            for (ArrayList<Long> r : fresh) {
                // System.out.println("Fresh Range: " + r.get(0) + " - " + r.get(1));
                if (f >= r.get(0) && f <= r.get(1)) {
                    count++;
                    break;
                }
            }
        }

        System.out.println("Food still good count: " + count);

        Long freshIDs= 0L;
        for (ArrayList<Long> r : fresh) {
            freshIDs += 1 + (r.get(1) - r.get(0));
        }
        System.out.println("Part 2: IDs of Fresh: " + freshIDs);
    }

    /*
     * 408189072454215 TOO HIGH
     * 338693411431456 CORRECT
     */

    public static ArrayList<ArrayList<Long>> freshList(ArrayList<String> range) {
        ArrayList<ArrayList<Long>> fresh = new ArrayList<>();
        for (String r : range) {
            String[] limits = r.split("-");
            long start = Long.parseLong(limits[0]);
            long end = Long.parseLong(limits[1]);
            ArrayList<Long> toAdd = new ArrayList<>();
            toAdd.add(start);
            toAdd.add(end);
            fresh.add(toAdd);
        }
        return fresh;
    }

    public static ArrayList<ArrayList<Long>> refineRanges(ArrayList<ArrayList<Long>> ranges) {
        for (int i = 0; i < ranges.size(); i++) {
            ArrayList<Long> r1 = ranges.get(i);
            for (int j = 0; j < ranges.size(); j++) {
                ArrayList<Long> r2 = ranges.get(j);
                if (r1 != r2) {
                    if (r1.get(0) <= r2.get(0) && r1.get(1) >= r2.get(1)) {
                        ranges.remove(r2);
                        i = 0;
                        break;
                    } else if (r2.get(0) <= r1.get(0) && r2.get(1) >= r1.get(1)) {
                        ranges.remove(r1);
                        i = 0;
                        break;
                    } else if (r1.get(0) < r2.get(0) && r1.get(1) >= r2.get(0) && r1.get(1) <= r2.get(1)) {
                        Long newEnd = r2.get(1);
                        Long newStart = r1.get(0);
                        ArrayList<Long> newRange = new ArrayList<>();
                        newRange.add(newStart);
                        newRange.add(newEnd);
                        ranges.remove(r1);
                        ranges.remove(r2);
                        ranges.add(newRange);
                        i = 0;
                        break;
                    } else if (r2.get(0) < r1.get(0) && r2.get(1) >= r1.get(0) && r2.get(1) <= r1.get(1)) {
                        Long newEnd = r1.get(1);
                        Long newStart = r2.get(0);
                        ArrayList<Long> newRange = new ArrayList<>();
                        newRange.add(newStart);
                        newRange.add(newEnd);
                        ranges.remove(r1);
                        ranges.remove(r2);
                        ranges.add(newRange);
                        i = 0;
                        break;
                    }
                }
            }
        }
        return ranges;
    }
    
}
