package model;

import java.time.LocalDate;
import java.util.Comparator;

/**
 * Moment defines what a moment/assignment is and what it can do. A moment have an description and a
 * date/deadline.
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
    return "Den"
        + " "
        + this.deadline.getDayOfMonth()
        + " "
        + this.deadline.getMonth()
        + " "
        + this.deadline.getYear()
        + ":"
        + " "
        + this.name;
  }

  // Getters and Setters

  /** @return the date of the deadline */
  public LocalDate getDeadline() {
    return deadline;
  }

  /** @return the description/name of the moment */
  public String getName() {
    return name;
  }

  /**
   * sets the name/description of the moment
   *
   * @param name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Comparator moment and prioritizes earlier dates. Is called whenever you add or remove a Moment.
   */
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
