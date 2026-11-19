package se.lexicon.data;
import se.lexicon.model.Contact;
import se.lexicon.exception.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;


public class FileContactDAOImpl implements ContactDAO{

    //-Path filePath
    private final Path filePath;

    public FileContactDAOImpl(Path filePath) throws ContactStorageException {
        this.filePath = filePath;
        initializeFile();
    }
    private void initializeFile() throws ContactStorageException {
        try {
            if (filePath.getParent() != null) {
                Files.createDirectories(filePath.getParent());
            }
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            throw new ContactStorageException("Failed to initialize contact storage file.", e);
        }
    }

    @Override
    public List<Contact> findAll() throws ContactStorageException {
        List<Contact> contacts = new ArrayList<>();

        if (Files.notExists(filePath)) {
            return contacts;
        }

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }
                String[] parts = line.split(",", -1);
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Invalid contact record");
                }

                Contact contact = new Contact(parts[0], parts[1]);
                contacts.add(contact);
            }

        } catch (IOException | IllegalArgumentException e) {
            throw new ContactStorageException("Could not read contacts: " + e.getMessage());
        }

        return contacts;
    }

    @Override
    public void save(Contact contact) throws ContactStorageException, DuplicateContactException {
        List<Contact> contacts = findAll();
       //Check weather the contact already exists
        boolean exists = contacts.stream()
                                 .anyMatch(c -> c.getName()
                                  .equalsIgnoreCase(contact.getName()));

        if (exists) {
            throw new DuplicateContactException("Contact already exists: " + contact.getName());
        }
       //Save contact to file
        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {

            writer.write(contact.getName() + "," + contact.getPhoneNumber());
            writer.newLine();

        } catch (IOException e) {
            throw new ContactStorageException(
                    "Could not save contact: " + e.getMessage());
        }
    }



    @Override
    public Contact findByName(String name) throws ContactStorageException {
            return findAll().stream()
                            .filter(c -> c.getName().equalsIgnoreCase(name))
                            .findFirst()
                             .orElse(null);
    }




}
