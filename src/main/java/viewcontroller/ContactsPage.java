package viewcontroller;

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
import model.event.UserChangedEvent;

/**
 * This class represent the "address"-book that is shown when you press the "kontakter" button in
 * the SidePanel
 *
 * @author Johanna
 */
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

  /**
   * Creates the contact if the contacts textareas is approved
   *
   * @param event
   */
  @FXML
  void createContact(ActionEvent event) {
    samePageErrorMgm();
    if (isContactApproved()) {
      Contact c =
          new Contact(contactName.getText(), contactEmail.getText(), contactPhone.getText());
      contactsObserverList.add(c);
      model.addContact(c);
      resetInputs();
      seeContactAnchorpane.toFront();
    }
    updateInfo();
    contactsListview.setItems(contactsObserverList);
    contactsListview.getSelectionModel().selectLast();
  }

  /**
   * "Edits" the contact by crearing a new one.
   *
   * @param event
   */
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
    updateInfo();
    samePageErrorMgm();
  }

  /**
   * when pressing the "add new contact - button" the page resets
   *
   * @param event
   */
  @FXML
  void addNewContact(ActionEvent event) {
    resetNewContact();
  }

  /**
   * when pressing the delete-button the contact is deleted, you get to the page where you can add a
   * contact and all the inputs is reset
   *
   * @param event
   */
  @FXML
  void deleteContact(ActionEvent event) {
    removeContact();
    addContactAnchorPane.toFront();
    resetInputs();
  }

  // Is filled areas approved

  /**
   * Check if the email textarea contains a "@" and a "."
   *
   * @return boolean
   */
  private boolean isEmailApproved() {
    return (contactEmail.getText().contains("@") && contactEmail.getText().contains("."));
  }

  /**
   * Check if the phone number textarea contains at least 3 characters and only numbers
   *
   * @return boolean
   */
  private boolean isPhoneApproved() {
    return (contactPhone.getText().matches("[0-9]+") && contactPhone.getText().length() >= 3);
  }
  /**
   * Check if the the name textarea is not empty
   *
   * @return boolean
   */
  private boolean isTextareaFilled() {
    return contactName.getText().trim().isEmpty();
  }
  /**
   * Check if all the criterias for text areas if filled
   *
   * @return boolean
   */
  private boolean isContactApproved() {
    return (!isTextareaFilled() && isEmailApproved() && isPhoneApproved());
  }

  /** resets everything that has with the add contact AnchorPane to do with */
  private void resetNewContact() {
    resetInputs();
    addContactAnchorPane.toFront();
    contactLabel.setText("Ny kontakt");
    samePageErrorMgm();
  }

  private void resetInputs() {
    contactName.clear();
    contactPhone.clear();
    contactEmail.clear();
    samePageErrorMgm();
  }

  private static final List acceptedTitles =
      Arrays.asList("Examinator", "Elev", "Handledare", "Annan");

  private void removeContact() {
    final int selectedIdx = contactsListview.getSelectionModel().getSelectedIndex();
    if (selectedIdx != -1) {
      contactsListview.getItems().remove(selectedIdx);
    }
    Contact c = contactsListview.getSelectionModel().getSelectedItem();
    model.removeContact(c);
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
      isTextAreaFilled.setVisible(true);
    }
    if (!isEmailApproved()) {
      isEmailApproved.setVisible(true);
    }
    if (!isPhoneApproved()) {
      isPhoneApproved.setVisible(true);
    }
  }

  private void updateInfo() {
    contactCourse.getItems().clear();
    contactCourse.getItems().addAll(getCourseNames());
    contactCourse.getSelectionModel().select(0);

    contactsObserverList.clear();
    for (Contact c : model.getSavedContacts()) {
      contactsObserverList.add(c);
    }

    contactsListview.setItems(contactsObserverList);
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

  @Subscribe
  public void onUserChanged(UserChangedEvent event) {
    updateInfo();
    resetNewContact();
  }

  /**
   * Show the contact who is selected in the listview in the seeContactAnchorPane
   *
   * @param mouseEvent
   */
  public void showContact(javafx.scene.input.MouseEvent mouseEvent) {
    showSelectedContact();
  }

  @Override
  public void initPage(Min5a model, Optional<MainPage> mainPage) {
    this.model = model;

    contactTitle.getItems().addAll(acceptedTitles);
    contactTitle.getSelectionModel().select(0);
    // samePageErrorMgm();
    // Todo fix so that the current courses (if there are any existing already) are in the courses

  }
}
