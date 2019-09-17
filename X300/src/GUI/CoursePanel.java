package GUI;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.awt.*;

public class CoursePanel extends AnchorPane {

    @FXML
    private ImageView courseImage;

    @FXML
    private Text courseNameLabel;


    @FXML
    public Image getCourseImage(String course) {
        String iconPath;
        Image coursePic = new javafx.scene.image.Image(getClass().getClassLoader().getResourceAsStream(iconPath));
        iconPath = "RecipeSearch/resources/icon_flag_sweden.png";
        return coursePic;
    }



}