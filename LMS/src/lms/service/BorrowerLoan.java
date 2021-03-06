package lms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import lms.domain.Book;
import lms.domain.BookCopies;
import lms.domain.BookLoan;
import lms.domain.Borrower;
import lms.domain.LibraryBranch;

class BorrowerLoan extends MenuItem {

    private String label;
    private MenuType menuType;
    private Borrower borrower;
    // Used for checkout operation
    private LibraryBranch branch = null;
    private BookCopies bookCopy = null;
    // used for return operation
    private BookLoan bookLoan = null;
    
    protected BorrowerLoan(final String label, final MenuType menuType, final Borrower borrower) {
        this.label = label;
        this.menuType = menuType;
        this.borrower = borrower;
    }
    
    // Constructors for checkout operation
    protected BorrowerLoan(final String label, final MenuType menuType, final Borrower borrower, final LibraryBranch branch) {
        this(label, menuType, borrower);
        this.branch = branch;
    }
    protected BorrowerLoan(final String label, final MenuType menuType, final Borrower borrower, final BookCopies bookCopy) {
        this(label, menuType, borrower);
        this.bookCopy = bookCopy;
    }
    
    // Constructor for return operation
    protected BorrowerLoan(final String label, final MenuType menuType, final BookLoan bookLoan) {
        this(label, menuType, bookLoan.getBorrower());
        this.branch = bookLoan.getBranch();
        this.bookLoan = bookLoan;
    }

    @Override
    protected String getLabel() {
        return this.label;
    }
    
    @Override
    protected void action() {
        switch(menuType) {
            case BORR_LOAN_CHECKOUT:
                try {
                    final BorrowerService service = new BorrowerService();
                    if(bookCopy == null) {
                        final List<MenuItem> menuItems = new ArrayList<MenuItem>();
                        final Menu menu;
                        if(branch == null) {
                            final List<LibraryBranch> branches = service.readAllBranches();
                            for(final LibraryBranch branch : branches) {
                                menuItems.add(new BorrowerLoan(branch.getName() + ", " + branch.getAddress(), MenuType.BORR_LOAN_CHECKOUT, borrower, branch));
                            }
                            
                            menu = new Menu("Pick the Branch you want to check out from:", menuItems, MenuType.BORR_LOAN);
                        } else {
                            final Set<BookCopies> bookCopies = branch.getBookCopies();
                            for(final BookCopies bookCopy : bookCopies) {
                                final Book book = bookCopy.getBook();
                                final String author = book.getAuthor().getName();
                                menuItems.add(new BorrowerLoan(book.getTitle() + " by " + author, MenuType.BORR_LOAN_CHECKOUT, borrower, bookCopy));
                            }
                            
                            menu = new Menu("Pick the Book you want to add copies of, to your branch:", menuItems, MenuType.BORR_LOAN);
                        }
                        Menu.putMenu(menuType, menu);
                        Menu.prompt(menuType);
                    } else {
                        service.checkoutBook(borrower, bookCopy);
                        branch = null;
                        bookCopy = null;
                        Menu.prompt(MenuType.BORR_LOAN);
                    }
                } catch(final SQLException exception) {
                    exception.printStackTrace();
                }
                break;
            case BORR_LOAN_RETURN:
                if(bookLoan == null) {
                    final List<MenuItem> menuItems = new ArrayList<MenuItem>();
                    final Set<BookLoan> bookLoans = borrower.getBookLoans();
                    for(final BookLoan bookLoan : bookLoans) {
                        menuItems.add(new BorrowerLoan(bookLoan.getBook().getTitle() + " from " + bookLoan.getBranch().getName() + " (Due: " + bookLoan.getDueDate() + ")", MenuType.BORR_LOAN_RETURN, bookLoan));
                    }
                    
                    final Menu menu = new Menu("Pick the book you would like to return:", menuItems, MenuType.BORR_LOAN);
                    Menu.putMenu(menuType, menu);
                    Menu.prompt(menuType);
                } else {
                    try {
                        final BorrowerService service = new BorrowerService();
                        service.returnBook(bookLoan);
                    } catch (final SQLException exception) {
                        exception.printStackTrace();
                    }
                    bookLoan = null;
                    Menu.prompt(MenuType.BORR_LOAN);
                }
                break;
            default:
                throw new IllegalArgumentException(menuType + " is not a valid librarian menu");
        }
    }
}
