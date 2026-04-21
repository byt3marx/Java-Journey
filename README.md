# My Java Journey ☕

A structured collection of my Java exercises and projects as I progress
from beginner to confident programmer.

---

## 📁 Project Structure

```
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
│       ├── StudentTest.java 
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
├── .gitignore 
├── README.md 
├── README_Professional.md

```

---

## 📁 Progress

* **[01-Basics](./01-Basics)**
  Hello World, variables, data types, Scanner, arithmetic operators,
  modulo, conditionals, basic calculator.

* **[02-Interactive-Logic](./02-Interactive-Logic)**
  Smart Calculator with loops, nested validation, defensive
  programming, and structured control flow.

* **[03-OOP-Foundations](./03-OOP-Foundations)**
  Introduction to classes, objects, constructors, encapsulation,
  and object-oriented design principles.

* **[04-Collections](./04-Collections)**
  Working with ArrayLists, sorting data, Comparable vs Comparator,
  and structured data handling.

* **[05-Projects](./05-Projects)**
  Full CLI-based Student Manager System with layered architecture,
  validation, persistence, and real-world structure.

---

# 🧠 Week 1 Complete -- Foundations & Control Flow

## 🔹 How Java Works

* Java source code is written in `.java` files.
* Code must be compiled:

```bash
javac FileName.java
```

* This creates a `.class` file (bytecode).
* The program runs with:

```bash
java FileName
```

### Execution Flow

```
.java → javac → .class → JVM → Program runs
```

---

## 🔹 Program Structure

Every Java program contains:

```java
public class ClassName {
    public static void main(String[] args) {
        // Program starts here
    }
}
```

Key rules:

* File name must match the public class name.
* Java is case-sensitive.
* Every statement ends with `;`.

---

## 🔹 Variables & Data Types

```java
int age = 25;
double height = 1.75;
String name = "John";
boolean isStudent = true;
```

Key concepts:

* Java is strongly typed.
* Types must match assigned values.
* Casting allows type conversion:

```java
double result = (double) 5 / 2;
```

---

## 🔹 Integer vs Double Division

```java
int a = 5;
int b = 2;
System.out.println(a / b); // 2
```

Integer division removes decimals.

```java
System.out.println(5.0 / 2); // 2.5
```

---

## 🔹 User Input (Scanner)

```java
import java.util.Scanner;
Scanner scanner = new Scanner(System.in);
```

Reading input:

```java
String text = scanner.nextLine();
int number = scanner.nextInt();
double value = scanner.nextDouble();
```

Important:

* `nextInt()` and `nextDouble()` do not consume newline.
* Must clear buffer before using `nextLine()`.

---

## 🔹 Operators

```
+  -  *  /  %
```

Modulo `%` is used for:

* Even/odd checks
* Remainders
* Divisibility logic

Example:

```java
if (num % 2 == 0) {
    System.out.println("Even");
}
```

---

## 🔹 Conditional Logic

```java
if (condition) {
}
else if (condition) {
}
else {
}
```

Important:

* `else if` stops checking after a match.
* Separate `if` statements check all conditions.
* Always handle invalid input.

---

## 🔹 Loops

### While Loop

```java
while (condition) {
    // repeat while true
}
```

Used to:

* Repeat calculator logic
* Validate user input
* Build interactive systems

---

## 🔹 Nested Validation Loops

Implemented layered control flow:

* Main loop controls program repetition.
* Inner loop validates operation input.
* Inner loop validates continue input.
* Defensive division handling.
* Case-insensitive input handling.

This structured approach prevents:

* Invalid progression
* Infinite loops
* Crashes
* Poor user experience

---

## 🛠 Projects Completed (Week 1)

* Hello World
* Personal Info App
* Even/Odd Checker
* Basic Calculator
* Smart Calculator (Interactive, validated, loop-driven)

---

# 🧠 Week 2 Complete -- Program Structure

## 🔹 Methods

Programs were refactored into reusable methods.

Concepts learned:

