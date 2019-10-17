package model.listener;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import model.StudySession;
import model.event.timer.StudyTimerCompletedEvent;
import model.manager.StudySessionManagerInterface;

public class TimerListener {

  @Inject private StudySessionManagerInterface studySessionManager;

  @Inject
  public TimerListener(final EventBus eventBus) {
    eventBus.register(this);
  }

  @Subscribe
  private void onStudyTimerCompleted(final StudyTimerCompletedEvent studyTimerCompletedEvent) {
    StudySession studySession = new StudySession();
    studySession.setStop(studyTimerCompletedEvent.getStoppedAt());
    studySession.setStart(studyTimerCompletedEvent.getStartedAt());
    studySession.setCourseId(studyTimerCompletedEvent.getCourse().getId());
    studySession.setOwnedBy(studyTimerCompletedEvent.getOwner().getId());

    this.studySessionManager.create(studySession);
  }
}