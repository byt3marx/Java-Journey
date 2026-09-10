package storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Book;
import model.Loan;
import model.Member;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibraryFileService {

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .setPrettyPrinting()
            .create();

    public void save(List<Book> books,
                     List<Member> members,
                     List<Loan> loans,
                     Path filePath) {

        List<LoanRecord> loanRecords = loans.stream()
                .map(loan -> new LoanRecord(
                        loan.getId(),
                        loan.getBook().getId(),
                        loan.getMember().getId(),
                        loan.getBorrowedDate(),
                        loan.getDueDate(),
                        loan.getReturnedDate()
                ))
                .toList();

        LibraryData data = new LibraryData(
                books,
                members,
                loanRecords
        );

        String json = gson.toJson(data);

        try {
            Path parent = filePath.getParent();

            if (parent != null) {
                Files.createDirectories(parent);
            }

            Files.writeString(filePath, json);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save library data.", e);
        }
    }

    public LibraryData load(Path filePath) {
        if (!Files.exists(filePath)) {
            return new LibraryData(
                    List.of(),
                    List.of(),
                    List.of()
            );
        }
        try {
            String json = Files.readString(filePath);
            return gson.fromJson(json, LibraryData.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load library data.", e);
        }
    }

    private List<Loan> rebuildLoans(LibraryData data) {
        List<Loan> loans = new ArrayList<>();

        for (LoanRecord record : data.getLoans()) {
            Book book = data.getBooks().stream()
                    .filter(b -> b.getId() == record.getBookId())
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Book not found for loan record."));

            Member member = data.getMembers().stream()
                    .filter(m -> m.getId() == record.getMemberId())
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Member not found for loan record."));

            Loan loan = new Loan(
                    record.getId(),
                    book,
                    member,
                    record.getBorrowedDate(),
                    record.getDueDate(),
                    record.getReturnedDate()
            );

            loans.add(loan);
        }

        return loans;
    }

    public LibraryState loadState(Path filePath) {
        LibraryData data = load(filePath);

        List<Loan> loans = rebuildLoans(data);

        return new LibraryState(
                data.getBooks(),
                data.getMembers(),
                loans
        );
    }
}
