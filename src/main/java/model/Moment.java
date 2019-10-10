package model;

import java.time.LocalDate;
import java.util.Comparator;

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
  public String toString() {
    return "Den" + " " + this.getDeadline() + ":" + " " + this.getName();
  }

  // Getters and Setters
  public LocalDate getDeadline() {
    return deadline;
  }

  public void setDeadline(
      LocalDate deadline) { // do we want the user to be able to change the deadline date?
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

  // Comparator for moment that prioritizes earlier dates
  // Is called whenever you add or remove a Moment
  public Comparator<Moment> byDate =
      new Comparator<Moment>() {
        @Override
        public int compare(Moment moment1, Moment moment2) {
          if (moment1.getDeadline().isBefore(moment2.getDeadline())) {
            return -1;
          }
          if (moment1.getDeadline().isAfter(moment2.getDeadline())) {
            return 1;
          }
          return 0;
        }
      };
}
