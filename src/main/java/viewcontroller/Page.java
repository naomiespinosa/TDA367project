package viewcontroller;

import java.util.Optional;
import model.Min5a;

public interface Page {
  void initPage(Min5a model, Optional<MainPage> mainPage);
}
