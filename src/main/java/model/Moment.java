package model;

import java.time.LocalDate;
import java.util.Comparator;

/**
 * Moment defines what a moment/assignment is and what it can do. A moment have an description and a
 * date
 */
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

  /** Comparator for moment that prioritizes earlier dates */
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

  // Getters and Setters
  private LocalDate getDeadline() {
    return deadline;
  }

  private void setDeadline(
      LocalDate deadline) { // do we want the user to be able to change the deadline date?
    this.deadline = deadline;
  }

  private String getName() {
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
