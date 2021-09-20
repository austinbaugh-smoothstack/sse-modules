package lms.domain;

import java.util.Set;

public class Borrower {
    private Integer cardNumber;
    private String name;
    private String address;
    private String phone;
    private Set<BookLoan> bookLoans;
    
    public Integer getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(final Integer cardNumber) {
        this.cardNumber = cardNumber;
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
    public String getPhone() {
        return phone;
    }
    public void setPhone(final String phone) {
        this.phone = phone;
    }
    public Set<BookLoan> getBookLoans() {
        return bookLoans;
    }
    public void setBookLoans(final Set<BookLoan> bookLoans) {
        this.bookLoans = bookLoans;
    }
}
