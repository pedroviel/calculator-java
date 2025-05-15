import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


import java.net.URL;
import java.util.ResourceBundle;

public class CalculatorController implements Initializable {

    @FXML
    private Button button;

    @FXML
    private Label labelOlaMundo;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("Clicou foi...");
        labelOlaMundo.setText("Olá Mundo!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
