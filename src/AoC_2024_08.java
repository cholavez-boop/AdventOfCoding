import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AoC_2024_08 {
    public static void main(String[] args) {
        //File myObj = new File("/Users/Lorenzo Galvez/Documents/Work Files/JustCoding/AdventOfCoding/AoCInputs/AOC2024_08.txt");
        File myObj = new File("D:/Real Life/Oracle/NonWork/AoC_2025/AdventOfCoding/AoCInputs/AOC2024_08.txt");
        ArrayList<String> map = new ArrayList<>();
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                map.add(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }

        HashMap<Character, ArrayList<ArrayList<Integer>>> mapOfAntennae = antennae(map);
        // mapOfAntennae.forEach( (k, v) -> {System.out.println(k + " -> " + v); } );

        System.out.println("Antinodes: " + countAntinodes(putAntinodes(map, findAntinodes(mapOfAntennae))));
    }

    //Part 1
    public static HashMap<Character, ArrayList<ArrayList<Integer>>> antennae(ArrayList<String> map) {
        HashMap<Character, ArrayList<ArrayList<Integer>>> antennaLoc = new HashMap<>();
        for (int z = 0; z < map.size(); z++) {
            String row = map.get(z);
            // System.out.println(row);
            for (int a = 0; a < row.length(); a++) {
                Character chr = row.charAt(a);
                ArrayList<ArrayList<Integer>> allCoords = new ArrayList<>();
                if (chr != '.') {
                    ArrayList<Integer> coords = new ArrayList<>();
                    coords.add(z);
                    coords.add(row.indexOf(chr));
                    if (!antennaLoc.containsKey(chr)) {
                        allCoords.add(coords);
                        antennaLoc.put(chr, allCoords);
                    } else {
                        allCoords = antennaLoc.get(chr);
                        allCoords.add(coords);
                        antennaLoc.replace(chr, allCoords);
                    }
                }
            }
        }
        return antennaLoc;
    }

    public static ArrayList<ArrayList<Integer>> findAntinodes(HashMap<Character, ArrayList<ArrayList<Integer>>> antLocs) {
        ArrayList<ArrayList<Integer>> antinode = new ArrayList<>();
        ArrayList<Integer> locA = new ArrayList<>();
        ArrayList<Integer> locB = new ArrayList<>();
        int gridSize = 50;
        for (Character currCha : antLocs.keySet()) {
            for (int b = 0; b < antLocs.get(currCha).size(); b++) {
                locA = antLocs.get(currCha).get(b);
                for (int c = b+1; c < antLocs.get(currCha).size(); c++) {
                    locB = antLocs.get(currCha).get(c);
                    ArrayList<Integer> modifer = antinodeLine(locA, locB);
                    ArrayList<Integer> newLineCoord = new ArrayList<>();
                    if (locB.get(1) > locA.get(1)) {
                        // '\'
                        // Down
                        newLineCoord = locB;
                        while (newLineCoord.get(0) < gridSize && newLineCoord.get(1) < gridSize) {
                            newLineCoord = lineCoords(newLineCoord, modifer, true, true);
                            // System.out.println(newLineCoord);
                            antinode.add(newLineCoord);
                        }
                        // Up
                        newLineCoord = locA;
                        while (newLineCoord.get(0) >= 0 && newLineCoord.get(1) >= 0) {
                            newLineCoord = lineCoords(newLineCoord, modifer, false, false);
                            // System.out.println(newLineCoord);
                            antinode.add(newLineCoord);
                        }
                    } else {
                        // '/'
                        // Down
                        newLineCoord = locB;
                        while (newLineCoord.get(0) < gridSize && newLineCoord.get(1) >= 0) {
                            newLineCoord = lineCoords(newLineCoord, modifer, false, true);
                            // System.out.println(newLineCoord);
                            antinode.add(newLineCoord);
                        }
                        // Up
                        newLineCoord = locA;
                        while (newLineCoord.get(0) >= 0 && newLineCoord.get(1) < gridSize) {
                            newLineCoord = lineCoords(newLineCoord, modifer, true, false);
                            // System.out.println(newLineCoord);
                            antinode.add(newLineCoord);
                        }
                    }
                    // antinode.add(addCoords(locA, locB));
                    // System.out.println(locA + " + " + locB + " = " + addCoords(locA, locB));
                    // antinode.add(subtractCoords(locA, locB));
                    // System.out.println(locA + " - " + locB + " = " + subtractCoords(locA, locB));
                }
            }
        }
        // antinode.forEach( (n) -> {
        //     // System.out.print("\n" + n);
        //     if (!(n.get(0) >= 50 || n.get(0) < 0 || n.get(1) >= 50 || n.get(1) < 0)) {
        //         System.out.print("\n" + n);
        //         // System.out.print(n + " -> Out of bounds\n");
        //     }
        // } );
        return antinode;
    }

    public static ArrayList<Integer> addCoords(ArrayList<Integer> locA, ArrayList<Integer> locB) {
        ArrayList<Integer> coordSum = new ArrayList<>();
        int diffA = locB.get(0) - locA.get(0);
        int diffB = Math.abs(locB.get(1) - locA.get(1));
        coordSum.add(locB.get(0) + diffA);
        if (locB.get(1) > locA.get(1)) {
            coordSum.add(locB.get(1) + diffB);
        } else {
            coordSum.add(locB.get(1) - diffB);
        }
        
        return coordSum;
    }

    public static ArrayList<Integer> subtractCoords(ArrayList<Integer> locA, ArrayList<Integer> locB) {
        ArrayList<Integer> coordDiff = new ArrayList<>();
        int diffA = locB.get(0) - locA.get(0);
        int diffB = Math.abs(locB.get(1) - locA.get(1));
        coordDiff.add(locA.get(0) - diffA);
        if (locB.get(1) > locA.get(1)) {
            coordDiff.add(locA.get(1) - diffB);
        } else {
            coordDiff.add(locA.get(1) + diffB);
        }
        return coordDiff;
    }

    public static ArrayList<String> putAntinodes(ArrayList<String> map, ArrayList<ArrayList<Integer>> antinodeLocs) {
        int maxRow = map.size();
        int maxCol = map.get(0).length();
        antinodeLocs.forEach( (n) -> {
            if (!(n.get(0) >= maxRow || n.get(0) < 0 || n.get(1) >= maxCol || n.get(1) < 0) &&
                map.get(n.get(0)).charAt(n.get(1)) != '#') {
                StringBuilder sb = new StringBuilder(map.get(n.get(0)));
                sb.setCharAt(n.get(1), '#');
                map.set(n.get(0), sb.toString());
            }
        } );
        // map.forEach( (s) -> {
        //     System.out.println(s);
        // });
        return map;
    }

    public static int countAntinodes(ArrayList<String> map) {
        int antinodes = 0;
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(0).length(); j++) {
                //Part 1: == '#'
                //Part 2: != '.'
                if (map.get(i).charAt(j) != '.') {
                    antinodes++;
                }
            }
        }
        return antinodes;
    }

    //Part 2
    public static ArrayList<Integer> antinodeLine(ArrayList<Integer> locA, ArrayList<Integer> locB) {
        ArrayList<Integer> line = new ArrayList<>();
        int diffA = locB.get(0) - locA.get(0);
        int diffB = Math.abs(locB.get(1) - locA.get(1));
        line.add(diffA);
        line.add(diffB);
        return line;
    }

    public static ArrayList<Integer> lineCoords(ArrayList<Integer> loc, ArrayList<Integer> modifer, boolean rightDir, boolean downDir) {
        ArrayList<Integer> coordSum = new ArrayList<>();
        int colMul = downDir ? 1 : -1;
        coordSum.add(loc.get(0) + (modifer.get(0) * colMul));
        int rowMul = rightDir ? 1 : -1;
        coordSum.add(loc.get(1) + (modifer.get(1) * rowMul));
        return coordSum;
    }
}
