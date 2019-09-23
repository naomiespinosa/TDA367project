package model;

import java.util.List;

public class Course {
    private String name;
    private String courseCode;
    private List<Moment> momentItems;
    private List<StudySession> studySessions;
    private List<ToDo> toDoList;
    private int year;
    private int studyPeriod;
    private boolean isActive;

    public Course(String name, String courseCode, int year, int studyPeriod) {
        this.name = name;
        this.courseCode = courseCode;
        this.year = year;
        this.studyPeriod = studyPeriod;
    }

    // TODO - This method is used to end a course and keep the information
    public void endCourse(){
        // Code
    }

    // TODO - This method deletes the course and eventually deletes the information? or stores it somewhere
    public void deleteCourse(){
        // Code
    }
}
