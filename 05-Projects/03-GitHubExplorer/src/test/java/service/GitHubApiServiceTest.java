package service;

import client.GitHubClient;
import model.GitHubUser;
import org.junit.jupiter.api.Test;
import parser.JsonMapper;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import model.GitHubRepository;
import java.util.List;

public class GitHubApiServiceTest {

    private final JsonMapper jsonMapper = new JsonMapper();

    private GitHubService createService(GitHubClient fakeClient) {
        return new GitHubApiService(fakeClient, jsonMapper);
    }

    @Test
    void findUserByUsernameReturnsUserWhenClientReturnsJson() {
        GitHubClient fakeClient = new GitHubClient() {
            @Override
            public Optional<String> fetchUserJson(String username) {
                return Optional.of("""
                        {
                          "login": "marx",
                          "name": "Marx User",
                          "bio": "Testing service layer",
                          "public_repos": 10,
                          "followers": 20,
                          "following": 3,
                          "html_url": "https://github.com/marx"
                        }
                        """);
            }

            @Override
            public Optional<String> fetchUserRepositoriesJson(String username) {
                return Optional.empty();
            }
        };

        GitHubService service = createService(fakeClient);

        Optional<GitHubUser> result = service.findUserByUsername("marx");

        assertTrue(result.isPresent());
        assertEquals("marx", result.get().getLogin());
        assertEquals("Marx User", result.get().getName());
        assertEquals(10, result.get().getPublicRepos());
        assertEquals("https://github.com/marx", result.get().getHtmlUrl());
    }

    @Test
    void findUserByUsernameReturnsEmptyWhenClientReturnsEmpty() {
        GitHubClient fakeClient = new GitHubClient() {
            @Override
            public Optional<String> fetchUserJson(String username) {
                return Optional.empty();
            }

            @Override
            public Optional<String> fetchUserRepositoriesJson(String username) {
                return Optional.empty();
            }
        };

        GitHubService service = createService(fakeClient);

        Optional<GitHubUser> result = service.findUserByUsername("unknown-user");

        assertTrue(result.isEmpty());
    }

    @Test
    void findUserByUsernameThrowsExceptionForBlankUsername() {
        GitHubClient fakeClient = new GitHubClient() {
            @Override
            public Optional<String> fetchUserJson(String username) {
                return Optional.empty();
            }

            @Override
            public Optional<String> fetchUserRepositoriesJson(String username) {
                return Optional.empty();
            }
        };

        GitHubService service = createService(fakeClient);

        assertThrows(
                IllegalArgumentException.class,
                () -> service.findUserByUsername("   ")
        );
    }

    @Test
    void findUserByUsernameThrowsExceptionForNullUsername() {
        GitHubClient fakeClient = new GitHubClient() {
            @Override
            public Optional<String> fetchUserJson(String username) {
                return Optional.empty();
            }

            @Override
            public Optional<String> fetchUserRepositoriesJson(String username) {
                return Optional.empty();
            }
        };

        GitHubService service = createService(fakeClient);

        assertThrows(
                IllegalArgumentException.class,
                () -> service.findUserByUsername(null)
        );
    }

    @Test
    void findRepositoriesByUsernameReturnsRepositoriesWhenClientReturnsJson() {
        GitHubClient fakeClient = new GitHubClient() {
            @Override
            public Optional<String> fetchUserJson(String username) {
                return Optional.empty();
            }

            @Override
            public Optional<String> fetchUserRepositoriesJson(String username) {
                return Optional.of("""
                        [
                          {
                            "name": "repo-one",
                            "description": "First repo",
                            "language": "Java",
                            "stargazers_count": 12,
                            "forks": 3,
                            "html_url": "https://gitHub.com/marx/repo-one"
                          },
                          {
                            "name": "repo-two",
                            "description": null,
                            "language": null,
                            "stargazers_count": 5,
                            "forks": 1,
                            "html_url": "https://gitHub.com/marx/repo-two"
                          }
                        ]    
                        """);
            }
        };

        GitHubService service = createService(fakeClient);

        Optional<List<GitHubRepository>> result =
                service.findRepositoriesByUsername("marx");

        assertTrue(result.isPresent());
        assertEquals(2, result.get().size());

        GitHubRepository first = result.get().get(0);
        assertEquals("repo-one", first.getName());
        assertEquals("First repo", first.getDescription());
        assertEquals("Java", first.getLanguage());
        assertEquals(12, first.getStars());
        assertEquals(3, first.getForks());
        assertEquals("https://gitHub.com/marx/repo-one", first.getHtmlUrl());
    }

    @Test
    void findRepositoriesByUsernameReturnsEmptyWhenClientReturnsEmpty() {
        GitHubClient fakeClient = new GitHubClient() {
            @Override
            public Optional<String> fetchUserJson(String username) {
                return Optional.empty();
            }

            @Override
            public Optional<String> fetchUserRepositoriesJson(String username) {
                return Optional.empty();
            }
        };

        GitHubService service = createService(fakeClient);

        Optional<List<GitHubRepository>> result =
                service.findRepositoriesByUsername("unknown-user");

        assertTrue(result.isEmpty());
    }

    @Test
    void findRepositoriesByUsernameThrowsExceptionForBlankUsername() {
        GitHubClient fakeClient = new GitHubClient() {
            @Override
            public Optional<String> fetchUserJson(String username) {
                return Optional.empty();
            }

            @Override
            public Optional<String> fetchUserRepositoriesJson(String username) {
                return Optional.empty();
            }
        };

        GitHubService service = createService(fakeClient);

        assertThrows(
                IllegalArgumentException.class,
                () -> service.findUserByUsername("  ")
        );
    }
}
