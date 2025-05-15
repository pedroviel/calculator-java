import operations.Division;
import operations.Multiplication;
import operations.Sub;
import operations.Sum;

import java.util.Scanner;

public class CalculatorApp {
    public static void main(String[] args) {
        Sum sum = new Sum();
        Sub sub = new Sub();
        Division div = new Division();
        Multiplication mul = new Multiplication();

        CalculatorGUI gui = new CalculatorGUI();
        gui.show();



    }
}