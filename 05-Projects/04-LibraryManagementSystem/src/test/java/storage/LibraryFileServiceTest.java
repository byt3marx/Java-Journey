package storage;

import model.Book;
import model.Loan;
import model.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryFileServiceTest {

    private LibraryFileService fileService;
    private Path filePath;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        fileService = new LibraryFileService();
        filePath = tempDir.resolve("library.json");
    }

    @Test
    void saveAndLoadPreservesBooksAndMembers() {

        List<Book> books = List.of(
                new Book(1,
                        "Space",
                        "Mr. Universe",
                        411)
        );

        List<Member> members = List.of(
                new Member(
                        1,
                        "Ragnar",
                        "ragnar@gmail.com",
                        "060-666-777"
                )
        );

        List<Loan> loans = List.of();

        fileService.save(
                books,
                members,
                loans,
                filePath
        );

        LibraryState state = fileService.loadState(filePath);

        assertEquals(1, state.getBooks().size());
        assertEquals("Space", state.getBooks().get(0).getTitle());

        assertEquals(1, state.getMembers().size());
        assertEquals("Ragnar", state.getMembers().get(0).getName());
    }

    @Test
    void saveAndLoadPreservesActiveLoan() {

        Book book = new Book(
                1,
                "Space",
                "Mr. Universe",
                411
        );
        List<Book> books = List.of(book);

        Member member = new Member(
                1,
                "Ragnar",
                "ragnar@gmail.com",
                "060-666-777"
        );
        List<Member> members = List.of(member);

        LocalDate borrowDate = LocalDate.of(2026, 9, 12);
        LocalDate dueDate = LocalDate.of(2026, 9, 13);

        Loan loan = new Loan(
                1,
                book,
                member,
                borrowDate,
                dueDate,
                null);
        List<Loan> loans = List.of(loan);

        fileService.save(
                books,
                members,
                loans,
                filePath
        );

        LibraryState state = fileService.loadState(filePath);

        assertEquals(1, state.getLoans().size());

        Loan loadedLoan = state.getLoans().get(0);

        assertEquals(book.getId(), loadedLoan.getBook().getId());
        assertEquals(member.getId(), loadedLoan.getMember().getId());
        assertEquals(borrowDate, loadedLoan.getBorrowedDate());
        assertEquals(dueDate, loadedLoan.getDueDate());
        assertNull(loadedLoan.getReturnedDate());
    }

    @Test
    void saveAndLoadPreservesReturnedLoan() {

        Book book = new Book(
                1,
                "Space",
                "Mr. Universe",
                111
        );
        List<Book> books = List.of(book);

        Member member = new Member(
                1,
                "Ragnar",
                "ragnar@gmail.com",
                "060-666-777"
        );
        List<Member> members = List.of(member);

        LocalDate borrowedDate = LocalDate.of(2026, 9, 14);
        LocalDate dueDate = LocalDate.of(2026, 9, 29);
        LocalDate returnedDate = LocalDate.of(2026, 9, 25);

        Loan loan = new Loan(
                1,
                book,
                member,
                borrowedDate,
                dueDate,
                returnedDate
        );
        List<Loan> loans = List.of(loan);

        fileService.save(
                books,
                members,
                loans,
                filePath
        );

        LibraryState state = fileService.loadState(filePath);
        Loan loadedLoan = state.getLoans().get(0);

        assertEquals(returnedDate, loadedLoan.getReturnedDate());
    }

    @Test
    void loadMissingFileReturnsEmptyState() {
        LibraryState state = fileService.loadState(filePath);

        assertTrue(state.getBooks().isEmpty());
        assertTrue(state.getMembers().isEmpty());
        assertTrue(state.getLoans().isEmpty());
    }

}
