package com.example.telephonebook;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.util.Random;

public class PhoneBook {

    private final ObservableList<PhoneBookEntry> entries;

    private final FilteredList<PhoneBookEntry> filteredEntries;


    @SuppressWarnings("unused")
    public PhoneBook(ObservableList<PhoneBookEntry> entries) {
        this.entries = entries;
        this.filteredEntries = new FilteredList<>(entries);
    }

    public PhoneBook() {
        this.entries = FXCollections.observableArrayList();
        this.filteredEntries = new FilteredList<>(entries);
    }

    public static PhoneBook loadWithExampleData() {
        PhoneBook book = new PhoneBook();
        for (int i = 0; i < 20; i++) {
            book.addEntryInitial(new PhoneBookEntry(
                    "ExampleFirstName" + i,
                    "ExampleLastName" + i,
                    String.valueOf(new Random().nextInt(1000000000))));
        }
        return book;
    }

    public ObservableList<PhoneBookEntry> getEntries() {
        return entries;
    }

    public void addEntry(PhoneBookEntry entry) {
        entries.add(entry);
        PhoneBookUI.getInstance().fileSaver.save();
    }

    public void removeEntry(PhoneBookEntry entry) {
        entries.remove(entry);
        PhoneBookUI.getInstance().fileSaver.save();
    }

    public ObservableList<PhoneBookEntry> search(String query) {
        final String finalQuery = query.toLowerCase();
        filteredEntries.setPredicate(entry ->
                entry.getFirstName().toLowerCase().contains(finalQuery)
                        || entry.getLastName().toLowerCase().contains(finalQuery)
                        || entry.getPhoneNumber().toLowerCase().contains(finalQuery));
        return filteredEntries;
    }

    public void addEntryInitial(PhoneBookEntry entry) {
        entries.add(entry);
    }
}
