package model.listener;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import model.StudySession;
import model.event.timer.StudyTimerCompletedEvent;
import model.manager.StudySessionManagerInterface;

/** Listens to timer changes. */
public class TimerListener {

  @Inject private StudySessionManagerInterface studySessionManager;

  @Inject
  public TimerListener(final EventBus eventBus) {
    eventBus.register(this);
  }

  /**
   * Creates new study sessions from completed timers.
   *
   * @param studyTimerCompletedEvent The event from the completed timer.
   */
  @Subscribe
  private void onStudyTimerCompleted(final StudyTimerCompletedEvent studyTimerCompletedEvent) {
    StudySession studySession = new StudySession();
    studySession.setStop(studyTimerCompletedEvent.getStoppedAt());
    studySession.setStart(studyTimerCompletedEvent.getStartedAt());
    studySession.setCourseId(studyTimerCompletedEvent.getCourse().getId());

    this.studySessionManager.create(studySession);
  }
}
