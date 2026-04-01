import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
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

  private boolean isValidIndex(int index) {
      return index >= 0 && index < students.size();
  }

  public boolean hasStudents() {
    return !students.isEmpty();
  }

  public Student getStudent(int index) {
      if (!isValidIndex(index)) {
          return null;
      }
      return students.get(index);
  }

  public int getStudentCount() {
    return students.size();
  }

  public int getIndexOfStudent(Student student) {
      return students.indexOf(student);
  }

  public Student addStudent(String name, int age) {
    if (!isValidName(name)) {
      return null;
    }

    if (!isValidAge(age)) {
      return null;
    }

    Student student = new Student(name, age);
    students.add(student);

    return student;
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
  
  public boolean editStudentName(int index, String newName) {
    if (!isValidIndex(index)) {
      return false;
    }

    if (!isValidName(newName)) {
      return false;
    }

    Student student = getStudent(index);
    student.setName(newName);

    return true;
  }

  public boolean editStudentAge(int index, int newAge) {
    if (!isValidIndex(index)) {
      return false;
    }

    if (!isValidAge(newAge)) {
      return false;
    }

    Student student = getStudent(index);
    student.setAge(newAge);

    return true;
  }

  public boolean editStudentBoth(int index, String newName, int newAge) {
    if (!hasStudents()) {
      return false;
    }

    if (!isValidIndex(index)) {
      return false;
    }

    if (!isValidName(newName)) {
      return false;
    }

    if (!isValidAge(newAge)) {
      return false;
    }

    Student student = getStudent(index);
    student.setName(newName);
    student.setAge(newAge);

    return true;
  }

  public Student removeStudent(int index) {
    if (!isValidIndex(index)) {
      return null;
    }

    return students.remove(index);
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