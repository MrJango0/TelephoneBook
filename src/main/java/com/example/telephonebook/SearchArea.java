package com.example.telephonebook;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class SearchArea {

    public AnchorPane anchorPane = new AnchorPane();

    public SearchArea(PhoneBookUI phoneBookUI) {
        //Create textfield to enter search query
        TextField searchArea = new TextField();

        searchArea.setMaxWidth(300);
        searchArea.setPromptText("Search for an entry...");

        //Allow to search by pressing enter
        searchArea.setOnKeyPressed(e -> {
            if (e.getCode().toString().equals("ENTER")) {
                phoneBookUI.entryArea.getTable().setItems(phoneBookUI.phoneBook.search(searchArea.getText()));
            }
        });


        anchorPane.getChildren().add(searchArea);

        //Create button for searching phonebook
        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> phoneBookUI.entryArea.getTable().setItems(phoneBookUI.phoneBook.search(searchArea.getText())));
        anchorPane.getChildren().add(searchButton);

        //Set anchors
        AnchorPane.setLeftAnchor(searchArea, 10.0);
        AnchorPane.setTopAnchor(searchArea, 10.0);
        AnchorPane.setRightAnchor(searchArea, 70.0);
        AnchorPane.setBottomAnchor(searchArea, 10.0);

        AnchorPane.setTopAnchor(searchButton, 10.0);
        AnchorPane.setRightAnchor(searchButton, 10.0);
        AnchorPane.setBottomAnchor(searchButton, 10.0);

    }

}
