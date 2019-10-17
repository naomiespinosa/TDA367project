package model.event;

import model.User;

public class UserChangedEvent {
  private User newUser;

  public UserChangedEvent(final User newUser) {
    this.newUser = newUser;
  }

  public User getNewUser() {
    return newUser;
  }
}
