package app;

import service.LibraryService;
import storage.LibraryFileService;
import storage.LibraryState;
import ui.ConsoleUI;

import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
        LibraryService service = new LibraryService();
        LibraryFileService fileService = new LibraryFileService();
        Path filePath = Path.of("data", "library.json");

        LibraryState state = fileService.loadState(filePath);

        service.loadData(
                state.getBooks(),
                state.getMembers(),
                state.getLoans()
        );

        ConsoleUI ui = new ConsoleUI(service);
        ui.run();

        fileService.save(
                service.getBooks(),
                service.getMembers(),
                service.getLoans(),
                filePath
        );
    }
}
