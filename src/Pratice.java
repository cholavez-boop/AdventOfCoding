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
}
