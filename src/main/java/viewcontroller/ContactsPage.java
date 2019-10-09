package viewcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Contact;
import model.Course;
import model.ToDo;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ContactsPage extends Observer implements Initializable {

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
        private ComboBox contactTitel;

        String[] acceptedTitles = {"Lärare", "Elev", "Handledare", "Övrig"};

        @FXML
        void creacteContact(ActionEvent event) { }

        @FXML
        void deleteContact(ActionEvent event) { }

        @FXML
        void openNewContactPage(ActionEvent event) {

        }

        public ContactsPage(ListView l, TextField contactName, TextField phoneTextField, TextField contactEmail, ComboBox contactCourse, ComboBox contactTitel) {
                this.contactsListview = l;
                this.contactName = contactName;
                this.contactphone = phoneTextField;
                this.contactEmail = contactEmail;
                this.contactCourse = contactCourse ;
                this.contactTitel = contactTitel;
        }

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
                        //this.courseContact.setText(c.getCourse()); //TODO
                        contactTitel.getSelectionModel().select(c.getTitel());
                }

        }

        @Override
        public void initialize(URL location, ResourceBundle resources) {
                updateInfo();
                CourseManager.attach(this);
        }

        private void updateInfo() {
                //Titel
                contactTitel.getItems().clear();
                contactTitel.getItems().addAll(acceptedTitles);
                contactTitel.getSelectionModel().select("Lärare");

                // Courses
                contactCourse.getItems().clear();
                contactCourse.getItems().addAll(CourseManager.getCourses());
                contactCourse.getSelectionModel().select(CourseManager.getCourses().get(0));
        }


        @Override
        public void update() {
                updateInfo();
        }
}

