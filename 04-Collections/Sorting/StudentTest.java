public class StudentTest implements Comparable<StudentTest> {
  private String name;
  private int age;

  public StudentTest(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  @Override
  public int compareTo(StudentTest other) {
    return this.name.compareTo(other.name);
  }
}