package viewcontroller;

import model.Min5a;

import java.util.Optional;

public interface Page {
    void initPage(Min5a model, Optional<MainPage> mainPage);
}
