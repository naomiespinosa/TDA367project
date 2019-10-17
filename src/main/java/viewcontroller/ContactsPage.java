package viewcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import model.Contact;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ContactsPage extends Observer implements Initializable{


    private ArrayList<Contact> contacts = new ArrayList();
    private ObservableList<Contact> contactsObserverList =
            FXCollections.observableArrayList();

         @FXML
         private ListView<Contact> contactsListview = new ListView<>();



        @FXML
        private TextField contactName;

        @FXML
        private TextField contactEmail;

        @FXML
        private TextField contactPhone;

        @FXML
        private ComboBox contactCourse;

        @FXML
        private ComboBox contactTitle;

    @FXML
    private Label name;

    @FXML
    private Label email;

    @FXML
    private Label number;

    @FXML
    private Label course;

    @FXML
    private Label title;

    @FXML
    private AnchorPane addContactAnchorPane;

    @FXML
    private AnchorPane seeContactAnchorpane;

    @FXML
    void createContact(ActionEvent event) {
        if (!isContactApproved()) {
            contactsObserverList.add(new Contact(contactName.getText(), contactEmail.getText(), contactPhone.getText()));
            resetInputs();
            seeContactAnchorpane.toFront();

        }
    }
    @FXML
    void listview(MouseEvent event) {
    getSelectedContact();
    }

    @FXML
    void addNewContact(ActionEvent event) {
        addContactAnchorPane.toFront();

    }


    private void resetInputs() {
        contactName.clear();
        contactPhone.clear();
        contactEmail.clear();
    }

    private boolean isContactApproved() {
        return contactName.getText().trim().isEmpty() || contactEmail.getText().trim().isEmpty() || contactPhone.getText().trim().isEmpty();
    }

    private static final List acceptedTitles = Arrays.asList("Lärare", "Elev", "Handledare", "Övrig");

        private List courseNames = new ArrayList<>();


            public void removeContact(Contact c) {

            }

        public void contactsListChanged(){
        }


    private void populateContactListView() {
            contactsListview.setItems(contactsObserverList);
    }


       /*private void presentContact() {
              //c =  getSelectedContact();
            if (c != null) {

                        name.setText(c.getName());
                        number.setText(c.getPhoneNumber());
                        email.setText(c.getEmail());
                     //   contactCourse.getSelectionModel().select(c.getCourse()); //Detta måste converteras till en string
                     //   contactTitle.getSelectionModel().select(c.getTitel()); // -//-
                }

        }*/


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

   private void getSelectedContact() {
        if (contactsListview.getSelectionModel().getSelectedIndex() != -1) {
            Contact selectedContact = contactsListview.getSelectionModel().getSelectedItem();
            name.setText(selectedContact.getName());
            email.setText(selectedContact.getEmail());
            number.setText(selectedContact.getPhoneNumber());


        }
   }


    @Override
        public void update() {
               updateInfo();
        }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateInfo();
        populateContactListView();
    }


    public void listhej(javafx.scene.input.MouseEvent mouseEvent) {
            getSelectedContact();
    }
}

