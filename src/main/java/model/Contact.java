package model;

public class Contact {

    private String name;
    private String email;
    private int phoneNumber;
    Course course;
    private String titel; //Teacher, student ...

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getPhoneNumber() {
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

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }
}
