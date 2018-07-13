package com.example.shubham.githubassi;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shubham on 7/13/2018.
 */

public class Repositories {
    int id;
    String name;
    @SerializedName("full_name")
    String fullName;
    Users owner;
    String language;
}
