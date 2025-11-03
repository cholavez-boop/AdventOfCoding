import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AoC_2024_07 {
    public static void main(String[] args) {
        //File myObj = new File("/Users/Lorenzo Galvez/Documents/Work Files/JustCoding/AdventOfCoding/AoCInputs/AOC2024_07.txt");
        File myObj = new File("D:/Real Life/Oracle/NonWork/AoC_2025/AdventOfCoding/AoCInputs/AOC2024_07.txt");

        ArrayList<ArrayList<Long>> listOfNumbers = new ArrayList<ArrayList<Long>>();
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                ArrayList<Long> numbers = new ArrayList<Long>();
                String data = myReader.nextLine();
                String[] sNums = data.split("\s");
                sNums[0] = sNums[0].replace(":", "");
                for (String sNum : sNums) {
                    numbers.add(Long.parseLong(sNum));
                }
                listOfNumbers.add(numbers);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }
        // System.out.println(listOfNumbers);
        // for (ArrayList<Long> equation : listOfNumbers) {
        //     System.out.println(equation);
        // }

        System.out.println("Sum of lines that could be true: " + sumOfTrue(listOfNumbers));

    }

    //Part 1
    public static Long sumOfTrue(ArrayList<ArrayList<Long>> data) {
        Long sum = 0L;
        int failed = 0;
        for (int i = 0; i < data.size(); i++) {
            // System.out.println(data.get(i));
            if (canCalculate(data.get(i))) {
                sum += data.get(i).get(0);
            } else {
                failed++;
                System.out.println(failed + ": No Solution: " + data.get(i));
            }
        }
        return sum;
    }

    public static boolean canCalculate(ArrayList<Long> data) {
        Long goal = data.get(0);
        boolean showMath = false;
        if (data.size()-2 == 0) {
            showMath = true;
        }
        Long currentVal = 0L;
        boolean allOps = false;

        ArrayList<Integer> binary = new ArrayList<>();
        for (int k = 0; k < data.size()-2; k++) {
            binary.add(0);
        }
        // System.out.println("New Set");
        // System.out.println(data);
        // System.out.println(data.size()-2 + ": " + binary);

        while (true) {
            StringBuilder sb = new StringBuilder();
            for (Integer bin : binary) {
                sb.append(bin);
            }
            String binaryString = sb.toString();
            Long binVal = Long.parseLong(binaryString);
            // System.out.println("Checking " + binaryString);
            if (binVal == 0) {
                if (allOps) {
                    break;
                } else {
                    allOps = true;
                }
            }
            currentVal = data.get(1);
            if (showMath) {
               System.out.print("\n" + currentVal); 
            }
            for (int a = 0; a < binary.size(); a++) {
                if (showMath) {
                    System.out.print(" (" + currentVal + ")");
                }
                if (binary.get(a) == 1) {
                    if (showMath) {
                        System.out.print(" + " + data.get(a+2));
                    }
                    currentVal += data.get(2+a);
                } else if (binary.get(a) == 0) {
                    if (showMath) {
                        System.out.print(" * " + data.get(a+2));
                    }
                    currentVal *= data.get(2+a);
                } else if (binary.get(a) == 2) {
                    // Concat
                    if (showMath) {
                        System.out.print(" || " + data.get(a+2));
                    }
                    String valString = currentVal.toString();
                    String toConcat = data.get(a+2).toString();
                    if (showMath) {
                        System.out.print(" (" + valString + ") + (" + toConcat + ") ->");
                    }
                    valString = valString.concat(toConcat);
                    if (showMath) {
                        System.out.print(" (" + valString + ")");
                    }
                    currentVal = Long.parseLong(valString);
                }
                // else {
                //     System.out.println(binary.get(a));
                //     break;
                // }
            }
            if (showMath) {
                System.out.print(" = " + currentVal + "\n");
                System.out.println("Comparing " + goal + " and " + currentVal);
            }
            if (currentVal - goal == 0L) {
                // System.out.println("Found Solution\n" + binVal);
                return true;
            }
            // if (currentVal <= 0) {
            //     System.out.println(binary + "\n" + data + "\n" + currentVal);
            // }
            binary = iterateBinary(binary);
        }
        // System.out.println("No Solution");
        return false;
    }

    public static ArrayList<Integer> iterateBinary (ArrayList<Integer> binary) {
        binary.add(0, binary.remove(0)+1);
        int radx = 2;
        for (int b = 0; b < binary.size(); b++) {
            if (binary.get(b) > radx) {
                binary.add(b, binary.remove(b)%(radx+1));
                if (b+1 < binary.size()) {
                    binary.add(b+1, binary.remove(b+1)+1);
                }
            }
        }
        return binary;
    }

    /*
     * WRONG
     * 
     * 12554334358754
     */
}

