package viewcontroller;

import model.User;

public abstract class UserManger {
  // TODO last user or null
  private static User activeUser = new User("");

  static void setActiveUser(User user) {
    activeUser = user;
    activeUser.testing();
    PanelItemManager.setUser(activeUser);
      CourseManager.update();
  }

  public static User getActiveUser() {
    return activeUser;
  }
}
