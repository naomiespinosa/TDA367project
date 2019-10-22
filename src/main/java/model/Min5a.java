package model;

import com.google.common.eventbus.EventBus;
import java.util.*;
import java.util.function.Predicate;
import model.event.CourseChangeEvent;
import model.event.UserChangedEvent;

/** The overall model for our student app */
public class Min5a {
  private Map<Integer, User> userMap;
  private Optional<User> activeUser;
  public static EventBus bus; // should be public? or use a getter?
  public static Boolean isFirstRun = true;

  private Min5a() {
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
      //
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
    User user = User.createUser(personNumber, name, password);
    userMap.put(personNumber, user);
  }

  /**
   * Checks if the username is already taken
   *
   * @param id social security number
   * @return if the user is unique
   */
  public boolean isUserUnique(Integer id) {
    return !userMap.containsKey(id);
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
    activeUser.get().addCourse(course);
    // activeUser.ifPresent(u -> u.addCourse(course));
    bus.post(new CourseChangeEvent());
  }

  public void notifyCourseChangedEvent() {
    bus.post(new CourseChangeEvent());
  }

  /**
   * Deletes course
   *
   * @param course desired course
   */
  public void deleteCourse(Course course){
    activeUser.get().deleteCourse(course);
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
  public Iterable<Course> filterCourses(
      Predicate<Course> p) { // todo how to make activeUser present
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
   * Retrieve the userName
   *
   * @return the name of the user
   */
  public String getActiveUserName() {
    return activeUser.get().getName();
  }

  /**
   * Sets the name  of the user
   *
   * @param name the desired name of the user
   */
  public void setActiveUserName(String name) {
    activeUser.get().setName(name);
    bus.post(new UserChangedEvent());
  }

  /**
   * Retrieves the social security code
   *
   * @return the social security code
   */
  public int getActiveUserId() {
    return activeUser.get().getPersonNumber();
  }

  /**
   * Initialise the model with a list of users.
   *
   * @param users list of users
   */
  public void setUsers(List<User> users) {
    for (User user : users) userMap.put(user.getPersonNumber(), user);
  }

  public List<String> getActiveCourseName() {
    List<String> tempList = new ArrayList<>();

    for (Course course : activeCourses()) {
      tempList.add(course.getName());
    }

    return tempList;
  }

  public List<String> getInactiveCoursesName() {
    List<String> tempList = new ArrayList<>();

    for (Course course : inActiveCourses()) {
      tempList.add(course.getName());
    }

    return tempList;
  }
}
