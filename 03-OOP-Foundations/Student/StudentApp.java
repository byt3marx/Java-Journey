class StudentApp {

  public static void main(String[] args) {

    Student s1 = new Student("Anna", 20);
    Student s2 = new Student("Mark", 22);
    Student s3 = new Student("Igor", 5);

    System.out.println(s1.getName());
    System.out.println(s1.getAge());
    System.out.println(s2.getName());
    System.out.println(s2.getAge());

    s1.setAge(-10);
    System.out.println(s2.getAge());

    s3.setName("Rok");
    s3.setAge(25);
    System.out.println(s3.getName());
    System.out.println(s3.getAge());

    
  }
}