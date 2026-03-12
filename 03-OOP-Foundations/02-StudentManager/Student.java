class Student {

  private String name;
  private int age;

  public Student(String name, int age) {
    setName(name); //important to use setName instead of this.name = name, because the constructor also uses validation
    setAge(age);
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public void setName(String name) {
    if (name != null && !name.isBlank() && name.matches("[\\p{L} .'-]+")) {
      this.name = name;
    } else {
      System.out.println("Invalid name. Only letters allowed.");
    }
  }

  public void setAge(int age) {
    if (age >= 0 && age <= 120) {
      this.age = age;
    } else {
      System.out.println("Invalid age.");
    }
  }

  public String toString() {
    return name + " (" + age + ")";
  }
}