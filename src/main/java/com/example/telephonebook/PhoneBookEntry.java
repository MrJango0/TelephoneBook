package com.example.telephonebook;

@SuppressWarnings("UnusedReturnValue")
public class PhoneBookEntry {

    private String firstName, lastName, phoneNumber;

    public PhoneBookEntry(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public PhoneBookEntry setFirstName(String firstName) {
        this.firstName = firstName;
        PhoneBookUI.getInstance().fileSaver.save();
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public PhoneBookEntry setLastName(String lastName) {
        this.lastName = lastName;
        PhoneBookUI.getInstance().fileSaver.save();
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public PhoneBookEntry setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        PhoneBookUI.getInstance().fileSaver.save();
        return this;
    }

}
