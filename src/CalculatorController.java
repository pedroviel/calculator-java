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
    private boolean isResult = false;
    private boolean percentage = false;


    @FXML
    private void clearEntryAction(ActionEvent event) {
        currentNumber = "0";
        display.setText("0");
    }

    @FXML
    private void clearAction(ActionEvent event) {
        currentNumber = "0";
        firstNumber = "";
        calculationType = "";
        isResult = false;
        percentage = false;
        display.setText("0");
        savedNumbers.setText("");
    }

    @FXML
    private void deleteAction(ActionEvent event) {
        if(isResult) {
            clearAction(event);
            return;
        }

        if(currentNumber.length() <= 1){
            currentNumber = "0";
        } else {
            currentNumber = currentNumber.substring(0, currentNumber.length() - 1);
        }

        display.setText(currentNumber);
    }

    @FXML
    private void addAction(ActionEvent event) {
        calculationSetup("+");
    }

    @FXML
    private void divAction(ActionEvent event) {
        calculationSetup("÷");
    }

    @FXML
    private void mulAction(ActionEvent event) {
        calculationSetup("×");
    }

    @FXML
    private void subAction(ActionEvent event) {
        calculationSetup("-");
    }

    @FXML
    private void powAction(ActionEvent event) {
        calculationSetup("²");
    }

    @FXML
    private void squareRootAction(ActionEvent event) {
        calculationSetup("√");
    }

    @FXML
    private void inverseNumberAction(ActionEvent event) {
        calculationSetup("¹⁄ₓ");
    }

    @FXML
    private void modAction(ActionEvent event) {
        calculationSetup("mod");
    }

    @FXML
    private void percentageAction(ActionEvent event) {
        percentage = true;
        if(isResult) {
            result = Double.parseDouble(currentNumber) / 100;
            currentNumber = String.valueOf(result);
            firstNumber = "";
            calculationType = "";
            savedNumbers.setText("");
            updateDisplayAndResult(result);
            percentage = false;
            isResult = true;
        } else if(calculationType == null || calculationType.isEmpty() || firstNumber.isEmpty()) {
            result = 0;
            currentNumber = "0";
            updateDisplayAndResult(result);
            percentage = false;
            isResult = true;
        } else {
            calculate(event);
        }
    }

    @FXML
    private void decimalAction(ActionEvent event) {
        if(!currentNumber.contains(".")) {
            currentNumber += ".";
            updateTextField();
        }
    }

    private void calculationSetup(String calculationType) {
        if(!firstNumber.isEmpty() && !this.calculationType.isEmpty() && !isResult) {
            calculate(null);
        }
        this.calculationType = calculationType;
        firstNumber = currentNumber;
        currentNumber = "";
        isResult = false;

        updateSavedNumbersDisplay(calculationType);
    }

    private void updateSavedNumbersDisplay(String calculationType) {
        switch (calculationType) {
            case "²":
                savedNumbers.setText(firstNumber + "²");
                break;
            case "√":
                savedNumbers.setText("√" + firstNumber);
                break;
            case "¹⁄ₓ":
                savedNumbers.setText("1/" + firstNumber);
                break;
            default:
                savedNumbers.setText(firstNumber + " " + calculationType);
                break;
        }
    }

    @FXML
    private void calculate(ActionEvent event) {
        if(firstNumber.isEmpty() || calculationType.isEmpty()) {
            return;
        }

        double num1 = Double.parseDouble(firstNumber);
        double num2 = 0;
        switch (calculationType) {
            case "+":
            case "-":
            case "×":
            case "÷":
            case "mod":
                if(currentNumber.isEmpty()) {
                    return;
                }
                num2 = Double.parseDouble(currentNumber);
                if(percentage){
                    num2 = num2 / 100;
                    currentNumber = String.valueOf(num2);
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
                    if(num2 == 0) {
                        zeroDivImpossible();
                        return;
                    }
                    isResult = true;
                    result = num1 / num2;
                    break;
                case "mod":
                    if(num2 == 0) {
                        zeroDivImpossible();
                        return;
                    }
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
                if(num1 == 0) {
                    zeroDivImpossible();
                    return;
                }
                result = 1/num1;
                savedNumbers.setText("1/" + firstNumber + " =");
                updateDisplayAndResult(result);
                isResult = true;
                break;
            default:
                break;
        }
    }

    private void zeroDivImpossible() {
        display.setText("Undefined");
        savedNumbers.setText("");
        currentNumber = "0";
        calculationType = "";
        isResult = false;
        percentage = false;
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
        if(isResult) {
            currentNumber = "0";
            firstNumber = "";
            calculationType = "";
            savedNumbers.setText("");
            isResult = false;
        }
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
