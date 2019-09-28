package controller;

import com.google.inject.Inject;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Course;
import model.timer.TimerManager;

public class FullPageTimer implements Observer, Initializable {
  @Inject private TimerManager timerManager;

  @FXML private AnchorPane transperentAnchorPane;

  @FXML private AnchorPane fullPageTimerAnchorPane;

  @FXML private ImageView closeFullPageTimer;

  @FXML private Button startPauseFullPageTimer;

  @FXML private Label startPauseLabel;

  @FXML private Label timeLabel;

  public void toggleTimer() {
    if (this.timerManager.isRunning()) {
      this.timerManager.stop();
    } else {
      this.timerManager.start(new Course("asd","sd",1,1));
    }
  }

  @Override
  public void update(Observable observable, Object o) {
    this.startPauseLabel.setText(this.timerManager.isRunning() ? "Stoppa" : "Starta");
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.timerManager.addObserver(this);
  }
}
