package service;

import model.GitHubUser;
import java.util.Optional;
import client.GitHubHttpClient;
import parser.JsonMapper;
import java.util.Optional;

public class GitHubApiService implements GitHubService {

    @Override
    public Optional<GitHubUser> findUserByUsername(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be blank.");
        }

        Optional<String> json = gitHubHttpClient.fetchUserJson(username);

        if (json.isEmpty()) {
            return Optional.empty();
        }

        GitHubUser user = jsonMapper.mapToGitHubUser(json.get());
        return Optional.of(user);
    }

    private final GitHubHttpClient gitHubHttpClient;
    private final JsonMapper jsonMapper;

    public GitHubApiService(GitHubHttpClient gitHubHttpClient, JsonMapper jsonMapper) {
        this.gitHubHttpClient = gitHubHttpClient;
        this.jsonMapper = jsonMapper;
    }
}
