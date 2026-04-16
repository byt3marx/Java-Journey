package model;

public class Student implements Comparable<Student> {

  private String name;
  private int age;
  private final int id;
  private String email;

  public Student(int id, String name, int age, String email) {
    this.id = id;
    setName(name); //important to use setName instead of this.name = name, because the constructor also uses validation
    setAge(age);
    setEmail(email);
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

  public String getEmail() {
      return email;
  }

  public void setName(String name) {
    if (!isValidName(name)) {
        throw new IllegalArgumentException("Name must not be blank and can only contain letters, spaces, and basic punctuation.");
    }
    this.name = name.trim();
  }

  public static boolean isValidName(String name) {
      return name != null && !name.isBlank() && name.matches("[\\p{L} .'-]+");
  }

  public void setAge(int age) {
    if (!isValidAge(age)) {
      throw new IllegalArgumentException("Invalid age: " + age + ". Age must be between 1 and 120.");
    }
    this.age = age;
  }

  public static boolean isValidAge(int age) {
      return age >= 1 && age <= 120;
  }

  public void setEmail(String email) {
      if (!isValidEmail(email)) {
          throw new IllegalArgumentException("Email must contain one '@' and a '.' after it.");
      }
      this.email = email.trim();
  }

  public static boolean isValidEmail(String email) {

      if (email == null) return false;
      email = email.trim();
      if (email.isBlank()) return false;

      int atIndex = email.indexOf("@");

      if (atIndex == -1) return false; //no @
      if (atIndex != email.lastIndexOf("@")) return false; //check for multiple @
      if (atIndex == 0 || atIndex == email.length() -1 ) return false;

      int dotIndex = email.indexOf(".", atIndex + 1);
      if (dotIndex == -1) return false;   // no dot after @
      if (dotIndex == atIndex + 1) return false; //nothing between @ and .
      if (dotIndex == email.length() -1) return false; // dot at end

      return true;
  }

  public String toString() {
    return "ID: " + id + " | " + name + " (" + age + ")";
  }

  @Override
  public int compareTo(Student other) {
    return this.getName().compareTo(other.getName());
  }
}