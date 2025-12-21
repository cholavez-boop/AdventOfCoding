import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AoC_2025_06_1 {
    public static void main(String[] args) {
        // File myObj = new File("/Users/Lorenzo Galvez/Documents/Work Files/JustCoding/AdventOfCoding/AoCInputs/AOC2025_06.txt");
        File myObj = new File("D:/Real Life/Oracle/NonWork/AoC_2025/AdventOfCoding/AoCInputs/AOC2025_06.txt");

        ArrayList<ArrayList<Integer>> values = new ArrayList<>();
        int lineNum = 0;
        Long ans = 0L;
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split("\s+");
                // System.out.println(Arrays.toString(parts));
                if (lineNum == 0) {
                    for (String p : parts) {
                        ArrayList<Integer> vals = new ArrayList<>();
                        if (p.equals("")) {
                            continue;
                        }
                        vals.add(Integer.parseInt(p));
                        values.add(vals);    
                    }   
                } else if (lineNum == 4) {
                    int idx = 0;
                    for (String p : parts) {
                        ans += operate(values.get(idx), p);
                        idx++;
                    }
                } else {
                    int offset = 0;
                    if (parts.length > values.size()) {
                        offset = parts.length - values.size();
                    }
                    for (int i = 0; i < values.size(); i++) {
                        ArrayList<Integer> vals = new ArrayList<>();
                        vals.addAll(values.get(i));
                        vals.add(Integer.parseInt(parts[i+offset]));
                        values.set(i, vals);
                    }
                }         
                lineNum++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }

        System.out.println("The total is: " + ans);
    }

    public static Long operate(ArrayList<Integer> vals, String op) {
        Long result = 0L;
        if (op.equals("*")) {
            result = 1L;
            for (Integer v : vals) {
                result *= v;
            }
        } else if (op.equals("+")) {
            for (Integer v : vals) {
                result += v;
            }
        }
        return result;
    }

    // 32438341839702 TOO HIGH
    // 6725216329103 CORRECT
}
