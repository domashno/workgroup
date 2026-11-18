# Java Exception Handling Practice Tasks

A collection of Java console applications demonstrating core exception-handling techniques, including `try-catch` blocks, custom exceptions, nested exception handling, manual `throw` statements, and `try-with-resources`.

## Project Overview

### Exercise 1: Basic Exception Handling
Prompts the user to enter two integers and divides the first number by the second. Uses a `try-catch` block to catch `ArithmeticException` and prevent the application from crashing when the divisor is zero.

### Exercise 2: Multiple Exception Types
Reads an integer from the user and verifies that it falls within a specified range (1 to 100). Handles non-integer inputs using `InputMismatchException` and throws a custom `OutOfRangeException` when values fall outside the allowed bounds.

### Exercise 3: Custom Exception
Simulates basic bank account operations such as deposits and withdrawals via a `BankAccount` class. Defines and throws a custom checked exception (`InsufficientBalanceException`) using `throw` and `throws` whenever a withdrawal exceeds the available balance.

### Exercise 4: Nested try-catch Blocks
Reads lines from a text file and parses each line into an integer using nested `try-catch` blocks. The outer block handles file-level errors (`NoSuchFileException` and `IOException`), while the inner block catches `NumberFormatException` to skip invalid lines without halting execution.

### Exercise 6: Throwing Exceptions
Validates whether an input string matches a standard email format (`user@domain.com`). Manually throws an unchecked `IllegalArgumentException` with a descriptive error message when the string is null, empty, or improperly formatted.

### Exercise 7: Custom Exceptions for List Operations
Manages an `ArrayList` of names with methods to add and search entries. Throws a custom `DuplicateNameException` when attempting to add an existing name and a custom `NameNotFoundException` when a searched name is missing.

### Exercise 8: Try-with-Resources
Writes strings of text to an output file using Java's `try-with-resources` statement. Ensures that `BufferedWriter` and `FileWriter` (`AutoCloseable` resources) are automatically closed once the write operation completes or if an `IOException` occurs.

---

