package parser;

import model.GitHubUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import model.GitHubRepository;
import java.util.List;

public class JsonMapperTest {

    @Test
    void mapToGitHubUserMapsValidJsonCorrectly() {
        JsonMapper mapper = new JsonMapper();

        String json = """
                {
                  "login": "marx",
                  "name": "Marx User",
                  "bio": "Testing GitHub Explorer",
                  "public_repos": 12,
                  "followers": 34,
                  "following": 5,
                  "html_url": "https://github.com/marx"
                  }
                """;

        GitHubUser user = mapper.mapToGitHubUser(json);

        assertEquals("marx", user.getLogin());
        assertEquals("Marx User", user.getName());
        assertEquals("Testing GitHub Explorer", user.getBio());
        assertEquals(12, user.getPublicRepos());
        assertEquals(34, user.getFollowers());
        assertEquals(5, user.getFollowing());
        assertEquals("https://github.com/marx", user.getHtmlUrl());
    }

    @Test
    void MapToGitHubUserAllowsNullOptionalFields() {
        JsonMapper mapper = new JsonMapper();

        String json = """
                {
                  "login": "marx",
                  "name": null,
                  "bio": null,
                  "public_repos": 1,
                  "followrs": 1,
                  "following": 0,
                  "html_url": "https://github.com/marx"
                }
                """;

        GitHubUser user = mapper.mapToGitHubUser(json);

        assertEquals("marx", user.getLogin());
        assertNull(user.getName());
        assertNull(user.getBio());
        assertEquals(1, user.getPublicRepos());
        assertEquals(0, user.getFollowers());
        assertEquals("https://github.com/marx", user.getHtmlUrl());
    }

    @Test
    void mapToGitHubRepositoriesMapsValidJsonArrayCorrectly() {
        JsonMapper mapper = new JsonMapper();

        String json = """
                [
                  {
                    "name": "repo-one",
                    "description": "First test repository",
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
                """;

        List<GitHubRepository> repositories = mapper.mapToGitHubRepositories(json);

        assertEquals(2, repositories.size());

        GitHubRepository first = repositories.get(0);
        assertEquals("repo-one", first.getName());
        assertEquals("First test repository", first.getDescription());
        assertEquals("Java", first.getLanguage());
        assertEquals(12, first.getStars());
        assertEquals(3, first.getForks());
        assertEquals("https://gitHub.com/marx/repo-one", first.getHtmlUrl());

        GitHubRepository second = repositories.get(1);
        assertEquals("repo-two", second.getName());
        assertNull(second.getDescription());
        assertNull(second.getLanguage());
        assertEquals(5, second.getStars());
        assertEquals(1, second.getForks());
        assertEquals("https://gitHub.com/marx/repo-two", second.getHtmlUrl());
    }
}
