import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Min5a;
import persistence.JsonPersistence;
import persistence.Persistence;
import viewcontroller.PageLoader;

public class Min5aApplication extends Application {
  private static Min5a model;
  private Boolean isFirstRun = true;

  @Override
  public void start(Stage stage) {
    model = Min5a.createMin5a();
    File save = new File("min5a.json");

    if (!save.toString().isEmpty()) {
      Persistence jsonPersistence = new JsonPersistence(model);
      try {
        jsonPersistence.load();
      } catch (IOException e) {
      }
    }

    PageLoader.setModel(model);
    Scene scene = new Scene(PageLoader.createMainPage(), 1200, 700);
    stage.setTitle("Min5a");
    stage.setScene(scene);
    stage.show();
  }

  /** @param args the command line arguments */
  public static void main(String[] args) {
    launch(args);

    Runtime.getRuntime()
        .addShutdownHook(
            new Thread(
                new Runnable() {
                  @Override
                  public void run() {
                    Persistence jsonPersistence = new JsonPersistence(model);
                    try {
                      jsonPersistence.save();
                    } catch (IOException e) {

                    }
                  }
                }));
  }
}
