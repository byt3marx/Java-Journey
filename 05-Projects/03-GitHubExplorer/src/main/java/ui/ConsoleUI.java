package ui;

import java.util.Scanner;

import model.GitHubRepository;
import service.GitHubService;
import model.GitHubUser;
import java.util.Optional;
import java.util.List;

public class ConsoleUI {

    private final Scanner scanner = new Scanner(System.in);
    private final GitHubService gitHubService;

    public ConsoleUI(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    public void start() {

        boolean running = true;

        System.out.println("Welcome to GitHub Explorer");

        while (running) {
            showMenu();

            String choice = scanner.nextLine();
            running = handleMenuChoice(choice);
        }

        System.out.println("Goodbye.");
    }

    private void showMenu() {
        System.out.println();
        System.out.println("1. Search GitHub user");
        System.out.println("2. Search user repositories");
        System.out.println("3. Exit");
        System.out.println("Choose option: ");
    }

    private boolean handleMenuChoice(String choice) {
        if (choice.equals("1")) {
            searchUser();
            return true;
        } else if (choice.equals("2")) {
            searchRepositories();
            return true;
        } else if (choice.equals("3")) {
            return false;
        } else {
            System.out.println("Invalid option. Please choose 1, 2 or 3.");
            return true;
        }
    }

    private void searchUser() {
        String username = readUsername();

        try {
            Optional<GitHubUser> user = gitHubService.findUserByUsername(username);

            if (user.isPresent()) {
                displayUserProfile(user.get());
            } else {
                System.out.println("GitHub user not found.");
            }
        } catch (RuntimeException e) {
            System.out.println("Something went wrong while contacting GitHub. Please try again later.");
        }
    }

    private void searchRepositories() {
        String username = readUsername();

        try {
            Optional<List<GitHubRepository>> repositories =
                    gitHubService.findRepositoriesByUsername(username);

            if (repositories.isEmpty()) {
                System.out.println("GitHub user not found.");
            } else if (repositories.get().isEmpty()) {
                System.out.println("This user has no public repositories.");
            } else {
                displayRepositories(repositories.get());
            }
        } catch (RuntimeException e) {
            System.out.println("Something went wrong while contacting GitHub. Please try again later.");
        }
    }

    private void displayUserProfile(GitHubUser user) {
        System.out.println();
        System.out.println("GitHub User Profile");
        System.out.println("-------------------");
        System.out.println("Username: " + user.getLogin());
        System.out.println("Name: " + formatNullable(user.getName()));
        System.out.println("Bio: " + formatNullable(user.getBio()));
        System.out.println("Public repos: " + user.getPublicRepos());
        System.out.println("Followers: " + user.getFollowers());
        System.out.println("Following: " + user.getFollowing());
        System.out.println("Profile: " + user.getHtmlUrl());
    }

    private void displayRepositories(List<GitHubRepository> repositories) {
        System.out.println();
        System.out.println("GitHub Repositories");
        System.out.println("-------------------");

        for (GitHubRepository repository : repositories) {
            System.out.println("Name: " + repository.getName());
            System.out.println("Description: " + formatNullable(repository.getDescription()));
            System.out.println("Language: " + formatNullable(repository.getLanguage()));
            System.out.println("Stars: " + repository.getStars());
            System.out.println("Forks: " + repository.getForks());
            System.out.println("URL: " + repository.getHtmlUrl());
            System.out.println();
        }
    }

    private String readUsername() {
        String username = "";

        while (username.isBlank()) {
            System.out.println("Enter GitHub username: ");
            username = scanner.nextLine();

            if (username.isBlank()) {
                System.out.println("Username cannot be blank. Please try again.");
            }
        }

        return username;
    }

    private String formatNullable(String value) {
        if (value == null || value.isBlank()) {
            return "Not provided";
        }
        return value;
    }
}
