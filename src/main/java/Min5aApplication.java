import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Min5a;
import viewcontroller.PageLoader;

public class Min5aApplication extends Application {
  private Min5a model;

  @Override
  public void start(Stage stage) {
    model = Min5a.createMin5a();
    /*   JsonPersistence jsonPersistence = new JsonPersistence(model);

        try {
          jsonPersistence.load();
        }catch( IOException e){

        }
    */

    PageLoader.setModel(model);
    Scene scene = new Scene(PageLoader.createMainPage(), 1200, 700);
    stage.setTitle("Min5a");
    stage.setScene(scene);
    stage.show();
  }
}
