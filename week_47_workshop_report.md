# Project Report: Contact App Workshop

## 1. Project Overview
The **Contact App Workshop** is a Java console application built using Maven (`se.lexicon`). The application allows users to add, view, and search contacts persisted in a local text file (`data/contacts.txt`). 

The system was engineered using the **Model-View-Controller (MVC)** architectural pattern, the **Data Access Object (DAO)** pattern, and structured **Exception Handling** (both checked and unchecked exceptions).

---

## 2. Team Collaboration & Git Workflow
Our team of five collaborated remotely and followed an organized, agile development workflow:

1. **Planning & Task Allocation (Microsoft Teams):**  
   We held a kickoff meeting on Microsoft Teams to review the workshop requirements, align on the UML class diagram, and divide responsibilities across packages so each member could work independently without merge conflicts.
2. **Version Control & GitHub Workflow:**  
   - Each team member worked on their assigned package on a dedicated branch.
   - Completed features were pushed to GitHub.
   - Team members opened **Pull Requests (PRs)** for code review and integration into the main branch, ensuring consistent method signatures, clean code standards, and proper package imports across all layers.

---

## 3. Team Roles & Responsibilities

| Team Member | Assigned Package | Classes Created | Key Responsibilities |
| :--- | :--- | :--- | :--- |
| **Soraya** | `se.lexicon.model` | `Contact.java` | Implemented the `Contact` entity with `name` and `phoneNumber` attributes. Added input validation inside setters (enforcing non-empty names and a 10-digit regex phone format) throwing unchecked `IllegalArgumentException`. |
| **Ezhilarasi** | `se.lexicon.exception` | `ContactStorageException.java`<br>`DuplicateContactException.java`<br>`ExceptionHandler.java` | Created custom checked exceptions for storage I/O failures and duplicate contact entries. Built a centralized `ExceptionHandler` utility to format and display clear error messages. |
| **Haleema** | `se.lexicon.data` | `ContactDAO.java`<br>`FileContactDAOImpl.java` | Defined the persistence contract (`findAll`, `save`, `findByName`) and implemented file-based storage using `java.nio.file` and **try-with-resources** blocks while keeping the data layer free of console output. |
| **Christopher** | `se.lexicon.controller` | `ContactController.java` | Built the application controller to coordinate user actions between the View and DAO, manage the main execution loop, and route exceptions to `ExceptionHandler`. |
| **Ervin** | `se.lexicon.view`<br>`se.lexicon` | `ContactView.java`<br>`Main.java` | Implemented all console user interface menus, prompts, and output formatting in `ContactView`. Configured dependency wiring and application startup in `Main`. |

---

## 4. Architecture & Implementation Summary

```text
src/main/java/se/lexicon
├── Main.java                        (Ervin)
├── controller
│   └── ContactController.java       (Christopher)
├── data
│   ├── ContactDAO.java              (Haleema)
│   └── FileContactDAOImpl.java      (Haleema)
├── exception
│   ├── ContactStorageException.java (Ezhilarasi)
│   ├── DuplicateContactException.java (Ezhilarasi)
│   └── ExceptionHandler.java        (Ezhilarasi)
├── model
│   └── Contact.java                 (Soraya)
└── view
    └── ContactView.java             (Ervin)
```

### How the Layers Work Together (MVC Pattern)
* **Model & Data Layer (`Soraya` & `Haleema`):** Holds the domain data (`Contact`), enforces data integrity via validation rules, and handles reading/writing records to `data/contacts.txt`.
* **Exception Layer (`Ezhilarasi`):** Separates error definitions and formatting from business logic, ensuring both validation errors and file I/O issues are handled gracefully.
* **View Layer (`Ervin`):** Handles all `Scanner` inputs and `System.out` console displays without knowing how contacts are stored or validated.
* **Controller & Entry Point (`Christopher` & `Ervin`):** `Main` initializes and connects the DAO, View, and Controller, while `ContactController` processes user selections and coordinates responses across the entire application.

---

## 5. Conclusion
By dividing the codebase along clean architectural boundaries and using GitHub Pull Requests for integration, our team delivered a modular, maintainable, and well-structured Java application where every component has a single, clear responsibility.
