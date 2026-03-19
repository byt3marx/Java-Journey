import java.util.Scanner;

class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    StudentManager manager = new StudentManager();

    manager.loadFromFile("students.txt");

    boolean running = true;

    while (running) {
      System.out.println("\n--- Student Manager ---");
      System.out.println("1. Add student");
      System.out.println("2. Show students");
      System.out.println("3. Remove student");
      System.out.println("4. Search student by name");
      System.out.println("5. Edit student");
      System.out.println("6. Sort students by name");
      System.out.println("7. Sort students by age");
      System.out.println("8. Exit");
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
          System.out.println("1. Edit name");
          System.out.println("2. Edit age");
          System.out.println("3. Edit both");

          System.out.print("Choose option: ");

          String editChoice = scanner.nextLine();

          switch (editChoice) {
            
            case "1":
              editStudentNameFlow(scanner, manager);
              break;

            case "2":
              editStudentAgeFlow(scanner, manager);
              break;

            case "3":
              editStudentFlow(scanner, manager);
              break;
            
            default:
              System.out.println("Invalid edit option.");
          }
          break;
        
        case "6":
          manager.sortByName();
          manager.showStudents();
          break;

        case "7":
          manager.sortByAge();
          manager.showStudents();
          break;

        case "8":
          manager.saveToFile("students.txt");
          System.out.println("Data saved. Exiting program.");
          running = false;
          break;

        default:
          System.out.println("Invalid option.");
      }
    }

    scanner.close();
  }

  private static void addStudentFlow(Scanner scanner, StudentManager manager) {

    String name = readValidName(scanner, manager, "Enter student name: ");

    int age = readValidAge(scanner, manager, "Enter student age: ");

    manager.addStudent(name, age);
  }

  public static void editStudentNameFlow(Scanner scanner, StudentManager manager) {
    if(!manager.hasStudents()) {
      System.out.println("No students to edit.");
      return;
    }

    manager.showStudents();

    int studentNumber = readValidStudentNumber(scanner, manager, "Enter student number to edit: ");
    String newName = readValidName(scanner, manager, "Enter new student name: ");

    boolean success = manager.editStudentName(studentNumber, newName);

    if (success) {
      System.out.println("Student name updated.");
      manager.showStudents();
    } else {
      System.out.println("Could not update student name.");
    }
  }

  public static void editStudentAgeFlow(Scanner scanner, StudentManager manager) {
    if(!manager.hasStudents()) {
      System.out.println("No students to edit.");
      return;
    }

    manager.showStudents();

    int studentNumber = readValidStudentNumber(scanner, manager, "Enter student number to edit: ");
    int newAge = readValidAge(scanner, manager, "Enter new student age: ");

    boolean success = manager.editStudentAge(studentNumber, newAge);

    if (success) {
      System.out.println("Student age updated.");
      manager.showStudents();
    } else {
      System.out.println("Could not update student age.");
      return;
    }
  }

  private static void editStudentFlow(Scanner scanner, StudentManager manager) {
    if(!manager.hasStudents()) {
      System.out.println("No students to edit.");
      return;
    }
    
    manager.showStudents();

    int studentNumber = readValidStudentNumber(scanner, manager, "Enter student number to edit: ");

    String newName = readValidName(scanner, manager, "Enter new student name: ");

    int newAge = readValidAge(scanner, manager, "Enter new age: ");

    boolean success = manager.editStudent(studentNumber, newName, newAge);

    if (success) {
      System.out.println("Student updated.");
      manager.showStudents();
    } else {
      System.out.println("Could not update student.");
      return;
    }
  }

  private static void removeStudentFlow(Scanner scanner, StudentManager manager) {
    if (!manager.hasStudents()) {
      System.out.println("No students to remove.");
      return;
    }
    
    manager.showStudents();

    int removeStudent = readValidStudentNumber(scanner, manager, "Enter student number to remove: ");

    manager.removeStudent(removeStudent);
  }

  private static void searchStudentFlow(Scanner scanner, StudentManager manager) {
    if (!manager.hasStudents()) {
      System.out.println("No students in the list.");
      return;
    }

    String searchName = readValidName(scanner, manager, "Enter student name to search: ");

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
  
  private static String readValidName(Scanner scanner, StudentManager manager, String prompt) {

    while (true) {
      
      System.out.print(prompt);
      String name = scanner.nextLine().trim();

      if (manager.isValidName(name)) {
        return name;
      }
        
      System.out.println("Invalid name. Only letters allowed.");
      
    }
  }

  private static int readValidStudentNumber(Scanner scanner, StudentManager manager, String prompt) {

    while (true) {
      
      System.out.print(prompt);
      String input = scanner.nextLine();

      try {
        int studentNumber = Integer.parseInt(input);

        if (studentNumber >= 1 && studentNumber <= manager.getStudentCount()) {
          return studentNumber - 1;
        }

        System.out.println("Invalid student number.");

      } catch (NumberFormatException e) {
        System.out.println("Invalid number. Enter a whole number.");
      }
    }
  }

}