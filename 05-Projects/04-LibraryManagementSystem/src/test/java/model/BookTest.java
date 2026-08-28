package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BookTest {

    @Test
    void validBookIsCreated() {

        Book book = new Book(
                1,
                "A walk to remember",
                "Paul Walker",
                422
        );

        assertEquals(1, book.getId());
        assertEquals("A walk to remember", book.getTitle());
        assertEquals("Paul Walker", book.getAuthor());
        assertEquals(422, book.getNumberOfPages());
    }

    @Test
    void zeroIdThrows() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Book(
                        0,
                        "Dune",
                        "Frank Something",
                        412
                )
        );
    }

    @Test
    void negativeIdThrows() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Book(
                        -1,
                        "Dune",
                        "Frank Something",
                        411
                )
        );
    }

    @Test
    void nullTitleThrows() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Book(
                        4,
                        null,
                        "Jason Who",
                        15
                )
        );
    }

    @Test
    void blankTitleThrows() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Book(
                        5,
                        "  ",
                        "Somebody Somewhere",
                        32
                )
        );
    }

    @Test
    void nullAuthorThrows() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Book(
                        23,
                        "Beyond the horizon",
                        null,
                        31
                )
        );
    }

    @Test
    void blankAuthorThrows() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Book(
                        44,
                        "Deep blue sea",
                        "   ",
                        111
                )
        );
    }

    @Test
    void zeroNumberOfPagesThrows() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Book(
                        54,
                        "The wast fields",
                        "Sahara desert",
                        0
                )
        );
    }

    @Test
    void negativeNumberOfPagesThrows() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Book(
                        143,
                        "Under the leaves",
                        "Tall Tree",
                        -2
                )
        );
    }

}
