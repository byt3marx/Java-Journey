class Human {
  public static void main(String[] args) {

    String name = "Sasha";
    int age = 20;
    double height = 1.73;
    boolean isStudent = false;
    int favouriteNumber = 7;
    String country = "Slovenia";
    boolean isLearningJava = false;

    System.out.println("Name: " + name);
    System.out.println("Age: " + age);
    System.out.println("Height: " + height);
    System.out.println("Student: " + isStudent);
    System.out.println("Favourite Number: " + favouriteNumber);
    System.out.println("Country: " + country);
    System.out.println("Learning Java: " + isLearningJava);

    //learning how java handles variables and data types//

    int a = 5;
    int b = 2;
    System.out.println(a/b); //prints 2 because it is an integer division

    double c = 5;
    int e = 2;
    System.out.println(c/e); //prints 2.5 because c is a double and e is an integer, so the result is a double

    System.out.println(5/2);
    System.out.println(5.0/2);
    System.out.println((double)5/2); //casting 5 to a double before division, so the result is a double

  }
}