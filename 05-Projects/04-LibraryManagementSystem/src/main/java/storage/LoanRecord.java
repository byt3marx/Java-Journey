package storage;

import java.time.LocalDate;

public class LoanRecord {

    private final int id;
    private final int bookId;
    private final int memberId;
    private final LocalDate borrowedDate;
    private final LocalDate dueDate;
    private final LocalDate returnedDate;

    public LoanRecord(int id,
                      int bookId,
                      int memberId,
                      LocalDate borrowedDate,
                      LocalDate dueDate,
                      LocalDate returnedDate) {

        this.id = id;
        this.bookId = bookId;
        this.memberId = memberId;
        this.borrowedDate = borrowedDate;
        this.dueDate = dueDate;
        this.returnedDate = returnedDate;
    }

    public int getId() {
        return id;
    }

    public int getBookId() {
        return bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }
}
