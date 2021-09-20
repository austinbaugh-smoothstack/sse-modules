package lms.service;

import java.util.ArrayList;
import java.util.List;

public class LMS {
    
    public static void main(final String[] _args) {
        final String mainTitle = "Welcome to the SS Library Management System. Which category of a user are you?";
        final List<MenuItem> mainCommands = new ArrayList<MenuItem>();
        mainCommands.add(new GoToMenu("Librarian",     MenuType.LIB_MAIN));
        mainCommands.add(new GoToMenu("Administrator", MenuType.ADMIN_MAIN));
        mainCommands.add(new GoToMenu("Borrower",      MenuType.BORR_MAIN));
        final Menu main = new Menu(mainTitle, mainCommands, null);
        Menu.putMenu(MenuType.MAIN, main);
        
        final List<MenuItem> libCommands = new ArrayList<MenuItem>();
        libCommands.add(new GoToMenu("Enter Branch you manage", MenuType.LIB_BRANCH));
        final Menu libMenu = new Menu(null, libCommands, MenuType.MAIN);
        Menu.putMenu(MenuType.LIB_MAIN, libMenu);
        
        final List<MenuItem> adminCommands = new ArrayList<MenuItem>();
        adminCommands.add(new AdminOperation("Books",            AdminOperationTarget.BOOK));
        adminCommands.add(new AdminOperation("Authors",          AdminOperationTarget.AUTHOR));
        adminCommands.add(new AdminOperation("Genres",           AdminOperationTarget.GENRE));
        adminCommands.add(new AdminOperation("Publishers",       AdminOperationTarget.PUBLISHER));
        adminCommands.add(new AdminOperation("Library Branches", AdminOperationTarget.BRANCH));
        adminCommands.add(new AdminOperation("Borrowers",        AdminOperationTarget.BORROWER));
        adminCommands.add(new GoToMenu("Over-ride Due Date for a Book Loan", MenuType.ADMIN_LOAN));
        final Menu adminMenu = new Menu(null, adminCommands, MenuType.MAIN);
        Menu.putMenu(MenuType.ADMIN_MAIN, adminMenu);
        
        Menu.prompt(MenuType.MAIN);
    }
}
