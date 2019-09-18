package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class Main extends Application {


    @Override
    public void start(Stage stage) throws Exception {

        ResourceBundle bundle = java.util.ResourceBundle.getBundle("resources/min5a");

        Parent root = FXMLLoader.load(getClass().getResource("CoursePanelItem.fxml"), bundle);

        Scene scene = new Scene(root, 142, 105);

        stage.setTitle(bundle.getString("application.name"));
        stage.setScene(scene);
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}