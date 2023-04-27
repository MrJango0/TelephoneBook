package com.example.telephonebook;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileSaver {

    private static final Path path = Paths.get("C:\\PhoneBook\\phonebook.json");
    private final PhoneBook phoneBook;
    public FileSaver() {
        this.phoneBook = load();
    }
    public void save() {

        JsonFactory factory = new JsonFactory () ;
        try (OutputStream os = Files.newOutputStream(path);
             JsonGenerator jg = factory.createGenerator(os)) {
            jg.writeStartArray();
            phoneBook.getEntries().forEach(entry -> {
                try {
                    jg.writeStartObject();
                    jg.writeStringField("firstName", entry.getFirstName());
                    jg.writeStringField("lastName", entry.getLastName());
                    jg.writeStringField("phoneNumber", entry.getPhoneNumber());
                    jg.writeEndObject();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            jg.writeEndArray();

        } catch ( IOException e) {
            e. printStackTrace () ;
        }


    }

    public PhoneBook load() {

        if(!Files.exists(path)) {
            try {

                //File doesnt exist -> Create file and return a new phonebook with example data
                Files.createDirectories(path.getParent());
                Files.createFile(path);
                return PhoneBook.loadWithExampleData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        PhoneBook book = new PhoneBook();
        try ( InputStream is = Files.newInputStream(path)) {
            ObjectMapper mapper = new ObjectMapper() ;
            JsonNode root = mapper.readTree(is) ;

            root.elements().forEachRemaining(node -> {
                PhoneBookEntry entry = new PhoneBookEntry(
                        node.get("firstName").asText(),
                        node.get("lastName").asText(),
                        node.get("phoneNumber").asText()
                );
                book.addEntryInitial(entry);
            });

        } catch ( IOException e) {
            e. printStackTrace () ;
        }

        return book;
    }

    public PhoneBook getPhoneBook() {
        return phoneBook;
    }
}
