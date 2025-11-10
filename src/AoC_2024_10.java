import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AoC_2024_10 {
    public static void main(String[] args) {
        // File myObj = new File("/Users/Lorenzo Galvez/Documents/Work Files/JustCoding/AdventOfCoding/AoCInputs/AOC2024_10.txt");
        File myObj = new File("D:/Real Life/Oracle/NonWork/AoC_2025/AdventOfCoding/AoCInputs/AOC2024_10.txt");

        ArrayList<String> inputMap = new ArrayList<>();
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                inputMap.add(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }

        ArrayList<ArrayList<Integer>> topoMap = new ArrayList<>();
        for (String dataRow : inputMap) {
            ArrayList<Integer> rowHeights = new ArrayList<>();
            for (int d = 0; d < dataRow.length(); d++) {
                rowHeights.add(Integer.parseInt(String.valueOf(dataRow.charAt(d))));
            }
            topoMap.add(rowHeights);
        }
        // for (ArrayList<Integer> row : topoMap) {
        //     System.out.println(row);
        // }

        ArrayList<ArrayList<Integer>> trailheads = findTrailheads(topoMap);
        // for (ArrayList<Integer> coords : trailheads) {
        //     System.out.println(coords);
        // }

        int totalScore = 0;
        for (ArrayList<Integer> coordinate : trailheads) {
            totalScore += getScore(topoMap, coordinate);
        }

        System.out.println("Total Score: " + totalScore);
    }

    public static ArrayList<ArrayList<Integer>> findTrailheads(ArrayList<ArrayList<Integer>> map) {
        ArrayList<ArrayList<Integer>> trailheads = new ArrayList<>();
        for (int row = 0; row < map.size(); row++) {
            for (int col = 0; col < map.get(row).size(); col++) {
                ArrayList<Integer> coord = new ArrayList<>();
                if (map.get(row).get(col).equals(0)) {
                    coord.add(row);
                    coord.add(col);
                    // coord.add(0); // Trailhead Score
                    trailheads.add(coord);
                }
            }
        }
        return trailheads;
    }

    public static int getScore(ArrayList<ArrayList<Integer>> map, ArrayList<Integer> trailhead) {
        // for Part 2, just comment out anything that uses visited array
        int score = 0;
        ArrayList<ArrayList<Integer>> nextStep = new ArrayList<>();
        ArrayList<ArrayList<Integer>> visited = new ArrayList<>();
        int row = trailhead.get(0);
        int col = trailhead.get(1);
        int step = map.get(row).get(col);

        nextStep.add(goToList(row, col, step));
        while (!nextStep.isEmpty()) {
            row = nextStep.get(0).get(0);
            col = nextStep.get(0).get(1);
            step = nextStep.get(0).get(2) + 1;

            if (row-1 >= 0 && map.get(row-1).get(col).equals(step)) {
                //up
                if (step == 9 && !visited.contains(goToList(row-1, col, step))) {
                    score++;
                    visited.add(goToList(row-1, col, step));
                } else {
                   nextStep.add(goToList(row-1, col, step)); 
                }
            }
            if (row+1 < map.size() && map.get(row+1).get(col).equals(step)) {
                //down
                if (step == 9 && !visited.contains(goToList(row+1, col, step))) {
                    score++;
                    visited.add(goToList(row+1, col, step));
                } else {
                    nextStep.add(goToList(row+1, col, step));
                }
            }
            if (col-1 >= 0 && map.get(row).get(col-1).equals(step)) {
                //left
                if (step == 9 && !visited.contains(goToList(row, col-1, step))) {
                    score++;
                    visited.add(goToList(row, col-1, step));
                } else {
                    nextStep.add(goToList(row, col-1, step));
                }
            }
            if (col+1 < map.get(row).size() && map.get(row).get(col+1).equals(step)) {
                //right
                if (step == 9 && !visited.contains(goToList(row, col+1, step))) {
                    score++;
                    visited.add(goToList(row, col+1, step));
                } else {
                    nextStep.add(goToList(row, col+1, step));
                }
            }

            nextStep.remove(0);
        }

        return score;      
    }

    public static ArrayList<Integer> goToList(int row, int col, int step) {
        ArrayList<Integer> stepToAdd = new ArrayList<>();
        stepToAdd.add(row);
        stepToAdd.add(col);
        stepToAdd.add(step);
        return stepToAdd;
    }

    /*
     * GUESSES
     * 1706 TOO HIGH
     * 794 CORRECT
     */
}
