package viewcontroller;

import model.User;

import java.util.ArrayList;
import java.util.List;

abstract class CourseManager {
    private static User user = User.getInstance();

    //Observer
    private static List<Observer> observers = new ArrayList<Observer>();
    private int state;

    static void attach(Observer observer){
        observers.add(observer);
    }

    static void createNewCourse(String name, String code, int year, int period) {
        user.addCourse(name,code,year,period);
        notifyAllObservers();
    }

    private static void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }
    // Adding Courses


}
