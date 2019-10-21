package viewcontroller;

import com.cathive.fx.guice.GuiceFXMLLoader;
import com.google.inject.Inject;
import java.io.IOException;
import java.net.URL;

import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import model.Course;
import model.Min5a;

public class PageLoader {
  @Inject private GuiceFXMLLoader fxmlLoader;
  @Inject private Min5a model;

  private static class Result<T> {
    T page;
    AnchorPane root;

    Result(T page, AnchorPane root) {
      this.page = page;
      this.root = root;
    }
  }

  private <T> Result<T> loadPage(String fxmlFile) {
    Result<T> result = null;
    try {
      URL fxmlURL = getClass().getResource(fxmlFile);
      GuiceFXMLLoader.Result res = fxmlLoader.load(fxmlURL);
      result = new Result<T>(res.getController(), res.getRoot());
    } catch (IOException exception) {
      exception.printStackTrace();  // TODO: display user message!
    }
    return result;
  }

  public Parent createMainPage() {
    Result<MainPage> res = loadPage("fxml/MainPage.fxml");
    res.page.init();
    return res.root;
  }

  AnchorPane createHomePage(MainPage parent) {
    Result<HomePage> res = loadPage("fxml/HomePage");
    res.page.setParent(parent);
    res.page.init();
    return res.root;
  }

  AnchorPane createCourseSelectionPage(MainPage parent) {
    Result<CourseSelectionPage> res = loadPage("fxml/CourseSelectionPage.fxml");
    res.page.setParent(parent);
    res.page.init();
    return res.root;
  }

  AnchorPane createStatisticsPage() {
    Result<StatisticsPage> res = loadPage("fxml/StatisticsPage.fxml");
    return res.root;
  }

  AnchorPane createCoursePanelItem(Course course, MainPage parent) {
    Result<CoursePanelItem> res = loadPage("fxml/CoursePanelItem.fxml");
    res.page.init(course, parent);
    return res.root;
  }

  AnchorPane createCourseMainPage(Course course, MainPage mainPage) {
    Result<CourseMainPage> res = loadPage("fxml/CourseMainPage.fxml");
    res.page.init(course);
    res.page.setParent(mainPage);
    return res.root;
  }

  AnchorPane createTimerPage() {
    Result<TimerPage> res = loadPage("fxml/TimerPage.fxml");
    return res.root;
  }
}
