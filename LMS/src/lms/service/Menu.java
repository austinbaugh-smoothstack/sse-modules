package lms.service;
import java.util.List;
import java.util.Scanner;

class Menu {
    
    private String title;
    private List<MenuItem> items;
    
    Menu(final String title, final List<MenuItem> items, final MenuType previous) {
        this.title = title;
        this.items = items;
        if(previous != null) {
            this.items.add(new GoToMenu("Quit to previous", previous));
        }
    }
    
    protected void prompt(final Scanner scanner) {
        if(title != null) {
            System.out.println(title);
        }
        final int numItems = items.size();
        for(int i = 0; i < numItems; i++) {
            System.out.println((i + 1) + ") " + items.get(i).getLabel());
        }
        System.out.println();
        
        if(scanner.hasNextInt()) {
            final int itemIndex = scanner.nextInt() - 1;
            items.get(itemIndex).action();
        }
        
    }
}
