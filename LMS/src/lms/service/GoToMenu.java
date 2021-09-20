package lms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lms.domain.Borrower;
import lms.domain.LibraryBranch;

class GoToMenu extends MenuItem {
    
    private String label;
    private MenuType menuType;
    
    protected GoToMenu(final String label, final MenuType menuType) {
        this.label = label;
        this.menuType = menuType;
    }

    @Override
    protected String getLabel() {
        return this.label;
    }
    
    @Override
    protected void action() {
        switch(menuType) {
            case LIB_BRANCH:
                try {
                    final BorrowerService service = new BorrowerService();
                    final List<MenuItem> menuItems = new ArrayList<MenuItem>();
                    
                    final List<LibraryBranch> branches = service.readAllBranches();
                    for(final LibraryBranch branch : branches) {
                        menuItems.add(new LibrarianOperation(branch.getName() + ", " + branch.getAddress(), MenuType.LIB_BRANCH, branch, null));
                    }
                    
                    final Menu menu = new Menu(null, menuItems, MenuType.LIB_MAIN);
                    LMS.putMenu(MenuType.LIB_BRANCH, menu);
                } catch (final SQLException exception) {
                    exception.printStackTrace();
                }
                break;
            case BORR_MAIN:
                try {
                    final BorrowerService service = new BorrowerService();
                    final Borrower borrower = service.readBorrowerByCardNumber();
                    final List<MenuItem> menuItems = new ArrayList<MenuItem>();
                    
                    menuItems.add(new BorrowerOperation("Check out a book", MenuType.BORR_LOAN_CHECKOUT, borrower));
                    menuItems.add(new BorrowerOperation("Return a book", MenuType.BORR_LOAN_RETURN, borrower));
                    
                    final Menu menu = new Menu(null, menuItems, MenuType.MAIN);
                    LMS.putMenu(MenuType.BORR_MAIN, menu);
                } catch (final SQLException exception) {
                    exception.printStackTrace();
                }
                break;
            default:
                break;
        }
        LMS.prompt(menuType);
    }
}
