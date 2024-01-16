import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Sudoku {
    public Sudoku() {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("SUDOKU");
        JLabel[] LabelArray = new JLabel[10];
        JPanel[] PanelArray = new JPanel[10];
        frame.setDefaultCloseOperation(3);
        frame.pack();
        frame.setVisible(true);
        frame.setLayout(new GridLayout(3, 3));
        frame.setVisible(true);

        int j;
        for(j = 0; j < 10; ++j) {
            PanelArray[j] = new JPanel();
            PanelArray[j].setSize(300, 300);
            PanelArray[j].setLayout(new GridLayout(3, 3));
            PanelArray[j].setBackground(Color.GREEN);
            PanelArray[j].setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));
        }

        for(j = 1; j < 10; ++j) {
            ArrayList<Integer> SudokuNumbers = new ArrayList();

            for(int i = 0; i < 9; ++i) {
                SudokuNumbers.add(i);
            }

            Collections.shuffle(SudokuNumbers);
            JLabel[] labels = new JLabel[10];

            for(int i = 0; i < 9; ++i) {
                JLabel labelTemparory = new JLabel("", 0);
                labelTemparory.setText(String.valueOf(SudokuNumbers.get(i)));
                labels[i] = labelTemparory;
                PanelArray[j].add(labels[i]);
            }

            frame.add(PanelArray[j]);
        }

        frame.pack();
        frame.setSize(900, 900);
    }
}