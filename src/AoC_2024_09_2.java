import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AoC_2024_09_2 {
    
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
        // HashMap<Integer, ArrayList<Integer>> allFiles = decodeString(input, true);
        // HashMap<Integer, ArrayList<Integer>> allSpaces = decodeString(input, false);
        ArrayList<String> decodedString = decodeString(input);
        // for (int x = 0; x < allFiles.size(); x++) {
        //     System.out.print(allFiles.containsKey(x) ? x + ": " + allFiles.get(x) + "; " : "No " + x + " in allFiles; ");
        //     System.out.println(allSpaces.containsKey(x) ? x + ": " + allSpaces.get(x) : "No " + x + " in allSpaces");
        // }
        // decodedString.forEach( (k, v) -> {
        //     System.out.print(k + ": " + v + ", ");
        // });
        // System.out.println(arrayString(allFiles, allSpaces));
        // ArrayList<String> compactedFiles = compactFiles(allFiles, allSpaces);
        // System.out.println(decodedString);
        System.out.println("\nSum: " + checkSum(decodedString));
    }

    //Part 2
    public static ArrayList<String> decodeString(String input) {
        HashMap<Integer, ArrayList<Integer>> files = new HashMap<>(); // Starting index, file length
        HashMap<Integer, ArrayList<Integer>> space = new HashMap<>();
        boolean file = false;
        // !storeFiles;
        int idxFile = 0;
        int idxTota = 0;
        for (int i = 0; i < input.length(); i++) {
            file = !file;
            ArrayList<Integer> info = new ArrayList<>();
            // ArrayList<Integer> spaceInfo = new ArrayList<>();
            int amt = Integer.parseInt(String.valueOf(input.charAt(i)));
            info.add(idxTota);
            info.add(amt);
            // info.add(file ? 1 : 0);
            // decode.put(idxFile, info);
            // System.out.println(idxFile + ": " + info);
            if (file) {
                files.put(idxFile, info);
            } else {
                if (amt > 0) {
                    space.put(idxFile, info);
                }
                idxFile++;
            }
            idxTota += amt;
        }
        // space.forEach((k, v) -> {
        //     System.out.print(k + ": " + v + ", ");
        // });
        // debug
        files.forEach( (k, v) -> {
            space.forEach((y, e) -> {
                if (v.get(0) == e.get(0)) {
                    System.out.println("DUPLICATE: " + k + "=" + v + " & " + y + "=" + e);
                }
            });
        });

        return compactFiles(files, space, idxTota + Integer.parseInt(String.valueOf(input.charAt(input.length()-1))));
    }

    // In the works
    public static ArrayList<String> compactFiles(
        HashMap<Integer, ArrayList<Integer>> files,
        HashMap<Integer, ArrayList<Integer>> spaces,
        int maxIndex) {
        for (int z = files.size()-1; z >= 0; z--) {
            //9999 -> 0
            for (int a = 0; a < spaces.size(); a++) {
                //0 -> 9998
                if (spaces.containsKey(a) &&
                    spaces.get(a).get(1) > files.get(z).get(1) &&
                    spaces.get(a).get(0) < files.get(z).get(0)) {
                    //Logic
                    // System.out.println("Moving " + z + ": " + files.get(z));
                    int pastIndex = files.get(z).get(0);
                    int moveIndex = spaces.get(a).get(0);
                    int fileSize = files.get(z).get(1);
                    ArrayList<Integer> replacementList = new ArrayList<>();
                    ArrayList<Integer> replacementSpace = new ArrayList<>();
                    ArrayList<Integer> pastFileToSpace = new ArrayList<>();
                    replacementList.add(moveIndex);
                    replacementList.add(fileSize);
                    files.replace(z, replacementList);
                    replacementSpace.add(spaces.get(a).get(0)+fileSize);
                    // int errorInt = spaces.get(a).get(1) - fileSize;
                    // if (errorInt < 1) {
                    //     System.out.println("ERROR! INT IS < 0");
                    // }
                    replacementSpace.add(spaces.get(a).get(1)-fileSize);
                    spaces.replace(a, replacementSpace);
                    pastFileToSpace.add(pastIndex);
                    pastFileToSpace.add(fileSize);
                    spaces.put(-a, pastFileToSpace);
                    break;
                    /*
                     * [00, 3] SPACE
                     * [75, 2] FILES
                     * ... <> 999999
                     * TRANSFORM
                     * 
                     * [02, 1] SPACE
                     * [00, 2] FILES
                     * 999999. <>
                     */
                }
            }
        }
        // System.out.println(files);
        // System.out.println(spaces);
        return arrayString(files, spaces, maxIndex);
    }

    public static ArrayList<String> arrayString(
        HashMap<Integer, ArrayList<Integer>> files,
        HashMap<Integer, ArrayList<Integer>> spaces,
        int maxIndex) {
        ArrayList<String> finalArray = new ArrayList<>();

        HashMap<Integer, ArrayList<Integer>> stringAsMap = new HashMap<>();
        for (int i = 0; i < files.size(); i++) {
            // if (!files.containsKey(i)) {
            //     continue;
            // }
            ArrayList<Integer> newArray = new ArrayList<>();
            newArray.add(i);
            newArray.add(files.get(i).get(1));
            // System.out.println(i + ": " + files.get(i) + " -> " + files.get(i).get(0) + ": " + newArray);
            stringAsMap.put(files.get(i).get(0), newArray);
        }
        for (int i = -spaces.size(); i <= spaces.size(); i++) {
            if (!spaces.containsKey(i)) {
                continue;
            }
            ArrayList<Integer> newArray = new ArrayList<>();
            newArray.add(-1);
            newArray.add(spaces.get(i).get(1));
            // System.out.println(i + ": " + spaces.get(i) + " -> " + spaces.get(i).get(0) + ": " + newArray);
            // if (stringAsMap.containsKey(spaces.get(i).get(0))) {
            //     System.out.println("Duplicate!");
            // }
            stringAsMap.put(spaces.get(i).get(0), newArray);
        }

        // System.out.println(stringAsMap);

        for (int i = 0; i < maxIndex; i++) {
            if (stringAsMap.containsKey(i)) {
                for (int j = 0; j < stringAsMap.get(i).get(1); j++) {
                    String stringToAdd = stringAsMap.get(i).get(0) == -1 ? "." : stringAsMap.get(i).get(0).toString();
                    // System.out.print(stringToAdd);
                    finalArray.add(stringToAdd);
                }
            }
        }

        return finalArray;
    }

    public static Long checkSum(ArrayList<String> input) {
        Long sum = 0L;
        for (int a = 0; a < input.size(); a++) {
            if (input.get(a) != ".") {
                sum += (a * Integer.parseInt(input.get(a)));
            }
        }
        return sum;
    }

    /*
     * GUESSES
     * 8081431669194 WRONG
     * 7737040638294 WRONG
     * 7717157016000 WRONG
     * 664863124417 TOO LOW
     * 647333555406 TOO LOW
     * 606577812716 TOO LOW
     */
}

