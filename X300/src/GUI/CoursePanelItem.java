package GUI;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class CoursePanelItem extends AnchorPane {
   // private Controller parentController;
   // private Course course;

    @FXML
    private ImageView courseImageView;

    @FXML
    private Text courseNameLabel;


//CONSTRUCTOR FOR COURSE PANEL ITEM
/* public CoursePanelItem(Course course, Controller controller) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CoursePanelItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.course = course;
        this.parentController = recipeSearchController;

        CourseNameLabel.setText(course.getName());
        CourseImageView.setImage(course.getFXImage());


    }*/

}

