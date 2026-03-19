import java.util.*;

public class SortNumbers {
  public static void main(String[] args) {
    ArrayList<Integer> numbers = new ArrayList<>();

    numbers.add(43);
    numbers.add(34);
    numbers.add(23);

    Collections.sort(numbers);

    System.out.println(numbers);
  }
}