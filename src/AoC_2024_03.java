import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class AoC_2024_03 {
    public static void main(String[] args) {
        //File myObj = new File("/Users/Lorenzo Galvez/Documents/Work Files/JustCoding/AdventOfCoding/AoCInputs/AOC2024_03.txt");
        File myObj = new File("D:/Real Life/Oracle/NonWork/AoC_2025/AdventOfCoding/AoCInputs/AOC2024_03.txt");

        ArrayList<String> matches = new ArrayList<>();
        boolean doMul = true;
        Pattern p1 = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        Pattern p2 = Pattern.compile("do\\(\\)");
        Pattern p3 = Pattern.compile("don't\\(\\)");
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                int cutoff = 0;
                Matcher m1 = p1.matcher(data);
                while (m1.find()) {
                    int start = m1.start();
                    int end = m1.end();
                    int latest = 0;
                    String prevString = data.substring(cutoff, end);
                    //System.out.println("PrevString: " + prevString);
                    //System.out.println("\nChecking between index " + cutoff + " and " + start);
                    Matcher m2 = p2.matcher(prevString);
                    Matcher m3 = p3.matcher(prevString);

                    while (m2.find() && m2.end() > latest && prevString.length() > 2) {
                        // System.out.println("\n" + cutoff + " to " + end);
                        // System.out.println("PrevString: " + prevString);
                        // System.out.println("Found do() at: " + m2.start() + " to " + m2.end());
                        doMul = true;
                        latest = m2.end();
                    }

                    while (m3.find() && m3.end() > latest && prevString.length() > 2) {
                        // System.out.println("\n" + cutoff + " to " + end);
                        // System.out.println("PrevString: " + prevString);
                        // System.out.println("Found don't() at: " + m3.start() + " to " + m3.end());
                        doMul = false;
                        latest = m3.end();
                    }

                    cutoff = end;

                    if (doMul) {
                        String toAdd = data.substring(start, end);
                        // System.out.print("*");
                        matches.add(toAdd);
                    }
                }
            }
            //System.out.println(matches);
            System.out.println("\n" + findSum(matches));
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }
    }

    //Part 1
    public static int findSum(ArrayList<String> compData) {
        int sum = 0;
        for (int i = 0; i < compData.size(); i++) {
            String data = compData.get(i);
            String[] nums = data.replace("mul(", "").replace(")", "").split(",");
            int a = Integer.valueOf(nums[0]);
            int b = Integer.valueOf(nums[1]);
            sum += a * b;
        }
        return sum;
    }

    //Part 2
}
