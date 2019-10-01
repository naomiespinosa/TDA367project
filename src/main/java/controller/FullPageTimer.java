package controller;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Course;
import model.timer.TimerManager;
import model.timer.event.StudyTimerStartedEvent;
import model.timer.event.StudyTimerStoppedEvent;

public class FullPageTimer implements Initializable {
  @Inject private TimerManager timerManager;

  @Inject private EventBus eventBus;

  @FXML private AnchorPane transperentAnchorPane;

  @FXML private AnchorPane fullPageTimerAnchorPane;

  @FXML private ImageView closeFullPageTimer;

  @FXML private Button startPauseFullPageTimer;

  @FXML private Label startPauseLabel;

  @FXML private Label timeLabel;

  public void onTimerButtonClick() {
    if (this.timerManager.isRunning()) {
      this.timerManager.stop();
    } else {
      this.timerManager.start(new Course("asd", "sd", 1, 1));
    }
  }

  @Subscribe
  public void onStudyTimerStarted(final StudyTimerStartedEvent event) {}

  @Subscribe
  public void onStudyTimerStopped(final StudyTimerStoppedEvent event) {}

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.eventBus.register(this);
  }
}
