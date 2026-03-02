import java.util.Scanner;

class smartCalculator {
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter first number: ");
    double num1 = scanner.nextDouble();

    System.out.println("Enter second number: ");
    double num2 = scanner.nextDouble();
    scanner.nextLine();

    System.out.println("Choose operation (+, -, *, /, %): ");
    String operation = scanner.next();
    

    if (operation.equals("+")) { //.equals compares actual text content, == compares memory locations
      double sum = num1 + num2;
      System.out.println("The sum of your numbers is: " + sum);
    }
    
    if (operation.equals("-")) {
      double difference = num1 - num2;
      System.out.println("The difference of your numbers is: " + difference);
    }

    if (operation.equals("*")) {
      double product = num1 * num2;
      System.out.println("The product of your numbers is: " + product);
    }

    if (operation.equals("/")) {
      double quotient = num1 / num2;
      System.out.println("The quotient of your numbers is: " + quotient);
    }

    if (operation.equals("%")) {
      double remainder = num1 % num2;
      System.out.println("The remainder of your numbers is: " + remainder);
    }


    scanner.close();
  }
}