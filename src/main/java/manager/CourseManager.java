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
    for (Observer observer : observers) {
      observer.update();
    }
  }

  public void deleteCourse(Course course) {
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
        .namedParam("ownedBy", user.getUsername())
        .run();

    notifyAllObservers();
  }

  public void save(Course course) {}

  public void update() {
    notifyAllObservers();
  }
}
