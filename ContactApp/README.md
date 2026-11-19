# Contact App Workshop

A Java console application for managing contacts stored in a local text file, built using the **MVC (Model-View-Controller)** architecture, **DAO (Data Access Object)** pattern, and structured exception handling.

---

## Project Setup & Structure

* **Group ID:** `se.lexicon`
* **Artifact ID:** `contact-app-workshop`

```text
src/main/java/se/lexicon
├── Main.java
├── controller
│   └── ContactController.java
├── data
│   ├── ContactDAO.java
│   └── FileContactDAOImpl.java
├── exception
│   ├── ContactStorageException.java
│   ├── DuplicateContactException.java
│   └── ExceptionHandler.java
├── model
│   └── Contact.java
└── view
    └── ContactView.java
```

---

## Tasks Achieved

### Task 1: The Model & Validation (Unchecked Exceptions)
* Created `Contact` inside `se.lexicon.model` with `name` and `phoneNumber` fields.
* Enforced input validation in the setter methods (`setName` and `setPhoneNumber`) and invoked them directly from the constructor.
* Threw unchecked `IllegalArgumentException` when a name is null/blank or when a phone number does not match the 10-digit regular expression (`^\d{10}$`).

### Task 2: Custom Exceptions (Checked Exceptions)
* Defined two custom checked exceptions extending `Exception` inside `se.lexicon.exception`:
  * `ContactStorageException`: Thrown when file initialization, reading, or writing fails due to `IOException`.
  * `DuplicateContactException`: Thrown when attempting to save a contact whose name or phone number already exists.
* Created a centralized `ExceptionHandler` utility with a static `handle(Exception e)` method to categorize and format error messages cleanly.

### Task 3: The Data Layer (DAO & Try-with-Resources)
* Defined the persistence contract in the `ContactDAO` interface (`findAll`, `save`, and `findByName`).
* Implemented `FileContactDAOImpl` using `java.nio.file` (`Path`, `Files`, `BufferedReader`, and `BufferedWriter`).
* Used **try-with-resources** blocks to guarantee that file streams close automatically after reading or writing.
* Kept the DAO strictly focused on data persistence—zero console output (`System.out`) occurs inside the data layer; all communication happens via return values or exceptions.

### Task 4: The View, Controller & Application Entry Point
* **`ContactView` (`se.lexicon.view`):** Encapsulates all console I/O (`Scanner` and `System.out`), rendering menus, contact lists, prompts, and status messages.
* **`ContactController` (`se.lexicon.controller`):** Orchestrates application flow in a `run()` loop, captures user selections from the View, delegates data operations to `ContactDAO`, and routes caught exceptions to `ExceptionHandler`.
* **`Main` (`se.lexicon.Main`):** Initializes `FileContactDAOImpl` (pointing to `data/contacts.txt`), `ContactView`, and `ContactController`, then starts the application.

### Task 5: Explanation of the MVC Design Pattern

The **Model-View-Controller (MVC)** pattern separates an application into three interconnected components to isolate business logic from user interface concerns:

1. **Model (`Contact` & `ContactDAO`):** Represents the application's data structure, validation rules, and persistence logic. It operates independently of how data is presented to the user.
2. **View (`ContactView`):** Handles all presentation and user input tasks. It displays menus and results to the user without knowing how contacts are validated or stored on disk.
3. **Controller (`ContactController`):** Acts as the coordinator between the View and the Model. It receives user commands from the View, triggers operations in the Model/DAO, handles exceptions, and updates the View with the outcome.

**Key Benefits:**
* **Separation of Concerns:** Each layer has a single, clear responsibility.
* **Maintainability & Extensibility:** Storage can be migrated (e.g., from a `.txt` file to a relational database) or the UI can be replaced (e.g., from CLI to a GUI or web frontend) without rewriting the core business logic.
* **Testability:** Model validation and DAO persistence can be unit-tested independently without simulating console input.

---

## How to Run

1. Clone the repository to your local machine.
2. Open the project in your IDE (e.g., IntelliJ IDEA) as a Maven project.
3. Run `se.lexicon.Main`.
4. Follow the interactive console menu to add, list, or search contacts. Stored contacts are saved automatically to `data/contacts.txt`.
