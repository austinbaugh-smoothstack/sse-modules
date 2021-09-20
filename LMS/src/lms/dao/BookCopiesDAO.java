package lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lms.domain.Book;
import lms.domain.BookCopies;

public class BookCopiesDAO extends BaseDAO<BookCopies> {
    
    @Override
    protected List<BookCopies> extractData(final ResultSet resultSet) throws SQLException {
        final List<BookCopies> bookCopiess = new ArrayList<BookCopies>();
        while(resultSet.next()) {
            final BookCopies bookCopies = new BookCopies();
            bookCopies.setNumberOfCopies(resultSet.getInt("noOfCopies"));
            
            final BookDAO bookDao = new BookDAO(getConnection());
            final Book book = bookDao.selectById(resultSet.getInt("bookId"));
            bookCopies.setBook(book);
            
            bookCopiess.add(bookCopies);
        }
        return bookCopiess;
    }

    public BookCopiesDAO(final Connection connection) {
        super(connection, "tbl_book_copies");
    }

    public void insert(final BookCopies bookCopies) throws SQLException {
        final String query = "insert into tbl_book_copies (bookId, branchId, noOfCopies) values (?, ?, ?)";
        final Object[] values = {
            bookCopies.getBook().getId(),
            bookCopies.getBranch().getId(),
            bookCopies.getNumberOfCopies(),
        };
        save(query, values);
    }

    public void update(final BookCopies bookCopies) throws SQLException {
        final String query = "update tbl_book_copies set noOfCopies = ? where bookId = ? and branchId = ?";
        final Object[] values = {
            bookCopies.getNumberOfCopies(),
            bookCopies.getBook().getId(),
            bookCopies.getBranch().getId(),
        };
        save(query, values);
    }

    public void delete(final BookCopies bookCopies) throws SQLException {
        final String query = "delete from tbl_book_copies where bookId = ? and branchId = ?";
        final Object[] values = {
            bookCopies.getBook().getId(),
            bookCopies.getBranch().getId(),
        };
        save(query, values);
    }

    public List<BookCopies> selectAllByBranchId(final Integer branchId) throws SQLException {
        final String query = "select * from tbl_book_copies where branchId = ?";
        final Object[] values = { branchId };
        return read(query, values);
    }
}
