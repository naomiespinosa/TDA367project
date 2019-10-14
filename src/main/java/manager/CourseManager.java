package manager;

import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import model.Course;
import model.User;
import org.codejargon.fluentjdbc.api.FluentJdbc;
import org.codejargon.fluentjdbc.api.mapper.ObjectMappers;
import viewcontroller.Observer;

public class CourseManager {
  @Inject private FluentJdbc fluentJdbc;
  @Inject private ObjectMappers objectMappers;

  private List<Observer> observers = new ArrayList<Observer>();

  public void attach(Observer observer) {
    observers.add(observer);
  }

  private void notifyAllObservers() {
    List<Observer> observersClone = new ArrayList<>(observers);

    for (Observer observer : observersClone) {
      observer.update();
    }
  }

  public void deleteCourse(final Course course) {
    this.fluentJdbc
        .query()
        .update("DELETE FROM courses WHERE id = :id")
        .namedParam("id", course.getId())
        .run();

    notifyAllObservers();
  }

  public void createNewCourse(String name, String code, int year, int period, final User user) {
    this.fluentJdbc
        .query()
        .update(
            "INSERT INTO courses (name, code, year, studyPeriod, ownedBy) VALUES (:name, :code, :year, :studyPeriod, :ownedBy)")
        .namedParam("name", name)
        .namedParam("code", code)
        .namedParam("year", year)
        .namedParam("studyPeriod", period)
        .namedParam("ownedBy", user.getId())
        .run();

    notifyAllObservers();
  }

  public void save(Course course) {
    this.fluentJdbc
        .query()
        .update(
            "UPDATE courses SET name = :name, code = :code, year = :year, studyPeriod = :studyPeriod, ownedBy = :ownedBy, grade = :grade, isActive = :isActive WHERE id = :id")
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

  public void update() {
    notifyAllObservers();
  }
}
