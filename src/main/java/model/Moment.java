package model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Comparator;
import java.util.Collections;

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
  public LocalDate getDeadline() {
    return deadline;
  }

  public void setDeadline(LocalDate deadline) { // do we want the user to be able to change the deadline date?
    this.deadline = deadline;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
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
