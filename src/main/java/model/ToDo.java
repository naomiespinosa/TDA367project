package model;

public class ToDo {
  private boolean isDone;
  private String description;

  ToDo(String description, boolean isDone) { // Constructor, a todoItem is not done when created
    this.description = description;
    this.isDone = false;
  }

  private boolean getIsDone() {
    return isDone;
  }

  private void setActive() {
    this.isDone = false;
  }

  private void setInactive() {
    this.isDone = true;
  }

  private String getDiscription() {
    return description;
  }

  private void setDescription(String description) {
    this.description = description;
  }
}
