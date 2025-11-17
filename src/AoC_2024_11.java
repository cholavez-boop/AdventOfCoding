import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AoC_2024_11 {
    public static void main(String[] args) {
        File myObj = new File("/Users/Lorenzo Galvez/Documents/Work Files/JustCoding/AdventOfCoding/AoCInputs/AOC2024_11.txt");
        // String[] splitInput = input.split("\s");
        // ArrayList<Long> plutoStones = new ArrayList<>();
        int numOfBlinks = 35;
        Long reallyBigNum = 841729134425L;
        int index = 0;

        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                // System.out.println(data);
                if (index <= 376408) {
                    index++;

                    continue;
                }
                ArrayList<Long> plutoStone = new ArrayList<>();
                plutoStone.add(Long.parseLong(data));
                for (int b = 0; b < numOfBlinks; b++) {
                    plutoStone = blink(plutoStone);
                }
                reallyBigNum += plutoStone.size();
                System.out.println("Currenlty: " + index + " -> " + reallyBigNum);
                index++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }
        // String input = "2 77706 5847 9258441 0 741 883933 12";
        /*
         * If the stone is engraved with the number 0, it is replaced by a stone engraved with the number 1.
         * If the stone is engraved with a number that has an even number of digits, it is replaced by two stones.
         *      The left half of the digits are engraved on the new left stone, and the right half of the digits are engraved on the new right stone. (The new numbers don't keep extra leading zeroes: 1000 would become stones 10 and 0.)
         * If none of the other rules apply, the stone is replaced by a new stone;
         *      the old stone's number multiplied by 2024 is engraved on the new stone.
         */
         
        // for (int p = 0; p < splitInput.length; p++) {
        //     plutoStones.add(Long.parseLong(splitInput[p]));
        // }

        // ArrayList<ArrayList<Long>> stonesAbstraced = new ArrayList<>();

        // Long blong = 0L;
        
        // for (int a = 0; a < plutoStones.size(); a++) {
            // ArrayList<Long> gha = new ArrayList<>();
            // gha.add(plutoStones.get(0));
            // for (int b = 0; b < numOfBlinks; b++) {
            //     plutoStones = blink(plutoStones);
            //     // gha = blink(gha);
            // }
            // stonesAbstraced.add(gha);
            // blong += gha.size();
        // }
        
        // System.out.println(plutoStones.size() + " number of pluto stones!");
        
        // for (ArrayList<Long> ghu : stonesAbstraced) {
        //     blong += ghu.size();
        // }
        // System.out.println(plutoStones.size() + " number of pluto stones!");
        // writeToFile(plutoStones);
    }

    //Part 1
    public static ArrayList<Long> blink(ArrayList<Long> plutoStones) {
        ArrayList<Long> newPlutoStones = new ArrayList<>();

        for (int stone = 0; stone < plutoStones.size(); stone++) {
            if (plutoStones.get(stone) == 0) {
                newPlutoStones.add(1L);
            } else {
                String stoneString = String.valueOf(plutoStones.get(stone));
                // System.out.println(stoneSt  ring);
                if (stoneString.length()%2 == 0) {
                    if (stoneString.length() == 2) {
                        newPlutoStones.add(Long.parseLong(String.valueOf(stoneString.charAt(0))));
                        newPlutoStones.add(Long.parseLong(String.valueOf(stoneString.charAt(1))));
                    } else {
                        newPlutoStones.add(Long.parseLong(stoneString.substring(0, stoneString.length()/2)));
                        newPlutoStones.add(Long.parseLong(stoneString.substring(stoneString.length()/2)));
                    }
                } else {
                    newPlutoStones.add(plutoStones.get(stone) * 2024);
                }
            }
            // System.out.println(newPlutoStones);
        }

        return newPlutoStones; 
    }

    //Part 2 (and I could have done this for part 1 tbh)
    // public static Long numOfStone() {
    //     return 0L;
    // }

    // public static ArrayList<Long> innerArray() {
    //     ArrayList<Long> inner = new ArrayList<>();
    //     return inner;
    // }

    /*
     * Part 1
     * 60973 TOO LOW
     * 190865 CORRECT
     */

     public static void writeToFile(ArrayList<Long> plutoStones) {
        try {
            FileWriter w = new FileWriter("AOC2024_11.txt");
            for (Long l : plutoStones) {
                w.write(String.valueOf(l)+"\n");
            }
            w.close();
            System.out.println("Saved to AOC2024_11.");
        } catch (IOException e) {
            System.out.println("Whoops!");
            e.printStackTrace();
        }
     }
}
