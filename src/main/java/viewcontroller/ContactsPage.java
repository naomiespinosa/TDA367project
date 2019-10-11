package viewcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.Contact;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ContactsPage extends Observer implements Initializable{

        Contact c;
        private ObservableList<Contact> contactsName =
                FXCollections.observableArrayList(new Contact("Johanna Wiberg","fggf","0204044","Teacher"));

         @FXML
         private ListView<?> contactsListview;

        @FXML
        private TextField contactName;

        @FXML
        private TextField contactEmail;

        @FXML
        private TextField contactphone;

        @FXML
        private ComboBox contactCourse;

        @FXML
        private ComboBox contactTitle;

        private static final List acceptedTitles = Arrays.asList("Lärare", "Elev", "Handledare", "Övrig");

        private List courseNames = new ArrayList<>();

        public void newContact(){

        }

        public void removeContact(){

        }

        public void contactsListChanged(){

        }


       private void presentContact(Contact c) {
                if (c != null) {
                        contactName.setText(c.getName());
                        contactphone.setText(c.getPhoneNumber());
                        contactEmail.setText(c.getEmail());
                        contactCourse.getSelectionModel().select(c.getCourse());
                        contactTitle.getSelectionModel().select(c.getTitel());
                }

        }


        private void updateInfo() {
                //Titel
            contactTitle.getItems().clear();
            contactTitle.getItems().addAll(acceptedTitles);
            contactTitle.getSelectionModel().select("Lärare");

                // Courses
                contactCourse.getItems().clear();
                contactCourse.getItems().addAll(getCourseNames());
                contactCourse.getSelectionModel().select(0);
        }

    private List<String> getCourseNames() {
            for (int i = 0; i < CourseManager.getCourses().size();i++){
                courseNames.add(CourseManager.getCourses().get(i).getName());
            }
            return courseNames;
    }


    @Override
        public void update() {
               updateInfo();
        }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateInfo();
    }
}

