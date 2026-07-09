package ui;

import java.util.Scanner;
import service.GitHubService;
import model.GitHubUser;
import java.util.Optional;

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
        System.out.println("2. Exit");
        System.out.println("Choose option: ");
    }

    private boolean handleMenuChoice(String choice) {
        if (choice.equals("1")) {
            searchUser();
            return true;
        } else if (choice.equals("2")) {
            return false;
        } else {
            System.out.println("Invalid option. Please choose 1 or 2.");
            return true;
        }
    }

    private void searchUser() {
        String username = "";

        while (username.isBlank()) {
            System.out.println("Enter GitHub username: ");
            username = scanner.nextLine();

            if (username.isBlank()) {
                System.out.println("Username cannot be blank. Please try again.");
            }
        }

        Optional<GitHubUser> user = gitHubService.findUserByUsername(username);

        if (user.isPresent()) {
            displayUserProfile(user.get());
        } else {
            System.out.println("GitHub user not found.");
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

    private String formatNullable(String value) {
        if (value == null || value.isBlank()) {
            return "Not provided";
        }
        return value;
    }
}
