import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class StudentManager {

  private ArrayList<Student> students;

  private int nextId = 1;

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

  public int getStudentCount() {
    return students.size();
  }

  public Student addStudent(String name, int age) {
    if (!isValidName(name)) {
      return null;
    }

    if (!isValidAge(age)) {
      return null;
    }

    Student student = new Student(nextId,name, age);
    students.add(student);
    nextId ++;

    return student;
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
      if(!isValidName(newName)) {
          return false;
      }
      student.setName(newName);
      return true;
  }

  public boolean editStudentAgeById (int id, int newAge) {
      Student student = findStudentById(id);

      if (student == null) {
          return false;
      }
      if(!isValidAge(newAge)){
          return false;
      }
      student.setAge(newAge);
      return true;
  }

  public boolean editStudentBothById (int id, String newName, int newAge) {
      Student student = findStudentById(id);

      if(student == null) {
          return false;
      }
      if(!isValidName(newName)) {
          return false;
      }
      if(!isValidAge(newAge)) {
          return false;
      }

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

  public Student removeStudentById(int id) {
      for (int i = 0; i < students.size(); i++) {
          if (students.get(i).getId() == id) {
              return removeStudent(i);
          }
      }
      return null;
  }

  public void saveToFile(String studentsFile) {

    try {
      FileWriter writer = new FileWriter(studentsFile);

      for (Student student : students) {
        writer.write(student.getId() + "," + student.getName() + "," + student.getAge());
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

      int highestId = 0; //tracks max ID

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] parts = line.split(",");

        if (parts.length != 3) continue;

        int id = Integer.parseInt(parts[0].trim());
        String name = parts[1].trim();
        int age = Integer.parseInt(parts[2].trim());

        Student student = new Student(id, name, age);
        students.add(student);

        if (id > highestId) {
            highestId = id;
        }
      }
      scanner.close();

      nextId = highestId + 1; //set next ID

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

  public void sortById() {
      students.sort((s1, s2) -> Integer.compare(s1.getId(), s2.getId()));
  }

}