package lms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LMS {
    private static final Scanner scanner = new Scanner(System.in);
    private static Map<MenuType, Menu> menus = new HashMap<MenuType, Menu>();
    
    private static void constructMainMenus() {
        final String mainTitle = "Welcome to the SS Library Management System. Which category of a user are you?";
        final List<MenuItem> mainCommands = new ArrayList<MenuItem>();
        mainCommands.add(new GoToMenu("Librarian",     MenuType.LIB_MAIN));
        mainCommands.add(new GoToMenu("Administrator", MenuType.ADMIN_MAIN));
        mainCommands.add(new GoToMenu("Borrower",      MenuType.BORR_MAIN));
        final Menu main = new Menu(mainTitle, mainCommands, null);
        menus.put(MenuType.MAIN, main);
        
        final List<MenuItem> libCommands = new ArrayList<MenuItem>();
        libCommands.add(new GoToMenu("Enter Branch you manage", MenuType.LIB_BRANCH));
        final Menu libMenu = new Menu(null, libCommands, MenuType.MAIN);
        menus.put(MenuType.LIB_MAIN, libMenu);
        
        final List<MenuItem> adminCommands = new ArrayList<MenuItem>();
        adminCommands.add(new AdminOperation("Books",            AdminOperationTarget.BOOK));
        adminCommands.add(new AdminOperation("Authors",          AdminOperationTarget.AUTHOR));
        adminCommands.add(new AdminOperation("Book Loan",        AdminOperationTarget.LOAN));
        adminCommands.add(new AdminOperation("Genres",           AdminOperationTarget.GENRE));
        adminCommands.add(new AdminOperation("Publishers",       AdminOperationTarget.PUBLISHER));
        adminCommands.add(new AdminOperation("Library Branches", AdminOperationTarget.BRANCH));
        adminCommands.add(new AdminOperation("Borrowers",        AdminOperationTarget.BORROWER));
        final Menu adminMenu = new Menu(null, adminCommands, MenuType.MAIN);
        menus.put(MenuType.ADMIN_MAIN, adminMenu);
    }
    
    static String getInput() {
        String input;
        do {
            input = scanner.nextLine();
        } while(input.isEmpty());
        return input;
    }
    
    static void prompt(final MenuType menuType) {
        menus.get(menuType).prompt(scanner);
    }
    
    static void putMenu(final MenuType menuType, final Menu menu) {
        menus.put(menuType, menu);
    }
    
    public static void main(final String[] _args) {
        constructMainMenus();
        prompt(MenuType.MAIN);
    }
}
