package lms.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Menu {
    
    private String title;
    private List<MenuItem> items;
    
    private static Map<MenuType, Menu> menus = new HashMap<MenuType, Menu>();
    
    static void prompt(final MenuType menuType) {
        menus.get(menuType).prompt();
    }
    
    static void putMenu(final MenuType menuType, final Menu menu) {
        menus.put(menuType, menu);
    }
    
    Menu(final String title, final List<MenuItem> items, final MenuType previous) {
        this.title = title;
        this.items = items;
        if(previous != null) {
            this.items.add(new GoToMenu("Quit to previous", previous));
        }
    }
    
    protected void prompt() {
        if(title != null) {
            System.out.println(title);
        }
        final int numItems = items.size();
        for(int i = 0; i < numItems; i++) {
            System.out.println((i + 1) + ") " + items.get(i).getLabel());
        }
        System.out.println();
        
        final int itemIndex = Integer.parseInt(ScannerUtil.getInput()) - 1;
        items.get(itemIndex).action();
        
    }
}
