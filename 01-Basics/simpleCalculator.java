import java.util.Scanner;

//simple even or odd calculator
class simpleCalculator {
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter your number: ");
    int num = scanner.nextInt();

    if (num % 2 == 0) {
      System.out.println("The number is even.");
    } else {
      System.out.println("The number is odd.");
    }

    scanner.close();
  }
}