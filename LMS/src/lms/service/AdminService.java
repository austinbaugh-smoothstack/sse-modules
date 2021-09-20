package lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import lms.dao.AuthorDAO;
import lms.dao.BookDAO;
import lms.dao.BorrowerDAO;
import lms.dao.GenreDAO;
import lms.dao.LibraryBranchDAO;
import lms.dao.PublisherDAO;
import lms.domain.Author;
import lms.domain.Book;
import lms.domain.Borrower;
import lms.domain.Genre;
import lms.domain.LibraryBranch;
import lms.domain.Publisher;

class AdminService {
    private ConnectionUtil connUtil = new ConnectionUtil();
    
    List<Book> readAllBooks() throws SQLException {
        Connection connection = connUtil.getConnection();
        try {
            final BookDAO bookDao = new BookDAO(connection);
            final List<Book> books = bookDao.selectAll();
            
            final GenreDAO genreDao = new GenreDAO(connection);
            for(final Book book : books) {
                book.setGenres(genreDao.selectAllByBookId(book.getId()));
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
    
    List<Author> readAllAuthors() throws SQLException {
        Connection connection = connUtil.getConnection();
        try {
            return new AuthorDAO(connection).selectAll();
        } catch (final SQLException exception) {
            System.out.println(exception);
            connection.rollback();
        } finally {
            connection.close();
        }
        return Collections.emptyList();
    }
    
    List<Genre> readAllGenres() throws SQLException {
        Connection connection = connUtil.getConnection();
        try {
            return new GenreDAO(connection).selectAll();
        } catch (final SQLException exception) {
            System.out.println(exception);
            connection.rollback();
        } finally {
            connection.close();
        }
        return Collections.emptyList();
    }
    
    List<Publisher> readAllPublishers() throws SQLException {
        Connection connection = connUtil.getConnection();
        try {
            return new PublisherDAO(connection).selectAll();
        } catch (final SQLException exception) {
            System.out.println(exception);
            connection.rollback();
        } finally {
            connection.close();
        }
        return Collections.emptyList();
    }
    
    List<LibraryBranch> readAllBranches() throws SQLException {
        Connection connection = connUtil.getConnection();
        try {
            return new LibraryBranchDAO(connection).selectAll();
        } catch (final SQLException exception) {
            System.out.println(exception);
            connection.rollback();
        } finally {
            connection.close();
        }
        return Collections.emptyList();
    }
    
    List<Borrower> readAllBorrowers() throws SQLException {
        Connection connection = connUtil.getConnection();
        try {
            return new BorrowerDAO(connection).selectAll();
        } catch (final SQLException exception) {
            System.out.println(exception);
            connection.rollback();
        } finally {
            connection.close();
        }
        return Collections.emptyList();
    }
}
