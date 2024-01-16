import java.awt.BorderLayout;
import java.awt.Desktop.Action;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;


public class Main {


    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("GridLayout Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2));
        frame.pack(); frame.setVisible(true);

        String labels[] = { "A", "B", "C", "D", "E", "F", "G" };
        final JComboBox comboBox = new JComboBox(labels);
        comboBox.setMaximumRowCount(5);
        comboBox.setEditable(true);
        frame.add(comboBox, BorderLayout.NORTH);

        JSlider aJSlider = new JSlider();
        frame.add(aJSlider);
        JSlider aaJSlider = new JSlider(JSlider.VERTICAL);
        JSlider bJSlider = new JSlider(JSlider.HORIZONTAL);

        JSpinner spinner = new JSpinner();
        frame.add(spinner);
        spinner.setValue(42);

        JButton button = new JButton("dog");
        frame.add(button);

        JLabel label = new JLabel("dsffdsdsf");
        frame.add(label);

        JTextField tf = new JTextField("Hello Dave");
        frame.add(tf);

        JCheckBox aCheckBox = new JCheckBox();
        frame.add(aCheckBox);
        aCheckBox.setSelected(true);

        JRadioButton aRadioButton = new JRadioButton();
        frame.add(aRadioButton);
        frame.setSize(900,900);

    }

}
