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
└── README.md
```

---

## 📁 Progress

* **[01-Basics](./01-Basics)**
  Hello World, variables, data types, Scanner, arithmetic operators,
  modulo, conditionals, basic calculator.

* **[02-Interactive-Logic](./02-Interactive-Logic)**
  Smart Calculator with loops, nested validation, defensive
  programming, and structured control flow.

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

## 🚀 Next Focus

* Classes and objects
* Constructors
* Encapsulation
* Collections (`ArrayList`)
* Object-based program design

---

Week 3 complete.
