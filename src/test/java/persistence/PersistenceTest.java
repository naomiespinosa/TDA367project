package persistence;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import model.Min5a;
import model.TestData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PersistenceTest {
  private static Min5a model;

  @BeforeAll
  public static void setup() {
    model = Min5a.createMin5a();
  }

  @Test
  public void testRoundTrip() throws IOException {
    TestData.insertUsers(1, model);
    Persistence save = new JsonPersistence(model);
    save.save();

    Min5a newModel = Min5a.createMin5a(); // clean model
    Persistence load = new JsonPersistence(newModel);
    load.load();

    assertEquals(
        model.getUsers().size(), newModel.getUsers().size()); // TODO check contents as well!
  }
}
