package lms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lms.domain.Book;
import lms.domain.LibraryBranch;

class LibrarianBranch extends MenuItem {

    private String label;
    private MenuType menuType;
    private LibraryBranch branch;
    private Book book = null;
    
    protected LibrarianBranch(final String label, final MenuType menuType, final LibraryBranch branch, final Book book) {
        this.label = label;
        this.menuType = menuType;
        this.branch = branch;
        this.book = book;
    }

    @Override
    protected String getLabel() {
        return this.label;
    }
    
    @Override
    protected void action() {
        switch(menuType) {
            case LIB_BRANCH: {
                    final List<MenuItem> menuItems = new ArrayList<MenuItem>();
                    menuItems.add(new LibrarianBranch("Update the details of the Library", MenuType.LIB_BRANCH_UPDATE, branch, null));
                    menuItems.add(new LibrarianBranch("Add copies of Book to the Branch", MenuType.LIB_BRANCH_COPIES, branch, null));
                    final Menu menu = new Menu(null, menuItems, MenuType.LIB_BRANCH);
                    Menu.putMenu(menuType, menu);
                    Menu.prompt(menuType);
                }
                break;
            case LIB_BRANCH_UPDATE:
                try {
                    final LibrarianService service = new LibrarianService();
                    service.updateBranch(branch);
                    Menu.prompt(MenuType.LIB_BRANCH);
                } catch (final SQLException exception) {
                    exception.printStackTrace();
                }
                break;
            case LIB_BRANCH_COPIES:
                try {
                    final LibrarianService service = new LibrarianService();
                    if(book == null) {
                        final List<Book> books = service.readAllBooks();
                        final List<MenuItem> menuItems = new ArrayList<MenuItem>();
                        for(final Book book : books) {
                            final String author = book.getAuthor().getName();
                            menuItems.add(new LibrarianBranch(book.getTitle() + " by " + author, MenuType.LIB_BRANCH_COPIES, branch, book));
                        }
                        
                        final Menu menu = new Menu("Pick the Book you want to add copies of, to your branch:", menuItems, menuType);
                        Menu.putMenu(menuType, menu);
                        Menu.prompt(menuType);
                    } else {
                        Menu.prompt(MenuType.LIB_BRANCH);
                    }
                } catch (final SQLException exception) {
                    exception.printStackTrace();
                }
                break;
            default:
                throw new IllegalArgumentException(menuType + " is not a valid librarian menu");
        }
    }
}
