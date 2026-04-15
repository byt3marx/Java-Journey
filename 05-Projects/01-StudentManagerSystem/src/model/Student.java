package model;

public class Student implements Comparable<Student> {

  private String name;
  private int age;
  private final int id;

  public Student(int id, String name, int age) {
    this.id = id;
    setName(name); //important to use setName instead of this.name = name, because the constructor also uses validation
    setAge(age);
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
    if (!isValidName(name)) {
        throw new IllegalArgumentException("Name must not be blank and can only contain letters, spaces, and basic punctuation.");
    }
    this.name = name.trim();
  }

  private static boolean isValidName(String name) {
      return name != null && !name.isBlank() && name.matches("[\\p{L} .'-]+");
  }

  public void setAge(int age) {
    if (!isValidAge(age)) {
      throw new IllegalArgumentException("Invalid age: " + age + ". Age must be between 1 and 120.");
    }
    this.age = age;
  }

  private static boolean isValidAge(int age) {
      return age >= 1 && age <= 120;
  }

  public String toString() {
    return "ID: " + id + " | " + name + " (" + age + ")";
  }

  @Override
  public int compareTo(Student other) {
    return this.getName().compareTo(other.getName());
  }
}