package app;

import service.LibraryService;
import ui.ConsoleUI;

public class Main {

    public static void main(String[] args) {
        LibraryService service = new LibraryService();
        ConsoleUI ui = new ConsoleUI(service);
        ui.run();
    }
}
