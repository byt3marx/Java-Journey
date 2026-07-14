package service;

import client.GitHubClient;
import model.GitHubUser;
import java.util.Optional;
import client.GitHubHttpClient;
import parser.JsonMapper;
import java.util.Optional;
import client.GitHubClient;

import model.GitHubRepository;
import java.util.List;

public class GitHubApiService implements GitHubService {

    @Override
    public Optional<GitHubUser> findUserByUsername(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be blank.");
        }

        Optional<String> json = gitHubClient.fetchUserJson(username);

        if (json.isEmpty()) {
            return Optional.empty();
        }

        GitHubUser user = jsonMapper.mapToGitHubUser(json.get());
        return Optional.of(user);
    }

    @Override
    public Optional<List<GitHubRepository>> findRepositoriesByUsername(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be blank");
        }

        Optional<String> json = gitHubClient.fetchUserRepositoriesJson(username);

        if (json.isEmpty()) {
            return Optional.empty();
        }

        List<GitHubRepository> repositories = jsonMapper.mapToGitHubRepositories(json.get());

        return Optional.of(repositories);
    }

    private final GitHubClient gitHubClient;

    private final JsonMapper jsonMapper;

    public GitHubApiService(GitHubClient gitHubClient, JsonMapper jsonMapper) {
        this.gitHubClient = gitHubClient;
        this.jsonMapper = jsonMapper;
    }
}
