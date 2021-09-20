package lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lms.dao.BookCopiesDAO;
import lms.dao.BookLoanDAO;
import lms.dao.BorrowerDAO;
import lms.dao.LibraryBranchDAO;
import lms.domain.BookCopies;
import lms.domain.BookLoan;
import lms.domain.Borrower;
import lms.domain.LibraryBranch;

class BorrowerService {
    private ConnectionUtil connUtil = new ConnectionUtil();
    
    Borrower readBorrowerByCardNumber() throws SQLException {
        Connection connection = connUtil.getConnection();
        try {
            System.out.println("Enter your Card Number:");
            
            final BorrowerDAO borrowerDao = new BorrowerDAO(connection);
            final BookLoanDAO bookLoanDao = new BookLoanDAO(connection);
            
            Borrower borrower = borrowerDao.selectByCardNumber(Integer.parseInt(LMS.getInput()));
            while(borrower == null) {
                System.out.println("Not a valid card number, please try again");
                borrower = borrowerDao.selectByCardNumber(Integer.parseInt(LMS.getInput()));
            }
            
            final Borrower finalBorrower = borrower;
            final Set<BookLoan> bookLoans = bookLoanDao
                    .selectAllByBorrowerCardNumber(borrower.getCardNumber())
                    .stream()
                    .peek(bookCopy -> bookCopy.setBorrower(finalBorrower))
                    .collect(Collectors.toSet());
            borrower.setBookLoans(bookLoans);
            
            return finalBorrower;
        } catch (final SQLException exception) {
            System.out.println(exception);
            connection.rollback();
        } finally {
            connection.close();
        }
        return null;
    }

    void returnBook(final BookLoan bookLoan) throws SQLException {
        Connection connection = connUtil.getConnection();
        try {
            final BookLoanDAO bookLoanDao = new BookLoanDAO(connection);
            final Borrower borrower = bookLoan.getBorrower();
            Set<BookLoan> loans = borrower.getBookLoans();
            loans.remove(bookLoan);
            borrower.setBookLoans(loans);
            
            bookLoanDao.delete(bookLoan);
            
            //TODO add loaned book to library branch's book copies
            
            connection.commit();
        } catch (final SQLException exception) {
            System.out.println(exception);
            connection.rollback();
        } finally {
            connection.close();
        }
    }
    
    void checkoutBook(final Borrower borrower, final BookCopies bookCopy) throws SQLException {
        Connection connection = connUtil.getConnection();
        try {
            final BookLoanDAO bookLoanDao = new BookLoanDAO(connection);
            final BookLoan bookLoan = new BookLoan();
            
            bookLoan.setBorrower(borrower);
            bookLoan.setBranch(bookCopy.getBranch());
            bookLoan.setBook(bookCopy.getBook());
            
            final ZonedDateTime today = LocalDate.now().atStartOfDay(ZoneId.systemDefault());
            final Date dateOut = Date.from(today.toInstant());
            bookLoan.setDateOut(new Timestamp(dateOut.getTime()));
            
            final Date dueDate = Date.from(today.plusDays(7).toInstant());
            bookLoan.setDueDate(new Timestamp(dueDate.getTime()));
            
            bookLoanDao.insert(bookLoan);
            
            final LibrarianService service = new LibrarianService();
            final int numberOfCopies = bookCopy.getNumberOfCopies() - 1;
            service.updateBookCopies(bookCopy, numberOfCopies, connection);
            
            connection.commit();
        } catch (final SQLException exception) {
            System.out.println(exception);
            connection.rollback();
        } finally {
            connection.close();
        }
    }
    
    List<LibraryBranch> readAllBranches() throws SQLException {
        Connection connection = connUtil.getConnection();
        try {
            final LibraryBranchDAO branchDao = new LibraryBranchDAO(connection);
            final List<LibraryBranch> branches = branchDao.selectAll();
            
            final BookCopiesDAO bookCopiesDao = new BookCopiesDAO(connection);
            for(final LibraryBranch branch : branches) {
                final Set<BookCopies> bookCopies = bookCopiesDao
                        .selectAllByBranchId(branch.getId())
                        .stream()
                        .peek(bookCopy -> bookCopy.setBranch(branch))
                        .collect(Collectors.toSet());
                branch.setBookCopies(bookCopies);
            }
            
            return branches;
        } catch (final SQLException exception) {
            System.out.println(exception);
            connection.rollback();
        } finally {
            connection.close();
        }
        return Collections.emptyList();
    }
}
