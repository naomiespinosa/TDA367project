package model;

// This class represent a normal checklist for the user and will either be done or not
class ToDo {
  private boolean isDone;
  private String description;

  ToDo(String description) { // Constructor, a todoItem is not done when created
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

  private String getDescription() {
    return description;
  }

  private void setDescription(String description) {
    this.description = description;
  }
}
