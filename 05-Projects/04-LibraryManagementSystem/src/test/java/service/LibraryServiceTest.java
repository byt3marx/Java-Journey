package service;

import model.Book;
import model.Member;
import model.Loan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryServiceTest {

    private LibraryService service;

    @BeforeEach
            void setUp() {
        service = new LibraryService();

    }

    @Test
    void addBookReturnsCreatedBook() {

        Book book = service.addBook(
                "Think like a monk",
                "Jay Shetty",
                432
        );

        assertEquals(1, book.getId());
        assertEquals("Think like a monk", book.getTitle());
        assertEquals("Jay Shetty", book.getAuthor());
        assertEquals(432, book.getNumberOfPages());
    }

    @Test
    void addBookGeneratesSequentialIds() {

        Book firstBook = service.addBook("Emotional Intelligence", "Daniel Goleman", 354);
        Book secondBook = service.addBook("The Magic", "Rhonda Byerne", 350);

        assertEquals(1, firstBook.getId());
        assertEquals(2, secondBook.getId());
    }

    @Test
    void addMemberReturnsCreatedMember() {

        Member member = service.addMember(
                "Saša",
                "sasa.krama@gmail.com",
                "041-323-323"
        );

        assertEquals(1, member.getId());
        assertEquals("Saša", member.getName());
        assertEquals("sasa.krama@gmail.com", member.getEmail());
        assertEquals("041-323-323", member.getPhoneNumber());
    }

    @Test
    void addMemberGeneratesSequentialIds() {

        Member firstMember = service.addMember(
                "Miha",
                "mihael@gmail.com",
                "051-523-555");

        Member secondMember = service.addMember(
                "Martina",
                "tina@gmail.com",
                "070-442-221");

        assertEquals(1, firstMember.getId());
        assertEquals(2, secondMember.getId());
    }

    @Test
    void findBookByIdReturnsBookWhenFound() {

        Book book = service.addBook(
                "Something green",
                "The Forest",
                123
        );

        Optional<Book> result = service.findBookById(book.getId());

        assertTrue(result.isPresent());
        assertEquals("Something green", result.get().getTitle());
    }

    @Test
    void findBookByIdReturnsEmptyWhenNotFound() {

        Optional<Book> result = service.findBookById(111);

        assertTrue(result.isEmpty());
    }

    @Test
    void findMemberByIdReturnsMemberWhenFound() {

        Member member = service.addMember(
                "Sara",
                "sara.krama@gmail.com",
                "031-222-111"
        );

        Optional<Member> result = service.findMemberById(member.getId());

        assertTrue(result.isPresent());
        assertEquals("Sara", result.get().getName());
    }

    @Test
    void findMemberByIdReturnsEmptyWhenNotFound() {

        Optional<Member> result = service.findMemberById(43);

        assertTrue(result.isEmpty());
    }

    @Test
    void borrowBookCreatesLoan() {

        Book book = service.addBook(
                "The blue sky",
                "Mother Earth",
                400
        );

        Member member = service.addMember(
                "Rok",
                "roki@gmail.com",
                "031-111-111"
        );

        LocalDate borrowedDate = LocalDate.of(2026, 8, 27);
        LocalDate dueDate = LocalDate.of(2026, 9, 10);

        Loan loan = service.borrowBook(
                book.getId(),
                member.getId(),
                borrowedDate,
                dueDate
        );

        assertEquals(1, loan.getId());
        assertEquals(book, loan.getBook());
        assertEquals(member, loan.getMember());
        assertEquals(borrowedDate, loan.getBorrowedDate());
        assertEquals(dueDate, loan.getDueDate());
        assertNull(loan.getReturnedDate());

    }

    @Test
    void borrowBookFailsWhenBookNotFound() {

        Member member = service.addMember(
                "Olga",
                "olgica@gmail.com",
                "051-233-322"
        );

        LocalDate borrowedDate = LocalDate.of(2026, 6, 15);
        LocalDate dueDate = LocalDate.of(2026, 7, 15);

        assertThrows(
                IllegalArgumentException.class,
                () -> service.borrowBook(
                        555,
                        member.getId(),
                        borrowedDate,
                        dueDate
                )
        );
    }

    @Test
    void borrowBookFailsWhenMemberNotFound() {

        Book book = service.addBook(
                "How do planes fly",
                "Wind Master",
                112
        );

        LocalDate borrowedDate = LocalDate.of(2026, 6, 17);
        LocalDate dueDate = LocalDate.of(2026, 7, 17);

        assertThrows(
                IllegalArgumentException.class,
                () -> service.borrowBook(
                        book.getId(),
                        12,
                        borrowedDate,
                        dueDate
                )
        );
    }

    @Test
    void borrowBookFailsWhenBookAlreadyBorrowed() {

        Book book = service.addBook(
                "Wonders of the Solar System",
                "Brian Cox",
                220
        );

        Member firstMember = service.addMember(
                "Kekec",
                "keko@gmail.com",
                "041-333-112"
        );

        Member secondMember = service.addMember(
                "Mojca",
                "mojci@gmail.com",
                "041-333-113"
        );

        LocalDate borrowedDate = LocalDate.of(2026, 5, 10);
        LocalDate dueDate = LocalDate.of(2026, 6, 10);

        service.borrowBook(
                book.getId(),
                firstMember.getId(),
                borrowedDate,
                dueDate
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> service.borrowBook(
                        book.getId(),
                        secondMember.getId(),
                        borrowedDate,
                        dueDate
                )
        );
    }

    @Test
    void returnBookMarksLoanAsReturned() {

        Book book = service.addBook(
                "Stars in the sky",
                "The universe",
                1111
        );

        Member member = service.addMember(
                "Star Lord",
                "star.lord@gmail.com",
                "000-111-111"
        );

        LocalDate borrowedDate = LocalDate.of(2026, 8, 25);
        LocalDate dueDate = LocalDate.of(2026, 9, 25);
        LocalDate returnedDate = LocalDate.of(2026, 9, 23);

        Loan loan = service.borrowBook(
                book.getId(),
                member.getId(),
                borrowedDate,
                dueDate
        );

        service.returnBook(loan.getId(), returnedDate);

        assertEquals(returnedDate, loan.getReturnedDate());
    }

    @Test
    void returnBookFailsWhenAlreadyReturned() {

        Book book = service.addBook(
                "Stars in the sky",
                "The universe",
                1111
        );

        Member member = service.addMember(
                "Star Lord",
                "star.lord@gmail.com",
                "000-111-111"
        );

        LocalDate borrowedDate = LocalDate.of(2026, 8, 25);
        LocalDate dueDate = LocalDate.of(2026, 9, 25);
        LocalDate returnedDate = LocalDate.of(2026, 9, 23);
        LocalDate secondReturnedDate = LocalDate.of(2026, 9, 24);

        Loan loan = service.borrowBook(
                book.getId(),
                member.getId(),
                borrowedDate,
                dueDate
        );

        service.returnBook(loan.getId(), returnedDate);

        assertThrows(
                IllegalArgumentException.class,
                () -> service.returnBook(
                        loan.getId(), secondReturnedDate
                )
        );
    }

    @Test
    void returnBookFailsWhenLoanNotFound() {

        LocalDate returnedDate = LocalDate.of(2026, 9, 15);

        assertThrows(
                IllegalArgumentException.class,
                () -> service.returnBook(
                        22,
                        returnedDate
                )
        );
    }

}
