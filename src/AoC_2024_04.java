import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class AoC_2024_04 {
    public static void main(String[] args) {
        ArrayList<String> dataLines = readData();
        // for (String line : dataLines) {
        //     System.out.println(line);
        // }

        int xmasFound = 0;

        //Part 1
        // xmasFound += horizontal(dataLines);
        // xmasFound += vertical(dataLines);
        // xmasFound += diagonal(dataLines);

        //Part 2
        xmasFound += xmas(dataLines);

        System.out.println("Total XMAS found: " + xmasFound);
    }

    public static ArrayList<String> readData() {
        //File myObj = new File("/Users/Lorenzo Galvez/Documents/Work Files/JustCoding/AdventOfCoding/AoCInputs/AOC2024_04.txt");
        File myObj = new File("D:/Real Life/Oracle/NonWork/AoC_2025/AdventOfCoding/AoCInputs/AOC2024_04.txt");

        ArrayList<String> dataLines = new ArrayList<>();
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                dataLines.add(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }
        return dataLines;
    }

    //Part 1
    // public static int horizontal(ArrayList<String> dataLines) {
    //     int horiz = 0;
    //     Pattern p1 = Pattern.compile("XMAS");
    //     Pattern p2 = Pattern.compile("SAMX");
    //     for (String line : dataLines) {
    //         Matcher m1 = p1.matcher(line);
    //         Matcher m2 = p2.matcher(line);
    //         while (m1.find()) {
    //             horiz += 1;
    //         }
    //         while (m2.find()) {
    //             horiz += 1;
    //         }
    //     }
    //     return horiz;
    // }

    // public static int vertical(ArrayList<String> dataLines) {
    //     int vert = 0;
    //     int lineLength = dataLines.get(0).length();
    //     Pattern p1 = Pattern.compile("XMAS");
    //     Pattern p2 = Pattern.compile("SAMX");
    //     for (int i = 0; i < lineLength; i++) {
    //         StringBuilder colBuilder = new StringBuilder();
    //         for (String line : dataLines) {
    //             colBuilder.append(line.charAt(i));
    //         }
    //         String column = colBuilder.toString();
    //         Matcher m1 = p1.matcher(column);
    //         Matcher m2 = p2.matcher(column);
    //         while (m1.find()) {
    //             vert += 1;
    //         }
    //         while (m2.find()) {
    //             vert += 1;
    //         }
    //     }
    //     return vert;
    // }

    // public static int diagonal(ArrayList<String> dataLines) {    
    //     int diag = 0;
    // Pattern p = Pattern.compile("X");
    // for (int j = 0; j < dataLines.size(); j++) {
    //     Matcher m = p.matcher(dataLines.get(j));
    //     while (m.find()) {
    //         int index = m.start();
    //         // diag lower right
    //         if (j < dataLines.size() - 3 && index < dataLines.get(j).length() - 3) {
    //             if (dataLines.get(j+1).charAt(index+1) == 'M' &&
    //                 dataLines.get(j+2).charAt(index+2) == 'A' &&
    //                 dataLines.get(j+3).charAt(index+3) == 'S') {
    //                     diag += 1;
    //             }
    //         }
    //         // diag lower left
    //         if (j < dataLines.size() - 3 && index >= 3) {
    //             if (dataLines.get(j+1).charAt(index-1) == 'M' &&
    //                 dataLines.get(j+2).charAt(index-2) == 'A' &&
    //                 dataLines.get(j+3).charAt(index-3) == 'S') {
    //                     diag += 1;
    //             }
    //         }
    //         // diag upper right
    //         if (j >= 3 && index < dataLines.get(j).length() - 3) {
    //             if (dataLines.get(j-1).charAt(index+1) == 'M' &&
    //                 dataLines.get(j-2).charAt(index+2) == 'A' &&
    //                 dataLines.get(j-3).charAt(index+3) == 'S') {
    //                     diag += 1;
    //             }
    //         }
    //         // diag upper left
    //         if (j >= 3 && index >= 3) {
    //             if (dataLines.get(j-1).charAt(index-1) == 'M' &&
    //                 dataLines.get(j-2).charAt(index-2) == 'A' &&
    //                 dataLines.get(j-3).charAt(index-3) == 'S') {
    //                     diag += 1;
    //             }
    //         }
    //     }
    // }
    //     return diag;
    // }

    //Part 2
    public static int xmas(ArrayList<String> dataLines) {
        int xNaMAS = 0;
        Pattern p = Pattern.compile("A");
        for (int j = 0; j < dataLines.size(); j++) {
            Matcher m = p.matcher(dataLines.get(j));
            while (m.find()) {
                int index = m.start();
                // diag lower right
                if (j < dataLines.size() - 1 &&
                    j > 0 &&
                    index < dataLines.get(j).length() - 1 &&
                    index > 0) {
                    //M.S
                    //.A.
                    //M.S
                    if (dataLines.get(j-1).charAt(index-1) == 'M' &&
                        dataLines.get(j+1).charAt(index+1) == 'S' &&
                        dataLines.get(j+1).charAt(index-1) == 'M' &&
                        dataLines.get(j-1).charAt(index+1) == 'S') {
                            xNaMAS += 1;
                    }
                    //M.M
                    //.A.
                    //S.S
                    if (dataLines.get(j-1).charAt(index-1) == 'M' &&
                        dataLines.get(j+1).charAt(index+1) == 'S' &&
                        dataLines.get(j+1).charAt(index-1) == 'S' &&
                        dataLines.get(j-1).charAt(index+1) == 'M') {
                            xNaMAS += 1;
                    }
                    //S.M
                    //.A.
                    //S.M
                    if (dataLines.get(j-1).charAt(index-1) == 'S' &&
                        dataLines.get(j+1).charAt(index+1) == 'M' &&
                        dataLines.get(j+1).charAt(index-1) == 'S' &&
                        dataLines.get(j-1).charAt(index+1) == 'M') {
                            xNaMAS += 1;
                    }
                    //S.S
                    //.A.
                    //M.M
                    if (dataLines.get(j-1).charAt(index-1) == 'S' &&
                        dataLines.get(j+1).charAt(index+1) == 'M' &&
                        dataLines.get(j+1).charAt(index-1) == 'M' &&
                        dataLines.get(j-1).charAt(index+1) == 'S') {
                            xNaMAS += 1;
                    }
                }
            }
        }
        return xNaMAS;
    }
}
