package model;

import java.time.LocalDate;

public class Loan {

    private final int id;
    private final Book book;
    private final Member member;
    private final LocalDate borrowedDate;
    private final LocalDate dueDate;
    private LocalDate returnedDate;

    public Loan(int id,
                Book book,
                Member member,
                LocalDate borrowedDate,
                LocalDate dueDate,
                LocalDate returnedDate) {

        validateId(id);
        validateBook(book);
        validateMember(member);
        validateBorrowedDate(borrowedDate);
        validateDueDate(borrowedDate, dueDate);
        validateReturnedDate(borrowedDate, returnedDate);

        this.id = id;
        this.book = book;
        this.member = member;
        this.borrowedDate = borrowedDate;
        this.dueDate = dueDate;
        this.returnedDate = returnedDate;
    }

    public int getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public Member getMember() {
        return member;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    private void validateId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id must be larger than 0.");
        }
    }

    private void validateBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null.");
        }
    }

    private void validateMember(Member member) {
        if (member == null) {
            throw new IllegalArgumentException("Member cannot be null.");
        }
    }

    private void validateBorrowedDate(LocalDate borrowedDate) {
        if (borrowedDate == null) {
            throw new IllegalArgumentException("Borrowed date cannot be null.");
        }
    }

    private void validateDueDate(LocalDate borrowedDate, LocalDate dueDate) {
        if (dueDate == null) {
            throw new IllegalArgumentException("Due date cannot be null.");
        }

        if (dueDate.isBefore(borrowedDate)) {
            throw new IllegalArgumentException("Due date cannot be before the borrowed date.");
        }
    }

    private void validateReturnedDate(LocalDate borrowedDate, LocalDate returnedDate) {
        if (returnedDate != null && returnedDate.isBefore(borrowedDate)) {
            throw new IllegalArgumentException("Returned date cannot be before borrowed date.");
        }
    }

    public void returnBook(LocalDate returnedDate) {
        if (returnedDate == null) {
            throw new IllegalArgumentException("Returned date cannot be null.");
        }

        if (this.returnedDate != null) {
            throw new IllegalArgumentException("This loan has already been returned.");
        }

        validateReturnedDate(borrowedDate, returnedDate);

        this.returnedDate = returnedDate;
    }

}
