package model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class LoanTest {

    Book book = new Book(1, "Interstellar", "Someone Somewhere", 555);
    Member member = new Member(13, "Sasha", "sasa@gmail.com", "031-111-222");
    LocalDate borrowedDate = LocalDate.of(2026, 5, 24);
    LocalDate dueDate = LocalDate.of(2026, 6, 25);

    @Test
    void validLoanIsCreated() {

        Loan loan = new Loan(
                1,
                book,
                member,
                borrowedDate,
                dueDate,
                null
        );

        assertEquals(1, loan.getId());
        assertEquals(book, loan.getBook());
        assertEquals(member, loan.getMember());
        assertEquals(borrowedDate, loan.getBorrowedDate());
        assertEquals(dueDate, loan.getDueDate());
        assertNull(loan.getReturnedDate());
    }

    @Test
    void zeroIdThrows() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Loan(
                        0,
                        book,
                        member,
                        borrowedDate,
                        dueDate,
                        null
                )
        );
    }

    @Test
    void negativeIdThrows() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Loan(
                        -2,
                        book,
                        member,
                        borrowedDate,
                        dueDate,
                        null
                )
        );
    }

    @Test
    void nullBookThrows() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Loan(
                        3,
                        null,
                        member,
                        borrowedDate,
                        dueDate,
                        null
                )
        );
    }

    @Test
    void nullMemberThrows() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Loan(
                        3,
                        book,
                        null,
                        borrowedDate,
                        dueDate,
                        null
                )
        );
    }

    @Test
    void nullBorrowedDateThrows() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Loan(
                        3,
                        book,
                        member,
                        null,
                        dueDate,
                        null
                )
        );
    }

    @Test
    void nullDueDateThrows() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Loan(
                        3,
                        book,
                        member,
                        borrowedDate,
                        null,
                        null
                )
        );
    }

    @Test
    void dueDateBeforeBorrowedDateThrows() {

        LocalDate invalidDueDate = LocalDate.of(2026, 5, 19);

        assertThrows(
                IllegalArgumentException.class,
                () -> new Loan(
                        3,
                        book,
                        member,
                        borrowedDate,
                        invalidDueDate,
                        null
                )
        );
    }

    @Test
    void returnedDateBeforeBorrowedDateThrows() {

        LocalDate invalidReturnedDate = LocalDate.of(2026, 5, 21);

        assertThrows(
                IllegalArgumentException.class,
                () -> new Loan(
                        3,
                        book,
                        member,
                        borrowedDate,
                        dueDate,
                        invalidReturnedDate
                )
        );
    }

    @Test
    void returnBookSetsReturnedDate() {

        Loan loan = new Loan(
                3,
                book,
                member,
                borrowedDate,
                dueDate,
                null
        );

        LocalDate returnedDate = LocalDate.of(2026, 6, 10);

        loan.returnBook(returnedDate);

        assertEquals(returnedDate, loan.getReturnedDate());

    }

    @Test
    void returnBookWithNullDateThrows() {

        Loan loan = new Loan(
                3,
                book,
                member,
                borrowedDate,
                dueDate,
                null
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> loan.returnBook(null)
        );
    }

    @Test
    void returnBookTwiceThrows() {

        Loan loan = new Loan(
                3,
                book,
                member,
                borrowedDate,
                dueDate,
                null
        );

        LocalDate firstReturnedDate = LocalDate.of(2026, 6, 10);
        LocalDate secondReturnedDate = LocalDate.of(2026, 6, 11);

        loan.returnBook(firstReturnedDate);

        assertThrows(
                IllegalArgumentException.class,
                () -> loan.returnBook(secondReturnedDate)
        );
    }

}
