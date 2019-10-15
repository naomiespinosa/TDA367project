package listener;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import event.timer.StudyTimerCompletedEvent;
import manager.StudySessionManager;
import model.StudySession;

public class TimerListener {

  @Inject private StudySessionManager studySessionManager;

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
