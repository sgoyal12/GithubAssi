package com.example.shubham.githubassi;

/**
 * Created by shubham on 7/12/2018.
 */

public class ShowUsers {
   private String title;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ShowUsers(String title, String url) {
        this.title = title;

        this.url = url;
    }
}
