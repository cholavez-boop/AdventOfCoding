import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AoC_2023_12 {
    public static void main(String[] args) {
        // File myObj = new File("/Users/Lorenzo Galvez/Documents/Work Files/JustCoding/AdventOfCoding/AoCInputs/AOC2023_12.txt");
        File myObj = new File("D:/Real Life/Oracle/NonWork/AoC_2025/AdventOfCoding/AoCInputs/AOC2023_12.txt");

        //Read File
        ArrayList<ArrayList<String>> input = new ArrayList<>();
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String nextLine = myReader.nextLine();
                String[] inputLine = nextLine.split("\s");
                ArrayList<String> innerInput = new ArrayList<>();
                for (String s : inputLine) {
                    innerInput.add(s);
                }
                input.add(innerInput);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }

        //Process Input
        HashMap<String, ArrayList<Integer>> springs = new HashMap<>();
        ArrayList<String> keys = new ArrayList<>();
        for (ArrayList<String> innerInput : input) {
            if (!springs.containsKey(innerInput.get(0))) {
                springs.put(innerInput.get(0), intsList(innerInput.get(1)));
            } else {
                // System.out.println("There are duplicate rows of springs: " + innerInput.get(0));
                StringBuilder sb = new StringBuilder();
                sb.append(innerInput.get(0));
                while (springs.containsKey(sb.toString())){
                    sb.append("X");
                }
                springs.put(sb.toString(), intsList(innerInput.get(1)));
            }
        }
        keys.addAll(springs.keySet());
        // System.out.println(keys);

        // Show processed input & find num of possible combos
        int totalPos = 0;
        for (String str : keys) {
            reducePos(str, springs.get(str));
            // totalPos += getNumOfPos(str, springs.get(str));
        }

        System.out.println("Sum: " + totalPos);
    }

    public static ArrayList<Integer> intsList(String intInput) {
        ArrayList<Integer> intsList = new ArrayList<>();
        String[] intsArr = intInput.split(",");
        for (String t : intsArr) {
            intsList.add(Integer.parseInt(t));
        }
        return intsList;
    }

    public static String reducePos(String string, ArrayList<Integer> notes) {
        for (int h : notes) {
            if (h == 1) {continue;}
            StringBuilder regex = new StringBuilder();
            for (int i = 0; i < h; i++) {
                regex.append('#');
            }
            Pattern p = Pattern.compile(regex.toString());
            Matcher m = p.matcher(string);
            if (m.find()) {
                System.out.println(h + " is in " + string + ": " + notes);
            }
        }
        return string;
    }
    
    public static int getNumOfPos(String inp, ArrayList<Integer> pattern) {
        StringBuilder regex = new StringBuilder();
        regex.append(".*");
        for (int p = 0; p < pattern.size(); p++) {
            for (int num = 0; num < pattern.get(p); num++) {
                regex.append("#");
            }
            if (p == pattern.size()-1) {
                regex.append(".*");
            } else {
                regex.append(".+");
            }
        }
        // System.out.println(regex.toString() + pattern);
        Pattern p = Pattern.compile(regex.toString());

        int qMarks = 0;
        for (int i = 0; i < inp.length(); i++) {
            if (inp.charAt(i) == '?') {
                qMarks++;
            }
        }
        ArrayList<Boolean> dotOrHash = new ArrayList<>();
        for (int q = 0; q < qMarks; q++) {
            dotOrHash.add(true);
        }
        // System.out.println(dotOrHash);
        int possCombo = 0;
        while (dotOrHash.contains(true)) {
            Matcher m = p.matcher(possibleString(inp, dotOrHash));
            if (m.find()) {
                possCombo++;
            }
            dotOrHash = nextBin(dotOrHash);
            System.out.println(dotOrHash);
        }

        return possCombo;
    }

    public static String possibleString(String inpt, ArrayList<Boolean> binary) {
        int binIdx = 0;
        StringBuilder stringToCheck = new StringBuilder();
        for (int s = 0; s < inpt.length(); s++) {
            if (inpt.charAt(s) != 'X') {
                if (inpt.charAt(s) != '?') {
                    stringToCheck.append(inpt.charAt(s));
                } else {
                    if (binary.get(binIdx)) {
                        stringToCheck.append('.');
                    } else {
                        stringToCheck.append('#');
                    }
                    binIdx++;
                }
            }
        }
        return stringToCheck.toString();      
    }

    public static ArrayList<Boolean> nextBin(ArrayList<Boolean> binary) {
        /*
         * 1 = T
         * 0 = F
         * 
         * if T -> F, STOP
         * if F -> T, check next index
         */
        for (int z = 0; z < binary.size(); z++) {
            boolean bin = !binary.get(z);
            binary.remove(z);
            binary.add(z, bin);
            if (!binary.get(z)) {
                break;
            }
        }
        // System.out.println(binary);
        return binary;
    }
}
