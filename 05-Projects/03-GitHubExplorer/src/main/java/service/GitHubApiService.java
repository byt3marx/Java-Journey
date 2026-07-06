package service;

import model.GitHubUser;
import java.util.Optional;

public class GitHubApiService implements GitHubService {

    @Override
    public Optional<GitHubUser> findUserByUsername(String username) {
        return Optional.empty();
    }
}
