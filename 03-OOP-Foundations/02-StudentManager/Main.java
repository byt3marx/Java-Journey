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
      System.out.println("4. Search student by name");
      System.out.println("5. Edit student");
      System.out.println("6. Exit");
      System.out.println("Choose option: ");

      String input = scanner.nextLine();

      switch (input) {
        case "1":
          addStudentFlow(scanner, manager);
          break;
        
        case "2":
          manager.showStudents();
          break;

        case "3":
          removeStudentFlow(scanner, manager);
          break;

        case "4":
          searchStudentFlow(scanner, manager);
          break;

        case "5":
          editStudentFlow(scanner, manager);
          break;

        case "6":
          running = false;
          System.out.println("Program ended.");
          break;

        default:
          System.out.println("Invalid option.");
      }
    }

    scanner.close();
  }

  private static void addStudentFlow(Scanner scanner, StudentManager manager) {
    
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
  }

  private static void editStudentFlow(Scanner scanner, StudentManager manager) {
    manager.showStudents();

    System.out.println("Enter student number to edit: ");
    String editInput = scanner.nextLine();

    try {
      int studentNumber = Integer.parseInt(editInput);
      System.out.println("Enter new name: ");
      String newName = scanner.nextLine();

      System.out.println("Enter new age: ");
      String newAgeInput = scanner.nextLine();

      int newAge = Integer.parseInt(newAgeInput);

      manager.editStudent(studentNumber - 1, newName, newAge);

    } catch (NumberFormatException e) {
      System.out.println("Invalid number.");
    }
  }

  private static void removeStudentFlow(Scanner scanner, StudentManager manager) {
    manager.showStudents();

    System.out.println("Enter student number to remove: ");
    String removeInput = scanner.nextLine();

    try {
      int studentNumber = Integer.parseInt(removeInput);
      manager.removeStudent(studentNumber - 1);
    } catch (NumberFormatException e) {
      System.out.println("Invalid number.");
    }

  }

  private static void searchStudentFlow(Scanner scanner, StudentManager manager) {
    System.out.println("Enter student name to search: ");
    String searchName = scanner.nextLine();

    manager.findStudentByName(searchName);
  }
}