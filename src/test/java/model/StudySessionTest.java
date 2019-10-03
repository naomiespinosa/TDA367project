package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

import java.util.Date;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

public class StudySessionTest {
  @ParameterizedTest
  @ValueSource(ints = {25, 500})
  public void testDurationCalculationFromDates(final int minutes) {
    final Course course = mock(Course.class);

    StudySession studySession =
        new StudySession(
            new Date(2000, 1, 1, 0, 0, 0), new Date(2000, 1, 1, 0, minutes, 0), course);

    assertEquals(minutes * 60, studySession.getDuration().getSeconds());
  }

  @Test
  public void testStartDateIsSet() {
    final Course course = mock(Course.class);
    final Date startDate = new Date(2000, 1, 1, 0, 0, 0);
    StudySession studySession = new StudySession(startDate, new Date(2000, 1, 1, 0, 1, 0), course);

    assertSame(startDate, studySession.getStart());
  }

  @Test
  public void testStopDateIsSet2() {
    final Course course = mock(Course.class);
    final Date stopDate = new Date(2000, 1, 1, 0, 0, 0);
    StudySession studySession = new StudySession(new Date(2000, 1, 1, 0, 1, 0), stopDate, course);

    assertSame(stopDate, studySession.getStop());
  }

  @Test
  public void testCourseIsSet() {
    final Course course = mock(Course.class);
    final Date stopDate = new Date(2000, 1, 1, 0, 0, 0);
    StudySession studySession = new StudySession(new Date(2000, 1, 1, 0, 1, 0), stopDate, course);

    Course testCourse = studySession.getCourse();

    assertEquals(testCourse,course);
  }
}



