# Library Management System

A Java command-line application for managing books, members, and library loans.

The project was built as part of my Java learning journey and focuses on object-oriented design, validation, service-layer logic, JSON persistence, testing, and clean separation of responsibilities.

## Features

### Books

* Add books
* View all books
* Edit individual book fields

    * Title
    * Author
    * Number of pages
* Remove books
* Prevent removal of books with active loans

### Members

* Add members
* View all members
* Edit individual member fields

    * Name
    * Email
    * Phone number
* Remove members
* Prevent removal of members with active loans

### Loans

* Borrow books
* Return books
* View active loans
* View complete loan history
* Prevent borrowing a book that is already on loan

### Persistence

* Library data is automatically saved to JSON
* Data is restored when the application starts
* Books, members, loans, and return states are preserved
* IDs continue from the highest stored ID after restarting the application

### CLI

* Organized Books, Members, and Loans submenus
* Input validation
* Human-readable date format
* Colored console output using ANSI escape codes

## Technologies

* Java
* Maven
* Gson
* JUnit 5
* IntelliJ IDEA
* Git / GitHub

## Project Structure

```text
src/
├── main/
│   └── java/
│       ├── app/
│       │   └── Main.java
│       ├── model/
│       │   ├── Book.java
│       │   ├── Member.java
│       │   └── Loan.java
│       ├── service/
│       │   └── LibraryService.java
│       ├── storage/
│       │   ├── LibraryData.java
│       │   ├── LibraryFileService.java
│       │   ├── LibraryState.java
│       │   ├── LocalDateAdapter.java
│       │   └── LoanRecord.java
│       └── ui/
│           ├── ConsoleColors.java
│           └── ConsoleUI.java
└── test/
    └── java/
        ├── model/
        ├── service/
        └── storage/
```

## Architecture

The application follows a simple layered structure:

```text
Main
  ↓
ConsoleUI
  ↓
LibraryService
  ↓
Book / Member / Loan
```

Persistence is handled separately:

```text
Main
  ↕
LibraryFileService
  ↕
JSON
```

The UI handles user interaction, the service layer handles application logic, and the model classes enforce domain validation.

## Domain Rules

Some important rules enforced by the application include:

* Book and member IDs must be positive.
* Book titles and authors cannot be blank.
* Page count must be greater than zero.
* Member names cannot be blank.
* Email addresses must follow the project's validation rules.
* Phone numbers may contain digits, spaces, `+`, and `-`, and must contain at least one digit.
* A book cannot have more than one active loan.
* A returned date cannot be before the borrowed date.
* A book cannot be returned twice.
* Books and members with active loans cannot be removed.

## Data Persistence

The application stores data in:

```text
data/library.json
```

Gson is used for JSON serialization and deserialization.

Loans are stored using `LoanRecord`, which references books and members by ID instead of embedding complete duplicate objects.

When data is loaded, those relationships are reconstructed into normal `Loan` objects.

Dates in JSON use ISO format:

```text
yyyy-MM-dd
```

Dates displayed in the console use:

```text
dd-MM-yyyy
```

## Running the Application

Clone the repository and open the project in IntelliJ IDEA, or run it using Maven/Java from the project directory.

The application starts from:

```text
app.Main
```

On exit, the current library state is automatically written to the JSON data file.

## Testing

The project includes JUnit 5 tests for:

* `Book`
* `Member`
* `Loan`
* `LibraryService`
* JSON persistence and state restoration

The tests cover model validation, borrowing and returning books, duplicate-loan prevention, removal rules, persistence, and ID restoration.

## Versions

### v1.0

Initial completed Library Management System including:

* Book/member management
* Borrowing and returning
* Loan history
* Validation
* JSON persistence
* Automated tests

### v1.1

CLI and domain refinement:

* Books, Members, and Loans submenus
* Field-specific Book editing
* Field-specific Member editing
* Book removal
* Member removal
* Active-loan deletion protection
* Additional service tests
* Improved console prompts
* ANSI console colors

## What I Learned

This project helped me practice and understand:

* Object-oriented design
* Encapsulation
* Mutable vs immutable object design
* Object references and shared state
* Service-layer architecture
* Domain validation
* Java collections
* `Optional`
* Streams
* Exception handling
* `LocalDate`
* File I/O
* JSON serialization with Gson
* DTO-style persistence objects
* Maven dependencies
* JUnit 5 testing
* Application state restoration
* Git versioning and tagged releases

## Status

**Completed — Version 1.1**
