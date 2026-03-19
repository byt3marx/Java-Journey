import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

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
      return;
    }

    if (!isValidAge(age)) {
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
  
  public boolean editStudentName(int index, String newName) {
    if (index < 0 || index >= students.size()) {
      return false;
    }

    if (!isValidName(newName)) {
      return false;
    }

    Student student = students.get(index);
    student.setName(newName);

    return true;
  }

  public boolean editStudentAge(int index, int newAge) {
    if (index < 0 || index >= students.size()) {
      return false;
    }

    if (!isValidAge(newAge)) {
      return false;
    }

    Student student = students.get(index);
    student.setAge(newAge);

    return true;
  }

  public boolean editStudentBoth(int index, String newName, int newAge) {
    if (!hasStudents()) {
      return false;
    }

    if (index < 0 || index >= students.size()) {
      return false;
    }

    if (!isValidName(newName)) {
      return false;
    }

    if (!isValidAge(newAge)) {
      return false;
    }

    Student student = students.get(index);
    student.setName(newName);
    student.setAge(newAge);

    return true;
  }

  public void removeStudent(int index) {
    if (!hasStudents()) {
      return;
    }

    if (index < 0 || index >= students.size()) {
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

  public void loadFromFile(String studentsFile) {

    try {
      Scanner scanner = new Scanner(new File(studentsFile));
      students.clear();

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] parts = line.split(",");

        if (parts.length != 2) continue;

        String name = parts[0].trim();
        int age = Integer.parseInt(parts[1].trim());

        Student student = new Student(name, age);
        students.add(student);
      }
      scanner.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public void sortByName() {
    Collections.sort(students);
  }

  public void sortByAge() {
    Collections.sort(students, (s1, s2) -> Integer.compare(s1.getAge(), s2.getAge()));
  }

}