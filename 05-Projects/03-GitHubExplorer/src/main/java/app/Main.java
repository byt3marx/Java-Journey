package app;

import client.GitHubHttpClient;
import parser.JsonMapper;
import ui.ConsoleUI;
import service.GitHubApiService;
import service.GitHubService;
import ui.ConsoleUI;

public class Main {

    public static void main(String[] args) {
        GitHubHttpClient gitHubHttpClient = new GitHubHttpClient();
        JsonMapper jsonMapper = new JsonMapper();

        GitHubService gitHubService = new GitHubApiService(gitHubHttpClient, jsonMapper);
        ConsoleUI ui = new ConsoleUI(gitHubService);

        ui.start();
    }
}
