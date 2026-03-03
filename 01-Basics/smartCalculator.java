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
        double sum = num1 + num2;
        System.out.println("Result: " + sum);
    } else if (operation.equals("-")) {
        double difference = num1 - num2;
        System.out.println("Result: " + difference);
    } else if (operation.equals("*")) {
        double product = num1 * num2;
        System.out.println("Result: " + product);
    } else if (operation.equals("/")) {
        if (num2 == 0) {
          System.out.println("Cannot divide by zero.");
        } else {
            double quotient = num1 / num2;
            System.out.println("Result: " + quotient);
        }
   } else if (operation.equals("%")) {
        double remainder = num1 % num2;
        System.out.println("Result: " + remainder);
    } else {
        System.out.println("Invalid operation.");
    }

    scanner.close();
  }
}