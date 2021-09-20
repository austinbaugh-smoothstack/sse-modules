package lms.service;

import java.sql.SQLException;

import java.util.List;
import java.util.stream.Collectors;

import lms.domain.Author;
import lms.domain.Genre;

class AdminOperation extends MenuItem {

    private String labelSuffix;
    private AdminOperationTarget actionTarget;
    
    AdminOperation(final String labelSuffix, final AdminOperationTarget target) {
        this.labelSuffix = labelSuffix;
        this.actionTarget = target;
    }
    
    @Override
    protected void action() {
        final AdminService service = new AdminService();
        switch(actionTarget) {
            case BOOK:
                try {
                    final int[] columnWidths = {6, 20, 20, 20, 20};
                    final String[] columns = {"ID", "TITLE", "AUTHOR", "GENRE", "PUBLISHER"};
                    final Table table = new Table(columnWidths, columns);
                    
                    final List<Object[]> tableValues = service.readAllBooks().stream().map(book -> {
                        final List<Author> authors = book.getAuthors();
                        final String author = authors.isEmpty() ? new String() : authors.get(0).getName();
                        
                        final List<Genre> genres = book.getGenres();
                        final String genre = genres.isEmpty() ? new String() : genres.get(0).getName();
                        
                        return new Object[]{ book.getId(), book.getTitle(), author, genre, book.getPublisher().getName() };
                    }).collect(Collectors.toList());
                    table.setValues(tableValues);
                    
                    System.out.print(table);
                } catch (final SQLException exception) {
                    exception.printStackTrace();
                }
                break;
            default:
                System.out.println("Unimplemented");
                break;
        }
    }

    @Override
    protected String getLabel() {
        return "Add/Update/Delete/Read " + labelSuffix;
    }
}
