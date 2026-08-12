package service;

import client.GitHubClient;
import model.GitHubRepository;
import model.GitHubUser;
import parser.JsonMapper;

import java.util.Optional;
import java.util.List;

public class GitHubApiService implements GitHubService {

    private final GitHubClient gitHubClient;
    private final JsonMapper jsonMapper;

    public GitHubApiService(GitHubClient gitHubClient, JsonMapper jsonMapper) {
        this.gitHubClient = gitHubClient;
        this.jsonMapper = jsonMapper;
    }

    @Override
    public Optional<GitHubUser> findUserByUsername(String username) {
        validateUsername(username);

        Optional<String> json = gitHubClient.fetchUserJson(username);

        if (json.isEmpty()) {
            return Optional.empty();
        }

        GitHubUser user = jsonMapper.mapToGitHubUser(json.get());
        return Optional.of(user);
    }

    @Override
    public Optional<List<GitHubRepository>> findRepositoriesByUsername(String username) {
        validateUsername(username);

        Optional<String> json = gitHubClient.fetchUserRepositoriesJson(username);

        if (json.isEmpty()) {
            return Optional.empty();
        }

        List<GitHubRepository> repositories = jsonMapper.mapToGitHubRepositories(json.get());

        return Optional.of(repositories);
    }

    private void validateUsername(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be blank");
        }
    }
}
