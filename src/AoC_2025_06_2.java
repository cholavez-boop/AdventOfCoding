import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AoC_2025_06_2 {
    public static void main(String[] args) {
        // File myObj = new File("/Users/Lorenzo Galvez/Documents/Work Files/JustCoding/AdventOfCoding/AoCInputs/AOC2025_06.txt");
        File myObj = new File("D:/Real Life/Oracle/NonWork/AoC_2025/AdventOfCoding/AoCInputs/AOC2025_06.txt");

        ArrayList<String> values = new ArrayList<>();
        int lineNum = 0;
        Long ans = 0L;
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (lineNum == 4) {
                    values.add("");
                    String[] chars = data.split("\s+");
                    int i = 0;
                    Long smallAns;
                    boolean mult = true;
                    if (chars[0].equals("*")) {
                        smallAns = 1L;
                        mult = true;
                    } else {
                        smallAns = 0L;
                        mult = false;
                    }
                    // System.out.println(Arrays.toString(chars));
                    // ArrayList<Integer> valInEqu = new ArrayList<>();
                    for (String s : values) {
                        if (!s.isBlank()) {
                            s = s.trim();
                            if (mult) {
                                smallAns *= Long.parseLong(s);
                            } else {
                                smallAns += Long.parseLong(s);
                            }
                            // System.out.print(Integer.parseInt(s) + " " + chars[i] + " ");
                        } else {
                            // System.out.print("= ?\n");
                            ans += smallAns;
                            i++;
                            if (i < 1000) {
                                if (chars[i].equals("+")) {
                                    smallAns = 0L;
                                    mult = false;
                                } else if (chars[i].equals("*")) {
                                    smallAns = 1L;
                                    mult = true;
                                }
                            }
                        }
                    }
                } else if (lineNum == 0) {
                    for (char c : data.toCharArray()) {
                        StringBuilder firstLine = new StringBuilder();
                        firstLine.append(c);
                        values.add(firstLine.toString());
                    }
                } else {
                    for (int i = 0; i < values.size(); i++) {
                        StringBuilder nextLine = new StringBuilder();
                        nextLine.append(values.get(i));
                        nextLine.append(data.charAt(i));
                        values.set(i, nextLine.toString());
                    }
                }
                lineNum++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }

        System.out.println(ans);
    }

    // 10600728099803 TOO LOW
    // 10600728099803 + 13062 = 10600728112865
}
