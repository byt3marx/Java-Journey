package model;

import com.google.gson.annotations.SerializedName;

public class GitHubRepository {
    private final String name;
    private final String description;
    private final String language;

    @SerializedName("stargazers_count")
    private final int stars;

    private final int forks;

    @SerializedName("html_url")
    private final String htmlUrl;

    public GitHubRepository(String name,
                            String description,
                            String language,
                            int stars,
                            int forks,
                            String htmlUrl) {
        this.name = name;
        this.description = description;
        this.language = language;
        this.stars = stars;
        this.forks = forks;
        this.htmlUrl = htmlUrl;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public int getStars() {
        return stars;
    }

    public int getForks() {
        return forks;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }
}
