public class Cow {
    int id;
    String color;
    int age;
    int totalMilked;
    int freshMilk;
    int sourMilk;
    int chocolateMilk;
    boolean isMistaken;

    public Cow(int id, String color, int age) {
        this.id = id;
        this.color = color;
        this.age = age;
        this.totalMilked = 0;
        this.freshMilk = 0;
        this.sourMilk = 0;
        this.chocolateMilk = 0;
        this.isMistaken = false;
    }

    public void resetMistake() {
        this.isMistaken = false;
    }
}
