package parser;

import model.GitHubUser;
import com.google.gson.Gson;

public class JsonMapper {

    private final Gson gson = new Gson();

    public GitHubUser mapToGitHubUser(String json) {
        return gson.fromJson(json, GitHubUser.class);
    }
}
