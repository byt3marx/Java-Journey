package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MemberTest {

    @Test
    void validMemberIsCreated() {

        Member member = new Member(
                1,
                "Liam",
                "liam@gmail.com",
                "031-111-112"
        );

        assertEquals(1, member.getId());
        assertEquals("Liam", member.getName());
        assertEquals("liam@gmail.com", member.getEmail());
        assertEquals("031-111-112", member.getPhoneNumber());
    }

    @Test
    void zeroIdThrows() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Member(
                        0,
                        "Liam",
                        "liam@gmail.com",
                        "031-111-112"
                )
        );
    }

    @Test
    void negativeIdThrows() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Member(
                        -3,
                        "Liam",
                        "liam@gmail.com",
                        "031-111-112"
                )
        );
    }

    @Test
    void nullNameThrows() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Member(
                        6,
                        null,
                        "something@gmail.com",
                        "041-111-112"
                )
        );
    }

    @Test
    void blankNameThrows() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Member(
                        6,
                        "   ",
                        "someone@gmail.com",
                        "041-111-112"
                )
        );
    }

    @Test
    void nullEmailThrows() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Member(
                        10,
                        "Brian",
                        null,
                        "041-111-112"
                )
        );
    }

    @Test
    void blankEmailThrows() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Member(
                        11,
                        "Brian",
                        "   ",
                        "031-111-112"
                )
        );
    }

    @Test
    void emailWithoutAtThrows() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Member(
                        1,
                        "Liam",
                        "liamgmail.com",
                        "031-111-112"
                )
        );
    }

    @Test
    void emailWithMultipleAtThrows() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Member(
                        1,
                        "Miha",
                        "miha@@gmail.com",
                        "000-000-000"
                )
        );
    }

    @Test
    void emailStartsWithAtThrows() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Member(
                        1,
                        "Nina",
                        "@ninagmail.com",
                        "000-000-000"
                )
        );
    }

    @Test
    void emailWithoutDotAfterAtThrows() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Member(
                        1,
                        "Gregor",
                        "grega@gmailcom",
                        "000-000-000"
                )
        );
    }

    @Test
    void emailWithDotImmediatelyAfterAtThrows() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Member(
                        1,
                        "Luka",
                        "luka@.com",
                        "000-000-000"
                )
        );
    }

    @Test
    void emailEndsWithDotThrows() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Member(
                        1,
                        "Rok",
                        "rok@gmailcom.",
                        "000-000-000"
                )
        );
    }

    @Test
    void nullPhoneNumberThrows() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Member(
                        1,
                        "Denis",
                        "danis@gmail.com",
                        null
                )
        );
    }

    @Test
    void blankPhoneNumberThrows() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Member(
                        1,
                        "Leon",
                        "leon@gmail.com",
                        "   "
                )
        );
    }

    @Test
    void invalidCharactersInPhoneNumberThrows() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Member(
                        1,
                        "Uroš",
                        "uro@gmail.com",
                        "031-32L-333"
                )
        );
    }

    @Test
    void noDigitsInPhoneNumberThrows() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Member(
                        1,
                        "Bojan",
                        "bojko@gmail.com",
                        "++--"
                )
        );
    }

}
