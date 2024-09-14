import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CowController {
    private CowView view;
    private List<Cow> cows;
    private Cow selectedCow;

    public CowController(CowView view) {
        this.view = view;
        initializeCows();
        view.getCheckButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cowIdText = view.getCowId();
                try {
                    int cowId = Integer.parseInt(cowIdText);
                    Cow cow = findCowById(cowId);
                    if (cow != null) {
                        selectedCow = cow;
                        milkCow(cow);
                        String cowData = buildCowData(cow);

                        view.showResultScreen(cowData, cows);
                    } else {
                        view.displayError("Cow not found.");
                    }
                } catch (NumberFormatException ex) {
                    view.displayError("Invalid Cow ID.");
                }
            }
        });

        view.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.showInputScreen();
            }
        });

        view.getResetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedCow != null && selectedCow.isMistaken) {
                    selectedCow.resetMistake();
                    String cowData = buildCowData(selectedCow);
                    view.showResultScreen(cowData, cows);
                }
            }
        });
    }

    private void initializeCows() {
        cows = new ArrayList<>();
        cows.add(new Cow(11111111, "WHITE", 3));
        cows.add(new Cow(22222222, "BROWN", 5));
        cows.add(new Cow(33333333, "WHITE", 2));
        cows.add(new Cow(44444444, "WHITE", 4));
        cows.add(new Cow(55555555, "BROWN", 1));
        cows.add(new Cow(66666666, "BROWN", 6));
        cows.add(new Cow(77777777, "WHITE", 5));
        cows.add(new Cow(88888888, "BROWN", 3));
        cows.add(new Cow(99999999, "WHITE", 1));
        cows.add(new Cow(10101010, "BROWN", 2));
    }

    private Cow findCowById(int id) {
        for (Cow cow : cows) {
            if (cow.id == id) {
                return cow;
            }
        }
        return null;
    }

    private void milkCow(Cow cow) {
        Random rand = new Random();
        double mistakeChance = cow.color.equals("WHITE") ? 0.005 * cow.age : 0.01 * cow.age;

        if (rand.nextDouble() < mistakeChance) {
            cow.isMistaken = true;
            cow.color = "SKY-BLUE";
        } else {
            if (cow.color.equals("WHITE")) {
                cow.freshMilk++;
            } else {
                cow.chocolateMilk++;
            }
            cow.totalMilked++;
        }
    }

    private String buildCowData(Cow cow) {
        return "Cow ID: " + cow.id + "<br/>Fresh Milk: " + cow.freshMilk + 
               "<br/>Sour Milk: " + cow.sourMilk + 
               "<br/>Chocolate Milk: " + cow.chocolateMilk + 
               "<br/>Total Milked: " + cow.totalMilked + 
               (cow.isMistaken ? "<br/><span style='color:red;'>BSOD Cow</span>" : "");
    }
}
