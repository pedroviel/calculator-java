import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import operations.Division;
import operations.Multiplication;
import operations.Sub;
import operations.Sum;

public class CalculatorApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Sum sum = new Sum();
        Sub sub = new Sub();
        Division div = new Division();
        Multiplication mul = new Multiplication();

        Pane root = FXMLLoader.load(getClass().getResource("Calculator.fxml"));
        Scene scene = new Scene(root, 300, 200);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}