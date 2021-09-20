package lms.domain;

import java.util.HashSet;
import java.util.Set;

public class LibraryBranch {
    private Integer id;
    private String name;
    private String address;
    private Set<BookCopies> bookCopies = new HashSet<BookCopies>();
    private Set<BookLoan> bookLoans = new HashSet<BookLoan>();
    
    public Integer getId() {
        return id;
    }
    public void setId(final Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(final String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(final String address) {
        this.address = address;
    }
    public Set<BookLoan> getBookLoans() {
        return bookLoans;
    }
    public void setBookLoans(final Set<BookLoan> bookLoans) {
        this.bookLoans = bookLoans;
    }
    public Set<BookCopies> getBookCopies() {
        return bookCopies;
    }
    public void setBookCopies(final Set<BookCopies> bookCopies) {
        this.bookCopies = bookCopies;
    }
}
