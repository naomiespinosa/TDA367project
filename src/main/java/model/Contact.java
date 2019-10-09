package model;

public class Contact {

    private String name;
    private String email;
    private String phoneNumber;
    private Course course;
    private String titel; //Teacher, student ...

    public Contact(String name, String email, String phoneNumber, String titel) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.titel = titel;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getTitel() {
        return titel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
