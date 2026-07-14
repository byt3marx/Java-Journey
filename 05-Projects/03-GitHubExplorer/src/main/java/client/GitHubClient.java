package client;

import java.util.Optional;

public interface GitHubClient {
    Optional<String> fetchUserJson(String username);

    Optional<String> fetchUserRepositoriesJson(String username);
}
