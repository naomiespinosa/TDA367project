package model;

import java.util.Date;
import java.util.HashMap;

public class StudySession {
    private Date date;
    private HashMap<Date, Date> time; // <start, finish> for all the time periods


    // TODO
    public StudySession() {
    }

    // TODO
    // Method that calculates the time och one session

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
