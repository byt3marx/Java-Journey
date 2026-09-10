package storage;

import model.Book;
import model.Member;

import java.util.List;

public class LibraryData {

    private final List<Book> books;
    private final List<Member> members;
    private final List<LoanRecord> loans;

    public LibraryData(List<Book> books,
                       List<Member> members,
                       List<LoanRecord> loans) {

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

    public List<LoanRecord> getLoans() {
        return loans;
    }
}
