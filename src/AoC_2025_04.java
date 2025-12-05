import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AoC_2025_04 {

    public static ArrayList<String> inputMap = new ArrayList<>();
    public static void main(String[] args) {
        File myObj = new File("/Users/Lorenzo Galvez/Documents/Work Files/JustCoding/AdventOfCoding/AoCInputs/AOC2025_04.txt");
        // File myObj = new File("D:/Real Life/Oracle/NonWork/AoC_2025/AdventOfCoding/AoCInputs/AOC2025_04.txt");

        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                inputMap.add(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }

        int rollsRemoved = 0;
        int removable = -1;
        while (removable != 0) {
            removable = checkRoll();
            rollsRemoved += removable;
        }
        System.out.println(rollsRemoved);
    }

    public static int checkRoll() {
        int x = 0;
        ArrayList<ArrayList<Integer>> rollsToRemove = new ArrayList<>();
        for (int y = 0; y < inputMap.size(); y++) {
            for (int z = 0; z < inputMap.get(y).length(); z++) {
                int temp = 0;
                if (inputMap.get(y).charAt(z) != '@') {
                    continue;
                }
                // System.out.println("Found an @");
                if (y-1 >= 0) { 
                    //top row
                    if (z-1 >= 0) {
                        //left
                        if (inputMap.get(y-1).charAt(z-1) == '@') {
                            temp++;
                        }
                    }
                    if (z+1 < inputMap.get(y).length()) {
                        //right
                        if (inputMap.get(y-1).charAt(z+1) == '@') {
                            temp++;
                        }
                    }
                    if (inputMap.get(y-1).charAt(z) == '@') {
                        temp++;
                    }
                }
                if (y+1 < inputMap.size()) {
                    //bottom
                    if (z-1 >= 0) {
                        //left
                        if (inputMap.get(y+1).charAt(z-1) == '@') {
                            temp++;
                        }
                    }
                    if (z+1 < inputMap.get(y).length()) {
                        //right
                        if (inputMap.get(y+1).charAt(z+1) == '@') {
                            temp++;
                        }
                    }
                    if (inputMap.get(y+1).charAt(z) == '@') {
                        temp++;
                    }
                }
                if (z-1 >= 0) {
                    //left
                    if (inputMap.get(y).charAt(z-1) == '@') {
                        temp++;
                    }
                }
                if (z+1 < inputMap.get(y).length()) {
                    //right
                    if (inputMap.get(y).charAt(z+1) == '@') {
                        temp++;
                    }
                }
                if (temp > 3) {
                    continue;
                } else {
                    x++;
                    ArrayList<Integer> coord = new ArrayList<>();
                    coord.add(y);
                    coord.add(z);
                    rollsToRemove.add(coord);
                }
            }
        }

        removeRolls(rollsToRemove);

        return x;
    }

    public static void removeRolls(ArrayList<ArrayList<Integer>> rollsToRemove) {
        for (ArrayList<Integer> coord : rollsToRemove) {
            // change @ to .
            int row = coord.get(0);
            int col = coord.get(1);
            StringBuilder currRow = new StringBuilder(inputMap.get(row));
            currRow.setCharAt(col, 'X');
            inputMap.remove(row);
            inputMap.add(row, currRow.toString());
        }
    }
}
