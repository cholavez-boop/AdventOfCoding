import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AoC_2024_12 {
    
    public static void main(String[] args) {
        // File myObj = new File("/Users/Lorenzo Galvez/Documents/Work Files/JustCoding/AdventOfCoding/AoCInputs/AOC2024_12.txt");
        File myObj = new File("D:/Real Life/Oracle/NonWork/AoC_2025/AdventOfCoding/AoCInputs/AOC2024_12.txt");

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

        // for (String line : inputMap) {
        //     System.out.println(line);
        // }

        HashMap<String, ArrayList<ArrayList<Integer>>> sectionAreas = findAreas(inputMap);
        // sectionAreas.forEach( (k, v) -> {
        //     System.out.println(k + ": " + v.size());
        // });
        splitGroup("U", sectionAreas.get("U"));
    }

    public static HashMap<String, ArrayList<ArrayList<Integer>>> findAreas(ArrayList<String> map) {
        HashMap<String, ArrayList<ArrayList<Integer>>> sections = new HashMap<>();
        for (int row = 0; row < map.size(); row++) {
            for (int col = 0; col < map.get(row).length(); col++) {
                /*
                 * 1. Check Char
                 * 2. put Char in sectionAreas
                 * 3. Put the row, col of char under the same group
                 * 4. do math on area and perimeter
                 */
                String currChar = String.valueOf(map.get(row).charAt(col));
                if (!sections.containsKey(currChar)) {
                    ArrayList<ArrayList<Integer>> initialCoords = new ArrayList<>();
                    ArrayList<Integer> coord = new ArrayList<>();
                    coord.add(row);
                    coord.add(col);
                    initialCoords.add(coord);
                    sections.put(currChar, initialCoords);
                } else {
                    ArrayList<Integer> coord = new ArrayList<>();
                    coord.add(row);
                    coord.add(col);
                    sections.get(currChar).add(coord);
                }
            }
        }
        return sections;
    }

    public static void splitGroup(String plant, ArrayList<ArrayList<Integer>> coordinates) {
        HashMap<String, ArrayList<ArrayList<Integer>>> groups = new HashMap<>();
        /*
         * get coordinate
         * check if adjacent to any coordinate in the group
         *      if adjacent, add to current group
         *      else, make a new group
         */
        int idx = 0;
        for (int c = 0; c < coordinates.size(); c++) {
            if (c == 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(plant);
                sb.append(String.valueOf(idx));
                ArrayList<ArrayList<Integer>> groupCoord = new ArrayList<>();
                groupCoord.add(coordinates.get(c));
                groups.put(sb.toString(), groupCoord);
                idx++;
                continue;
            }
            //check if coord is adjacent to a coord in groups
        }
    }
}
