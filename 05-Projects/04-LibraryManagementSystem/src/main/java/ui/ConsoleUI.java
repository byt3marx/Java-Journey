package ui;

import model.Loan;
import model.Member;
import service.LibraryService;
import model.Book;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
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
        List<Book> books = service.getBooks();

        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        for (Book b : books) {
            System.out.println(
                    "ID: " + b.getId()
                            + " | Title: " + b.getTitle()
                            + " | Author: " + b.getAuthor()
                            + " | Pages: " + b.getNumberOfPages()
            );
        }
    }

    private void viewMembers() {
        List<Member> members = service.getMembers();

        if (members.isEmpty()) {
            System.out.println("No members in the system.");
            return;
        }

        for (Member m : members) {
            System.out.println(
                    "ID: " + m.getId()
                            + " | Name: " + m.getName()
                            + " | Email: " + m.getEmail()
                            + " | Phone number: " + m.getPhoneNumber()
            );
        }
    }

    private void borrowBook() {
        viewBooks();
        Book book = readExistingBook("Enter book ID:");

        viewMembers();
        Member member = readExistingMember("Enter member ID:");

        LocalDate borrowedDate = readValidDate("Enter borrowed date (yyyy-MM-dd):");
        LocalDate dueDate = readValidDate("Enter due date (yyyy-MM-dd):");

        try {
            Loan loan = service.borrowBook(
                    book.getId(),
                    member.getId(),
                    borrowedDate,
                    dueDate
            );

            System.out.println("Loan created successfully. Loan ID: " + loan.getId());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void returnBook() {
        viewLoans();
        Loan loan = readExistingLoan("Enter loan ID:");
        LocalDate returnedDate = readValidDate("Enter returned date (yyyy-MM-dd):");

        try {
            service.returnBook(
                    loan.getId(),
                    returnedDate
            );

            System.out.println("Book returned successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewLoans() {
        List<Loan> loans = service.getLoans();

        if (loans.isEmpty()) {
            System.out.println("No active loans in the system.");
            return;
        }

        for (Loan l : loans) {
            System.out.println(
                    "ID: " + l.getId()
                            + " | Borrowed date: " + l.getBorrowedDate()
                            + " | Due date: " + l.getDueDate()
            );
        }
    }
//------------------------------------------------------------------------------------------------
    private int readValidId(String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine();

            try {
                int id = Integer.parseInt(input);

                if (id > 0) {
                    return id;
                }

                System.out.println("ID must be greater than 0.");

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid ID.");
            }
        }
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

    private Book readExistingBook(String prompt) {
        while (true) {
            int bookId = readValidId(prompt);

            Optional<Book> result = service.findBookById(bookId);

            if (result.isPresent()) {
                Book book = result.get();
                System.out.println("Selected: " + book.getTitle());
                return book;
            }

            System.out.println("Book not found");
        }
    }

    private Member readExistingMember(String prompt) {
        while (true) {
            int memberId = readValidId(prompt);

            Optional<Member> result = service.findMemberById(memberId);

            if (result.isPresent()) {
                Member member = result.get();
                System.out.println("Selected: " + member.getName());
                return member;
            }

            System.out.println("Member not found.");
        }
    }

    private Loan readExistingLoan(String prompt) {
        while (true) {
            int loanId = readValidId(prompt);

            Optional<Loan> result = service.findLoanById(loanId);

            if (result.isPresent()) {
                Loan loan = result.get();
                System.out.println(
                        "Selected: " + loan.getId()
                                + " | Book: " + loan.getBook().getTitle()
                                + " | Member: " + loan.getMember().getName());
                return loan;
            }

            System.out.println("Loan not found.");
        }
    }

    private LocalDate readValidDate(String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine();

            try {
                return LocalDate.parse(input);
            } catch (DateTimeParseException e) {
                System.out.println("Please enter a valid date in (yyyy:MM-dd) format.");
            }
        }

    }

}
