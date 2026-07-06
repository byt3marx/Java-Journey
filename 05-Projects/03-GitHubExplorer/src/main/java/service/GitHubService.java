package service;

import model.GitHubUser;
import java.util.Optional;

public interface GitHubService {
    Optional<GitHubUser> findUserByUsername(String username);
}
