import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AoC_2024_09_1 {
    
    public static void main(String[] args) {
        //File myObj = new File("/Users/Lorenzo Galvez/Documents/Work Files/JustCoding/AdventOfCoding/AoCInputs/AOC2024_09.txt");
        File myObj = new File("D:/Real Life/Oracle/NonWork/AoC_2025/AdventOfCoding/AoCInputs/AOC2024_09.txt");

        String input = new String();
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                input = myReader.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }
        // System.out.println(input);
        ArrayList<String> decodedString = decodeString(input);
        // System.out.println(decodedString);
        ArrayList<Integer> translatedString = translateString(decodedString);
        // System.out.println(translatedString);
        System.out.println("\nSum: " + checkSum(translatedString));
    }

    public static ArrayList<String> decodeString(String input) {
        ArrayList<String> decode = new ArrayList<>();
        boolean file = false;
        int idx = 0;
        for (int i = 0; i < input.length(); i++) {
            file = !file;
            for (int j = 0; j < Integer.parseInt(String.valueOf(input.charAt(i))); j++) {
                if (file) {
                    decode.add(String.valueOf(idx));
                } else {
                    decode.add(".");
                }
            }
            if (file) {
                idx++;
            }
        }
        return decode;
    }

    public static ArrayList<Integer> translateString(ArrayList<String> input) {
        ArrayList<Integer> translate = new ArrayList<>();
        int currIdx = -1;
        int inputIdx = input.size()-1;
        while (currIdx < inputIdx) {
            currIdx++;
            if (input.get(currIdx) != ".") {
                translate.add(Integer.parseInt(input.get(currIdx)));
            } else {
                while (input.get(inputIdx) == ".") {
                    inputIdx--;
                }
                translate.add(Integer.parseInt(input.get(inputIdx)));
                inputIdx--;
            }
        }
        return translate;
    }

    public static Long checkSum(ArrayList<Integer> input) {
        Long sum = 0L;
        for (int a = 0; a < input.size(); a++) {
            // System.out.print("\n" + sum);
            sum += (a * input.get(a));
            // System.out.print(" + (" + a + " * " + input.get(a) + ") = " + sum);
        }
        return sum;
    }
}
