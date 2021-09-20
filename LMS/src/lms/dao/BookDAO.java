package lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lms.domain.Author;
import lms.domain.Book;
import lms.domain.Publisher;

public class BookDAO extends BaseDAO<Book> {
    
    @Override
    protected List<Book> extractData(final ResultSet resultSet) throws SQLException {
        final List<Book> books = new ArrayList<Book>();
        while(resultSet.next()) {
            final Book book = new Book();
            book.setId(resultSet.getInt("bookId"));
            book.setTitle(resultSet.getString("title"));
                
            final AuthorDAO authorDao = new AuthorDAO(getConnection());
            final Author author = authorDao.selectById(resultSet.getInt("authId"));
            book.setAuthor(author);
            
            final PublisherDAO publisherDao = new PublisherDAO(getConnection());
            final Publisher publisher = publisherDao.selectById(resultSet.getInt("pubId"));
            book.setPublisher(publisher);
            
            books.add(book);
        }
        return books;
    }

    public BookDAO(final Connection connection) {
        super(connection, "tbl_book");
    }

    public void insert(final Book book) throws SQLException {
        book.setId(nextId("bookId"));
        final String query = "insert into tbl_book (bookId, title, authId, pubId) values (?, ?, ?, ?)";
        final Object[] values = { book.getId(), book.getTitle(), book.getAuthor().getId(), book.getPublisher().getId() };
        save(query, values);
    }

    public void update(final Book book) throws SQLException {
        final String query = "update tbl_book set title = ?, authId = ?, pubId = ? where bookId = ?";
        final Object[] values = { book.getTitle(), book.getAuthor().getId(), book.getPublisher().getId(), book.getId() };
        save(query, values);
    }

    public void delete(final Book book) throws SQLException {
        final String query = "delete from tbl_book where bookId = ?";
        final Object[] values = { book.getId() };
        save(query, values);
    }

    public Book selectById(final Integer id) throws SQLException {
        final String query = "select * from tbl_book where bookId = ?";
        final Object[] values = { id };
        final List<Book> result = read(query, values);
        return result.isEmpty() ? null : result.get(0);
    }
}
