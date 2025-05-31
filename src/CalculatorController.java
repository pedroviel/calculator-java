import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;
import java.util.Objects;

import static java.lang.Math.sqrt;

public class CalculatorController{
    @FXML
    private TextField display;

    @FXML
    private TextField savedNumbers;

    private String firstNumber = "";
    private String currentNumber = "";
    private String calculationType;
    private double result;
    boolean isResult = false;
    boolean percentage = false;

    @FXML
    private void clearEntryAction(ActionEvent event) {
        currentNumber = "0";
        display.setText("0");
    }

    @FXML
    private void clearAction(ActionEvent event) {
        currentNumber = "0";
        display.setText("0");
        savedNumbers.setText("");
    }

    @FXML
    private void deleteAction(ActionEvent event) {
        if(isResult) {
            clearAction(event);
        }

        if(currentNumber.length() == 1){
            display.setText("0");
            currentNumber = "0";
        }

        if(currentNumber.length() > 1){
            currentNumber = currentNumber.substring(0, currentNumber.length() - 1);
        }

        display.setText(currentNumber);
    }

    @FXML
    private void addAction(ActionEvent event) {
        isResult = false;
        calculationSetup("+");
    }

    @FXML
    private void divAction(ActionEvent event) {
        isResult = false;
        calculationSetup("÷");
    }

    @FXML
    private void mulAction(ActionEvent event) {
        isResult = false;
        calculationSetup("×");
    }

    @FXML
    private void subAction(ActionEvent event) {
        isResult = false;
        calculationSetup("-");
    }

    @FXML
    private void powAction(ActionEvent event) {
        isResult = false;
        calculationSetup("²");
    }

    @FXML
    private void squareRootAction(ActionEvent event) {
        isResult = false;
        calculationSetup("√");
    }

    @FXML
    private void inverseNumberAction(ActionEvent event) {
        isResult = false;
        calculationSetup("¹⁄ₓ");
    }

    @FXML
    private void modAction(ActionEvent event) {
        isResult = false;
        calculationSetup("mod");
    }

    @FXML
    private void percentageAction(ActionEvent event) {
        isResult = false;
        percentage = true;
        calculate(event);
    }

    private void calculationSetup(String calculationType) {
        this.calculationType = calculationType;
        firstNumber = currentNumber;
        currentNumber = "";
        if(calculationType.equals("²")){
            savedNumbers.setText(firstNumber + "²");
        } else if (calculationType.equals("√")) {
            savedNumbers.setText("√" + firstNumber);
        } else if(calculationType.equals("¹⁄ₓ")) {
            savedNumbers.setText("1/" + firstNumber);
        } else {
            savedNumbers.setText(firstNumber + " " + calculationType);
        }
    }

    @FXML
    private void calculate(ActionEvent event) {
        if(firstNumber == null || firstNumber.isEmpty()){
            result = 0;
            updateDisplayAndResult(result);
        }
        double num1 = Double.parseDouble(firstNumber);

        switch (calculationType) {
            case "+":
            case "-":
            case "×":
            case "÷":
            case "mod":
                double num2 = Double.parseDouble(currentNumber);
                if(percentage){
                    num2 = num1 * num2 / 100;
                    percentage = false;
                }
            switch (calculationType){
                case "+":
                    result = num1 + num2;
                    isResult = true;
                    break;
                case "-":
                    result = num1 - num2;
                    isResult = true;
                    break;
                case "×":
                    result = num1 * num2;
                    isResult = true;
                    break;
                case "÷":
                    result = num1 / num2;
                    isResult = true;
                    break;
                case "mod":
                    result = num1 % num2;
                    isResult = true;
                    break;
            }
                savedNumbers.setText(firstNumber + " " + calculationType + " " + currentNumber + " =");
                updateDisplayAndResult(result);
                break;
            case "²":
                result = num1 * num1;
                savedNumbers.setText(firstNumber + "²" + " =");
                updateDisplayAndResult(result);
                isResult = true;
                break;
            case "√":
                result = sqrt(num1);
                savedNumbers.setText("√" + firstNumber + " =");
                updateDisplayAndResult(result);
                isResult = true;
                break;
            case "¹⁄ₓ":
                result = 1/num1;
                savedNumbers.setText("1/" + firstNumber + " =");
                updateDisplayAndResult(result);
                isResult = true;
                break;
            default:
                break;
        }
    }

    private void updateDisplayAndResult(double result) {
        DecimalFormat df = new DecimalFormat("#.#######");
        String formattedResult = df.format(result).replace(",", ".");
        display.setText(String.valueOf(formattedResult));

        if(result % 1 == 0) {
            currentNumber = String.valueOf((int) result);
            display.setText(String.valueOf((int) result));
        } else {
            currentNumber = String.valueOf(formattedResult);
            display.setText(String.valueOf(formattedResult));
        }
    }

    @FXML
    private void button1Clicked(ActionEvent event){
        addNumber("1");
    }

    @FXML
    private void button2Clicked(ActionEvent event){
        addNumber("2");
    }

    @FXML
    private void button3Clicked(ActionEvent event){
        addNumber("3");
    }

    @FXML
    private void button4Clicked(ActionEvent event){
        addNumber("4");
    }

    @FXML
    private void button5Clicked(ActionEvent event){
        addNumber("5");
    }

    @FXML
    private void button6Clicked(ActionEvent event){
        addNumber("6");
    }

    @FXML
    private void button7Clicked(ActionEvent event){
        addNumber("7");
    }

    @FXML
    private void button8Clicked(ActionEvent event){
        addNumber("8");
    }

    @FXML
    private void button9Clicked(ActionEvent event){
        addNumber("9");
    }

    @FXML
    private void button0Clicked(ActionEvent event){
        addNumber("0");
    }

    private void addNumber(String number){
        if(Objects.equals(currentNumber, "0")){
            currentNumber = number;
        } else {
            currentNumber += number;
        }
        updateTextField();
    }

    private void updateTextField(){
        display.setText(currentNumber);
    }
}
