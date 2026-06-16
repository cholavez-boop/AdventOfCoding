import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AOC_2015_01 {
    public static void main(String[] args) {
        // File myObj = new File("/Users/Lorenzo Galvez/Documents/Work Files/JustCoding/AdventOfCoding/AoCInputs/AOC2023_12.txt");
        File myObj = new File("D:/Real Life/Oracle/NonWork/AoC_2025/AdventOfCoding/AoCInputs/AOC2015_01.txt");

        //Read File
        ArrayList<String> input = new ArrayList<>();
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String nextLine = myReader.nextLine();
                input.add(nextLine);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }

        //System.out.println(input);

        int floor = 0;
        int firstBasementPosition = -1;
        for (int i = 0; i < input.get(0).length(); i++) {
            char c = input.get(0).charAt(i);
            if (c == '(') {
                floor++;
            } else if (c == ')') {
                floor--;
            }
            if (floor == -1 && firstBasementPosition == -1) {
                firstBasementPosition = i + 1;
            }
        }
        System.out.println("Final floor: " + floor);
        System.out.println("First basement position: " + firstBasementPosition);
    }
}
