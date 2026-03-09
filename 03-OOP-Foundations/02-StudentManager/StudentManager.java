import java.util.ArrayList;

class StudentManager {

  private ArrayList<Student> students;

  public StudentManager() {
    students = new ArrayList<>();
  }

  public void addStudent(String name, int age) {
    Student student = new Student(name, age);
    students.add(student);
    System.out.println("Student added.");
  }

  public void showStudent() {
    if(students.isEmpty()) {
      System.out.println("No students in the list.");
      return;
    }

    for (int i = 0; i < students.size(); i++) {
      System.out.println((i + 1) + ". " + students.get(i));
    }
  }

  public void removeStudent(int index) {
    if (index < 0 || index >= students.size()) {
      System.out.println("Invalid student number.");
      return;
    }

    Student removedStudent = students.remove(index);
    System.out.println("Removed: " + removedStudent);
  }
}