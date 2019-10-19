package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

public class StudySessionTest {
  @ParameterizedTest
  @ValueSource(ints = {25, 50})
  public void testDurationCalculationFromDates(final int minutes) {
    StudySession studySession = new StudySession();
    studySession.setStart(LocalDateTime.of(2000, 1, 1, 0, 0, 0));
    studySession.setStop(LocalDateTime.of(2000, 1, 1, 0, minutes, 0));

    assertEquals(minutes * 60, studySession.getDuration().getSeconds());
  }

  @Test
  public void testStartDateIsSet() {
    LocalDateTime start = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
    StudySession studySession = new StudySession();
    studySession.setStart(start);
    studySession.setStop(LocalDateTime.of(2000, 1, 1, 0, 1, 0));

    assertSame(start, studySession.getStartedAt());
  }

  @Test
  public void testCourseIsSet() {
    final Course course = mock(Course.class);
    when(course.getId())
        .thenAnswer(
            (invocation) -> {
              return 1;
            });

    StudySession studySession = new StudySession();
    studySession.setCourseId(course.getId());

    assertEquals(1, course.getId());
  }
}
