import java.util.Scanner;

class smartCalculator {
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    String answer = "yes";

    
    while (answer.equals("yes")) {
    
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
    
    System.out.println("Do you want to continue? (yes/no): ");
    scanner.nextLine(); //clears leftover newline
    
    while (true) {
      answer = scanner.nextLine().toLowerCase();

      if (answer.equals("yes") || answer.equals("no")) {
        break;
      } else {
        System.out.println("Invalid input. Please type yes or no: ");
      }
    }
    }

    scanner.close();
  }
}