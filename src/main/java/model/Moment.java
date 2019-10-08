package model;

import java.time.LocalDate;
import java.util.Date;

public class Moment {
  private String name;
  private LocalDate deadline;
  private boolean isPastDeadline;

  public Moment(String name, LocalDate deadline) {
    this.name = name;
    this.deadline = deadline;
    this.isPastDeadline = false;
  }

  @Override
  public String toString() { return "Den" + " " + this.getDeadline() + ":" + " " + this.getName(); }

  // Getters and Setters
  private LocalDate getDeadline() {
    return deadline;
  }

  private void setDeadline(LocalDate deadline) { // do we want the user to be able to change the deadline date?
    this.deadline = deadline;
  }

  private String getName() {
    return name;
  }

  private void setName(String name) {
    this.name = name;
  }

  private boolean getIsPastDeadline() {
    return isPastDeadline;
  }

  private void setPastDeadline() {
    this.isPastDeadline = true;
  }

  private void setNotPastDeadline() {
    this.isPastDeadline = false;
  }
}
