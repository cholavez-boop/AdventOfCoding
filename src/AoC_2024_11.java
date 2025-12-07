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
        plutoStones.sort(null);

        Long blong = 0L;
        int numOfBlinks = 75;
        for (Long stone : plutoStones) {
            blong += blink(stone, numOfBlinks);
        }
        System.out.println(blong + " number of pluto stones!");
        // System.out.println(memo);
    }

    //Part 1
    // public static ArrayList<Long> blink(ArrayList<Long> plutoStones) {
    //     ArrayList<Long> newPlutoStones = new ArrayList<>();

    //     for (int stone = 0; stone < plutoStones.size(); stone++) {
    //         if (plutoStones.get(stone) == 0) {
    //             newPlutoStones.add(1L);
    //         } else {
    //             String stoneString = String.valueOf(plutoStones.get(stone));
    //             // System.out.println(stoneSt  ring);
    //             if (stoneString.length()%2 == 0) {
    //                 if (stoneString.length() == 2) {
    //                     newPlutoStones.add(Long.parseLong(String.valueOf(stoneString.charAt(0))));
    //                     newPlutoStones.add(Long.parseLong(String.valueOf(stoneString.charAt(1))));
    //                 } else {
    //                     newPlutoStones.add(Long.parseLong(stoneString.substring(0, stoneString.length()/2)));
    //                     newPlutoStones.add(Long.parseLong(stoneString.substring(stoneString.length()/2)));
    //                 }
    //             } else {
    //                 newPlutoStones.add(plutoStones.get(stone) * 2024);
    //             }
    //         }
    //         // System.out.println(newPlutoStones);
    //     }

    //     return newPlutoStones;
    // }

    //Part 2
    public static HashMap<Long, HashMap<Integer, Long>> memo = new HashMap<>();
    public static Long blink(Long plutoStone, int blinkTime) {
        if(memo.containsKey(plutoStone) && memo.get(plutoStone).containsKey(blinkTime)) {
            // System.out.print(blinkTime);
            return memo.get(plutoStone).get(blinkTime);
        } else {
            if (blinkTime == 0) {
                return 1L;
            } else {
                // System.out.println("New val " + plutoStone + " at time " + blinkTime);
                String stoneString = String.valueOf(plutoStone);
                HashMap<Integer, Long> innerMap = new HashMap<>();
                Long bLong = 0L;
                if (plutoStone == 0) {
                    bLong = blink(1L, blinkTime - 1);
                    innerMap.put(blinkTime, bLong);
                    if (memo.containsKey(plutoStone)) {
                        memo.get(plutoStone).put(blinkTime, bLong);
                    } else {
                        memo.put(plutoStone, innerMap);
                    }
                } else if (stoneString.length()%2 == 0) {
                    if (stoneString.length() == 2) {
                        bLong = blink(Long.parseLong(String.valueOf(stoneString.charAt(0))), blinkTime - 1) +
                                blink(Long.parseLong(String.valueOf(stoneString.charAt(1))), blinkTime - 1);
                    } else {
                        bLong = blink(Long.parseLong(stoneString.substring(0, stoneString.length()/2)), blinkTime - 1) +
                                blink(Long.parseLong(stoneString.substring(stoneString.length()/2)), blinkTime - 1);
                    }
                } else {
                    bLong = blink(plutoStone * 2024, blinkTime - 1);
                }
                innerMap.put(blinkTime, bLong);
                if (memo.containsKey(plutoStone)) {
                    memo.get(plutoStone).put(blinkTime, bLong);
                } else {
                    memo.put(plutoStone, innerMap);
                }
                return memo.get(plutoStone).get(blinkTime);
            }
        }
    }

    /*
     * Part 1
     * 60973 TOO LOW
     * 190865 CORRECT
     * 6521701160 - 50 blinks
     */
}
