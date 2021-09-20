package lms.domain;

public class BookCopies {
    private Book book;
    private LibraryBranch branch;
    private Integer numberOfCopies;
    
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
    public Integer getNumberOfCopies() {
        return numberOfCopies;
    }
    public void setNumberOfCopies(final Integer numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }
}
