package model;

import java.util.Date;
import java.util.HashMap;

// This class is a specific time where we use a timer and want the result to be shown.
class StudySession {
  private Date date;
  private HashMap<Date, Date> time; // <start, finish> for all the time periods

  // TODO
  // Method that calculates the time and one session

  // Method that calculates the time of multiple sessions

  // Getters and Setter
  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public HashMap<Date, Date> getTime() {
    return time;
  }

  public void setTime(HashMap<Date, Date> time) {
    this.time = time;
  }
}