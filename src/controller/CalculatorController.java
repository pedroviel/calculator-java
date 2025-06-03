package controller;

import enums.Operation;
import interfaces.NumberButtonClicked;
import interfaces.OperationAction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;
import java.util.Objects;

import static java.lang.Math.sqrt;

public class CalculatorController implements NumberButtonClicked, OperationAction {
    @FXML
    private TextField display;

    @FXML
    private TextField savedNumbers;

    private String firstNumber = "0";
    private String currentNumber = "0";
    private Operation currentOperation;
    private double result;
    private boolean isResult = false;
    private boolean percentage = false;
    private boolean isOperation = false;

    @FXML
    private void clearEntryAction(ActionEvent event) {
        currentNumber = "0";
        display.setText("0");
    }

    @FXML
    private void clearAction(ActionEvent event) {
        currentNumber = "0";
        firstNumber = "0";
        currentOperation = null;
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
    private void handleOperationButton(ActionEvent event) {
        Button operationButton = (Button) event.getSource();
        calculationSetup(operationButton.getText());
    }

    @FXML
    private void percentageAction(ActionEvent event) {
        percentage = true;
        if(isResult) {
            result = Double.parseDouble(currentNumber) / 100;
            currentNumber = String.valueOf(result);
            firstNumber = "0";
            currentOperation = null;
            savedNumbers.setText("");
            updateDisplayAndResult(result);
            percentage = false;
            isResult = true;
        } else if(currentOperation == null || firstNumber.isEmpty()) {
            result = 0;
            currentNumber = "0";
            updateDisplayAndResult(result);
            percentage = false;
            isResult = true;
        } else {
            calculate();
        }
    }

    @FXML
    private void decimalAction(ActionEvent event) {
        if(isResult) {
            currentNumber = "0.";
            firstNumber = "0";
            currentOperation = null;
            savedNumbers.setText("");
            percentage = false;
            isResult = false;
            updateTextField();
        }
        if(!currentNumber.contains(".")) {
            currentNumber += ".";
            updateTextField();
        }
    }

    public void calculationSetup(String operationSymbol) {
        if(isOperation) {
            this.currentOperation = Operation.fromSymbol(operationSymbol);
            updateSavedNumbersDisplay(operationSymbol);
            return;
        }

        if(!Objects.equals(firstNumber, "0") && this.currentOperation != null && !isResult) {
            calculate();
        }

        this.currentOperation = Operation.fromSymbol(operationSymbol);
        firstNumber = currentNumber;
        isResult = false;

        updateSavedNumbersDisplay(operationSymbol);
        isOperation = true;
        display.setText(firstNumber);
    }

    private void updateSavedNumbersDisplay(String operationSymbol) {
        Operation op = Operation.fromSymbol(operationSymbol);

        if(op == null) return;

        switch (op) {
            case SQUARE:
                savedNumbers.setText(firstNumber + "²");
                break;
            case SQRT:
                savedNumbers.setText("√" + firstNumber);
                break;
            case INV:
                savedNumbers.setText("1/" + firstNumber);
                break;
            default:
                savedNumbers.setText(firstNumber + " " + currentOperation.getSymbol());
                break;
        }
    }

    @FXML
    private void calculate() {
        if(firstNumber.isEmpty() || currentOperation == null) {
            return;
        }

        double num1 = Double.parseDouble(firstNumber);
        double num2 = 0;
        switch (currentOperation) {
            case ADD:
            case SUB:
            case MUL:
            case DIV:
            case MOD:
                if(currentNumber.isEmpty()) {
                    return;
                }
                num2 = Double.parseDouble(currentNumber);
                if(percentage){
                    num2 = num1 * num2 / 100;
                    switch (currentOperation){
                        case ADD:
                        case SUB:
                            break;
                        case MUL:
                            result = num2;
                            savedNumbers.setText(firstNumber + " " + currentOperation.getSymbol() + " " + currentNumber + " =");
                            updateDisplayAndResult(result);
                            isResult = true;
                            return;
                        case DIV:
                            result = num1 / num2 / 100;
                            savedNumbers.setText(firstNumber + " " + currentOperation.getSymbol() + " " + currentNumber + " =");
                            updateDisplayAndResult(result);
                            isResult = true;
                            return;
                    }
                    num2 = num1 * num2 / 100;
                    updateDisplayAndResult(num2);
                    percentage = false;
                }

                if((currentOperation == Operation.DIV || currentOperation == Operation.MOD) && num2 == 0) {
                    zeroDivImpossible();
                    return;
                }

            switch (currentOperation){
                case ADD:
                    result = num1 + num2;
                    break;
                case SUB:
                    result = num1 - num2;
                    break;
                case MUL:
                    result = num1 * num2;
                    break;
                case DIV:
                    result = num1 / num2;
                    break;
                case MOD:
                    result = num1 % num2;
                    break;
                }
                savedNumbers.setText(firstNumber + " " + currentOperation.getSymbol() + " " + currentNumber + " =");
                break;

            case SQUARE:
                result = num1 * num1;
                savedNumbers.setText(firstNumber + "²" + " =");
                break;
            case SQRT:
                result = sqrt(num1);
                savedNumbers.setText("√" + firstNumber + " =");
                break;
            case INV:
                if(num1 == 0) {
                    zeroDivImpossible();
                    return;
                }
                result = 1/num1;
                savedNumbers.setText("1/" + firstNumber + " =");
                break;
        }

        updateDisplayAndResult(result);
        isResult = true;
    }

    private void zeroDivImpossible() {
        display.setText("Undefined");
        savedNumbers.setText("");
        currentNumber = "0";
        firstNumber = "0";
        currentOperation = null;
        isResult = false;
        percentage = false;
        isOperation = false;
    }

    private void updateDisplayAndResult(double result) {
        DecimalFormat df = new DecimalFormat("#.#######");
        String formattedResult = df.format(result).replace(",", ".");
        display.setText(formattedResult);

        if(result % 1 == 0) {
            currentNumber = String.valueOf((int) result);
            display.setText(String.valueOf((int) result));
        } else {
            currentNumber = formattedResult;
            display.setText(formattedResult);
        }
    }

    @FXML
    public void handleNumberButton(ActionEvent event) {
        Button button = (Button) event.getSource();
        String number = button.getText();
        addNumber(number);
    }

    @Override
    public void addNumber(String number) {
        if(isResult) {
            currentNumber = "0";
            currentOperation = null;
            firstNumber = "0";
            savedNumbers.setText("");
            isResult = false;
            percentage = false;
        }

        if(isOperation) {
            currentNumber = "0";
            isOperation = false;
        }

        if(Objects.equals(currentNumber, "0")) {
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