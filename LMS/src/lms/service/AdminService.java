package lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    
    void updateBook(final Optional<Integer> id) throws SQLException {
        Connection connection = connUtil.getConnection();
        final BookDAO bookDao = new BookDAO(connection);
        try {
            final Book book;
            if(id.isPresent()) {
                book = bookDao.selectById(id.get());
            } else {
                book = new Book();
            }
            
            System.out.print("Title: ");
            book.setTitle(ScannerUtil.getInput());
            
            final List<Author> authors = readAllAuthors();
            int index = 1;
            for(final Author author : authors) {
                System.out.println(index + ") " + author.getName());
                index++;
            }
            System.out.print("Select an author or cancel operation with 'quit': ");
            final String authorSelection = ScannerUtil.getInput();
            if(authorSelection.equals("quit")) {
                return;
            }
            book.setAuthor(authors.get(Integer.parseInt(authorSelection)));
            
            // TODO add genres
            
            final List<Publisher> publishers = readAllPublishers();
            index = 1;
            for(final Publisher publisher : publishers) {
                System.out.println(index + ") " + publisher.getName());
                index++;
            }
            System.out.print("Select a publisher or cancel operation with 'quit': ");
            final String publisherSelection = ScannerUtil.getInput();
            if(publisherSelection.equals("quit")) {
                return;
            }
            book.setPublisher(publishers.get(Integer.parseInt(publisherSelection)));
            
            if(id.isPresent()) {
                bookDao.update(book);
            } else {
                bookDao.insert(book);
            }
            connection.commit();
        } catch (final SQLException exception) {
            System.out.println(exception);
            connection.rollback();
        } finally {
            connection.close();
        }
    }
            
    void updateAuthor(final Optional<Integer> id) throws SQLException {
        Connection connection = connUtil.getConnection();
        final AuthorDAO authorDao = new AuthorDAO(connection);
        try {
            final Author author;
            if(id.isPresent()) {
                author = authorDao.selectById(id.get());
            } else {
                author = new Author();
            }
            
            System.out.print("Name: ");
            author.setName(ScannerUtil.getInput());
            
            if(id.isPresent()) {
                authorDao.update(author);
            } else {
                authorDao.insert(author);
            }
            connection.commit();
        } catch (final SQLException exception) {
            System.out.println(exception);
            connection.rollback();
        } finally {
            connection.close();
        }
    }
            
    void updateGenre(final Optional<Integer> id) throws SQLException {
        Connection connection = connUtil.getConnection();
        final GenreDAO genreDao = new GenreDAO(connection);
        try {
            final Genre genre;
            if(id.isPresent()) {
                genre = genreDao.selectById(id.get());
            } else {
                genre = new Genre();
            }
            
            System.out.print("Genre: ");
            genre.setName(ScannerUtil.getInput());
            
            if(id.isPresent()) {
                genreDao.update(genre);
            } else {
                genreDao.insert(genre);
            }
            connection.commit();
        } catch (final SQLException exception) {
            System.out.println(exception);
            connection.rollback();
        } finally {
            connection.close();
        }
    }
            
    void updatePublisher(final Optional<Integer> id) throws SQLException {
        Connection connection = connUtil.getConnection();
        final PublisherDAO publisherDao = new PublisherDAO(connection);
        try {
            final Publisher publisher;
            if(id.isPresent()) {
                publisher = publisherDao.selectById(id.get());
            } else {
                publisher = new Publisher();
            }
            
            System.out.print("Name: ");
            publisher.setName(ScannerUtil.getInput());
            
            System.out.print("Address: ");
            publisher.setAddress(ScannerUtil.getInput());
            
            System.out.print("Phone: ");
            publisher.setPhone(ScannerUtil.getInput());
            
            if(id.isPresent()) {
                publisherDao.update(publisher);
            } else {
                publisherDao.insert(publisher);
            }
            connection.commit();
        } catch (final SQLException exception) {
            System.out.println(exception);
            connection.rollback();
        } finally {
            connection.close();
        }
    }
            
    void updateBranch(final Optional<Integer> id) throws SQLException {
        Connection connection = connUtil.getConnection();
        final LibraryBranchDAO branchDao = new LibraryBranchDAO(connection);
        try {
            final LibraryBranch branch;
            if(id.isPresent()) {
                branch = branchDao.selectById(id.get());
            } else {
                branch = new LibraryBranch();
            }
            
            System.out.print("Name: ");
            branch.setName(ScannerUtil.getInput());
            
            System.out.print("Address: ");
            branch.setAddress(ScannerUtil.getInput());
            
            if(id.isPresent()) {
                branchDao.update(branch);
            } else {
                branchDao.insert(branch);
            }
            connection.commit();
        } catch (final SQLException exception) {
            System.out.println(exception);
            connection.rollback();
        } finally {
            connection.close();
        }
    }
            
    void updateBorrower(final Optional<Integer> id) throws SQLException {
        Connection connection = connUtil.getConnection();
        final BorrowerDAO borrowerDao = new BorrowerDAO(connection);
        try {
            final Borrower borrower;
            if(id.isPresent()) {
                borrower = borrowerDao.selectByCardNumber(id.get());
            } else {
                borrower = new Borrower();
            }
            
            System.out.print("Name: ");
            borrower.setName(ScannerUtil.getInput());
            
            System.out.print("Address: ");
            borrower.setAddress(ScannerUtil.getInput());
            
            System.out.print("Phone: ");
            borrower.setPhone(ScannerUtil.getInput());
            
            
            if(id.isPresent()) {
                borrowerDao.update(borrower);
            } else {
                borrowerDao.insert(borrower);
            }
            connection.commit();
        } catch (final SQLException exception) {
            System.out.println(exception);
            connection.rollback();
        } finally {
            connection.close();
        }
    }
    
    void readBook(final Integer id) throws SQLException {
        Connection connection = connUtil.getConnection();
        try {
            final BookDAO bookDao = new BookDAO(connection);
            final Book book = bookDao.selectById(id);
            final String format = "%10s: %s %n";
            
            System.out.format(format, "ID", book.getId());
            System.out.format(format, "Title", book.getTitle());
            System.out.format(format, "Author", book.getAuthor().getName());
            System.out.format(format, "Publisher", book.getPublisher().getName());
            
            final List<Genre> genres = book.getGenres();
            System.out.format(format, "Genre", genres.isEmpty() ? "(none)" : book.getGenres().get(0).getName());
        } catch (final SQLException exception) {
            System.out.println(exception);
            connection.rollback();
        } finally {
            connection.close();
        }
    }
    
    void readAuthor(final Integer id) throws SQLException {
        Connection connection = connUtil.getConnection();
        try {
            final AuthorDAO authorDao = new AuthorDAO(connection);
            final Author author = authorDao.selectById(id);
            final String format = "%7s: %s %n";
            
            System.out.format(format, "ID", author.getId());
            System.out.format(format, "Name", author.getName());
        } catch (final SQLException exception) {
            System.out.println(exception);
            connection.rollback();
        } finally {
            connection.close();
        }
    }
    
    void readGenre(final Integer id) throws SQLException {
        Connection connection = connUtil.getConnection();
        try {
            final GenreDAO genreDao = new GenreDAO(connection);
            final Genre genre = genreDao.selectById(id);
            final String format = "%7s: %s %n";
            
            System.out.format(format, "ID", genre.getId());
            System.out.format(format, "Genre", genre.getName());
        } catch (final SQLException exception) {
            System.out.println(exception);
            connection.rollback();
        } finally {
            connection.close();
        }
    }
    
    void readPublisher(final Integer id) throws SQLException {
        Connection connection = connUtil.getConnection();
        try {
            final PublisherDAO publisherDao = new PublisherDAO(connection);
            final Publisher publisher = publisherDao.selectById(id);
            final String format = "%10s: %s %n";
            
            System.out.format(format, "ID", publisher.getId());
            System.out.format(format, "Name", publisher.getName());
            System.out.format(format, "Address", publisher.getAddress());
            System.out.format(format, "Phone", publisher.getPhone());
        } catch (final SQLException exception) {
            System.out.println(exception);
            connection.rollback();
        } finally {
            connection.close();
        }
    }
    
    void readBranch(final Integer id) throws SQLException {
        Connection connection = connUtil.getConnection();
        try {
            final LibraryBranchDAO branchDao = new LibraryBranchDAO(connection);
            final LibraryBranch branch = branchDao.selectById(id);
            final String format = "%10s: %s %n";
            
            System.out.format(format, "ID", branch.getId());
            System.out.format(format, "Name", branch.getName());
            System.out.format(format, "Address", branch.getAddress());
        } catch (final SQLException exception) {
            System.out.println(exception);
            connection.rollback();
        } finally {
            connection.close();
        }
    }
    
    void readBorrower(final Integer id) throws SQLException {
        Connection connection = connUtil.getConnection();
        try {
            final BorrowerDAO borrowerDao = new BorrowerDAO(connection);
            final Borrower borrower = borrowerDao.selectByCardNumber(id);
            final String format = "%15s: %s %n";
            
            System.out.format(format, "Card Number", borrower.getCardNumber());
            System.out.format(format, "Name", borrower.getName());
            System.out.format(format, "Address", borrower.getAddress());
            System.out.format(format, "Phone", borrower.getPhone());
        } catch (final SQLException exception) {
            System.out.println(exception);
            connection.rollback();
        } finally {
            connection.close();
        }
    }
    
    void deleteBook(final Integer id) throws SQLException {
        Connection connection = connUtil.getConnection();
        final BookDAO bookDao = new BookDAO(connection);
        try {
            final Book book = bookDao.selectById(id);
            
            bookDao.delete(book);
            connection.commit();
        } catch (final SQLException exception) {
            System.out.println(exception);
            connection.rollback();
        } finally {
            connection.close();
        }
    }
    void deleteAuthor(final Integer id) throws SQLException {
        Connection connection = connUtil.getConnection();
        final AuthorDAO authorDao = new AuthorDAO(connection);
        try {
            final Author author = authorDao.selectById(id);
            
            authorDao.delete(author);
            connection.commit();
        } catch (final SQLException exception) {
            System.out.println(exception);
            connection.rollback();
        } finally {
            connection.close();
        }
    }
    void deleteGenre(final Integer id) throws SQLException {
        Connection connection = connUtil.getConnection();
        final GenreDAO genreDao = new GenreDAO(connection);
        try {
            final Genre genre = genreDao.selectById(id);
            
            genreDao.delete(genre);
            connection.commit();
        } catch (final SQLException exception) {
            System.out.println(exception);
            connection.rollback();
        } finally {
            connection.close();
        }
    }
    void deletePublisher(final Integer id) throws SQLException {
        Connection connection = connUtil.getConnection();
        final PublisherDAO publisherDao = new PublisherDAO(connection);
        try {
            final Publisher publisher = publisherDao.selectById(id);
            
            publisherDao.delete(publisher);
            connection.commit();
        } catch (final SQLException exception) {
            System.out.println(exception);
            connection.rollback();
        } finally {
            connection.close();
        }
    }
    void deleteBranch(final Integer id) throws SQLException {
        Connection connection = connUtil.getConnection();
        final LibraryBranchDAO branchDao = new LibraryBranchDAO(connection);
        try {
            final LibraryBranch branch = branchDao.selectById(id);
            
            branchDao.delete(branch);
            connection.commit();
        } catch (final SQLException exception) {
            System.out.println(exception);
            connection.rollback();
        } finally {
            connection.close();
        }
    }
    void deleteBorrower(final Integer id) throws SQLException {
        Connection connection = connUtil.getConnection();
        final BorrowerDAO borrowerDao = new BorrowerDAO(connection);
        try {
            final Borrower borrower = borrowerDao.selectByCardNumber(id);
            
            borrowerDao.delete(borrower);
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
