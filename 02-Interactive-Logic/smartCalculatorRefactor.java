import java.util.Scanner;

class SmartCalculatorRefactor {

  public static double getNumber(Scanner scanner, String prompt) { //gets the number from user
    System.out.println(prompt);
    return scanner.nextDouble();
  }

  public static String getOperation(Scanner scanner) {
      while (true) {
      System.out.println("Choose operation (+, -, *, /, %): ");
      String op = scanner.next();

      if ("+-*/%".contains(op) && op.length() == 1) {
        return op;
      }
      System.out.println("Invalid operation. Try again.");
    }
  }

  public static double calculate(double num1, double num2, String op) { //does the calculation
    switch (op) {
      case "+":
        return num1 + num2;
      case "-":
        return num1 - num2;
      case "*":
        return num1 * num2;
      case "/":
        if (num2 == 0) {
          System.out.println("Cannot divide by zero.");
          return Double.NaN;
        } 
        return num1 / num2;
      case "%":
        return num1 % num2;
        default: 
          return 0;
    }
  }

  public static String askToContinue(Scanner scanner) {
    scanner.nextLine(); //clear leftover newline from nextDouble/next()
    while (true) {
      System.out.println("Do you want to continue? (Yes/No): ");
      String ans = scanner.nextLine().toLowerCase();

      if(ans.equals("yes") || ans.equals("no")) {
        return ans;
      }
      System.out.println("Invalid input. Please type yes or no.");
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String answer = "yes";

    while(answer.equals("yes")) {
      double num1 = getNumber(scanner, "Enter first number: ");
      String op = getOperation(scanner);
      double num2 = getNumber(scanner, "Enter second number: ");

      double result = calculate(num1, num2, op);
      System.out.println("Result: " + result);

      answer = askToContinue(scanner);

    }
    scanner.close();
    
  }
}