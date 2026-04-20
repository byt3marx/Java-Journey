package service;

import model.Student;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;


public class StudentFileService {

    public void saveToFile(List<Student> students, String filePath) {

        try (FileWriter writer = new FileWriter(filePath)) {

            for(Student student : students) {
                writer.write(student.getId() + "," + student.getName() + "," + student.getAge() + "," + student.getEmail());
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving data to file.");
            e.printStackTrace();
        }
    }

    public List<Student> loadFromFile(String filePath) {

        try (Scanner scanner = new Scanner(new File(filePath))) {
            ArrayList<Student> tempStudents = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length != 4) continue;

                try {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    int age = Integer.parseInt(parts[2].trim());
                    String email = parts[3].trim();

                    Student student = new Student(id, name, age, email);
                    tempStudents.add(student);

                } catch (IllegalArgumentException e) {
                    System.out.println("Skipping invalid line: " + line);
                }
            }
            return tempStudents;

        } catch (FileNotFoundException e) {
            System.out.println("No existing data file found. Starting fresh.");
            return new ArrayList<>();
        }
    }

}
