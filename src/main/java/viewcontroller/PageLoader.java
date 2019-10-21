package viewcontroller;

import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import model.Course;
import model.Min5a;

public class PageLoader {
  private static Min5a model;

  public static void setModel(Min5a model) {
    PageLoader.model = model;
  }

  private static class Result<T> {
    T ctrl;
    AnchorPane root;

    Result(T page, AnchorPane root) {
      this.ctrl = page;
      this.root = root;
    }
  }

  private static <T> Result<T> loadPage(String fxml) {
    Result<T> result = null;
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(PageLoader.class.getClassLoader().getResource(fxml));
      AnchorPane root = fxmlLoader.load();
      T ctrl = fxmlLoader.getController();
      model.register(ctrl); // TODO Let all pages listen to bus, perhaps do per pages
      result = new Result<T>(ctrl, root);
    } catch (IOException exception) {
      exception.printStackTrace(); // TODO: display user message!
    }
    return result;
  }

  public static Parent createMainPage() {
    Result<MainPage> res = loadPage("fxml/MainPage.fxml");
    res.ctrl.initPage(model, Optional.empty());
    return res.root;
  }

  public static AnchorPane createHomePage(MainPage parent) {
    Result<HomePage> res = loadPage("fxml/HomePage.fxml");
    res.ctrl.initPage(model, Optional.of(parent));
    return res.root;
  }

  static AnchorPane createCourseSelectionPage(MainPage parent) {
    Result<CourseSelectionPage> res = loadPage("fxml/CourseSelectionPage.fxml");
    res.ctrl.initPage(model, Optional.of(parent));
    return res.root;
  }

  static AnchorPane createStatisticsPage() {
    Result<StatisticsPage> res = loadPage("fxml/StatisticsPage.fxml");
    res.ctrl.initPage(model, Optional.empty());
    return res.root;
  }

  static AnchorPane createContactsPage() {
    Result<ContactsPage> res = loadPage("fxml/ContactsPage.fxml");
    res.ctrl.initPage(model, Optional.empty());
    return res.root;
  }

  static AnchorPane createCoursePanelItem(Course course, MainPage parent) {
    Result<CoursePanelItem> res = loadPage("fxml/CoursePanelItem.fxml");
    res.ctrl.setCourse(course);
    res.ctrl.initPage(model, Optional.of(parent));
    return res.root;
  }

  static AnchorPane createCourseMainPage(Course course, MainPage mainPage) {
    Result<CourseMainPage> res = loadPage("fxml/CourseMainPage.fxml");
    res.ctrl.initPage(model, Optional.of(mainPage));
    res.ctrl.setCourse(course);
    return res.root;
  }

  static AnchorPane createTimerPage() {
    Result<TimerPage> res = loadPage("fxml/TimerPage.fxml");
    res.ctrl.initPage(model, Optional.empty());
    return res.root;
  }
}
