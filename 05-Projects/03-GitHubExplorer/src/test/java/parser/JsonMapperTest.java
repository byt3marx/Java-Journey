package parser;

import model.GitHubUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
                  "html_url": "http://github.com/marx"
                  }
                    """;

        GitHubUser user = mapper.mapToGitHubUser(json);

        assertEquals("marx", user.getLogin());
        assertEquals("Marx User", user.getName());
        assertEquals("Testing GitHub Explorer", user.getBio());
        assertEquals(12, user.getPublicRepos());
        assertEquals(34, user.getFollowers());
        assertEquals(5, user.getFollowing());
        assertEquals("http://github.com/marx", user.getHtmlUrl());
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
}
