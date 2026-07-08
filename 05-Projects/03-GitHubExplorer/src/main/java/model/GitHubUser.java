package model;

import com.google.gson.annotations.SerializedName;

public class GitHubUser {

    private final String login;
    private final String name;
    private final String bio;

    @SerializedName("public_repos")
    private final int publicRepos;

    private final int followers;
    private final int following;

    @SerializedName("html_url")
    private final String htmlUrl;

    public GitHubUser(String login,
                      String name,
                      String bio,
                      int publicRepos,
                      int followers,
                      int following,
                      String htmlUrl) {
        this.login = login;
        this.name = name;
        this.bio = bio;
        this.publicRepos = publicRepos;
        this.followers = followers;
        this.following = following;
        this.htmlUrl = htmlUrl;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public int getPublicRepos() {
        return publicRepos;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }
}