* Method parameters
* Return values
* Helper methods
* Code reuse
* Separation of concerns

Example:

```java
public static double getNumber(Scanner scanner, String prompt) {
    System.out.print(prompt);
    return scanner.nextDouble();
}
```

Benefits:

* Cleaner code
* Easier debugging
* Reusable logic

---

## 🔹 Input Validation & Parsing

Improved input handling by reading full lines and parsing them.

Concept learned:

```java
Double.parseDouble(string)
```

Pattern implemented:

1. Read full input line
2. Attempt to parse
3. Catch invalid input

Example:

```java
try {
    double number = Double.parseDouble(line);
}
catch (NumberFormatException e) {
    System.out.println("Invalid number.");
}
```

Advantages:

* Eliminates Scanner buffer issues
* Robust input validation
* Cleaner control flow

---

# 🧠 Week 3 Complete -- Enums & Polymorphism

## 🔹 Enums

Used enums to represent fixed operation types.

Example:

```java
enum Operation {
    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE,
    MODULO
}
```

Benefits:

* Prevents invalid values
* Improves readability
* Replaces fragile string comparisons

---

## 🔹 Mapping User Input to Enums

Created a factory-style method to convert user input to enum values.

```java
public static Operation fromSymbol(String symbol)
```

Responsibilities:

* Normalize input
* Validate symbols
* Return the correct enum value

---

## 🔹 Polymorphism with Enums

Each operation now contains its own behavior.

Example:

```java
ADD {
    public double apply(double a, double b) {
        return a + b;
    }
}
```

Benefits:

* Eliminates large `switch` statements
* Each operation handles its own logic
* Cleaner, object-oriented design

---

## 🔹 Handling Invalid Mathematical Results

Used `Double.NaN` to represent undefined results such as division by zero.

Important concept:

```java
Double.isNaN(value)
```

Used to detect invalid results without crashing the program.

---

## 🛠 Projects Completed (Weeks 2–3)

* Smart Calculator
* Smart Calculator Refactor

Features implemented:

* Modular program design
* Helper methods
* Input parsing
* Defensive programming
* Enum-driven operations
* Polymorphic calculation logic
* Cleaner program architecture

---

# 🧠 Week 4 Complete -- Object-Oriented Programming & Student Manager

## 🔹 Classes and Objects

Introduced object-oriented programming through custom classes.

Concepts learned:

* defining classes  
* creating objects  
* fields and constructors  
* object state  

Example:

```java
Student s1 = new Student("Anna", 20);
```

---

## 🔹 Constructors and `this`

Used constructors to initialize object data.

```java
public Student(String name, int age) {
    this.name = name;
    this.age = age;
}
```

---

## 🔹 Encapsulation

Fields made private and accessed via getters and setters.

```java
private String name;
private int age;
```

---

## 🔹 Object References & Memory

```java
Student s1 = new Student("Anna", 20);
Student s2 = s1;
```

---

## 🔹 toString() Method

```java
public String toString() {
    return name + " (" + age + ")";
}
```

---

## 🔹 ArrayList & Collections

```java
ArrayList<Student> students = new ArrayList<>();
```

---

## 🔹 Index vs User Input

```java
index = input - 1;
```

---

## 🔹 Student Manager System

Features:

* Add student  
* Show students  
* Edit student  
* Remove student  
* Search student  

---

## 🔹 Program Architecture

* Main → UI  
* StudentManager → logic  
* Student → model  

---

## 🔹 Input Validation Refactor

```java
readValidName(...)
readValidAge(...)
readValidStudentNumber(...)
```

---

## 🔹 Layered Validation

* UI validation  
* logic validation  
* model protection  

---

## 🔹 Input Sanitization

```java
name = name.trim();
```

---

# 🧠 Week 5 Complete -- File I/O & Data Persistence

## 🔹 File Writing (Saving Data)

Implemented saving application state to a file.

Concepts learned:

* `FileWriter`
* Writing structured data (CSV format)
* Handling exceptions (`IOException`)
* `try-with-resources` for automatic resource management

Example:

