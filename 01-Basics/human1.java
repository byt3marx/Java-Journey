import java.util.Scanner;

//asks the user for information and then outputs the information in a sentence.
class human1 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter your name: ");
    String name = scanner.nextLine();

    System.out.println("Enter your age: ");
    int age = scanner.nextInt();
    scanner.nextLine();

    System.out.println("Enter your favourite color: ");
    String color = scanner.nextLine();

    System.out.println("Enter your height: ");
    double height = scanner.nextDouble();

    System.out.println("Enter your favourite number: ");
    int favNumber = scanner.nextInt();

    System.out.println("Hello " + name + ", you are " + age + " years old and your favourite color is " + color + ".");
    System.out.println("You are " + height + " cm tall, and your favourite number is " + favNumber + ".");
    
    scanner.close();
  }
}