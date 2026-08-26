package service;

import model.Loan;
import model.Member;
import model.Book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibraryService {

    private int nextBookId = 1;
    private int nextMemberId = 1;
    private int nextLoanId = 1;

    private final List<Book> books = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();
    private final List<Loan> loans = new ArrayList<>();

    public Book addBook(String title,
                        String author,
                        int numberOfPages) {

        Book book = new Book(nextBookId, title, author, numberOfPages);
        books.add(book);
        nextBookId++;

        return book;
    }

    public Member addMember(String name,
                         String email,
                         String phoneNumber) {

        Member member = new Member(nextMemberId, name, email, phoneNumber);
        members.add(member);
        nextMemberId++;

        return member;
    }

    public List<Book> getBooks() {
        return List.copyOf(books);
    }

    public List<Member> getMembers() {
        return List.copyOf(members);
    }

    public List<Loan> getLoans() {
        return List.copyOf(loans);
    }

    public Optional<Book> findBookById(int id) {
        return books.stream()
                    .filter(book -> book.getId() == id)
                    .findFirst();
    }

    public Optional<Member> findMemberById(int id) {
        return members.stream()
                      .filter(member -> member.getId() == id)
                      .findFirst();
    }

    public Optional<Loan> findLoanById(int id) {
        return loans.stream()
                    .filter(loan -> loan.getId() == id)
                    .findFirst();
    }

    public Loan borrowBook(int bookId,
                           int memberId,
                           LocalDate borrowedDate,
                           LocalDate dueDate) {

        Book book = findBookById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found."));

        Member member = findMemberById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found."));

        if (isBookBorrowed(bookId)) {
            throw new IllegalArgumentException("Book is already borrowed.");
        }

        Loan loan = new Loan(
                nextLoanId,
                book,
                member,
                borrowedDate,
                dueDate,
                null);

        loans.add(loan);
        nextLoanId++;

        return loan;
    }

    private boolean isBookBorrowed(int bookId) {
        return loans.stream()
                .anyMatch(loan ->
                        loan.getBook().getId() == bookId
                                && loan.getReturnedDate() == null);
    }

    public Loan returnBook(int loanId, LocalDate returnedDate) {

        Loan loan = findLoanById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("Loan not found."));

        loan.returnBook(returnedDate);

        return loan;
    }

}



