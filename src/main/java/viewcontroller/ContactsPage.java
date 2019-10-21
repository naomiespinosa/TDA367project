package viewcontroller;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import model.Contact;
import model.Course;
import model.Min5a;
import model.event.CourseChangeEvent;

public class ContactsPage implements Page {

  private ArrayList<Contact> contacts = new ArrayList();
  private ObservableList<Contact> contactsObserverList = FXCollections.observableArrayList();
  // FXML
  @FXML private ListView<Contact> contactsListview = new ListView<>();

  @FXML private TextField contactName;

  @FXML private TextField contactEmail;

  @FXML private TextField contactPhone;

  @FXML private ComboBox contactCourse;

  @FXML private ComboBox contactTitle;

  @FXML private Button createContact;

  @FXML private Label name;

  @FXML private Label email;

  @FXML private Label number;

  @FXML private Label contactLabel;

  @FXML private Label course;

  @FXML private Label title;

  @FXML private AnchorPane addContactAnchorPane;

  @FXML private AnchorPane seeContactAnchorpane;

  @FXML private Label isTextAreaFilled;

  @FXML private Label isEmailApproved;

  @FXML private Label isPhoneApproved;

  private Min5a model;

  private MainPage parent;
  private EventBus eventBus = Min5a.bus;

  /*  // TODO make this subscribe work
  @Subscribe
  private void onUserChange(final UserChangedEvent userChangedEvent) {
      user = userChangedEvent.getNewUser();
      updatePage();
  }*/

  private void updatePage() {
    updateInfo();
    populateContactListView();
    initSamePageErrorMgm();
  }

  /*void init() {
      courseManager.attach(this);
  }*/

  @FXML
  void createContact(ActionEvent event) {
    samePageErrorMgm();

    if (isContactApproved()) {
      contactsObserverList.add(
          new Contact(contactName.getText(), contactEmail.getText(), contactPhone.getText()));
    }
    contactsListview.setItems(contactsObserverList);
    seeContactAnchorpane.toFront();
    contactsListview.getSelectionModel().selectLast();
    showSelectedContact();
  }

  @FXML
  void editContact(ActionEvent event) {
    addContactAnchorPane.toFront();

    contactLabel.setText("Redigera kontakt");
    createContact.setText("Spara");

    if ((contactsListview.getSelectionModel().getSelectedIndex() != -1)) {
      Contact selectedContact = contactsListview.getSelectionModel().getSelectedItem();
      contactName.setText(selectedContact.getName());
      contactEmail.setText(selectedContact.getEmail());
      contactPhone.setText(selectedContact.getPhoneNumber());
      name.setText(selectedContact.getName());
      email.setText(selectedContact.getEmail());
      number.setText(selectedContact.getPhoneNumber());
      removeContact();
    }
    samePageErrorMgm();
  }

  @FXML
  void addNewContact(ActionEvent event) {
    resetNewContact();
  }

  @FXML
  void deleteContact(ActionEvent event) {
    removeContact();
    addContactAnchorPane.toFront();
  }

  // Is filled areas approved
  private boolean isEmailApproved() {
    return (contactEmail.getText().contains("@") && contactEmail.getText().contains("."));
  }

  private boolean isPhoneApproved() {
    return (contactPhone.getText().matches("[0-9]+") && contactPhone.getText().length() >= 3);
  }

  private boolean isTextareaFilled() {
    return contactName.getText().trim().isEmpty();
  }

  private boolean isContactApproved() {
    return (!isTextareaFilled() && isEmailApproved() && isPhoneApproved());
  }

  private void resetNewContact() {
    resetInputs();
    addContactAnchorPane.toFront();
    contactLabel.setText("Ny kontakt");
  }

  private void resetInputs() {
    contactName.clear();
    contactPhone.clear();
    contactEmail.clear();
    samePageErrorMgm();
  }

  private static final List acceptedTitles = Arrays.asList("Lärare", "Elev", "Handledare", "Övrig");

  public void removeContact() {
    final int selectedIdx = contactsListview.getSelectionModel().getSelectedIndex();
    if (selectedIdx != -1) {
      contactsListview.getItems().remove(selectedIdx);
    }
  }

  private void populateContactListView() {
    contactsListview.setItems(contactsObserverList);
  }

  private void samePageErrorMgm() {
    if (!isTextareaFilled()) {
      isTextAreaFilled.setVisible(false);
    } else {
      isTextAreaFilled.setVisible(true);
    }
    if (isEmailApproved()) {
      isEmailApproved.setVisible(false);
    } else {
      isEmailApproved.setVisible(true);
    }
    if (isPhoneApproved()) {
      isPhoneApproved.setVisible(false);
    } else {
      isPhoneApproved.setVisible(true);
    }
  }

  private void initSamePageErrorMgm() {
    if (isTextareaFilled()) {
      isTextAreaFilled.setText("*Allt måste vara ifyllt");
    }
    if (!isEmailApproved()) {
      isEmailApproved.setText("*Email-Addressen måste innehålla ett snabel a (@) och en punkt");
    }
    if (!isPhoneApproved()) {
      isPhoneApproved.setText("*Telefonnummret måste innehålla 3 eller fler siffror");
    }
  }

  private void updateInfo() {

    contactCourse.getItems().clear();
    contactCourse.getItems().addAll(getCourseNames());
    contactCourse.getSelectionModel().select(0);
  }

  // TODO SHOW UPDATES
  private List<String> getCourseNames() {
    List courseNames = new ArrayList<>();

    for (Course course : model.getCourses()) {
      courseNames.add(course.getName());
    }
    return courseNames;
  }

  private void showSelectedContact() {
    seeContactAnchorpane.toFront();
    if (contactsListview.getSelectionModel().getSelectedIndex() != -1) {
      Contact selectedContact = contactsListview.getSelectionModel().getSelectedItem();
      name.setText(selectedContact.getName());
      email.setText(selectedContact.getEmail());
      number.setText(selectedContact.getPhoneNumber());
      title.setText(contactTitle.getSelectionModel().getSelectedItem().toString());
      course.setText(contactCourse.getSelectionModel().getSelectedItem().toString());
    }
  }

  @Subscribe
  public void update(CourseChangeEvent event) {
    updateInfo();
  }

  public void showContact(javafx.scene.input.MouseEvent mouseEvent) {
    showSelectedContact();
  }

  @Override
  public void initPage(Min5a model, Optional<MainPage> mainPage) {
    this.model = model;

    contactTitle.getItems().addAll(acceptedTitles);
    contactTitle.getSelectionModel().select(0);
    // Todo fix so that the current courses (if there are any existing already) are in the courses
    // Combobox

  }
}
