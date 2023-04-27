package com.example.telephonebook;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PhoneBookUI extends Application {

    public PhoneBook phoneBook;
    public EntryArea entryArea;
    public SearchArea searchArea;
    public InsertArea insertArea;

    public FileSaver fileSaver;

    private static PhoneBookUI instance;

    public static void main(String[] args) {
        launch();
    }

    public static PhoneBookUI getInstance() {
        return instance;
    }

    @Override
    public void start(Stage stage) {
        instance = this;

        fileSaver = new FileSaver();
        //initialize phonebook
        this.phoneBook = fileSaver.getPhoneBook();




        //Create areas
        entryArea = new EntryArea(this);
        searchArea = new SearchArea(this);
        insertArea = new InsertArea(this);

        //Create layout
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.setSpacing(10);

        //Create scene
        Scene scene = new Scene(box, 400, 400);
        stage.setTitle("Phone Book");
        stage.setScene(scene);

        /*
        Add searchArea, insertArea and entryArea to the layout
         */
        box.getChildren().add(searchArea.anchorPane);
        box.getChildren().add(insertArea.anchorPane);
        box.getChildren().add(entryArea.anchorPane);

        stage.show();
    }
}
