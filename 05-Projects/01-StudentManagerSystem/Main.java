import java.util.List;
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
      System.out.println("4. Search students by name");
      System.out.println("5. Find student by ID");
      System.out.println("6. Edit student");
      System.out.println("7. Sort students by name");
      System.out.println("8. Sort students by age");
      System.out.println("9. Exit");
      System.out.print("Choose option: ");

      String input = scanner.nextLine();

      switch (input) {
        case "1":
          addStudentFlow(scanner, manager);
          break;
        
        case "2":
          manager.sortById();
          manager.showStudents();
          break;

        case "3":
          removeStudentFlow(scanner, manager);
          break;

        case "4":
          searchStudentsFlow(scanner, manager);
          break;

        case "5":
          findStudentByIdFlow(scanner, manager);
          break;

        case "6":
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
              editStudentBothFlow(scanner, manager);
              break;
            
            default:
              System.out.println("Invalid edit option.");
          }
          break;
        
        case "7":
          manager.sortByName();
          manager.showStudents();
          break;

        case "8":
          manager.sortByAge();
          manager.showStudents();
          break;

        case "9":
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

    Student addedStudent = manager.addStudent(name, age);

    if (addedStudent != null) {
      System.out.println("Student added: " + addedStudent);
    } else {
      System.out.println("Student could not be added.");
    }
  }

  public static void editStudentNameFlow(Scanner scanner, StudentManager manager) {
    if(!manager.hasStudents()) {
      System.out.println("No students to edit.");
      return;
    }

    manager.showStudents();

    Student selectedStudent = readValidId(scanner, manager, "Enter student ID to edit: ");
    String newName = readValidName(scanner, manager, "Enter new student name: ");

    boolean success = manager.editStudentNameById(selectedStudent.getId(), newName);

    if (success) {
      System.out.println("Student name updated: " + selectedStudent);
    } else {
      System.out.println("Student name could not be updated.");
    }
  }

  public static void editStudentAgeFlow(Scanner scanner, StudentManager manager) {
    if(!manager.hasStudents()) {
      System.out.println("No students to edit.");
      return;
    }

    manager.showStudents();

    Student selectedStudent = readValidId(scanner, manager, "Enter student ID to edit: ");
    int newAge = readValidAge(scanner, manager, "Enter new student age: ");

    boolean success = manager.editStudentAgeById(selectedStudent.getId(), newAge);

    if (success) {
      System.out.println("Student age updated: " + selectedStudent);
    } else {
      System.out.println("Student age could not be updated.");
    }
  }

  private static void editStudentBothFlow(Scanner scanner, StudentManager manager) {
    if(!manager.hasStudents()) {
      System.out.println("No students to edit.");
      return;
    }
    
    manager.showStudents();

    Student selectedStudent = readValidId(scanner, manager, "Enter student ID to edit: ");

    String newName = readValidName(scanner, manager, "Enter new student name: ");

    int newAge = readValidAge(scanner, manager, "Enter new student age: ");

    boolean success = manager.editStudentBothById(selectedStudent.getId(), newName, newAge);

    if (success) {
      System.out.println("Student updated: " + selectedStudent);
    } else {
      System.out.println("Student could not be updated.");
    }
  }

  private static void removeStudentFlow(Scanner scanner, StudentManager manager) {
    if (!manager.hasStudents()) {
      System.out.println("No students to remove.");
      return;
    }
    
    manager.showStudents();

    Student studentToRemove = readValidId(scanner, manager, "Enter student ID to remove: ");

    Student removedStudent = manager.removeStudentById(studentToRemove.getId());

    if (removedStudent != null) {
      System.out.println("Student removed: " + removedStudent);
    } else {
      System.out.println("Student could not be removed.");
    }
  }

  private static void searchStudentsFlow(Scanner scanner, StudentManager manager) {
    if (!manager.hasStudents()) {
      System.out.println("No students in the list.");
      return;
    }

    String searchName = readValidName(scanner, manager, "Enter name: ");

    List<Student> result = manager.findStudentsByName(searchName);

    Student selectedStudent;

    if (result.isEmpty()) {
      System.out.println("No students found.");
      return;
    }

    while (true) {

        if (result.size() == 1) {
            selectedStudent = result.get(0);
        } else {
            System.out.println("Found " + result.size() + " students:");

            for (int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i));
            }

            selectedStudent = readValidIdFromList(scanner, result, "Enter student ID: ");

        }

        boolean goBack = handleSelectedStudentMenu(scanner, manager, selectedStudent);

        if (goBack) {
            if(result.size() == 1) {
                return;
            } else {
                continue; //go back to result selection
            }
        } else {
            return; //edit/remove finished
        }
    }
  }

  private static void findStudentByIdFlow (Scanner scanner, StudentManager manager) {
      if (!manager.hasStudents()) {
          System.out.println("No students in the list.");
          return;
      }

      manager.showStudents();

      Student selectedStudent = readValidId(scanner, manager, "Enter student ID: ");

      handleSelectedStudentMenu(scanner, manager, selectedStudent);
    }

  private static boolean handleSelectedStudentMenu(Scanner scanner, StudentManager manager, Student selectedStudent) {

      while (true) {
          System.out.println("\nSelected student: " + selectedStudent);

          System.out.println("1. Edit student");
          System.out.println("2. Remove student");
          System.out.println("3. Back");

          int actionChoice = readValidChoice(scanner, 3, "Choose option: ");

          switch (actionChoice) {

              case 1:
                  System.out.println("\nEditing student: " + selectedStudent);

                  System.out.println("1. Edit name");
                  System.out.println("2. Edit age");
                  System.out.println("3. Edit both");
                  System.out.println("4. Back");

                  int editChoice = readValidChoice(scanner, 4, "Choose option: ");

                  switch (editChoice) {

                      case 1: {
                          String newName = readValidName(scanner, manager, "Enter new student name: ");
                          boolean success = manager.editStudentNameById(selectedStudent.getId(), newName);

                          if (success) {
                              System.out.println("Student name updated: " + selectedStudent);
                              return false;
                          } else {
                              System.out.println("Student name could not be updated.");
                          }
                      }
                      break;

                      case 2: {
                          int newAge = readValidAge(scanner, manager, "Enter new student age: ");
                          boolean success = manager.editStudentAgeById(selectedStudent.getId(), newAge);

                          if (success) {
                              System.out.println("Student age updated: " + selectedStudent);
                              return false;
                          } else {
                              System.out.println("Student age could not be updated.");
                          }
                      }
                      break;

                      case 3: {
                          String newName = readValidName(scanner, manager, "Enter new student name: ");
                          int newAge = readValidAge(scanner, manager, "Enter new student age: ");
                          boolean success = manager.editStudentBothById(selectedStudent.getId(), newName, newAge);

                          if (success) {
                              System.out.println("Student updated: " + selectedStudent);
                              return false;
                          } else {
                              System.out.println("Student could not be updated.");
                          }
                      }
                      break;

                      case 4:
                          break;
                  }
                  break;

              case 2:
                  Student removedStudent = manager.removeStudentById(selectedStudent.getId());

                  if (removedStudent != null) {
                      System.out.println("Student removed: " + removedStudent);
                  } else {
                      System.out.println("Student could not be removed.");
                  }
                  return false;

              case 3:
                  return true;
          }
      }
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

  private static int readValidChoice(Scanner scanner, int max, String prompt) {

      while (true) {
          System.out.print(prompt);
          String input = scanner.nextLine();

          try {
              int choice = Integer.parseInt(input);

              if (choice >= 1 && choice <= max) {
                  return choice;
              }

              System.out.println("Invalid choice. Enter a number between 1 and " + max + ".");

          } catch (NumberFormatException e) {
              System.out.println("Invalid number. Enter whole number.");
          }
      }
  }

  private static Student readValidId(Scanner scanner, StudentManager manager, String prompt) {

      while (true) {
          System.out.print(prompt);
          String input = scanner.nextLine();

          try {
              int id = Integer.parseInt(input);
              Student student = manager.findStudentById(id);

              if(student != null) {
                  return student;
              }
              System.out.println("Student with this ID does not exist.");
          } catch (NumberFormatException e) {
              System.out.println("Invalid number. Enter whole number.");
          }
      }
  }

  private static Student readValidIdFromList(Scanner scanner, List<Student> result, String prompt) {

      while (true) {
          System.out.print(prompt);
          String input = scanner.nextLine();

          try {
              int id = Integer.parseInt(input);

              for (Student s : result) {
                  if (s.getId() == id) {
                      return s;
                  }
              }

              System.out.println("Student with this ID is not in the search results.");

              } catch (NumberFormatException e) {
                System.out.println("Invalid number. Enter whole number.");
          }
      }
  }

}