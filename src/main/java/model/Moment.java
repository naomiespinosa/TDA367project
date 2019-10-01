package model;

import java.util.Date;

public class Moment {
  private String name;
  private Date deadline;
  private boolean isPastDeadline;

  Moment(String name, Date deadline) {
    this.name = name;
    this.deadline = deadline;
   this.isPastDeadline = false;
  }


  private Date getDeadline() {
    return deadline;
  }

  private void setDeadline(
      Date deadline) { // do we want the user to be able to change the deadline date?
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
