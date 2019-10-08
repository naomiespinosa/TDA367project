package viewcontroller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;

public class ContactsPage {

         @FXML
         private ListView<?> contactsListview;

        @FXML
        private TextField contactName;

        @FXML
        private TextField contactEmail;

        @FXML
        private TextField contactphone;

        @FXML
        private Spinner<?> contactCourse;

        @FXML
        private Spinner<?> contactTitle;

        @FXML
        void creacteContact(ActionEvent event) {

        }

        @FXML
        void deleteContact(ActionEvent event) {

        }

        @FXML
        void openNewContactPage(ActionEvent event) {

        }

    }

