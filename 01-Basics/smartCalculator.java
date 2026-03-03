import java.util.Scanner;

class smartCalculator {
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter first number: ");
    double num1 = scanner.nextDouble();

    System.out.println("Choose operation (+, -, *, /, %): ");
    String operation = scanner.next();

    System.out.println("Enter second number: ");
    double num2 = scanner.nextDouble();
       
    if (operation.equals("+")) { //.equals compares actual text content, == compares memory locations
        System.out.println("Result: " + (num1 + num2));
    } 
    else if (operation.equals("-")) {
          System.out.println("Result: " + (num1 - num2));
    } 
    else if (operation.equals("*")) {
        System.out.println("Result: " + (num1 * num2));
    } 
    else if (operation.equals("/")) {
        if (num2 == 0) {
          System.out.println("Cannot divide by zero.");
        } else {
            System.out.println("Result: " + (num1 / num2));
        }
   } 
   else if (operation.equals("%")) {
        System.out.println("Result: " + (num1 % num2));
    } 
    else {
        System.out.println("Invalid operation.");
    }

    scanner.close();
  }
}