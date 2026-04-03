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
          searchStudentsFlow(scanner, manager);
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
              editStudentBothFlow(scanner, manager);
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

    int studentNumber = readValidStudentNumber(scanner, manager, "Enter student number to edit: ");
    String newName = readValidName(scanner, manager, "Enter new student name: ");

    boolean success = manager.editStudentName(studentNumber, newName);

    if (success) {
        Student updatedStudent = manager.getStudent(studentNumber);
      System.out.println("Student name updated: " + updatedStudent);
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

    int studentNumber = readValidStudentNumber(scanner, manager, "Enter student number to edit: ");
    int newAge = readValidAge(scanner, manager, "Enter new student age: ");

    boolean success = manager.editStudentAge(studentNumber, newAge);

    if (success) {
        Student updatedStudent = manager.getStudent(studentNumber);
      System.out.println("Student age updated: " + updatedStudent);
    } else {
      System.out.println("Student age could not be updated.");
      return;
    }
  }

  private static void editStudentBothFlow(Scanner scanner, StudentManager manager) {
    if(!manager.hasStudents()) {
      System.out.println("No students to edit.");
      return;
    }
    
    manager.showStudents();

    int studentNumber = readValidStudentNumber(scanner, manager, "Enter student number to edit: ");

    String newName = readValidName(scanner, manager, "Enter new student name: ");

    int newAge = readValidAge(scanner, manager, "Enter new student age: ");

    boolean success = manager.editStudentBoth(studentNumber, newName, newAge);

    if (success) {
        Student updatedStudent = manager.getStudent(studentNumber);
      System.out.println("Student updated: " + updatedStudent);
    } else {
      System.out.println("Student could not be updated.");
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

    Student removedStudent = manager.removeStudent(removeStudent);

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

    if (result.size() == 0) {
      System.out.println("No students found.");
      return;
    }

    while (true) {

        if (result.size() == 1) {
            selectedStudent = result.get(0);
        } else {
            System.out.println("Found " + result.size() + " students:");

            for (int i = 0; i < result.size(); i++) {
                System.out.println((i + 1) + ". " + result.get(i));
            }

            selectedStudent = readValidIdFromList(scanner, result, "Enter student ID: ");

        }

        boolean backToSelection = false;

        while (true) {

    System.out.println("\nSelected student: " + selectedStudent);

    System.out.println("1. Edit");
    System.out.println("2. Remove");
    System.out.println("3. Back");

    int actionChoice = readValidChoice(scanner, 3, "Choose option: ");

    int index = manager.getIndexOfStudent(selectedStudent);

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
                    boolean success = manager.editStudentName(index, newName);

                    if (success) {
                        System.out.println("Student name updated: " + selectedStudent);
                        return;
                    } else {
                        System.out.println("Could not update student name.");
                    }
                }
                break;

                case 2: {
                    int newAge = readValidAge(scanner, manager, "Enter new student age: ");
                    boolean success = manager.editStudentAge(index, newAge);

                    if (success) {
                        System.out.println("Student age updated: " + selectedStudent);
                        return;
                    } else {
                        System.out.println("Could not update student age.");
                    }
                }
                break;

                case 3: {
                    String newName = readValidName(scanner, manager, "Enter new student name: ");
                    int newAge = readValidAge(scanner, manager, "Enter new student age: ");

                    boolean success = manager.editStudentBoth(index, newName, newAge);

                    if (success) {
                        System.out.println("Student updated: " + selectedStudent);
                        return;
                    } else {
                        System.out.println("Could not update student.");
                    }
                }
                break;

                case 4:
                    break;
            }
            break;

        case 2:
            Student removedStudent = manager.removeStudent(index);

            if (removedStudent != null) {
                System.out.println("Student removed: " + removedStudent);
            } else {
                System.out.println("Student could not be removed.");
            }
            return;

        case 3:
            if (result.size() == 1) {
                return;
            }  else {
                backToSelection = true;
            }
            break;
        }

        if (backToSelection) {
            break;
        }
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
              System.out.println("Student with this ID does not exist");
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