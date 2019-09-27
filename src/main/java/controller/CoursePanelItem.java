package controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class CoursePanelItem extends AnchorPane {
  private SidePanel parentController;

  @FXML private ImageView courseImageView;

  @FXML private Text courseNameLabel;

  // CONSTRUCTOR FOR COURSE PANEL ITEM
  public CoursePanelItem(SidePanel controller) {
    FXMLLoader fxmlLoader = new FXMLLoader(CoursePanelItem.class.getClassLoader().getResource("fxml/CoursePanelItem.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);

    try {
      fxmlLoader.load();
    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }

    this.parentController = controller;
  }
}
