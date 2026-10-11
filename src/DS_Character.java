public class DS_Character {
    String name;
    int level;

    public DS_Character(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "DS_Character{" +
                "name='" + name + '\'' +
                ", level=" + level +
                '}';
    }
}
