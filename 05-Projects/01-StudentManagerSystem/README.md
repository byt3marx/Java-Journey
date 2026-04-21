# 🎓 Student Manager System

A structured **CLI-based Java application** for managing students, built as part of my Java learning journey.

This project focuses on applying **object-oriented design, layered architecture, validation, and data persistence** in a real-world style system.

---

# 🚀 Overview

The Student Manager System is a console application that allows users to:

* create and manage student records
* perform search, edit, and delete operations
* sort and display structured data
* persist data between program runs

The system is designed with **clean separation of concerns**, making it scalable and maintainable.

---

# 🧠 Key Concepts Applied

This project combines multiple core Java concepts into one cohesive system:

* Object-Oriented Programming (OOP)
* Encapsulation and data validation
* Collections (`ArrayList`)
* File I/O (saving & loading data)
* Exception handling
* CLI design and user interaction
* Layered architecture

---

# 🏗 Architecture

The project follows a **layered structure**:

```text
ui       → user interaction (menus, input handling)
service  → business logic and coordination
model    → data representation and validation
```

---

## 📦 Package Breakdown

### 🔹 model

```text
Student.java
```

* Represents the student entity
* Contains fields: `id`, `name`, `age`, `email`
* Enforces validation through setters
* Prevents invalid object states

---

### 🔹 service

```text
StudentManager.java
StudentFileService.java
```

#### StudentManager

* Manages student collection (`ArrayList<Student>`)
* Handles:

    * add, edit, remove
    * search (by name and ID)
    * sorting (name, age, ID)
* Maintains `nextId` logic
* Coordinates application flow

#### StudentFileService

* Handles file persistence
* Saves students to file
* Loads students from file
* Returns data instead of mutating state
* Keeps file logic separate from business logic

---

### 🔹 ui

```text
Main.java
```

* Handles user interaction
* Displays menus and options
* Validates user input
* Controls navigation flow
* Delegates logic to `StudentManager`

---

# ⚙ Features

## 👤 Student Management

* Add new students
* Edit:

    * name
    * age
    * email
* Remove students

---

## 🔍 Search

* Search by **name** (case-insensitive, partial matching)
* Search by **ID**

---

## 🔄 Sorting

* Sort students by:

    * name (Comparable)
    * age (Comparator)
    * ID (default display order)

---

## 💾 Data Persistence

* Saves data to file on exit
* Loads data on program start
* Uses CSV format:

```text
id,name,age,email
```

---

## 🛡 Validation & Safety

* Model-level validation (`Student`)
* Exception-based validation (`IllegalArgumentException`)
* Layered validation:

    * UI → user input loops
    * Manager → flow control
    * Model → data protection

---

## 🔁 CLI Navigation

* Loop-driven menus (`while(true)`)
* Nested menu system
* `0 → go back` functionality
* Null-safe flow handling

---

# 🧪 Example Flow

```text
1. Add Student
2. Show Students
3. Search Student
4. Edit Student
5. Remove Student
0. Exit
```

* Input is validated at every step
* Invalid input does not crash the program
* Users can navigate freely between menus

---

# 📁 Project Structure

```text
01-StudentManagerSystem/
│
├── data/
│   └── students.txt
│
└── src/
    ├── model/
    │   └── Student.java
    │
    ├── service/
    │   ├── StudentManager.java
    │   └── StudentFileService.java
    │
    └── ui/
        └── Main.java
```

---

# ▶ How to Run

1. Navigate to the project directory:

```bash
cd 01-StudentManagerSystem
```

2. Compile the program:

```bash
javac src/ui/Main.java
```

3. Run the application:

```bash
java src.ui.Main
```

---

# 📈 What This Project Demonstrates

This project shows the ability to:

* design structured CLI applications
* apply object-oriented principles
* separate responsibilities across layers
* handle real-world data persistence
* build maintainable and scalable systems

---

# 🚀 Future Improvements

Possible extensions:

* GUI version (JavaFX)
* JSON-based persistence
* improved search (fuzzy matching)
* additional student fields (course, grades)
* export/import functionality

---

# 👨‍💻 Author

Part of my ongoing journey to become a confident Java developer by building structured, real-world style applications.
