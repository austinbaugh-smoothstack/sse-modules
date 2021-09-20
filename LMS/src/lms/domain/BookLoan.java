package lms.domain;

import java.sql.Timestamp;

public class BookLoan {
    private Book book;
    private LibraryBranch branch;
    private Borrower borrower;
    private Timestamp dueDate;
    private Timestamp dateOut;
    
    public Book getBook() {
        return book;
    }
    public void setBook(final Book book) {
        this.book = book;
    }
    public LibraryBranch getBranch() {
        return branch;
    }
    public void setBranch(final LibraryBranch branch) {
        this.branch = branch;
    }
    public Borrower getBorrower() {
        return borrower;
    }
    public void setBorrower(final Borrower borrower) {
        this.borrower = borrower;
    }
    public Timestamp getDueDate() {
        return dueDate;
    }
    public void setDueDate(final Timestamp dueDate) {
        this.dueDate = dueDate;
    }
    public Timestamp getDateOut() {
        return dateOut;
    }
    public void setDateOut(final Timestamp dateOut) {
        this.dateOut = dateOut;
    }
}
