package service;

import model.Student;

import java.util.*;
import java.text.Collator;
import java.util.Locale;

public class StudentManager {

  private ArrayList<Student> students;

  private int nextId = 1;

  private StudentFileService fileService;

  public StudentManager() {
    students = new ArrayList<>();
    fileService = new StudentFileService();
  }

  private boolean isValidIndex(int index) {
      return index >= 0 && index < students.size();
  }

  public boolean hasStudents() {
    return !students.isEmpty();
  }

  public Student addStudent(String name, int age, String email) {

      try {
          Student student = new Student(nextId, name, age, email);
          students.add(student);
          nextId++;
          return student;
      } catch (IllegalArgumentException e) {
          System.out.println(e.getMessage()); //temporary or debug-level
          return null;
      }
  }

  public void showStudents() {
    if(!hasStudents()) {
      System.out.println("No students in the list.");
      return;
    }

    for (Student s : students) {
      System.out.println(s);
    }
  }

  public List<Student> findStudentsByName(String name) {

    List<Student> foundStudents = new ArrayList<>();

    String search = name.trim().toUpperCase();

    for (Student s : students) {

      String studentName = s.getName().toUpperCase();

      if(studentName.startsWith(search)) {
        foundStudents.add(s);
      }
    }
    return foundStudents;
  }

  public Student findStudentById(int id) {

      for (Student s : students) {
          if(s.getId() == id){
              return s;
          }
      }
      return null;
  }

  public boolean editStudentNameById(int id, String newName) {
      Student student = findStudentById(id);

      if (student == null) {
          return false;
      }
      try {
          student.setName(newName);
          return true;
      } catch (IllegalArgumentException e) {
          System.out.println(e.getMessage());
          return false;
      }
  }

  public boolean editStudentAgeById (int id, int newAge) {
      Student student = findStudentById(id);

      if (student == null) {
          return false;
      }
      try {
          student.setAge(newAge);
          return true;
      } catch (IllegalArgumentException e) {
          System.out.println(e.getMessage());
          return false;
      }
  }

  public boolean editStudentEmailById(int id, String newEmail) {
      Student student = findStudentById(id);

      if (student == null) {
          return false;
      }
      try {
          student.setEmail(newEmail);
          return true;
      } catch (IllegalArgumentException e) {
          System.out.println(e.getMessage());
          return false;
      }
  }

  public boolean editStudentBothById (int id, String newName, int newAge) {
      Student student = findStudentById(id);

      if (student == null) {
          return false;
      }

      if (!Student.isValidName(newName) || !Student.isValidAge(newAge)) {
          return false;
      }

      try {
          student.setName(newName);
          student.setAge(newAge);
          return true;
      } catch (IllegalArgumentException e) {
          System.out.println(e.getMessage());
          return false;
      }
  }

  public Student removeStudent(int index) {
    if (!isValidIndex(index)) {
      return null;
    }

    return students.remove(index);
  }

  public Student removeStudentById(int id) {
      for (int i = 0; i < students.size(); i++) {
          if (students.get(i).getId() == id) {
              return removeStudent(i);
          }
      }
      return null;
  }

  public void saveToFile(String studentsFile) {
      fileService.saveToFile(students, studentsFile);
  }

  public void loadFromFile(String studentsFile) {
      students = new ArrayList<>(fileService.loadFromFile(studentsFile));

      int highestId = 0;

      for (Student student : students) {
          if (student.getId() > highestId) {
              highestId = student.getId();
          }
      }

      nextId = highestId + 1;
  }

  public void sortByName() {

      Collator collator = Collator.getInstance(new Locale("sl", "SI"));
      collator.setStrength(Collator.PRIMARY);

      students.sort((s1, s2) -> collator.compare(s1.getName(), s2.getName()));
  }

  public void sortByAge() {
      Collections.sort(students, (s1, s2) -> Integer.compare(s1.getAge(), s2.getAge()));
  }

  public void sortById() {
      students.sort((s1, s2) -> Integer.compare(s1.getId(), s2.getId()));
  }

}