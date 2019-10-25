package model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.google.common.eventbus.EventBus;
import org.junit.jupiter.api.Test;

public class TimerManagerTest {

  @Test
  void isTimerRunning() {
    TimerManager timerManager = new TimerManager(mock(EventBus.class));
    timerManager.start(mock(Course.class), mock(User.class));
    assertTrue(timerManager.isRunning());
  }
}
