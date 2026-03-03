# My Java Journey ☕

A structured collection of my Java exercises and projects as I progress
from beginner to confident programmer.

------------------------------------------------------------------------

## 📁 Project Structure

    My-Java-Journey/
    │
    ├── 01-Basics/
    │   ├── HelloWorld.java
    │   ├── VariablesDemo.java
    │   ├── EvenOdd.java
    │   ├── BasicCalculator.java
    │
    ├── 02-Interactive-Logic/
    │   ├── SmartCalculator.java
    │
    └── README.md

------------------------------------------------------------------------

## 📁 Progress

-   **[01-Basics](./01-Basics)**\
    Hello World, variables, data types, Scanner, arithmetic operators,
    modulo, conditionals, basic calculator.

-   **[02-Interactive-Logic](./02-Interactive-Logic)**\
    Smart Calculator with loops, nested validation, defensive
    programming, and structured control flow.

------------------------------------------------------------------------

# 🧠 Week 1 Complete -- Foundations & Control Flow

## 🔹 How Java Works

-   Java source code is written in `.java` files.
-   Code must be compiled:

``` bash
javac FileName.java
```

-   This creates a `.class` file (bytecode).
-   The program runs with:

``` bash
java FileName
```

### Execution Flow

    .java → javac → .class → JVM → Program runs

------------------------------------------------------------------------

## 🔹 Program Structure

Every Java program contains:

``` java
public class ClassName {
    public static void main(String[] args) {
        // Program starts here
    }
}
```

Key rules: - File name must match the public class name. - Java is
case-sensitive. - Every statement ends with `;`.

------------------------------------------------------------------------

## 🔹 Variables & Data Types

``` java
int age = 25;
double height = 1.75;
String name = "John";
boolean isStudent = true;
```

Key concepts: - Java is strongly typed. - Types must match assigned
values. - Casting allows type conversion:

``` java
double result = (double) 5 / 2;
```

------------------------------------------------------------------------

## 🔹 Integer vs Double Division

``` java
int a = 5;
int b = 2;
System.out.println(a / b); // 2
```

Integer division removes decimals.

``` java
System.out.println(5.0 / 2); // 2.5
```

------------------------------------------------------------------------

## 🔹 User Input (Scanner)

``` java
import java.util.Scanner;
Scanner scanner = new Scanner(System.in);
```

Reading input:

``` java
String text = scanner.nextLine();
int number = scanner.nextInt();
double value = scanner.nextDouble();
```

Important: - `nextInt()` and `nextDouble()` do not consume newline. -
Must clear buffer before using `nextLine()`.

------------------------------------------------------------------------

## 🔹 Operators

    +  -  *  /  %

Modulo `%` is used for: - Even/odd checks - Remainders - Divisibility
logic

Example:

``` java
if (num % 2 == 0) {
    System.out.println("Even");
}
```

------------------------------------------------------------------------

## 🔹 Conditional Logic

``` java
if (condition) {
}
else if (condition) {
}
else {
}
```

Important: - `else if` stops checking after a match. - Separate `if`
statements check all conditions. - Always handle invalid input.

------------------------------------------------------------------------

## 🔹 Loops

### While Loop

``` java
while (condition) {
    // repeat while true
}
```

Used to: - Repeat calculator logic - Validate user input - Build
interactive systems

------------------------------------------------------------------------

## 🔹 Nested Validation Loops

Implemented layered control flow:

-   Main loop controls program repetition.
-   Inner loop validates operation input.
-   Inner loop validates continue input.
-   Defensive division handling.
-   Case-insensitive input handling.

This structured approach prevents: - Invalid progression - Infinite
loops - Crashes - Poor user experience

------------------------------------------------------------------------

## 🛠 Projects Completed (Week 1)

-   Hello World
-   Personal Info App
-   Even/Odd Checker
-   Basic Calculator
-   Smart Calculator (Interactive, validated, loop-driven)

------------------------------------------------------------------------

## 🚀 Next Focus

-   Switch statements
-   Advanced control flow
-   Cleaner structural design
-   Modular thinking (methods)
-   Menu-based systems

------------------------------------------------------------------------

Week 1 complete.
