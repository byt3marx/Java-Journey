package parser;

import model.GitHubUser;
import com.google.gson.Gson;

import model.GitHubRepository;
import java.util.Arrays;
import java.util.List;

public class JsonMapper {

    private final Gson gson = new Gson();

    public GitHubUser mapToGitHubUser(String json) {
        return gson.fromJson(json, GitHubUser.class);
    }

    public List<GitHubRepository> mapToGitHubRepositories(String json) {
        GitHubRepository[] repositories = gson.fromJson(json, GitHubRepository[].class);

        return Arrays.asList(repositories);
    }
}