```java
try (FileWriter writer = new FileWriter(path)) {
    writer.write(data);
}
```

---

## 🔹 File Reading (Loading Data)

Implemented reading data from file and reconstructing objects.

Concepts learned:

* `Scanner` with files
* Reading line-by-line
* Parsing structured input
* Handling missing or corrupted data

---

## 🔹 Data Format (CSV)

Used a structured format:

```
id,name,age,email
```

Concept:

* Convert objects → text (save)
* Convert text → objects (load)

---

## 🔹 Robust File Handling

Improved resilience by:

* Skipping invalid lines
* Catching parsing errors
* Preventing crashes from corrupted data

```java
catch (IllegalArgumentException e) {
    System.out.println("Skipping invalid line");
}
```

---

## 🔹 Temporary List Pattern

Prevented data loss during loading:

* Load into temporary list
* Replace main list only after successful read

Concept:

```
load → temp list → assign → safe state
```

---

# 🧠 Week 6 Complete -- Architecture & Separation of Concerns

## 🔹 Package Structure

Refactored project into packages:

```
model → data representation  
service → business logic  
ui → user interaction  
```

Benefits:

* Clean separation of responsibilities
* Improved scalability
* Avoided class naming conflicts

---

## 🔹 Layered Architecture

Final structure:

* **Model** → validation + data integrity
* **Manager** → logic + coordination
* **UI** → input/output
* **FileService** → persistence

---

## 🔹 StudentFileService (New Layer)

Extracted file logic into dedicated class:

```java
public class StudentFileService
```

Responsibilities:

* Save students to file
* Load students from file
* Return data instead of mutating state

---

## 🔹 Dependency Usage

StudentManager now uses:

```java
private StudentFileService fileService;
```

Concept learned:

* Dependency composition
* Separation of responsibilities
* Cleaner method delegation

---

## 🔹 Data Ownership

Clarified responsibilities:

* StudentManager owns:

    * student list
    * nextId

* FileService handles:

    * file read/write

---

# 🧠 Week 7 Complete -- Validation & Defensive Programming

## 🔹 Model-Level Validation

Moved validation into the `Student` class.

```java
setName(...)
setAge(...)
setEmail(...)
```

Concept:

* Model protects its own state
* Invalid objects cannot be created

---

## 🔹 Exception-Based Validation

Used:

```java
throw new IllegalArgumentException(...)
```

Benefits:

* Immediate failure on invalid data
* Cleaner logic in manager layer

---

## 🔹 Centralized Validation Rules

Created reusable methods:

```java
isValidName(...)
isValidAge(...)
isValidEmail(...)
```

---

## 🔹 Email Validation

Implemented structured validation:

* exactly one `@`
* dot after `@`
* no blank values
* trimmed input

---

## 🔹 Layered Validation Strategy

* UI → user-friendly loops
* Manager → flow control
* Model → final protection

---

# 🧠 Week 8 Complete -- Advanced Control Flow & UX

## 🔹 Menu Loop Design

Converted menus into loop-driven systems:

```java
while (true)
```

---

## 🔹 Navigation Control

Implemented:

* `0 → go back`
* nested menu systems
* safe exit flow

---

## 🔹 Null-Safe Flow Handling

Handled navigation signals:

```java
if (selectedStudent == null) return;
```

---

## 🔹 Atomic Operations

Ensured safe updates:

* edit both fields → all or nothing
* prevented partial state changes

---

## 🔹 Clean CLI UX

Improvements:

* consistent prompts
* validation feedback
* reusable input helpers

---

## 🛠 Final Project Capabilities

Student Manager System now supports:

* Add students
* Edit name, age, email
* Edit multiple fields safely
* Remove students
* Search students (name & ID)
* Sort students (name, age, ID)
* Persistent storage (save/load)
* Structured CLI navigation

---

## 🚀 Next Focus

* JSON parsing & transformation
* Data structure traversal (trees)
* Recursion
* Building a JSON → HTML parser
* Working with external libraries (Jackson / Gson)

---