import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CalculatorController{
    @FXML
    private TextField display;

    @FXML
    private TextField savedNumbers;

    private String firstNumber = "";
    private String currentNumber = "";
    private String calculationType;

    @FXML
    void clearEntryAction(ActionEvent event) {
        display.setText("");
    }

    void clearAction(ActionEvent event) {
        display.setText("");
        savedNumbers.setText("");
    }

    void deleteAction(ActionEvent event) {

    }

    @FXML
    void addAction(ActionEvent event) {
        calculationSetup("+");
    }

    @FXML
    void divAction(ActionEvent event) {
        calculationSetup("/");
    }

    @FXML
    void mulAction(ActionEvent event) {
        calculationSetup("*");
    }

    @FXML
    void subAction(ActionEvent event) {
        calculationSetup("-");
    }

    @FXML
    void equalsAction(ActionEvent event) {
        calculationSetup("=");
    }

    void calculationSetup(String calculationType) {
        this.calculationType = calculationType;
        firstNumber = currentNumber;
        currentNumber = "";
        savedNumbers.setText(firstNumber + " " + calculationType);
    }

    void calculate() {
        double num1 = Double.parseDouble(firstNumber);
        double num2 = Double.parseDouble(currentNumber);
        double result = 0;
        switch (calculationType) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            case "=":
                display.setText(String.valueOf(result));
            default:
                break;
        }
    }

    @FXML
    void button1Clicked(ActionEvent event){
        addNumber("1");
    }

    @FXML
    void button2Clicked(ActionEvent event){
        addNumber("2");
    }

    @FXML
    void button3Clicked(ActionEvent event){
        addNumber("3");
    }

    @FXML
    void button4Clicked(ActionEvent event){
        addNumber("4");
    }

    @FXML
    void button5Clicked(ActionEvent event){
        addNumber("5");
    }

    @FXML
    void button6Clicked(ActionEvent event){
        addNumber("6");
    }

    @FXML
    void button7Clicked(ActionEvent event){
        addNumber("7");
    }

    @FXML
    void button8Clicked(ActionEvent event){
        addNumber("8");
    }

    @FXML
    void button9Clicked(ActionEvent event){
        addNumber("9");
    }

    @FXML
    void button0Clicked(ActionEvent event){
        addNumber("0");
    }

    void addNumber(String number){
        currentNumber += number;
        updateTextField();
    }

    void updateTextField(){
        display.setText(currentNumber);
    }
}
