package model;

/** This class represent a normal checklist for the user and will either be done or not */
public class ToDo {
  private boolean isDone; // for future purposes
  private String description;

  /**
   * Constructor for to-dos
   *
   * @param description what the to-do is about
   */
  public ToDo(String description) {
    this.description = description;
    this.isDone = false;
  }

  @Override
  public String toString() {
    return this.getDescription();
  }

  /** @return the description of a tod'o */
  public String getDescription() {
    return description;
  }
}
