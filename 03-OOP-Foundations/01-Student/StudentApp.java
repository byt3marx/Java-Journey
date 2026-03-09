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
    students.get(2).setAge(24);
    System.out.println(students.get(1));
    System.out.println(students.get(2));

    //students.remove(1);
    
    for (Student s : students) {
      System.out.println(s);
    }

    System.out.println(students.size());
    
  }
}