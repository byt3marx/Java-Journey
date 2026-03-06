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

  public String setName() {
    return name;
  }

  public int setAge() {
    return age;
  }

  public String toString() {
    return name + " (" + age + ")";
  }
}