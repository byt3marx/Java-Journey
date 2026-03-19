import java.util.*;

public class SortStrings {
  public static void main(String[] args) {
    ArrayList<String> names = new ArrayList<>();

    names.add("Rok");
    names.add("Mihael");
    names.add("Tjasa");

    Collections.sort(names);

    System.out.println(names);
  }
}