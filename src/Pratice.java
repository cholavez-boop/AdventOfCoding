import java.util.ArrayList;

public class Pratice {
    public static void main(String[] args) {
        countOneToHundred(true);
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
}
