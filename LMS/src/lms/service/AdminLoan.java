package lms.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import lms.domain.BookLoan;
import lms.domain.Borrower;

class AdminLoan extends MenuItem {
    
    private Borrower borrower;
    private BookLoan bookLoan = null;

    AdminLoan(final Borrower borrower) {
        this.borrower = borrower;
    }

    AdminLoan(final Borrower borrower, final BookLoan bookLoan) {
        this(borrower);
        this.bookLoan = bookLoan;
    }

    @Override
    protected String getLabel() {
        if(bookLoan == null) {
            return borrower.getName();
        } else {
            return bookLoan.getBook().getTitle() + " (Due: " + bookLoan.getDueDate() + ")";
        }
    }

    @Override
    protected void action() {
        final AdminService service = new AdminService();
        try {
            if(bookLoan == null) {
                final List<MenuItem> menuItems = new ArrayList<MenuItem>();
                
                final Set<BookLoan> bookLoans = service.readBookLoansByBorrower(borrower);
                for(final BookLoan bookLoan : bookLoans) {
                    menuItems.add(new AdminLoan(borrower, bookLoan));
                }
                
                final Menu menu = new Menu(null, menuItems, MenuType.ADMIN_MAIN);
                Menu.putMenu(MenuType.ADMIN_LOAN_BORROWER, menu);
                Menu.prompt(MenuType.ADMIN_LOAN_BORROWER);
            } else {
                System.out.print("Input new due date (dd-mm-yyyy): ");
                final String input = ScannerUtil.getInput();
                final Date newDate = new SimpleDateFormat("dd-MM-yyyy").parse(input);
                bookLoan.setDueDate(new Timestamp(newDate.getTime()));
                service.updateBookLoan(bookLoan);
                Menu.prompt(MenuType.ADMIN_MAIN);
            }
        } catch(final SQLException|ParseException exception) {
            System.out.println(exception);
        }
    }

}
