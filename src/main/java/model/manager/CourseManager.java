package model.manager;

import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import model.Course;
import model.Observer;
import model.User;
import org.codejargon.fluentjdbc.api.FluentJdbc;
import org.codejargon.fluentjdbc.api.mapper.ObjectMappers;

/**
 * This is a class that manages changes, additions and deleteions regarding classes
 *
 * @author Hanna
 * @author Robin
 */
public class CourseManager implements CourseManagerInterface {
  @Inject private FluentJdbc fluentJdbc;
  @Inject private ObjectMappers objectMappers;

  private List<Observer> observers = new ArrayList<Observer>();

  /**
   * Attaches all classes that want to observe changes in courses
   *
   * @param observer Classes that want to observe changes in courses
   */
  public void attach(Observer observer) {
    observers.add(observer);
  }

  /** Tells all the observers to update */
  private void notifyAllObservers() {
    List<Observer> observersClone = new ArrayList<>(observers);

    for (Observer observer : observersClone) {
      observer.update();
    }
  }

  /**
   * Deletes a course from the user, notifies observers
   *
   * @param course the course that is to be deleted
   */
  public void deleteCourse(final Course course) {
    this.fluentJdbc
        .query()
        .update("DELETE FROM courses WHERE id = :id")
        .namedParam("id", course.getId())
        .run();

    notifyAllObservers();
  }

  /**
   * Creates a new course that now belongs to the active user, notifies observers
   *
   * @param name name
   * @param code code
   * @param year year
   * @param period period
   * @param user user
   */
  public void createNewCourse(String name, String code, int year, int period, final User user) {
    this.fluentJdbc
        .query()
        .update(
            "INSERT INTO courses (name, courseCode, year, studyPeriod, ownedBy) VALUES (:name, :code, :year, :studyPeriod, :ownedBy)")
        .namedParam("name", name)
        .namedParam("code", code)
        .namedParam("year", year)
        .namedParam("studyPeriod", period)
        .namedParam("ownedBy", user.getId())
        .run();

    notifyAllObservers();
  }

  /**
   * Saves the course information
   *
   * @param course course
   */
  public void save(Course course) {
    this.fluentJdbc
        .query()
        .update(
            "UPDATE courses SET name = :name, courseCode = :code, year = :year, studyPeriod = :studyPeriod, ownedBy = :ownedBy, grade = :grade, isActive = :isActive WHERE id = :id")
        .namedParam("id", course.getId())
        .namedParam("name", course.getName())
        .namedParam("code", course.getCourseCode())
        .namedParam("year", course.getYear())
        .namedParam("studyPeriod", course.getStudyPeriod())
        .namedParam("ownedBy", course.getOwnedBy())
        .namedParam("grade", course.getGrade())
        .namedParam("isActive", course.isActive())
        .run();

    notifyAllObservers();
  }
}
