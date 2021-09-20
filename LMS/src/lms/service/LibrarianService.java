package lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import lms.dao.BookCopiesDAO;
import lms.dao.BookDAO;
import lms.dao.LibraryBranchDAO;
import lms.domain.Book;
import lms.domain.BookCopies;
import lms.domain.LibraryBranch;

class LibrarianService {
    private ConnectionUtil connUtil = new ConnectionUtil();
    
    void updateBranch(final LibraryBranch branch) throws SQLException {
        Connection connection = connUtil.getConnection();
        try {
            final LibraryBranchDAO branchDao = new LibraryBranchDAO(connection);
            System.out.println("You have chosen to update the Branch with Branch Id: " + branch.getId() + " and Branch Name: " + branch.getName());
            System.out.println("Enter ‘quit’ at any prompt to cancel operation.");
            System.out.println();
            
            System.out.println("Please enter new branch name or enter N/A for no change:");
            final String branchName = ScannerUtil.getInput();
            if(branchName.equalsIgnoreCase("quit")) {
                return;
            } else if(!branchName.equalsIgnoreCase("n/a")) {
                System.out.println("Please enter new branch address or enter N/A for no change:");
                final String branchAddress = ScannerUtil.getInput();
                if(branchAddress.equalsIgnoreCase("quit")) {
                    return;
                } else if(!branchAddress.equalsIgnoreCase("n/a")) {
                    branch.setName(branchName);
                    branch.setAddress(branchAddress);
                }
                branchDao.update(branch);
                connection.commit();
            }
        } catch (final SQLException exception) {
            System.out.println(exception);
            connection.rollback();
        } finally {
            connection.close();
        }
    }
    
    void updateBookCopies(final BookCopies bookCopies, final int numberOfCopies, final Connection connection) throws SQLException {
        final BookCopiesDAO bookCopiesDao = new BookCopiesDAO(connection);
        final LibraryBranch branch = bookCopies.getBranch();
        Set<BookCopies> allBookCopies = branch.getBookCopies();
        if(numberOfCopies == 0) {
            allBookCopies.remove(bookCopies);
            branch.setBookCopies(allBookCopies);
            bookCopiesDao.delete(bookCopies);
        } else {
            bookCopies.setNumberOfCopies(numberOfCopies);
            bookCopiesDao.update(bookCopies);
        }
        connection.commit();
    }
    
    void updateBookCopies(final LibraryBranch branch, final Book book) throws SQLException {
        Connection connection = connUtil.getConnection();
        try {
            Set<BookCopies> allBookCopies = branch.getBookCopies();
            final BookCopies bookCopies = allBookCopies
                .stream()
                .filter(copies -> copies.getBook().getId() == book.getId())
                .findAny()
                .orElse(null);
            final int originalValue = bookCopies != null ? bookCopies.getNumberOfCopies() : 0;
            System.out.println("Existing number of copies: " + originalValue);
            System.out.println("Enter ‘quit’ at any prompt to cancel operation.");
            System.out.println();
            
            System.out.println("Enter new number of copies:");
            final String input = ScannerUtil.getInput();
            if(input.equalsIgnoreCase("quit")) {
                return;
            } else {
                final int numberOfCopies = Integer.parseInt(input);
                if(bookCopies == null) {
                    if(numberOfCopies == 0) {
                        return;
                    }
                    final BookCopiesDAO bookCopiesDao = new BookCopiesDAO(connection);
                    final BookCopies newCopies = new BookCopies();
                    newCopies.setBook(book);
                    newCopies.setBranch(branch);
                    newCopies.setNumberOfCopies(numberOfCopies);
                    bookCopiesDao.insert(newCopies);
                    
                    allBookCopies.add(newCopies);
                    branch.setBookCopies(allBookCopies);
                } else {
                    updateBookCopies(bookCopies, numberOfCopies, connection);
                }
            }
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
            return new BookDAO(connection).selectAll();
        } catch (final SQLException exception) {
            System.out.println(exception);
            connection.rollback();
        } finally {
            connection.close();
        }
        return Collections.emptyList();
    }
}
