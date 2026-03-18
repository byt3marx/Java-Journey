import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class StudentManager {

  private ArrayList<Student> students;

  public StudentManager() {
    students = new ArrayList<>();
  }

  public boolean isValidName(String name) {
    return name != null && !name.isBlank() && name.matches("[\\p{L} .'-]+");
  }

  public boolean isValidAge(int age) {
    return age >= 0 && age <= 120;
  }

  public boolean hasStudents() {
    return !students.isEmpty();
  }

  public int getStudentCount() {
    return students.size();
  }

  public void addStudent(String name, int age) {
    if (!isValidName(name)) {
     // System.out.println("Invalid name. Only letters allowed.");
      return;
    }

    if (!isValidAge(age)) {
     // System.out.println("Invalid age.");
      return;
    }

    Student student = new Student(name, age);
    students.add(student);
    System.out.println("Student added.");
  }

  public void showStudents() {
    if(!hasStudents()) {
      System.out.println("No students in the list.");
      return;
    }

    for (int i = 0; i < students.size(); i++) {
      System.out.println((i + 1) + ". " + students.get(i));
    }
  }

  public void findStudentByName(String name) {
    if (!hasStudents()) {
     // System.out.println("No students in the list.");
      return;
    }
      
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
    if (!hasStudents()) {
     // System.out.println("No students to edit.");
      return;
    }

    if (index < 0 || index >= students.size()) {
     // System.out.println("Invalid student number.");
      return;
    }

    if (!isValidName(newName)) {
     // System.out.println("Invalid name. Only letters allowed.");
      return;
    }

    if (!isValidAge(newAge)) {
     // System.out.println("Invalid age.");
      return;
    }

    Student student = students.get(index);
    student.setName(newName);
    student.setAge(newAge);

    System.out.println("Updated: " + student);
  }

  public void removeStudent(int index) {
    if (!hasStudents()) {
     // System.out.println("No students to remove.");
      return;
    }

    if (index < 0 || index >= students.size()) {
     // System.out.println("Invalid student number.");
      return;
    }

    Student removedStudent = students.remove(index);
    System.out.println("Removed: " + removedStudent);
  }

  public void saveToFile(String studentsFile) {

    try {
      FileWriter writer = new FileWriter(studentsFile);

      for (Student student : students) {
        writer.write(student.getName() + "," + student.getAge());
        writer.write("\n");
      }

      writer.close();

    } catch(IOException e) {
      e.printStackTrace();
    }

  }
}