package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.Course;

import java.io.IOException;

public class CoursePanelItem {

  @FXML private ImageView courseImageView;

  @FXML private Text courseNameLabel;

  private Course courseTest;
  private CourseSelectionPage parentController;

  public CoursePanelItem(Course course, CourseSelectionPage ctr){
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CoursePanelItem.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);

    try {
      fxmlLoader.load();
    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }

    this.courseTest = course;
    this.parentController = ctr;

    this.courseNameLabel.setText(course.getName());
    //this.courseImageView.setImage(course.getImage());


  }
}
