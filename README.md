# My Java Journey ☕

A collection of all my Java exercises and projects.

------------------------------------------------------------------------

## 📁 Progress

-   **[01-Basics](./01-Basics)**: Hello World, Variables, Data Types,
    Scanner, Operators, Conditions, Smart Calculator.
-   **[02-Logic](#)**: Coming soon!

------------------------------------------------------------------------

## 🧠 What I've Learned So Far (Week 1)

### 🔹 How Java Works

-   Java programs are written in `.java` files.
-   Code must be compiled using:

``` bash
javac FileName.java
```

-   This creates a `.class` file (bytecode).
-   The program runs using:

``` bash
java FileName
```

Execution flow:

    .java → javac → .class → JVM → Program runs

------------------------------------------------------------------------

### 🔹 Basic Program Structure

Every Java program starts like this:

``` java
public class ClassName {
    public static void main(String[] args) {
        // Code starts here
    }
}
```

Important rules: - File name must match the public class name. - Java is
case-sensitive. - Every statement ends with `;`.

------------------------------------------------------------------------

### 🔹 Variables & Data Types

I learned to use:

``` java
int age = 25;
double height = 1.75;
String name = "John";
boolean isStudent = true;
```

Key concepts: - Java is strongly typed. - Types must match assigned
values. - `String` cannot be assigned to `int`. - Casting allows type
conversion:

``` java
double result = (double) 5 / 2;
```

------------------------------------------------------------------------

### 🔹 Integer vs Double Division

``` java
int a = 5;
int b = 2;
System.out.println(a / b); // 2
```

Integer division removes decimals.

Using double:

``` java
System.out.println(5.0 / 2); // 2.5
```

------------------------------------------------------------------------

### 🔹 User Input (Scanner)

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

Important: - `nextInt()` does not consume the newline. - Must use an
extra `scanner.nextLine()` when mixing with `nextLine()`.

------------------------------------------------------------------------

### 🔹 Operators

Arithmetic operators:

    +  -  *  /  %

Modulo `%` is used for: - Checking even/odd - Remainders - Divisibility
checks

Example:

``` java
if (num % 2 == 0) {
    System.out.println("Even");
}
```

------------------------------------------------------------------------

### 🔹 Conditional Statements

``` java
if (condition) {
}
else if (condition) {
}
else {
}
```

Important: - `else if` stops checking once a match is found. - Separate
`if` statements check all conditions. - Always include a final `else`
for invalid input.

------------------------------------------------------------------------

### 🔹 String Comparison

Wrong:

``` java
if (operation == "+")
```

Correct:

``` java
if (operation.equals("+"))
```

-   `==` compares memory location.
-   `.equals()` compares actual text content.

------------------------------------------------------------------------

### 🔹 Defensive Programming

Example:

``` java
if (num2 == 0) {
    System.out.println("Cannot divide by zero.");
}
```

Programs should handle invalid input safely.

------------------------------------------------------------------------

## 🛠 Projects Completed

-   Hello World
-   Personal Info App
-   Even/Odd Checker
-   Basic Calculator
-   Smart Calculator with validation and error handling
