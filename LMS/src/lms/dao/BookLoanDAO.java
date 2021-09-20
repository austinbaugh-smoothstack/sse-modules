package lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lms.domain.Book;
import lms.domain.BookLoan;
import lms.domain.LibraryBranch;

public class BookLoanDAO extends BaseDAO<BookLoan> {
    
    @Override
    protected List<BookLoan> extractData(final ResultSet resultSet) throws SQLException {
        final List<BookLoan> bookLoans = new ArrayList<BookLoan>();
        while(resultSet.next()) {
            final BookLoan bookLoan = new BookLoan();
            bookLoan.setDateOut(resultSet.getTimestamp("dateOut"));
            bookLoan.setDueDate(resultSet.getTimestamp("dueDate"));
            
            final BookDAO bookDao = new BookDAO(getConnection());
            final Book book = bookDao.selectById(resultSet.getInt("bookId"));
            bookLoan.setBook(book);
            
            final LibraryBranchDAO libraryBranchDao = new LibraryBranchDAO(getConnection());
            final LibraryBranch branch = libraryBranchDao.selectById(resultSet.getInt("branchId"));
            bookLoan.setBranch(branch);
            
            bookLoans.add(bookLoan);
        }
        return bookLoans;
    }

    public BookLoanDAO(final Connection connection) {
        super(connection);
    }

    public void insert(final BookLoan bookLoan) throws SQLException {
        final String query = "insert into tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate) values (?, ?, ?, ?, ?)";
        final Object[] values = {
            bookLoan.getBook().getId(),
            bookLoan.getBranch().getId(),
            bookLoan.getBorrower().getCardNumber(),
            bookLoan.getDateOut(),
            bookLoan.getDueDate(),
        };
        save(query, values);
    }

    public void update(final BookLoan bookLoan) throws SQLException {
        final String query = "update tbl_book_loans set dateOut = ?, dueDate = ? where bookId = ? and branchId = ? and cardNo = ?";
        final Object[] values = {
            bookLoan.getDateOut(),
            bookLoan.getDueDate(),
            bookLoan.getBook().getId(),
            bookLoan.getBranch().getId(),
            bookLoan.getBorrower().getCardNumber(),
        };
        save(query, values);
    }

    public void delete(final BookLoan bookLoan) throws SQLException {
        final String query = "delete from tbl_book_loans where bookId = ? and branchId = ? and cardNo = ?";
        final Object[] values = {
            bookLoan.getBook().getId(),
            bookLoan.getBranch().getId(),
            bookLoan.getBorrower().getCardNumber(),
        };
        save(query, values);
    }

    public List<BookLoan> selectAll() throws SQLException {
        return read("select * from tbl_book_loans", new Object[]{});
    }

    public List<BookLoan> selectAllByBranchId(final Integer branchId) throws SQLException {
        final String query = "select * from tbl_book_loans where branchId = ?";
        final Object[] values = { branchId };
        return read(query, values);
    }

    public List<BookLoan> selectAllByBorrowerCardNumber(final Integer cardNumber) throws SQLException {
        final String query = "select * from tbl_book_loans where cardNo = ?";
        final Object[] values = { cardNumber };
        return read(query, values);
    }
}
