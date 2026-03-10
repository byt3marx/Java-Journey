import java.util.Scanner;

class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    StudentManager manager = new StudentManager();

    boolean running = true;

    while (running) {
      System.out.println("\n--- Student Manager ---");
      System.out.println("1. Add student");
      System.out.println("2. Show students");
      System.out.println("3. Remove student");
      System.out.println("4. Exit");
      System.out.println("Choose option: ");

      String input = scanner.nextLine();

      switch (input) {
        case "1":
          System.out.println("Enter student name: ");
          String name = scanner.nextLine();

          System.out.println("Enter student age: ");
          String ageInput = scanner.nextLine();

          try {
            int age = Integer.parseInt(ageInput);
            manager.addStudent(name, age);
          } catch (NumberFormatException e) {
            System.out.println("Invalid age. Please enter a whole number.");
          }
          break;
        
        case "2":
          manager.showStudents();
          break;

        case "3":
          manager.showStudents();

          System.out.println("Enter student number to remove: ");
          String removeInput = scanner.nextLine();

          try {
            int studentNumber = Integer.parseInt(removeInput);
            manager.removeStudent(studentNumber -1);
          } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
          }
          break;

        case "4":
          running = false;
          System.out.println("Program ended.");
          break;

        default:
          System.out.println("Invalid option.");
      }
    }

    scanner.close();
  }
}