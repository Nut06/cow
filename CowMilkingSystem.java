public class CowMilkingSystem {
    public static void main(String[] args) {
        CowView view = new CowView();
        CowController controller = new CowController(view);
    }
}
