import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AoC_2025_03 {

    public static void main(String[] args) {
        // File myObj = new File("/Users/Lorenzo Galvez/Documents/Work Files/JustCoding/AdventOfCoding/AoCInputs/AOC2025_03.txt");
        File myObj = new File("D:/Real Life/Oracle/NonWork/AoC_2025/AdventOfCoding/AoCInputs/AOC2025_03.txt");

        ArrayList<String> batteries = new ArrayList<>();
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                batteries.add(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }

        Long totalJoltage = 0L;
        for (String string : batteries) {
            System.out.println("Checking battery: " + string);
            totalJoltage += checkJoltage(string);
        }

        System.out.println("Total Joltage: " + totalJoltage);
    }

    public static Long checkJoltage(String battery) {
        StringBuilder joltage = new StringBuilder();
        int greatestDigitPlace = 0;
        int notLast = 11;
        for (int j = 9; j > 0; j--) {
            for (int i = greatestDigitPlace; i < battery.length() - notLast; i++) {
                char c = battery.charAt(i);
                if (Integer.parseInt(String.valueOf(c)) == j) {
                    joltage.append(c);
                    if (joltage.length() == 12) {
                        System.out.println("Final Joltage: " + joltage.toString());
                        return Long.parseLong(joltage.toString());
                    }
                    greatestDigitPlace = i+1;
                    j = 10;
                    notLast--;
                }
            }
        }
        return -1L;
    }
    
    /*
     * 16329 TOO LOW
     * 16927 CORRECT
     * 167384358365132 CORRECT
     */
}