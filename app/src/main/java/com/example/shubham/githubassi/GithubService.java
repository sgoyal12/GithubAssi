package com.example.shubham.githubassi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by shubham on 7/12/2018.
 */

public interface GithubService {
    @GET("search/users")
    Call<SearchUsers> searchUsers(@Query("q") String search);
    @GET("users/{username}")
    Call<Users> User(@Path("username") String username);
    @GET("users/{username}/repos")
    Call<ArrayList<Repositories>> repoUser(@Path("username") String username);
    @GET("users/{username}/followers")
    Call<ArrayList<Users>> followersUser(@Path("username") String username);
    @GET("users/{username}/following")
    Call<ArrayList<Users>> followingUser(@Path("username") String username);

}
