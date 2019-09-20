package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class CoursePanelItem extends AnchorPane {
    //private Controller parentController;
  //  private Course course;

    @FXML
    private ImageView courseImageView;

    @FXML
    private Text courseNameLabel;



////CONSTRUCTOR FOR COURSE PANEL ITEM
// public CoursePanelItem(Course course, Controller controller) {
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CoursePanelItem.fxml"));
//        fxmlLoader.setRoot(this);
//        fxmlLoader.setController(this);
//
//        try {
//            fxmlLoader.load();
//        } catch (IOException exception) {
//            throw new RuntimeException(exception);
//        }
//
//        this.course = course;
//        this.parentController = controller;
//
//        courseNameLabel.setText(course.getCourseName());
//        //courseImageView.setImage(course.getCoursePicPath());

//    }

}

