package com.example.telephonebook;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;


public class EntryArea {

    public AnchorPane anchorPane = new AnchorPane();
    public TableView<PhoneBookEntry> table = new TableView<>();

    public EntryArea(PhoneBookUI phoneBookUI) {
        table.setMaxWidth(500);
        AnchorPane.setLeftAnchor(table, 10.0);
        AnchorPane.setRightAnchor(table, 10.0);
        AnchorPane.setTopAnchor(table, 0.0);
        AnchorPane.setBottomAnchor(table, 0.0);


        //Create columns for displaying phone book and button to delete entries
        TableColumn<PhoneBookEntry, String> firstNameColumn = new TableColumn<>("First Name");
        TableColumn<PhoneBookEntry, String> lastNameColumn = new TableColumn<>("Last Name");
        TableColumn<PhoneBookEntry, String> phoneNumberColumn = new TableColumn<>("Phone Number");
        TableColumn<PhoneBookEntry, Button> deleteColumn = new TableColumn<>("Delete");

        firstNameColumn.setMinWidth(100);
        lastNameColumn.setMinWidth(100);
        phoneNumberColumn.setMinWidth(100);
        deleteColumn.setMinWidth(50);

        firstNameColumn.setMaxWidth(250);
        lastNameColumn.setMaxWidth(250);
        phoneNumberColumn.setMaxWidth(400);
        deleteColumn.setMaxWidth(50);

        deleteColumn.setStyle("-fx-alignment: CENTER");

        //Add values to the columns
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        //Add delete button to each row
        addDeleteButtons(deleteColumn, phoneBookUI);

        //Add entire phoneBook to table (no filter yet since there is no search)
        table.setItems(phoneBookUI.phoneBook.getEntries());

        //Add functionality to edit cells
        firstNameColumn.setOnEditCommit(e -> {
            e.getRowValue().setFirstName(e.getNewValue());
        });
        lastNameColumn.setOnEditCommit(e -> e.getRowValue().setLastName(e.getNewValue()));
        phoneNumberColumn.setOnEditCommit(e -> e.getRowValue().setPhoneNumber(e.getNewValue()));


        //Add columns to table
        table.getColumns().add(firstNameColumn);
        table.getColumns().add(lastNameColumn);
        table.getColumns().add(phoneNumberColumn);
        table.getColumns().add(deleteColumn);

        //Make table editable
        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        table.setEditable(true);
        anchorPane.getChildren().add(table);
    }

    public TableView<PhoneBookEntry> getTable() {
        return table;
    }

    private void addDeleteButtons(TableColumn<PhoneBookEntry, Button> column, PhoneBookUI phoneBookUI) {
        Callback<TableColumn<PhoneBookEntry, Button>, TableCell<PhoneBookEntry, Button>> cellFactory = new Callback<>() {
            @Override
            public TableCell<PhoneBookEntry, Button> call(final TableColumn<PhoneBookEntry, Button> param) {
                return new TableCell<>() {

                    private final Button btn = new Button("X");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            PhoneBookEntry entry = getTableView().getItems().get(getIndex());
                            phoneBookUI.phoneBook.removeEntry(entry);
                        });
                    }

                    @Override
                    public void updateItem(Button item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
            }
        };
        column.setCellFactory(cellFactory);
    }
}
