package service;

import client.GitHubClient;
import model.GitHubUser;
import org.junit.jupiter.api.Test;
import parser.JsonMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class GitHubApiServiceTest {

    @Test
    void findUserByUsernameReturnsUserWhenClientReturnsJson() {
        GitHubClient fakeClient = username ->Optional.of(
                """
                        {
                          "login": "marx",
                          "name": "Marx User",
                          "bio": "Testing service layer",
                          "public_repos": 10,
                          "followers": 20,
                          "following": 3,
                          "html_url": "https://github.com/marx"
                        }
                        """
        );

        JsonMapper jsonMapper = new JsonMapper();
        GitHubService service = new GitHubApiService(fakeClient, jsonMapper);

        Optional<GitHubUser> result = service.findUserByUsername("marx");

        assertTrue(result.isPresent());
        assertEquals("marx", result.get().getLogin());
        assertEquals("Marx User", result.get().getName());
        assertEquals(10, result.get().getPublicRepos());
        assertEquals("https://github.com/marx", result.get().getHtmlUrl());
    }

    @Test
    void findUserByUsernameReturnsEmptyWhenClientReturnsEmpty() {
        GitHubClient fakeClient = username -> Optional.empty();

        JsonMapper jsonMapper = new JsonMapper();
        GitHubService service = new GitHubApiService(fakeClient, jsonMapper);

        Optional<GitHubUser> result = service.findUserByUsername("unknown-user");

        assertTrue(result.isEmpty());
    }

    @Test
    void findUserByUsernameThrowsExceptionForBlankUsername() {
        GitHubClient fakeClient = username -> Optional.empty();

        JsonMapper jsonMapper = new JsonMapper();
        GitHubService service = new GitHubApiService(fakeClient, jsonMapper);

        assertThrows(
                IllegalArgumentException.class,
                () -> service.findUserByUsername("   ")
        );
    }

    @Test
    void findUserByUsernameThrowsExceptionForNullUsername() {
        GitHubClient fakeClient = username -> Optional.empty();

        JsonMapper jsonMapper = new JsonMapper();
        GitHubService service = new GitHubApiService(fakeClient, jsonMapper);

        assertThrows(
                IllegalArgumentException.class,
                () -> service.findUserByUsername(null)
        );
    }
}
