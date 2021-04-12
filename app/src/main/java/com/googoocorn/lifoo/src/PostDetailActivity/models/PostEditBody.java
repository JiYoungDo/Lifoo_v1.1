package com.googoocorn.lifoo.src.PostDetailActivity.models;

import com.google.gson.annotations.SerializedName;

public class PostEditBody {
    @SerializedName("postUrl")
    private String postUrl;

    @SerializedName("postTitle")
    private String postTitle;

    @SerializedName("postBody")
    private String postBody;


    public PostEditBody(String postUrl, String postTitle, String postBody) {
        this.postUrl = postUrl;
        this.postTitle = postTitle;
        this.postBody = postBody;
    }
}
