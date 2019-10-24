package model.listener;

import com.google.common.eventbus.Subscribe;
import model.StudySession;
import model.event.timer.StudyTimerCompletedEvent;

/**
 * Creates study-session when timer is completed
 */
public class TimerListener {
  @Subscribe
  private void onStudyTimerCompleted(final StudyTimerCompletedEvent studyTimerCompletedEvent) {
    StudySession studySession = new StudySession();
    studySession.setStop(studyTimerCompletedEvent.getStoppedAt());
    studySession.setStart(studyTimerCompletedEvent.getStartedAt());
  }
}
