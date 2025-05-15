import javax.swing.*;
import java.awt.*;

public class CalculatorGUI {
    private JFrame frame;
    private JTextField display;
    private JPanel buttoPanel;

    public CalculatorGUI() {
        frame = new JFrame("Calculator");
        frame.setSize(300, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        display = new JTextField("0");
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(display, BorderLayout.NORTH);

        buttoPanel = new JPanel();
        buttoPanel.setLayout(new GridLayout(5, 4, 5, 5));
    }

    public void show() {
        frame.setVisible(true);
    }
}
