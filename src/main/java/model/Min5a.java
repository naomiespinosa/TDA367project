package model;

import com.google.common.eventbus.EventBus;
import java.util.*;
import java.util.function.Predicate;

import com.google.inject.Inject;
import model.event.CourseChangeEvent;
import model.event.UserChangedEvent;

/** The overall (aggregate) model of our monopoly game. */
public class Min5a {
  private Map<Integer, User> userMap;
  private Optional<User> activeUser;
  static EventBus bus;

  @Inject private Min5a() {
    userMap = new HashMap<>();
    activeUser = Optional.empty();
    bus = new EventBus();
  }

  /** Factory method for creating an instance of the Min5a model. */
  public static Min5a createMin5a() {
    return new Min5a();
  }

  /**
   * Register a handler to the event bus.
   *
   * @param handler the handler
   */
  public void register(Object handler) {
    bus.register(handler);
  }

  /**
   * The @login@ method checks if an user is registered and if a valid password is provided, the
   * user is made active. That is, all course related methods are concerning the active user.
   *
   * @param personNumber the user's person number
   * @param password the plain text password
   * @return was the login successful
   */
  public boolean login(Integer personNumber, String password) {
    if (!activeUser.isPresent() && userMap.containsKey(personNumber)) {
      User user = userMap.get(personNumber);
      if (user.hasPassword(password)) {
        activeUser = Optional.of(userMap.get(personNumber));
        bus.post(new UserChangedEvent());
        return true;
      }
    }
    return false; // TODO: no such user event
  }

  /**
   * Add an user to the model.
   *
   * @param personNumber social security number
   * @param name the user's name
   * @param password the plain text password, a hash will be stored
   */
  public void addUser(Integer personNumber, String name, String password) {
    if (userMap.containsKey(personNumber)) {
      // TODO notify that user already exists
    } else {
      User user = User.createUser(personNumber, name, password);
      userMap.put(personNumber, user);
    }
  }

  /**
   * Add a course to the active user's list of courses
   *
   * @param name course name
   * @param courseCode course code
   * @param year in which year did the course run
   * @param studyPeriod in which study period
   */
  public void addCourse(String name, String courseCode, int year, int studyPeriod) {
    Course course = new Course(name, courseCode, year, studyPeriod);
    activeUser.ifPresent(u -> u.addCourse(course));
    bus.post(new CourseChangeEvent());
  }

  /**
   * Retrieve all of the active user's courses
   *
   * @return iterable courses
   */
  public Iterable<Course> getCourses() {
    return activeUser.get().getCourses();
  }



  /**
   * Filter the list of the active user's courses
   *
   * @param p a predicate over a course
   * @return filtered list (iterable) of courses
   */
  public Iterable<Course> filterCourses(Predicate<Course> p) {
    return activeUser.get().filterCourses(p);
  }

  /**
   * Courses marked active
   *
   * @return active courses
   */
  public Iterable<Course> activeCourses() {
    return filterCourses(Course::isActive);
  }

  /**
   * Courses marked not active
   *
   * @return inactive courses
   */
  public Iterable<Course> inActiveCourses() {
    return filterCourses(c -> !c.isActive());
  }

  /**
   * Retrieve all users
   *
   * @return a list with users
   */
  public List<User> getUsers() {
    return new ArrayList<>(userMap.values()); // use defensive copying
  }

  /**
   * Initialise the model with a list of users.
   *
   * @param users list of users
   */
  public void setUsers(List<User> users) {
    for (User user : users) userMap.put(user.getPersonNumber(), user);
  }
}
