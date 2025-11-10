import java.util.ArrayList;

public class Pratice {
    public static void main(String[] args) {
        // countOneToHundred(true);
        graphDigits(600);
    }

    public static void countOneToHundred(boolean insert) {
        for (int i = 1; i <= 100; i++) {
            System.out.println(i);
            if (insert && i == 50) {
                System.out.println("Again");
                modifyCount(i);
            }
        }
    }

    public static void modifyCount(int count) {
        System.out.println("Modify Count at: " + count);
        countOneToHundred(false);
    }

    public static void test() {
        ArrayList<String> testStrings = new ArrayList<>();
        testStrings.add("one");
        testStrings.add("two");
        testStrings.add("three");
        testStrings.add("four");

        System.out.println(testStrings);
        String testing = testStrings.remove(2);
        System.out.println(testing);
        System.out.println(testStrings);
    }

    public static void graphDigits(int times) {
        for (int i = 1; i < times+1; i++) {
            int prod = 2024 * i;
            String prodDigits = String.valueOf(prod);
            System.out.print(prodDigits.length()%2 == 0 ? 2 : 1);
        }
    }
}
