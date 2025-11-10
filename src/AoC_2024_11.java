import java.util.*;

public class AoC_2024_11 {
    public static void main(String[] args) {
        String input = "2 77706 5847 9258441 0 741 883933 12";
        /*
         * If the stone is engraved with the number 0, it is replaced by a stone engraved with the number 1.
         * If the stone is engraved with a number that has an even number of digits, it is replaced by two stones.
         *      The left half of the digits are engraved on the new left stone, and the right half of the digits are engraved on the new right stone. (The new numbers don't keep extra leading zeroes: 1000 would become stones 10 and 0.)
         * If none of the other rules apply, the stone is replaced by a new stone;
         *      the old stone's number multiplied by 2024 is engraved on the new stone.
         */

        String[] splitInput = input.split("\s");
        ArrayList<Long> plutoStones = new ArrayList<>();
         
        for (int p = 0; p < splitInput.length; p++) {
        plutoStones.add(Long.parseLong(splitInput[p]));
        }

        // ArrayList<ArrayList<Long>> stonesAbstraced = new ArrayList<>();

        Long blong = 0L;
        int numOfBlinks = 75;
        // for (int a = 0; a < plutoStones.size(); a++) {
            ArrayList<Long> gha = new ArrayList<>();
            gha.add(plutoStones.get(0));
            for (int b = 0; b < numOfBlinks; b++) {
                // plutoStones = blink(plutoStones);
                gha = blink(gha);
            }
            // stonesAbstraced.add(gha);
            blong += gha.size();
        // }
        
        // System.out.println(plutoStones.size() + " number of pluto stones!");
        
        // for (ArrayList<Long> ghu : stonesAbstraced) {
        //     blong += ghu.size();
        // }
        System.out.println(blong + " number of pluto stones!");
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
}
