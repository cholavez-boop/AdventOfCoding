import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class AoC_2024_06 {
    public static void main(String[] args) {
        File myObj = new File("/Users/Lorenzo Galvez/Documents/Work Files/JustCoding/AdventOfCoding/AoCInputs/AOC2024_06.txt");
        //File myObj = new File("D:/Real Life/Oracle/NonWork/AoC_2025/AdventOfCoding/AoCInputs/AOC2024_06.txt");

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

        showMap(mapOfLab, false);
        showMap(reflectedMap(mapOfLab), false);

        //Part 1
        System.out.println("Total Moves: " + totalMovement(mapOfLab, startPoint));
        //Part 2
        //showMap(reflectedMap(mapOfLab), true);
    }

    //Part 1
    public static int totalMovement(HashMap<Integer, String> labMap, int[] pos) {
        int moves = 0;
        int boundsRow = labMap.size();
        int boundsCol = labMap.get(0).length();
        boolean inLab = true;

        System.out.println("Beginning Simulation... " + boundsCol + "x" + boundsRow);
        while (inLab) {
            if (labMap.get(pos[0]).charAt(pos[1]) == '^') {
                while (pos[0] > -1) {
                    if (pos[0]-1 < 0) {
                        System.out.println("Exited at the top");
                        moves++;
                        inLab = false;
                        break;
                    }
                    if (labMap.get(pos[0]-1).charAt(pos[1]) == '#') {
                        StringBuilder sb = new StringBuilder(labMap.get(pos[0]));
                        sb.setCharAt(pos[1], '>');
                        labMap.replace(pos[0], sb.toString());
                        break;
                    } else {
                        if (labMap.get(pos[0]-1).charAt(pos[1]) == '.') {
                            moves++;
                        }
                        pos[0]--;
                        String marked = labMap.get(pos[0]+1).substring(0, pos[1]) + "X" + labMap.get(pos[0]+1).substring(pos[1]+1);
                        labMap.replace(pos[0]+1, marked);
                        marked = labMap.get(pos[0]).substring(0, pos[1]) + "^" + labMap.get(pos[0]).substring(pos[1]+1);
                        labMap.replace(pos[0], marked);
                    }
                }
            } else if (labMap.get(pos[0]).charAt(pos[1]) == 'v') {
                while (pos[0] < boundsRow) {
                    if (pos[0]+1 >= boundsRow) {
                        System.out.println("Exited at the bottom");
                        moves++;
                        inLab = false;
                        break;
                    }
                    if (labMap.get(pos[0]+1).charAt(pos[1]) == '#') {
                        StringBuilder sb = new StringBuilder(labMap.get(pos[0]));
                        sb.setCharAt(pos[1], '<');
                        labMap.replace(pos[0], sb.toString());
                        break;
                    } else {
                        if (labMap.get(pos[0]+1).charAt(pos[1]) == '.') {
                            moves++;
                        }
                        pos[0]++;
                        String marked = labMap.get(pos[0]-1).substring(0, pos[1]) + "X" + labMap.get(pos[0]-1).substring(pos[1]+1);
                        labMap.replace(pos[0]-1, marked);
                        marked = labMap.get(pos[0]).substring(0, pos[1]) + "v" + labMap.get(pos[0]).substring(pos[1]+1);
                        labMap.replace(pos[0], marked);
                    }
                }
            } else if (labMap.get(pos[0]).charAt(pos[1]) == '<') {
                while (pos[1] > -1) {
                    if (pos[1]-1 < 0) {
                        System.out.println("Exited at the left");
                        moves++;
                        inLab = false;
                        break;
                    }
                    if (labMap.get(pos[0]).charAt(pos[1]-1) == '#') {
                        StringBuilder sb = new StringBuilder(labMap.get(pos[0]));
                        sb.setCharAt(pos[1], '^');
                        labMap.replace(pos[0], sb.toString());
                        break;
                    } else {
                        if (labMap.get(pos[0]).charAt(pos[1]-1) == '.') {
                            moves++;
                        }
                        pos[1]--;
                        StringBuilder sb = new StringBuilder(labMap.get(pos[0]));
                        sb.setCharAt(pos[1], '<');
                        sb.setCharAt(pos[1]+1, 'X');
                        labMap.replace(pos[0], sb.toString());
                    }
                }
            } else if (labMap.get(pos[0]).charAt(pos[1]) == '>') {
                while (pos[1] < boundsCol) {
                    if (pos[0]+1 >= boundsCol) {
                        System.out.println("Exited at the right");
                        moves++;
                        inLab = false;
                        break;
                    }
                    if (labMap.get(pos[0]).charAt(pos[1]+1) == '#') {
                        StringBuilder sb = new StringBuilder(labMap.get(pos[0]));
                        sb.setCharAt(pos[1], 'v');
                        labMap.replace(pos[0], sb.toString());
                        break;
                    } else {
                        if (labMap.get(pos[0]).charAt(pos[1]+1) == '.') {
                            moves++;
                        }
                        pos[1]++;
                        StringBuilder sb = new StringBuilder(labMap.get(pos[0]));
                        sb.setCharAt(pos[1], '>');
                        sb.setCharAt(pos[1]-1, 'X');
                        labMap.replace(pos[0], sb.toString());
                    }
                }
            } else {
                System.out.println("Error: Invalid Direction " + Arrays.toString(pos));
                inLab = false;
                showMap(labMap, true);
                break;
            }
        }
        showMap(labMap, false);

        return moves;
    }

    public static void showMap(HashMap<Integer, String> map, boolean showRowNum) {
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (showRowNum) {
                System.out.println(entry.getKey() + " = " + entry.getValue());
            } else {
                System.out.println(entry.getValue());
            }
        }
    }

    //Part 2
    public static boolean findLoops(HashMap<Integer, String> labMap, int[][] pos, char sentry) {
        Pattern p = Pattern.compile("(<|.)X+(.|>)");
        if (sentry == '^') {
            //>
        } else if (sentry == '>') {
            //v
        } else if (sentry == 'v') {
            //<
        } else if (sentry == '<') {
            //^
        } else {
            System.out.println("Did not find guard");
            return false;
        }
        return false;
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

