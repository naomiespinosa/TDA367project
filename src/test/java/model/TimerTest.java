package model;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import model.Timer.State;
import org.junit.jupiter.api.Test;

class TimerTest {
  @Test
  public void startingAlreadyActiveTimerShouldDoNothing() {
    final Course course = mock(Course.class);
    Timer timer = new TimerStub(course);
    timer.start();
    assertSame(State.ACTIVE, timer.getState());

    timer.start();
    assertSame(State.ACTIVE, timer.getState());
  }

  @Test
  public void startedTimerShouldHaveActiveState() {
    final Course course = mock(Course.class);
    Timer timer = new TimerStub(course);
    timer.start();
    assertSame(State.ACTIVE, timer.getState());
  }

  @Test
  public void canceledTimerShouldHaveCanceledState() {
    final Course course = mock(Course.class);
    Timer timer = new TimerStub(course);
    timer.start();
    assertSame(State.ACTIVE, timer.getState());
    timer.cancel();
    assertSame(State.CANCELED, timer.getState());
  }

  @Test
  public void newTimerShouldHaveInactiveState() {
    final Course course = mock(Course.class);
    Timer timer = new TimerStub(course);
    assertSame(State.INACTIVE, timer.getState());
  }

  @Test
  public void getElapsedTimeOfInactiveTimerShouldThrowException() {
    final Course course = mock(Course.class);
    Timer timer = new TimerStub(course);
    assertSame(State.INACTIVE, timer.getState());
    assertThrows(
        IllegalStateException.class,
        () -> {
          timer.getElapsedSeconds();
        });
  }

  @Test
  public void getElapsedTimeOfActiveTimerShouldReturnElapsedTime() {
    final Course course = mock(Course.class);
    Timer timer = new TimerStub(course);
    timer.start();
    assertSame(State.ACTIVE, timer.getState());
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
    }

    assertTrue(timer.getElapsedSeconds() > 0);
  }
}

class TimerStub extends Timer {
  public TimerStub(final Course course) {
    super(course);
  }

  public void start() {
    super.start();
  }

  public void cancel() {
    super.cancel();
  }
}
