package lms.service;

import java.sql.SQLException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lms.domain.Genre;

class AdminOperation extends MenuItem {

    private String labelSuffix;
    private AdminOperationTarget actionTarget;
    
    private void printTable() throws SQLException {
        final AdminService service = new AdminService();
        switch(actionTarget) {
            case BOOK: {
                final int[] columnWidths = {6, 20, 20, 20, 20};
                final String[] columns = {"ID", "TITLE", "AUTHOR", "GENRE", "PUBLISHER"};
                final Table table = new Table(columnWidths, columns);
                
                final List<Object[]> tableValues = service.readAllBooks().stream().map(book -> {
                    
                    final List<Genre> genres = book.getGenres();
                    final String genre = genres.isEmpty() ? new String() : genres.get(0).getName();
                    
                    return new Object[]{ book.getId(), book.getTitle(), book.getAuthor().getName(), genre, book.getPublisher().getName() };
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
    }
    
    AdminOperation(final String labelSuffix, final AdminOperationTarget target) {
        this.labelSuffix = labelSuffix;
        this.actionTarget = target;
    }
    
    @Override
    protected void action() {
        try {
            printTable();
            String input;
            do {
                System.out.println("Usage: quit/add/update/delete/read <id>");
                input = ScannerUtil.getInput();
            } while(parseOperation(input));
            Menu.prompt(MenuType.ADMIN_MAIN);
        } catch (final SQLException exception) {
            exception.printStackTrace();
        }
    }

    /*
     * parses the provide string based on the command option usage "quit/add/update/delete read <id>"
     * quit causes the function to return false, everything else return true
     */
    private boolean parseOperation(final String input) throws SQLException {
        final AdminService service = new AdminService();
        if(input.contains("quit")) {
            return false;
        }
        if(input.contains("add") || input.startsWith("update ")) {
            final Optional<Integer> id;
            if(input.contains("add")) {
                id = Optional.empty();
            } else {
                id = Optional.of(Integer.parseInt(input.substring(new String("update ").length())));
            }
            switch(actionTarget) {
                case BOOK:
                    service.updateBook(id);
                    break;
                case AUTHOR:
                    service.updateAuthor(id);
                    break;
                case GENRE:
                    service.updateGenre(id);
                    break;
                case PUBLISHER:
                    service.updatePublisher(id);
                    break;
                case BRANCH:
                    service.updateBranch(id);
                    break;
                case BORROWER:
                    service.updateBorrower(id);
                    break;
            }
        } else if(input.startsWith("delete ")) {
            final int id = Integer.parseInt(input.substring(new String("delete ").length()));
            switch(actionTarget) {
                case BOOK:
                    service.deleteBook(id);
                    break;
                case AUTHOR:
                    service.deleteAuthor(id);
                    break;
                case GENRE:
                    service.deleteGenre(id);
                    break;
                case PUBLISHER:
                    service.deletePublisher(id);
                    break;
                case BRANCH:
                    service.deleteBranch(id);
                    break;
                case BORROWER:
                    service.deleteBorrower(id);
                    break;
            }
        } else if(input.startsWith("read ")) {
            final int id = Integer.parseInt(input.substring(new String("read ").length()));
            switch(actionTarget) {
                case BOOK:
                    service.readBook(id);
                    break;
                case AUTHOR:
                    service.readAuthor(id);
                    break;
                case GENRE:
                    service.readGenre(id);
                    break;
                case PUBLISHER:
                    service.readPublisher(id);
                    break;
                case BRANCH:
                    service.readBranch(id);
                    break;
                case BORROWER:
                    service.readBorrower(id);
                    break;
            }
        }
        return true;
    }

    @Override
    protected String getLabel() {
        return "Add/Update/Delete/Read " + labelSuffix;
    }
}
