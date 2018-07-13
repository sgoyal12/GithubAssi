package com.example.shubham.githubassi;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shubham on 7/12/2018.
 */

public class Users {
    String login;
    int id;
    @SerializedName("avatar_url")
    String avatarUrl;
    String url;
    @SerializedName("followers_url")
    String followersUrl;
    @SerializedName("following_url")
    String followingUrl;
    @SerializedName("repos_url")
    String reposUrl;
    String name;
    String company;
    String email;
    @SerializedName("public_repos")
    int noOfRepos;
    int followers;
    int following;
}
