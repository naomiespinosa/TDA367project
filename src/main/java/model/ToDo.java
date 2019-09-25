package model;

// This class represent a normal checklist for the user and will either be done or not
public class ToDo {
  private boolean isDone;
  private String description;
  private String date;

  // Constructor, a todoItem is not done when created
  ToDo(String date, String description) {
    this.description = description;
    this.date = date;
    this.isDone = false;
  }

  // Getters and Setters
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

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }
}
