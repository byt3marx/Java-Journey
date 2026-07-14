package client;

import java.util.Optional;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class GitHubHttpClient implements GitHubClient {

    private static final String GITHUB_USERS_API_URL = "https://api.github.com/users/";

    private static final String REPOS_PATH = "/repos";

    private final HttpClient httpClient = HttpClient.newHttpClient();

    @Override
    public Optional<String> fetchUserRepositoriesJson(String username) {
        String url = GITHUB_USERS_API_URL + username + REPOS_PATH;
        return fetchJson(url);
    }

    @Override
    public Optional<String> fetchUserJson(String username) {
        String url = GITHUB_USERS_API_URL + username;
        return fetchJson(url);
    }

    private Optional<String> fetchJson(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            if (response.statusCode() == 200) {
                return Optional.of(response.body());
            }

            if (response.statusCode() == 404) {
                return Optional.empty();
            }

            throw new RuntimeException("GitHub API request failed with status code: " + response.statusCode());

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("GitHub API request was interrupted", e);
        } catch (IOException e) {
            throw new RuntimeException("Failed to fetch GitHub data", e);
        }
    }
}
