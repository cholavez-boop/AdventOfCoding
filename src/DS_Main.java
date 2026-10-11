
import java.util.ArrayList;

public class DS_Main {
    public static void main(String[] args) {
        DS_Character character1 = new DS_Character("Arox", 4);
        DS_Character character2 = new DS_Character("Joe", 3);
        DS_Character character3 = new DS_Character("Ayfor", 2);

        ArrayList<DS_Character> characters = new ArrayList<>();
        characters.add(character1);
        characters.add(character2);
        characters.add(character3);
        characters.add(new DS_Character("Elis", 5));


        for (DS_Character character : characters) {
            if (character.getLevel() >= 3) {
                System.out.println(character);
            }
        }
    }
}
