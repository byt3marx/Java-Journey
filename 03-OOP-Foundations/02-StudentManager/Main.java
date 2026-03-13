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
      System.out.print("Choose option: ");

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

    String name;

    while (true) {

      System.out.print("Enter student name: ");
      name = scanner.nextLine();

      if (manager.isValidName(name)){
          break;
      }
      
      System.out.println("Invalid name. Only letters allowed.");
    }

    int age = readValidAge(scanner, manager, "Enter student age: ");

    manager.addStudent(name, age);
  }

  private static void editStudentFlow(Scanner scanner, StudentManager manager) {
    if(!manager.hasStudents()) {
      System.out.println("No students to edit.");
      return;
    }
    
    manager.showStudents();

    int studentNumber;

    while (true) {
      System.out.print("Enter student number to edit: ");
      String editInput = scanner.nextLine();

      try {
        studentNumber = Integer.parseInt(editInput);

        if (studentNumber >= 1 && studentNumber <= manager.getStudentCount()) {
          break;
        }

        System.out.println("Invalid student number.");
      } catch (NumberFormatException e) {
        System.out.println("Invalid number. Enter whole number.");
      }
    }

    String newName;

    while (true) {
      System.out.print("Enter new name: ");
      newName = scanner.nextLine();

      if (manager.isValidName(newName)) {
        break;
      }

      System.out.println("Invalid name. Only letters allowed.");
    }

    int newAge;

    while (true) {
      System.out.print("Enter new age: ");
      String newAgeInput = scanner.nextLine();

      try {
        newAge = Integer.parseInt(newAgeInput);

        if (manager.isValidAge(newAge)) {
          break;
        }

        System.out.println("Invalid age. Age must be between 0 and 120.");

      } catch (NumberFormatException e) {
        System.out.println("Invalid age. Enter whole number.");
      }
    }

    manager.editStudent(studentNumber - 1, newName, newAge);
  }

  private static void removeStudentFlow(Scanner scanner, StudentManager manager) {
    if (!manager.hasStudents()) {
      System.out.println("No students to remove.");
      return;
    }
    
    manager.showStudents();

    int removeStudent;

    while (true) {
      System.out.print("Enter student number to remove: ");
      String removeStudentInput = scanner.nextLine();

      try {
        removeStudent = Integer.parseInt(removeStudentInput);

        if (removeStudent >= 1 && removeStudent <= manager.getStudentCount()) {
          break;
        }

        System.out.println("Invalid student number.");

      } catch (NumberFormatException e) {
        System.out.println("Invalid number.");
      }
    }

    manager.removeStudent(removeStudent - 1);
  }

  private static void searchStudentFlow(Scanner scanner, StudentManager manager) {
    if (!manager.hasStudents()) {
      System.out.println("No students in the list.");
      return;
    }

    String searchName;

    while (true) {
      System.out.print("Enter student name to search: ");
      searchName = scanner.nextLine();

      if (manager.isValidName(searchName)) {
        break;
      }
      
      System.out.println("Invalid name. Only letters allowed.");
    }

    manager.findStudentByName(searchName);
  }

  private static int readValidAge(Scanner scanner, StudentManager manager, String prompt) {

    while (true) {

      System.out.print(prompt);
      String ageInput = scanner.nextLine();

      try {
        int age = Integer.parseInt(ageInput);

        if (manager.isValidAge(age)) {
          return age;
        }

        System.out.println("Age must be between 0 and 120.");

      } catch (NumberFormatException e) {
        System.out.println("Invalid age. Enter a number.");
      }
    }
  }
}