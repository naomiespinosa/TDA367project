package viewcontroller;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Course;
import model.event.CourseSelectedEvent;
import model.event.timer.StudyTimerCanceledEvent;
import model.event.timer.StudyTimerCompletedEvent;
import model.event.timer.StudyTimerStartedEvent;
import model.event.timer.TimerTickEvent;

public class TimerPage implements Initializable {
  @Inject private EventBus eventBus;

  @FXML private AnchorPane fullPageTimerAnchorPane;
  @FXML private Button startPauseFullPageTimer;
  @FXML private Label startPauseLabel;
  @FXML private Label timeLabel;

  private Course course;

  public void onTimerButtonClick() {
//    if (timerManager.isRunning()) {
//      timerManager.cancel();
//    } else {
//      timerManager.start(this.course);
//    }
  }

  @Subscribe
  public void onStudyTimerStarted(final StudyTimerStartedEvent event) {
    Platform.runLater(() -> startPauseLabel.setText("Stoppa timer"));
  }

  @Subscribe
  public void onStudyTimerCanceled(final StudyTimerCanceledEvent event) {
    Platform.runLater(() -> startPauseLabel.setText("Starta timer"));
  }

  @Subscribe
  public void onStudyTimerCompleted(final StudyTimerCompletedEvent event) {
    Platform.runLater(() -> startPauseLabel.setText("Starta timer"));
  }

  @Subscribe
  public void onStudyTimerTick(final TimerTickEvent event) {
    Platform.runLater(
        () ->
            timeLabel.setText(
                String.format(
                    "%02d:%02d",
                    (event.getElapsedSeconds() % 3600) / 60, (event.getElapsedSeconds() % 60))));
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    eventBus.register(this);
    startPauseLabel.setText("Starta timer");
  }

  @Subscribe
  private void onCourseSelection(final CourseSelectedEvent courseSelectedEvent) {
    this.course = courseSelectedEvent.getCourse();
  }
}
