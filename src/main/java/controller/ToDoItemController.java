package controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import model.ToDo;

public class ToDoItemController extends AnchorPane {

    private CourseMainPage parentController;
    private ToDo toDo;

    // FXML declarations
    @FXML private TextArea ToDoDescriptionTextArea;

  // Constructor for Controller
  public ToDoItemController() {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ToDoItem.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);

    try {
      fxmlLoader.load();
    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }

  }
}
