import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AoC_2023_12 {
    public static void main(String[] args) {
        File myObj = new File("/Users/Lorenzo Galvez/Documents/Work Files/JustCoding/AdventOfCoding/AoCInputs/AOC2023_12.txt");
        // File myObj = new File("D:/Real Life/Oracle/NonWork/AoC_2025/AdventOfCoding/AoCInputs/AOC2023_12.txt");

        ArrayList<ArrayList<String>> input = new ArrayList<>();
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String nextLine = myReader.nextLine();
                String[] inputLine = nextLine.split("\s");
                ArrayList<String> innerInput = new ArrayList<>();
                for (String s : inputLine) {
                    innerInput.add(s);
                }
                input.add(innerInput);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }

        HashMap<String, ArrayList<Integer>> springs = new HashMap<>();
        for (ArrayList<String> innerInput : input) {
            if (!springs.containsKey(innerInput.get(0))) {
                ArrayList<Integer> intsList = new ArrayList<>();
                String[] intsArr = innerInput.get(1).split(",");
                for (String t : intsArr) {
                    intsList.add(Integer.parseInt(t));
                }
                springs.put(innerInput.get(0), intsList);
            } else {
                System.out.println("There are duplicate rows of springs");
            }
        }

        System.out.println(input);
    }
    
}
