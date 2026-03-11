import java.util.ArrayList;

class StudentManager {

  private ArrayList<Student> students;

  public StudentManager() {
    students = new ArrayList<>();
  }

  private boolean isValidName(String name) {
    return name != null && !name.isBlank() && name.matches("[\\p{L} .'-]+");
  }

  public void addStudent(String name, int age) {
    if (!isValidName(name)) {
      System.out.print("Invalid name. Use letters and spaces only.");
      return;
    }

    if (age < 0 || age > 120) {
      System.out.println("Invalid age.");
      return;
    }

    Student student = new Student(name, age);
    students.add(student);
    System.out.println("Student added.");
  }

  public void showStudents() {
    if(students.isEmpty()) {
      System.out.println("No students in the list.");
      return;
    }

    for (int i = 0; i < students.size(); i++) {
      System.out.println((i + 1) + ". " + students.get(i));
    }
  }

  public void findStudentByName(String name) {
      
      boolean found = false;

      for (Student s : students) {
        if (s.getName().equalsIgnoreCase(name)) {
          System.out.println("Found: " + s);
          found = true;
        }
      }
      if (!found) {
        System.out.println("Student not found.");
      }
    }

  public void editStudent(int index, String newName, int newAge) {
    if (students.isEmpty()) {
      System.out.println("No students to edit.");
      return;
    }

    if (index < 0 || index >= students.size()) {
      System.out.println("Invalid student number.");
      return;
    }

    if (!isValidName(newName)) {
      System.out.println("Invalid name. Use letters and spaces only.");
      return;
    }

    if (newAge < 0 || newAge > 120) {
      System.out.println("Invalid age.");
      return;
    }

    Student student = students.get(index);
    student.setName(newName);
    student.setAge(newAge);

    System.out.println("Updated: " + student);
  }

  public void removeStudent(int index) {

    if (students.isEmpty()) {
      System.out.println("No students to remove.");
      return;
    }

    if (index < 0 || index >= students.size()) {
      System.out.println("Invalid student number.");
      return;
    }

    Student removedStudent = students.remove(index);
    System.out.println("Removed: " + removedStudent);
  }
}