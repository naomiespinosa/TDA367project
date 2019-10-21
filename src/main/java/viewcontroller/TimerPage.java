package viewcontroller;

import com.google.common.eventbus.Subscribe;
import java.util.Optional;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Course;
import model.Min5a;
import model.event.CourseSelectedEvent;
import model.event.timer.StudyTimerCanceledEvent;
import model.event.timer.StudyTimerCompletedEvent;
import model.event.timer.StudyTimerStartedEvent;
import model.event.timer.TimerTickEvent;

public class TimerPage implements Page {
  private Min5a model;

  @FXML private AnchorPane fullPageTimerAnchorPane;
  @FXML private Button startPauseFullPageTimer;
  @FXML private Label startPauseLabel;
  @FXML private Label timeLabel;

  private Course course;

  TimerPage(EventBus eventBus) {
    this.eventBus = eventBus;
  }


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

  @Subscribe
  private void onCourseSelection(final CourseSelectedEvent courseSelectedEvent) {
    this.course = courseSelectedEvent.getCourse();
  }

  @Override
  public void initPage(Min5a model, Optional<MainPage> mainPage) {
    this.model = model;
    startPauseLabel.setText("Starta timer");
  }
}
