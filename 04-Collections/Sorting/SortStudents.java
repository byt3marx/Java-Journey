import java.util.*;

public class SortStudents {
  public static void main(String[] args) {
    ArrayList<Student> students = new ArrayList<>();

    students.add(new Student("Rozle", 30));
    students.add(new Student("Kekec", 25));
    students.add(new Student("Denis", 43));
    students.add(new Student("Cecilija", 28));

    Collections.sort(students, (s1, s2) -> s1.getAge() - s2.getAge());

    for(Student s : students) {
      System.out.println(s.getName() + " (" + s.getAge() + ")");
      
    }

    Collections.sort(students, (s1, s2) -> s2.getAge() - s1.getAge());

    for(Student s : students) {
      System.out.println(s.getName() + " (" + s.getAge() + ")");
    }

  }
}