import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CowView {
    private JFrame frame;
    private JPanel inputPanel;
    private JPanel resultPanel;
    private JTextField idField;
    private JButton checkButton;
    private JButton backButton;
    private JButton resetButton;
    private JLabel resultLabel;

    public CowView() {
        frame = new JFrame("Cow Milking System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0, 1));
        idField = new JTextField(8);
        checkButton = new JButton("Check");
        inputPanel.add(new JLabel("Enter Cow ID:"));
        inputPanel.add(idField);
        inputPanel.add(checkButton);

        resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());
        resultLabel = new JLabel();
        resultPanel.add(resultLabel, BorderLayout.CENTER);

        backButton = new JButton("Back");
        resetButton = new JButton("Reset Mistake");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(resetButton);
        resultPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(inputPanel);
        frame.setVisible(true);
    }

    public String getCowId() {
        return idField.getText();
    }

    public JButton getCheckButton() {
        return checkButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public void showInputScreen() {
        frame.getContentPane().removeAll();
        frame.add(inputPanel);
        frame.revalidate();
        frame.repaint();
    }

    public void showResultScreen(String cowData, List<Cow> cowList) {
        frame.getContentPane().removeAll();

        resultLabel.setText("<html>" + cowData + "<br/><br/>All Cows' Milk Production:<br/>");

        for (Cow cow : cowList) {
            String mistakeStatus = cow.isMistaken ? " (Mistake)" : "";
            resultLabel.setText(resultLabel.getText() + "Cow ID: " + cow.id + " - Total Milked: " + cow.totalMilked + mistakeStatus + "<br/>");
        }

        resultLabel.setText(resultLabel.getText() + "</html>");

        frame.add(resultPanel);
        frame.revalidate();
        frame.repaint();
    }

    public void displayError(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
}