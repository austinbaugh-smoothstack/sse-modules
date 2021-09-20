package lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import lms.dao.AuthorDAO;
import lms.dao.BookDAO;
import lms.dao.GenreDAO;
import lms.domain.Book;

class AdminService {
    private ConnectionUtil connUtil = new ConnectionUtil();
    
    void addBook() throws SQLException {
        final Book book = new Book();
        System.out.println("Book title:");
        book.setTitle(LMS.getInput());
        // TODO select publisher, author, and genres
        
        Connection connection = connUtil.getConnection();
        try {
            final BookDAO bookDao = new BookDAO(connection);
            //publisherDao.save(publisher)
            //bookDao.addBook(book);
            connection.commit();
        } catch (final SQLException exception) {
            System.out.println(exception);
            connection.rollback();
        } finally {
            connection.close();
        }
    }
    
    List<Book> readAllBooks() throws SQLException {
        Connection connection = connUtil.getConnection();
        try {
            final BookDAO bookDao = new BookDAO(connection);
            final List<Book> books = bookDao.selectAll();
            
            final GenreDAO genreDao = new GenreDAO(connection);
            final AuthorDAO authorDao = new AuthorDAO(connection);
            for(final Book book : books) {
                book.setGenres(genreDao.selectAllByBookId(book.getId()));
                book.setAuthors(authorDao.selectAllByBookId(book.getId()));
            }
            
            return books;
        } catch (final SQLException exception) {
            System.out.println(exception);
            connection.rollback();
        } finally {
            connection.close();
        }
        return Collections.emptyList();
    }
    
    void updateBook() throws SQLException {
        Connection connection = connUtil.getConnection();
        try {
            final BookDAO bookDao = new BookDAO(connection);
            // TODO update book
            final Book book = new Book();
            bookDao.update(book);
        } catch (final SQLException exception) {
            System.out.println(exception);
            connection.rollback();
        } finally {
            connection.close();
        }
    }
    
    void deleteBook() throws SQLException {
        Connection connection = connUtil.getConnection();
        try {
            final BookDAO bookDao = new BookDAO(connection);
            // TODO delete book
            final Book book = new Book();
            bookDao.delete(book);
        } catch (final SQLException exception) {
            System.out.println(exception);
            connection.rollback();
        } finally {
            connection.close();
        }
    }
}
