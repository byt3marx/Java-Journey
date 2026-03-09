class Student {

  private String name;
  private int age;

  public Student(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAge(int age) {
    if (age <= 0 || age > 120) {
      System.out.println("Invalid age.");
    }
    this.age = age;
  }

  public String toString() {
    return name + " (" + age + ")";
  }
}