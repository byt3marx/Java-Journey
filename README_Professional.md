# My Java Journey ☕

A structured collection of my Java exercises and projects as I progress
from beginner to confident programmer.

This repository documents my **step‑by‑step Java learning journey**,
including projects, refactors, and concepts learned each week.

------------------------------------------------------------------------

# 📁 Project Structure

    My-Java-Journey/
    │
    ├── 01-Basics/
    │   ├── welcome.java
    │   ├── human.java
    │   ├── simpleCalculator.java
    │   ├── calculator.java
    │   ├── smartCalculator.java
    │
    ├── 02-Interactive-Logic/
    │   ├── SmartCalculator.java
    │   ├── SmartCalculatorRefactor.java
    │
    ├── 03-OOP-Foundations/
    │   └── 01-Student/
    │       ├── Student.java
    │       ├── StudentApp.java
    │
    ├── 04-Collections/
    │   └── Sorting/
    │       ├── SortNumbers.java
    │       ├── SortStrings.java
    │       ├── SortStudents.java
    │       └── StudentTest.java
    │
    ├── 05-Projects/
    │   └── 01-StudentManagerSystem/
    │       ├── data/
    │       │   └── students.txt
    │       └── src/
    │           ├── model/
    │           │   └── Student.java
    │           ├── service/
    │           │   ├── StudentManager.java
    │           │   └── StudentFileService.java
    │           └── ui/
    │               └── Main.java
    │
    ├── README.md
    └── README_Professional.md

Each folder represents a **stage of learning**, with increasingly
structured and advanced implementations.

------------------------------------------------------------------------

# 🎯 Goals of This Repository

*   Document my Java learning progress
*   Build increasingly complex Java programs
*   Refactor code into cleaner, more maintainable structures
*   Practice defensive programming and validation
*   Apply object‑oriented principles step‑by‑step

------------------------------------------------------------------------

# 📚 Learning Roadmap

The repository follows a structured learning path:

    Week 1 → Java Fundamentals
    Week 2 → Control Flow & Interactive Programs
    Week 3 → Methods, Refactoring & Enums
    Week 4 → Object-Oriented Programming
    Week 5 → File I/O & Persistence
    Week 6 → Architecture & Separation of Concerns
    Week 7 → Validation & Defensive Programming
    Week 8 → Advanced CLI Systems & UX 

------------------------------------------------------------------------

# 🧠 Concepts Covered So Far

## Java Fundamentals

*   Java program structure
*   Compilation and execution
*   Variables and data types
*   Arithmetic operators
*   Integer vs floating‑point division
*   Scanner input

## Control Flow

*   if / else logic
*   switch statements
*   while loops
*   nested validation loops
*   defensive programming

## Program Structure

*   modular design
*   helper methods
*   parameter passing
*   return values
*   separation of concerns

## Input Handling

*   parsing input with `Double.parseDouble()`
*   try / catch exception handling
*   robust validation loops

## Enums & Polymorphism

*   enums for fixed operation types
*   mapping user input to enum values
*   polymorphic method implementations
*   removing switch statements with behavior-driven enums

## Object-Oriented Programming (OOP)

* classes and objects
* fields and constructors
* `this` keyword
* encapsulation (private fields, getters, setters)
* object references and memory model
*   default values (`null`, `0`)
*   `toString()` method

## Collections

* using `ArrayList` to store objects
* managing multiple objects in a collection
* index vs user-friendly numbering (1-based vs 0-based)

## Sorting

* implementing `Comparable`
* using `Comparator`
* sorting collections by different criteria
* understanding default vs custom ordering

## File I/O & Persistence

* writing data using `FileWriter`
* reading data using `Scanner`
* structured file formats (CSV)
* parsing text into objects
* handling missing files safely
* try-with-resources
* building resilient file loading systems

## Architecture & Design

* package structure (`model`, `service`, `ui`)
* layered architecture
* separation of concerns
* dependency composition
* service-based design (`StudentFileService`)
* data ownership and responsibility boundaries

## Validation & Defensive Programming

* model-level validation
* centralized validation logic
* exception-driven validation (`IllegalArgumentException`)
* layered validation strategy (UI → Manager → Model)
* input sanitization (`trim`)
* preventing invalid object states

## Advanced Control Flow & CLI Design

* nested menu systems
* loop-driven navigation (`while(true)`)
* safe exit and back navigation (`0 → go back`)
* null-safe flow handling
* reusable input helper methods
* improved user experience in CLI applications

------------------------------------------------------------------------

# 🛠 Projects

### Hello World

Basic Java program demonstrating compilation and execution.

### Personal Info App

Simple program using variables and output formatting.

### Even/Odd Checker

Uses modulo operator and conditional logic.

### Basic Calculator

Performs arithmetic operations using user input.

### Smart Calculator

Interactive calculator with validation loops and defensive input
handling.

### Smart Calculator Refactor

Refactored version featuring: - modular helper methods - robust input
parsing - enum-driven operations - polymorphic calculation logic

### Student Manager System

Console-based application for managing students.

Features:

* add student
* show students
* edit student (name, age, email)
* remove student
* search student (by name and ID)
* sort students (name, age, ID)
* persistent storage (save/load)
* structured CLI navigation

Implements:

* `ArrayList<Student>` storage
* layered architecture (UI / service / model)
* file persistence layer (`StudentFileService`)
* validation at multiple levels
* defensive programming patterns

Refactored to improve maintainability, scalability, and separation of concerns.

------------------------------------------------------------------------

# 🚀 Upcoming Topics

Planned next steps in the learning journey:

* JSON parsing and data transformation
* Tree structures and recursion
* Building a JSON → HTML parser
* Working with external libraries (Jackson / Gson)
* Improving search algorithms
* Expanding project architecture further

------------------------------------------------------------------------

# 📈 Long-Term Vision

This repository will gradually evolve into a collection of:

* Java learning projects
* refactored code examples
* object-oriented systems
* small applications demonstrating core programming concepts
* real-world style problem-solving projects

------------------------------------------------------------------------

# 👨‍💻 Author

Personal Java learning repository documenting the journey from beginner
to confident Java developer.
