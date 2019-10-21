package model;

/** This class represent a normal checklist for the user and will either be done or not */
public class ToDo {
  private boolean isDone;

  private String description;

  // Constructor, a todoItem is not done when created
  public ToDo(String description) {
    this.description = description;
    this.isDone = false;
  }

  // Makes the ToDos show up as strings in the CourseMainPage ListView
  @Override
  public String toString() {
    return this.getDescription();
  }

  // Getters and Setters
  String getDescription() {
    return description;
  }
}
