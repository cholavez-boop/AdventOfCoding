import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AoC_2025_08 {
    
    public static void main(String[] args) {
        // File myObj = new File("/Users/Lorenzo Galvez/Documents/Work Files/JustCoding/AdventOfCoding/AoCInputs/AOC2025_08.txt");
        File myObj = new File("D:/Real Life/Oracle/NonWork/AoC_2025/AdventOfCoding/AoCInputs/AOC2025_08.txt");

        ArrayList<Integer> xs = new ArrayList<>();
        ArrayList<Integer> ys = new ArrayList<>();
        ArrayList<Integer> zs = new ArrayList<>();
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split(",");
                xs.add(Integer.parseInt(parts[0]));
                ys.add(Integer.parseInt(parts[1]));
                zs.add(Integer.parseInt(parts[2]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }

        HashMap<Integer, ArrayList<Integer>> points = new HashMap<>();
        for (int i = 0; i < xs.size(); i++){
            ArrayList<Integer> point = new ArrayList<>();
            point.add(xs.get(i));
            point.add(ys.get(i));
            point.add(zs.get(i));
            points.put(i, point);
        }
        //
        // 3d distance formula
        // d(p,q) = sqrt( (x2 - x1)^2 + (y2 - y1)^2 + (z2 - z1)^2 )

        for (int i = 0; i < points.size(); i++){
            System.out.println(points.get(i));
        }
    }
}
