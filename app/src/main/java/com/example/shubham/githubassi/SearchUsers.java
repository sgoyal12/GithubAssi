package com.example.shubham.githubassi;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by shubham on 7/12/2018.
 */

public class SearchUsers {
    @SerializedName("total_count")
    int totalCount;
    ArrayList<Users> items;
}
