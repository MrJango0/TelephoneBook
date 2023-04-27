package com.example.telephonebook;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;


public class InsertArea {

    public AnchorPane anchorPane = new AnchorPane();

    public InsertArea(PhoneBookUI phoneBookUI) {
        //Create columns for adding new entries
        TextField firstNameEntry = new TextField();
        firstNameEntry.setMaxWidth(300);
        firstNameEntry.setPromptText("First Name");
        anchorPane.getChildren().add(firstNameEntry);

        TextField lastNameEntry = new TextField();
        lastNameEntry.setMaxWidth(300);
        lastNameEntry.setPromptText("Last Name");
        anchorPane.getChildren().add(lastNameEntry);

        TextField phoneNumberEntry = new TextField();
        phoneNumberEntry.setMaxWidth(300);
        phoneNumberEntry.setPromptText("Phone Number");
        anchorPane.getChildren().add(phoneNumberEntry);

        //Create label for displaying info
        Label infoLabel = new Label();
        infoLabel.setText("");
        infoLabel.setMaxWidth(300);
        infoLabel.setTextAlignment(TextAlignment.CENTER);
        infoLabel.setAlignment(Pos.CENTER);

        //Create button to add new entries and set action
        Button addButton = new Button("Add Entry");
        anchorPane.getChildren().add(addButton);
        anchorPane.getChildren().add(infoLabel);

        //Allow to add entries by pressing enter
        EventHandler<? super KeyEvent> enterPressHandler = e -> {
            if (e.getCode().toString().equals("ENTER")) {
                validateAndInsert(firstNameEntry, lastNameEntry, phoneNumberEntry, infoLabel, phoneBookUI);
            }
        };

        firstNameEntry.setOnKeyPressed(enterPressHandler);
        lastNameEntry.setOnKeyPressed(enterPressHandler);
        phoneNumberEntry.setOnKeyPressed(enterPressHandler);

        addButton.setOnAction(e -> validateAndInsert(firstNameEntry, lastNameEntry, phoneNumberEntry, infoLabel, phoneBookUI));


        AnchorPane.setLeftAnchor(firstNameEntry, 50.0);
        AnchorPane.setTopAnchor(firstNameEntry, 10.0);
        AnchorPane.setRightAnchor(firstNameEntry, 50.0);

        AnchorPane.setLeftAnchor(lastNameEntry, 50.0);
        AnchorPane.setTopAnchor(lastNameEntry, 50.0);
        AnchorPane.setRightAnchor(lastNameEntry, 50.0);

        AnchorPane.setLeftAnchor(phoneNumberEntry, 50.0);
        AnchorPane.setTopAnchor(phoneNumberEntry, 90.0);
        AnchorPane.setRightAnchor(phoneNumberEntry, 50.0);

        AnchorPane.setLeftAnchor(addButton, 50.0);
        AnchorPane.setTopAnchor(addButton, 130.0);
        AnchorPane.setRightAnchor(addButton, 50.0);

        AnchorPane.setLeftAnchor(infoLabel, 50.0);
        AnchorPane.setTopAnchor(infoLabel, 170.0);
        AnchorPane.setRightAnchor(infoLabel, 50.0);

    }

    private void validateAndInsert(TextField firstNameEntry, TextField lastNameEntry, TextField phoneNumberEntry, Label infoLabel, PhoneBookUI phoneBookUI) {
        if (firstNameEntry.getText().isEmpty()) {
            infoLabel.setText("First Name is empty!");
            return;
        }
        if (lastNameEntry.getText().isEmpty()) {
            infoLabel.setText("Last Name is empty!");
            return;
        }
        if (phoneNumberEntry.getText().isEmpty()) {
            infoLabel.setText("Phone Number is empty!");
            return;
        }
        phoneBookUI.phoneBook.addEntry(new PhoneBookEntry(firstNameEntry.getText(), lastNameEntry.getText(), phoneNumberEntry.getText()));
        infoLabel.setText("Entry added!");
        firstNameEntry.setText("");
        lastNameEntry.setText("");
        phoneNumberEntry.setText("");
    }

}
