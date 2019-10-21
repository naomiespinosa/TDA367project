package persistence;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import model.Min5a;
import model.User;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Collection;
import java.util.List;

public class JsonPersistence implements Persistence {
  private Min5a model;         // reference to the model
  private Gson gson;           // gson instance to convert from and to JSON
  private File jsonFile;       // path to JSON user file

  public JsonPersistence(Min5a model) {
    this.model = model;
    gson       = new Gson();
    URL file = getClass().getResource("/min5a.json");
    jsonFile   = new File(file.getFile());
  }

  @Override
  public void save() throws IOException {
    FileWriter writer = new FileWriter(jsonFile);

    List<User> users = model.getUsers();
    String json = gson.toJson(users);
    writer.write(json);
    writer.close();
  }

  @Override
  public void load() throws IOException {
    FileReader reader = new FileReader(jsonFile);
    Type listType = new TypeToken<List<User>>(){}.getType();

    List<User> users = gson.fromJson(reader, listType);
    model.setUsers(users);
  }
}
