import java.util.*;

public class SortStudents {
  public static void main(String[] args) {
    ArrayList<StudentTest> students = new ArrayList<>();

    students.add(new StudentTest("Rozle", 30));
    students.add(new StudentTest("Kekec", 25));
    students.add(new StudentTest("Denis", 43));
    students.add(new StudentTest("Cecilija", 28));

    Collections.sort(students, (s1, s2) -> s1.getAge() - s2.getAge());

    for(StudentTest s : students) {
      System.out.println(s.getName() + " (" + s.getAge() + ")");
      
    }

    Collections.sort(students, (s1, s2) -> s2.getAge() - s1.getAge());

    for(StudentTest s : students) {
      System.out.println(s.getName() + " (" + s.getAge() + ")");
    }

  }
}