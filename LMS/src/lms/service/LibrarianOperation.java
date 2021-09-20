package lms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lms.domain.Author;
import lms.domain.Book;
import lms.domain.LibraryBranch;

class LibrarianOperation extends MenuItem {

    private String label;
    private MenuType menuType;
    private LibraryBranch branch;
    private Book book = null;
    
    protected LibrarianOperation(final String label, final MenuType menuType, final LibraryBranch branch, final Book book) {
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
                    menuItems.add(new LibrarianOperation("Update the details of the Library", MenuType.LIB_BRANCH_UPDATE, branch, null));
                    menuItems.add(new LibrarianOperation("Add copies of Book to the Branch", MenuType.LIB_BRANCH_COPIES, branch, null));
                    final Menu menu = new Menu(null, menuItems, MenuType.LIB_BRANCH);
                    LMS.putMenu(menuType, menu);
                    LMS.prompt(menuType);
                }
                break;
            case LIB_BRANCH_UPDATE:
                try {
                    final LibrarianService service = new LibrarianService();
                    service.updateBranch(branch);
                    LMS.prompt(MenuType.LIB_BRANCH);
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
                            final List<Author> authors = book.getAuthors();
                            final String author = authors.isEmpty() ? "(unknown)" : authors.get(0).getName();
                            menuItems.add(new LibrarianOperation(book.getTitle() + " by " + author, MenuType.LIB_BRANCH_COPIES, branch, book));
                        }
                        
                        final Menu menu = new Menu("Pick the Book you want to add copies of, to your branch:", menuItems, menuType);
                        LMS.putMenu(menuType, menu);
                        LMS.prompt(menuType);
                    } else {
                        LMS.prompt(MenuType.LIB_BRANCH);
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
