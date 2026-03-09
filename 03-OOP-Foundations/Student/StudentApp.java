import java.util.ArrayList;

class StudentApp {

  public static void main(String[] args) {

    ArrayList<Student> students = new ArrayList<>();

    students.add(new Student("Anna", 20));
    students.add(new Student("Marx", 22));
    students.add(new Student("Rok", 25));

    for (Student s : students) {
      System.out.println(s);
    }

    students.get(1).setName("Mark");
    System.out.println(students.get(1));
    
  }
}