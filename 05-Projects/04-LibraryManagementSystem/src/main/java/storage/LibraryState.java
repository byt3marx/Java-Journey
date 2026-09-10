package storage;

import model.Book;
import model.Loan;
import model.Member;

import java.util.List;

public class LibraryState {

    private final List<Book> books;
    private final List<Member> members;
    private final List<Loan> loans;

    public LibraryState(List<Book> books,
                        List<Member> members,
                        List<Loan> loans) {

        this.books = books;
        this.members = members;
        this.loans = loans;
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Member> getMembers() {
        return members;
    }

    public List<Loan> getLoans() {
        return loans;
    }
}
