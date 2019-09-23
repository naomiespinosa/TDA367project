package model;

import java.util.Date;

public class Moment {
    private String name;
    private Date deadline;
    private boolean isPastDeadline;


    Moment(String name, Date deadline, boolean isPastDeadline){
        this.name=name;
        this.deadline=deadline;
        this.isPastDeadline=false;
    }

    private Date getDeadline(Date deadline){
        return deadline;
    }

    private void setDeadline(){    //do we want the user to be able to change the deadline date?
        this.deadline=deadline;
    }

    private String getName(String name){
        return name;
    }

    private void setName(){
        this.name=name;
    }

    private boolean getIsPastDeadline(Boolean isPastDeadline){
        return isPastDeadline;
    }

    private void setPastDeadline(){
        this.isPastDeadline=true;
    }

    private void setNotPastDeadline(){
        this.isPastDeadline=false;
    }

}
