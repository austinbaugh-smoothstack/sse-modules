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
        try {
            switch(actionTarget) {
                case BOOK: {
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
                    
                    table.print(tableValues);
                }
                break;
            case AUTHOR: {
                final int[] columnWidths = {6, 20};
                final String[] columns = {"ID", "AUTHOR NAME"};
                final Table table = new Table(columnWidths, columns);
                
                final List<Object[]> tableValues = service
                    .readAllAuthors()
                    .stream()
                    .map(author -> new Object[]{ author.getId(), author.getName() })
                    .collect(Collectors.toList());
                
                table.print(tableValues);
                }
                break;
            case GENRE: {
                final int[] columnWidths = {3, 15};
                final String[] columns = {"ID", "GENRE"};
                final Table table = new Table(columnWidths, columns);
                
                final List<Object[]> tableValues = service
                    .readAllGenres()
                    .stream()
                    .map(genre -> new Object[]{ genre.getId(), genre.getName() })
                    .collect(Collectors.toList());
                
                table.print(tableValues);
                }
                break;
            case PUBLISHER: {
                final int[] columnWidths = {6, 20, 20, 15};
                final String[] columns = {"ID", "PUBLISHER NAME", "ADDRESS", "PHONE"};
                final Table table = new Table(columnWidths, columns);
                
                final List<Object[]> tableValues = service
                    .readAllPublishers()
                    .stream()
                    .map(publisher -> new Object[]{ publisher.getId(), publisher.getName(), publisher.getAddress(), publisher.getPhone() })
                    .collect(Collectors.toList());
                
                table.print(tableValues);
                }
                break;
            case BRANCH: {
                final int[] columnWidths = {6, 20, 20};
                final String[] columns = {"ID", "BRANCH NAME", "ADDRESS"};
                final Table table = new Table(columnWidths, columns);
                
                final List<Object[]> tableValues = service
                    .readAllBranches()
                    .stream()
                    .map(branch -> new Object[]{ branch.getId(), branch.getName(), branch.getAddress() })
                    .collect(Collectors.toList());
                
                table.print(tableValues);
                }
                break;
            case BORROWER: {
                final int[] columnWidths = {8, 20, 20, 15};
                final String[] columns = {"CARD NUM", "NAME", "ADDRESS", "PHONE"};
                final Table table = new Table(columnWidths, columns);
                
                final List<Object[]> tableValues = service
                    .readAllBorrowers()
                    .stream()
                    .map(borr -> new Object[]{ borr.getCardNumber(), borr.getName(), borr.getAddress(), borr.getPhone() })
                    .collect(Collectors.toList());
                
                table.print(tableValues);
                }
                break;
            }
        } catch (final SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    protected String getLabel() {
        return "Add/Update/Delete/Read " + labelSuffix;
    }
}
