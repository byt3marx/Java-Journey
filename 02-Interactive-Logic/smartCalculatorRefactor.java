import java.util.Scanner;

enum Operation {
  ADD,
  SUBTRACT,
  MULTIPLY,
  DIVIDE,
  MODULO;

  public static Operation fromSymbol(String symbol) { //converts user input into Operation
    symbol = symbol.toLowerCase();
    
    switch(symbol) {
      
      case "+":
      case "add":
         return ADD;
      
      case "-":
      case "subtract":
         return SUBTRACT;
      
      case "*":
      case "multiply":
         return MULTIPLY;
      
      case "/":
      case "divide":
         return DIVIDE;
      
      case "%":
      case "modulo":
         return MODULO;
      
      default: return null;
    }
  }
}

class SmartCalculatorRefactor {

  public static double getNumber(Scanner scanner, String prompt) { //prints a prompt and reads double
    while (true) {
      System.out.print(prompt);

      String line = scanner.nextLine().trim();

      try {
        double number = Double.parseDouble(line);
        return number;
      } 
      catch (NumberFormatException e) {
        System.out.println("Invalid number. Try again.");
      }
    }   
  }

  public static Operation getOperation(Scanner scanner) { //repeatedly asks for operation until valid one is entered
      while (true) {
      System.out.print("Choose operation (+, -, *, /, %): ");
      String input = scanner.nextLine().trim();

      Operation op = Operation.fromSymbol(input);

      if(op != null) {
        return op;
      }
      System.out.println("Invalid operation. Try again.");
    }
  }

  public static double calculate(double num1, double num2, Operation op) { //does the calculation
    switch (op) {
      case ADD:
        return num1 + num2;
      case SUBTRACT:
        return num1 - num2;
      case MULTIPLY:
        return num1 * num2;
      case DIVIDE:
        if (num2 == 0) {
          System.out.println("Cannot divide by zero.");
          return Double.NaN;
        } 
        return num1 / num2;
      case MODULO:
        return num1 % num2;
        default: 
          return 0;
    }
  }

  public static String askToContinue(Scanner scanner) { //asks yes/no until valid
    while (true) {
      System.out.print("Do you want to continue? (Yes/No): ");
      String ans = scanner.nextLine().trim().toLowerCase();

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
      Operation op = getOperation(scanner);
      double num2 = getNumber(scanner, "Enter second number: ");

      double result = calculate(num1, num2, op);
      if (!Double.isNaN(result)) {
        System.out.println("Result: " + result);
      }
      
      answer = askToContinue(scanner);
    }
    scanner.close();   
  }
}