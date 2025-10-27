import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AoC_2024_05 {
    public static void main(String[] args) {
        //File myObj = new File("/Users/Lorenzo Galvez/Documents/Work Files/JustCoding/AdventOfCoding/AoCInputs/AOC2024_05.txt");
        File myObj = new File("D:/Real Life/Oracle/NonWork/AoC_2025/AdventOfCoding/AoCInputs/AOC2024_05.txt");

        HashMap<Integer, ArrayList<Integer>> pageOrders = new HashMap<>();
        ArrayList<ArrayList<Integer>> pages = new ArrayList<>();
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.contains("|")) {
                    String[] parts = data.split("\\|");
                    Integer prerequisite = Integer.parseInt(parts[1].trim());
                    Integer keyPage = Integer.parseInt(parts[0].trim());
                    ArrayList<Integer> prereqPages = new ArrayList<>();
                    if (!pageOrders.containsKey(keyPage)) {
                        prereqPages.add(prerequisite);
                        pageOrders.put(keyPage, prereqPages);
                    } else {
                        prereqPages = pageOrders.get(keyPage);
                        prereqPages.add(prerequisite);
                        pageOrders.replace(keyPage, prereqPages);
                    }
                } else if (!data.isBlank()) {
                    ArrayList<Integer> page = new ArrayList<>();
                    String[] nums = data.trim().split("\\,");
                    for (String num : nums) {
                        page.add(Integer.parseInt(num));
                    }
                    pages.add(page);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }

        //System.out.println(pageOrders);

        int sumOfCorrectedMiddlePages = 0;
        //int wrongs = 0;
        //int rights = 0;
        for (ArrayList<Integer> pageList : pages) {
            //System.out.println("\nChecking page list: " + pageList);
            if (!properOrder(pageOrders, pageList)) {
                ArrayList<Integer> correctedList = correctOrder(pageOrders, pageList);
                //System.out.println("Corrected order: " + correctedList);
                sumOfCorrectedMiddlePages += findMiddlePage(correctedList);
                //sumOfCorrectedMiddlePages += findMiddlePage(pageList);
                //wrongs ++;
            } else {
                //rights ++;
            }
        }
        //System.out.println("Total: " + pages.size() + "\nRight: " + rights + "\nWrong: " + wrongs);
        System.out.println("\nThe sum of the middle pages in corrected order is: " + sumOfCorrectedMiddlePages);
    }

    //Part 1
    public static int findMiddlePage(ArrayList<Integer> pageList) {
        return pageList.get((pageList.size() - 1) / 2);
    }

    public static boolean properOrder(HashMap<Integer, ArrayList<Integer>> pageOrders, ArrayList<Integer> pageList) {
        ArrayList<Integer> visitedPages = new ArrayList<>();
        for (int i = 1; i < pageList.size(); i++) {
            visitedPages.add(pageList.get(i-1));
            Integer currentPage = pageList.get(i);
            for (Integer prevPage : visitedPages) {
                if (!pageOrders.get(prevPage).contains(currentPage)) {
                    //System.out.println("NOT IN ORDER\n" + prevPage + " cannot come before " + currentPage);
                    return false;
                }
            }
        }
        //System.out.println("IN ORDER");
        return true;
    }

    //Part 2
    public static ArrayList<Integer> correctOrder(HashMap<Integer, ArrayList<Integer>> pageOrders, ArrayList<Integer> pageList) {
        ArrayList<Integer> visitedPages = new ArrayList<>();
        boolean redo = false;
        //System.out.println("\nChecking page list: " + pageList);
        for (int i = 1; i < pageList.size(); i++) {
            visitedPages.add(pageList.get(i-1));
            Integer currentPage = pageList.get(i);
            //System.out.println("Checking: " + pageList + "\nVisited: " + visitedPages);
            for (Integer prevPage : visitedPages) {
                //System.out.println("We're on page: " + currentPage + "\n" + visitedPages);
                if (!pageOrders.get(prevPage).contains(currentPage)) {
                    //System.out.println("ERROR: " + currentPage + " cannot come after " + prevPage);
                    //System.out.println("NOT IN ORDER\n" + prevPage + " cannot come before " + currentPage);
                    //Swap places
                    int indexNow = pageList.indexOf(currentPage);
                    int indexToSwap = pageList.indexOf(prevPage);
                    pageList.set(indexToSwap, currentPage);
                    pageList.set(indexNow, prevPage);
                    visitedPages.clear();
                    i = 0;
                    redo = true;
                    //System.out.println("RESTARTING");
                    break;
                }
            }
            if (redo) {
                redo = false;
                continue;
            }
        }
        return pageList;
    }
}

