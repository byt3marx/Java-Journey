import java.util.Scanner;

//simple calculator, takes two numbers and does the four basic calculation
class Calculator {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter first number: ");
    double num1 = scanner.nextDouble();

    System.out.println("Enter second number: ");
    double num2 = scanner.nextDouble();

    double sum = num1 + num2;
    double difference = num1 - num2;
    double product = num1 * num2;
    double quotient = num1 / num2;
    double remainder = num1 % num2;

    System.out.println("Sum: " + sum);
    System.out.println("Difference: " + difference);
    System.out.println("Product: " + product);
    System.out.println("Quotient: " + quotient);
    System.out.println("Remainder: " + remainder);

    scanner.close();
    
  }
}