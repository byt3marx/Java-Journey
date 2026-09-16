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

    public Book editBookTitle(int id, String title) {
        Book book = getBookOrThrow(id);
        book.updateTitle(title);
        return book;
    }

    public Book editBookAuthor(int id, String author) {
        Book book = getBookOrThrow(id);
        book.updateAuthor(author);
        return book;
    }

    public Book editBookNumberOfPages(int id, int numberOfPages) {
        Book book = getBookOrThrow(id);
        book.updateNumberOfPages(numberOfPages);
        return book;
    }

    public Book removeBook(int id) {
        Book book = getBookOrThrow(id);

        if (isBookBorrowed(id)) {
            throw new IllegalArgumentException("Cannot remove a book with an active loan.");
        }

        books.remove(book);
        return book;
    }

    private Book getBookOrThrow(int id) {
        return findBookById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found."));
    }

    public Member addMember(String name,
                         String email,
                         String phoneNumber) {

        Member member = new Member(nextMemberId, name, email, phoneNumber);
        members.add(member);
        nextMemberId++;

        return member;
    }

    public Member editMemberName(int id, String name) {
        Member member =getMemberOrThrow(id);
        member.updateName(name);
        return member;
    }

    public Member editMemberEmail(int id, String email) {
        Member member = getMemberOrThrow(id);
        member.updateEmail(email);
        return member;
    }

    public Member editMemberPhoneNumber(int id, String phoneNumber) {
        Member member = getMemberOrThrow(id);
        member.updatePhoneNumber(phoneNumber);
        return member;
    }

    public Member removeMember(int id) {
        Member member = getMemberOrThrow(id);

        if (hasActiveLoan(id)) {
            throw new IllegalArgumentException(
                    "Cannot remove a member with an active loan.");
        }

        members.remove(member);
        return member;
    }

    private boolean hasActiveLoan(int memberId) {
        return loans.stream()
                .anyMatch(loan ->
                        loan.getMember().getId() == memberId
                                && loan.getReturnedDate() == null
                );
    }

    private Member getMemberOrThrow(int id) {
        return findMemberById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found."));
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

    public List<Loan> getActiveLoans() {
        return loans.stream()
                .filter(loan -> loan.getReturnedDate() == null)
                .toList();
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

    public boolean isBookBorrowed(int bookId) {
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

    public void loadData(List<Book> books,
                         List<Member> members,
                         List<Loan> loans) {

        this.books.clear();
        this.members.clear();
        this.loans.clear();

        this.books.addAll(books);
        this.members.addAll(members);
        this.loans.addAll(loans);

        nextBookId = books.stream()
                .mapToInt(Book::getId)
                .max()
                .orElse(0) + 1;

        nextMemberId = members.stream()
                .mapToInt(Member::getId)
                .max()
                .orElse(0) + 1;

        nextLoanId = loans.stream()
                .mapToInt(Loan::getId)
                .max()
                .orElse(0) + 1;
    }

}



