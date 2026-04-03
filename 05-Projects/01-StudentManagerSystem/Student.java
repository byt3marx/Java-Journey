class Student implements Comparable<Student> {

  private String name;
  private int age;
  private final int id;

  public Student(int id, String name, int age) {
    setName(name); //important to use setName instead of this.name = name, because the constructor also uses validation
    setAge(age);
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public int getId() {
      return id;
  }

  public void setName(String name) {
    if (name != null && !name.isBlank() && name.matches("[\\p{L} .'-]+")) {
      this.name = name.trim();
    }
  }

  public void setAge(int age) {
    if (age >= 0 && age <= 120) {
      this.age = age;
    }
  }

  public String toString() {
    return "ID: " + id + " | " + name + " (" + age + ")";
  }

  @Override
  public int compareTo(Student other) {
    return this.getName().compareTo(other.getName());
  }
}