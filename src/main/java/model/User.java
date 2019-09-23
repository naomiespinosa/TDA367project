package model;

import java.util.List;

public class User {
    private String username;
    private String name;
    private List<Course> courses;

    public User(String username, String name) {
        this.username = username;
        this.name = name;
    }

    // Used to add a new course
    private void addCourse(String name, String courseCode, int year, int studyPeriod){
        courses.add(new Course(name, courseCode, year, studyPeriod));
    }

    private Course getCourse(int index){
        return courses.get(index);
    }

    private List<Course> getCourses(){
        return courses;
    }

    // Getters och Setters
    private String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    private String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }
}
