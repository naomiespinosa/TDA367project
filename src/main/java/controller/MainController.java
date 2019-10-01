package controller;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws IOException {
    Parent root =
        FXMLLoader.load(getClass().getClassLoader().getResource("fxml/CourseMainPage.fxml"));
    Scene scene = new Scene(root, 300, 275);
    stage.setTitle("Min5a");
    stage.setScene(scene);
    stage.show();
  }
}
