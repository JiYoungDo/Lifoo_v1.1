package com.example.lifoo_v11.src.PhotoPostActivity.models;

import com.google.gson.annotations.SerializedName;

public class PhotoPostBody {

    @SerializedName("postUrl")
    private String postUrl;

    @SerializedName("postTitle")
    private String postTitle;

    @SerializedName("postBody")
    private String postBody;

    public PhotoPostBody(String postUrl, String postTitle, String postBody) {
        this.postUrl = postUrl;
        this.postTitle = postTitle;
        this.postBody = postBody;
    }
}
