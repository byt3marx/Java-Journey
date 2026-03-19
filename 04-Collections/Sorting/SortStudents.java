import java.util.*;

public class SortStudents {
  public static void main(String[] args) {
    ArrayList<Student> students = new ArrayList<>();

    students.add(new Student("Rozle", 30));
    students.add(new Student("Kekec", 25));
    students.add(new Student("Denis", 43));
    students.add(new Student("Cecilija", 28));

    Collections.sort(students);

    for(Student s : students) {
      System.out.println(s.getName() + " (" + s.getAge() + ")");
    }
  }
}