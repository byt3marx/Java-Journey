package service;

import model.GitHubUser;
import java.util.Optional;

import model.GitHubRepository;
import java.util.List;

public interface GitHubService {
    Optional<GitHubUser> findUserByUsername(String username);

    Optional<List<GitHubRepository>> findRepositoriesByUsername(String username);
}
