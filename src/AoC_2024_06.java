import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

import java.util.regex.Matcher;

public class AoC_2024_06 {
    public static void main(String[] args) {
        //File myObj = new File("/Users/Lorenzo Galvez/Documents/Work Files/JustCoding/AdventOfCoding/AoCInputs/AOC2024_06.txt");
        File myObj = new File("D:/Real Life/Oracle/NonWork/AoC_2025/AdventOfCoding/AoCInputs/AOC2024_06.txt");

        HashMap<Integer, String> mapOfLab = new HashMap<>();
        int[] startPoint = new int[2];
        Pattern p = Pattern.compile("[v^<>]");
        try (Scanner myReader = new Scanner(myObj)) {
            Integer iter = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                mapOfLab.put(iter, data);
                Matcher m = p.matcher(data);
                if (m.find()) {
                    startPoint[0] = iter;
                    startPoint[1] = m.start();
                }
                iter++;
            
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }

        //showMap(mapOfLab, false);

        //Part 1
        System.out.println("Total Moves: " + guardsPatrol(mapOfLab, startPoint));
        // showMap(mapOfLab, false);
        // System.out.println("Start Point: " + Arrays.toString(startPoint));
        //Part 2
        // showMap(reflectedMap(mapOfLab), true);
        // System.out.println("Total Loops: " + totalMovement(mapOfLab, startPoint, true));
        System.out.println("Total Loops: " + checkForLoops(mapOfLab, startPoint));

    }

    //Part 1
    public static int guardsPatrol(HashMap<Integer, String> labMap, int[] startPoint) {
        int[][] directions = {{-1,0},{0,1},{1,0},{0,-1}}; //up, right, down, left
        int moves = 0;
        int turns = 0;
        int boundsRow = labMap.size();
        int boundsCol = labMap.get(0).length();
        int currentDir = 0;
        int[] pos = Arrays.copyOf(startPoint, startPoint.length);
        if (labMap.get(pos[0]).charAt(pos[1]) == '>') {
            currentDir = 1;
        } else if (labMap.get(pos[0]).charAt(pos[1]) == 'v') {
            currentDir = 2;
        } else if (labMap.get(pos[0]).charAt(pos[1]) == '<') {
            currentDir = 3;
        } else {
            currentDir = 0;
        }
        boolean inLab = true;
        while (inLab) {
            StringBuilder currRow = new StringBuilder(labMap.get(pos[0]));
            currRow.setCharAt(pos[1], 'X');
            labMap.replace(pos[0], currRow.toString());
            int newRow = pos[0] + directions[currentDir][0];
            int newCol = pos[1] + directions[currentDir][1];
            if (newRow < 0 || newRow >= boundsRow || newCol < 0 || newCol >= boundsCol) {
                System.out.println("Exited the lab.");
                moves++;
                inLab = false;
                break;
            } else if (moves > 1000000 || turns > 1000) {
                System.out.println("Too many moves, exiting to prevent infinite loop.");
                return 12345;
            }
            char nextCell = labMap.get(newRow).charAt(newCol);
            // System.out.println("Moving to " + newRow + "," + newCol + ": " + nextCell);
            if (nextCell == '#') {
                currentDir = (currentDir + 1) % 4; // Turn right
                turns++;
                // System.out.println("Hit a wall. Turning right.");
            } else {
                if (nextCell == '.') {
                    moves++;
                    // System.out.print(moves + " ");
                }
                pos[0] = newRow;
                pos[1] = newCol;
            }
        }
        return moves;
    }

    public static void showMap(HashMap<Integer, String> map, boolean showRowNum) {
        if (!showRowNum) {
            clearScreen();
        }
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (showRowNum) {
                String xyz = "";
                if (entry.getKey() < 10) {
                    xyz = "00";
                } else if (entry.getKey() < 100) {
                    xyz = "0";
                }
                System.out.println(xyz + entry.getKey() + " = " + entry.getValue());
            } else {
                System.out.println(entry.getValue());
            }
        }
    }

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Part 2

    public static int checkForLoops(HashMap<Integer, String> labMap, int[] pos) {
        //System.out.println("Checking if loop");
        int loops = 0;
        for (int i = 0; i < labMap.size(); i++) {
            for (int j = 0; j < labMap.get(i).length(); j++) {
                HashMap<Integer, String> mapToModify = new HashMap<Integer, String>(labMap);
                if (mapToModify.get(i).charAt(j) == 'X') {
                    StringBuilder rowBuilder = new StringBuilder(mapToModify.get(i));
                    rowBuilder.setCharAt(j, '#');
                    mapToModify.replace(i, rowBuilder.toString());
                    System.out.println("Testing adding wall at " + i + "," + j);
                    if (guardsPatrol(mapToModify, pos) == 12345) {
                        //System.out.println("Loop confirmed.");
                        loops++;
                    }
                    // else {
                    //     System.out.println("No Loop.");
                    // }
                    StringBuilder revertRow = new StringBuilder(mapToModify.get(i));
                    revertRow.setCharAt(j, 'X');
                    mapToModify.replace(i, revertRow.toString());
                }
            }
        }
        return loops;
    }

    public static HashMap<Integer, String> reflectedMap(HashMap<Integer, String> labMap) {
        HashMap<Integer, String> newMap = new HashMap<>();
        for (int i = 0; i < labMap.get(0).length(); i++) {
            StringBuilder colBuilder = new StringBuilder();
            for (int j = 0; j < labMap.size(); j++) {
                char elem = labMap.get(j).charAt(i);
                if (elem == '^') {
                    elem = '<';
                } else if (elem == '<') {
                    elem = '^';
                } else if (elem == 'v') {
                    elem = '>';
                } else if (elem == '>') {
                    elem = 'v';
                }
                colBuilder.append(elem);
            }
            newMap.put(i, colBuilder.toString());
        }
        return newMap;
    }
}

