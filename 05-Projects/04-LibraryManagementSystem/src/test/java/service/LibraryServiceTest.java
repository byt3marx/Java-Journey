package service;

import model.Book;
import model.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

}
