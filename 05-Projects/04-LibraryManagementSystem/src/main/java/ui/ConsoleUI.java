package ui;

import model.Member;
import service.LibraryService;
import model.Book;

import java.util.Scanner;

public class ConsoleUI {

    private final LibraryService service;
    private final Scanner scanner;

    public ConsoleUI(LibraryService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void run() {

        boolean running = true;

        System.out.println("Welcome to our Library");

        while (running) {
            showMainMenu();

            String choice = scanner.nextLine();
            running = handleMenuChoice(choice);
        }

        System.out.println("Goodbye.");

    }

    private void showMainMenu() {
        System.out.println();
        System.out.println("1. Add book");
        System.out.println("2. Add member");
        System.out.println("3. View books");
        System.out.println("4. View members");
        System.out.println("5. Borrow book");
        System.out.println("6. Return book");
        System.out.println("7. View loans");
        System.out.println("8. Exit");
    }

    private boolean handleMenuChoice(String choice) {

        return switch (choice) {
            case "1" -> {
                addBook();
                yield true;
            }
            case "2" -> {
                addMember();
                yield true;
            }
            case "3" -> {
                viewBooks();
                yield true;
            }
            case "4" -> {
                viewMembers();
                yield true;
            }
            case "5" -> {
                borrowBook();
                yield true;
            }
            case "6" -> {
                returnBook();
                yield true;
            }
            case "7" -> {
                viewLoans();
                yield true;
            }
            case "8" -> false;
            default -> {
                System.out.println("Invalid choice.");
                yield true;
            }
        };
    }

    private void addBook() {

        String title = readRequiredText("Title:");

        String author = readRequiredText("Author");

        System.out.println("Number of pages:");
        int numberOfPages = readValidNumberOfPages();

        Book book = service.addBook(title, author, numberOfPages);
        System.out.println("Book added successfully. ID: " + book.getId());
    }

    private void addMember() {

        String name = readRequiredText("Name");
        String email = readValidEmail();
        String phoneNumber = readValidPhoneNumber();

        Member member = service.addMember(name, email, phoneNumber);
        System.out.println("Member added successfully. ID: " + member.getId());
    }

    private void viewBooks() {
        // call service.getBooks()
    }

    private void viewMembers() {
        // call service.getMembers()
    }

    private void borrowBook() {

    }

    private void returnBook() {

    }

    private void viewLoans() {

    }

    private int readValidNumberOfPages() {
        while (true) {
            String input = scanner.nextLine();

            try {
                int numberOfPages = Integer.parseInt(input);

                if (numberOfPages > 0) {
                    return numberOfPages;
                }

                System.out.println("Number of pages must be greater than 0.");

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private String readValidEmail() {
        while (true) {
            System.out.println("E-mail:");
            String input = scanner.nextLine();

            if (input.isBlank()) {
                System.out.println("Email cannot be blank.");
                continue;
            }

            int atIndex = input.indexOf('@');
            int lastAtIndex = input.lastIndexOf('@');

            if (atIndex == -1 || atIndex != lastAtIndex) {
                System.out.println("Email must contain exactly one @.");
                continue;
            }
            if (atIndex == 0) {
                System.out.println("Email must contain something before @.");
                continue;
            }

            int dotIndex = input.indexOf('.', atIndex + 1);

            if (dotIndex == -1) {
                System.out.println("Email must contain a . after @.");
                continue;
            }
            if (dotIndex == atIndex + 1) {
                System.out.println("There has to be something between . and @.");
                continue;
            }
            if (dotIndex == input.length() - 1) {
                System.out.println("The . cannot be at the end of an email.");
                continue;
            }
            return input;
        }
    }

    private String readValidPhoneNumber() {
        while (true) {
            System.out.println("Phone number:");
            String input = scanner.nextLine();

            if (input.isBlank()) {
                System.out.println("Phone number cannot be blank.");
                continue;
            }
            if (!input.matches(".*\\d.*")) {
                System.out.println("Phone number must contain at least one digit.");
                continue;
            }
            if (!input.matches("[0-9+ -]+")) {
                System.out.println("Phone number contains invalid characters.");
                continue;
            }

            return input;
        }
    }

    private String readRequiredText(String fieldName) {
        while (true) {
            System.out.println(fieldName + ":");
            String input = scanner.nextLine();

            if (!input.isBlank()) {
                return input;
            }

            System.out.println(fieldName + " cannot be blank.");
        }
    }


}
