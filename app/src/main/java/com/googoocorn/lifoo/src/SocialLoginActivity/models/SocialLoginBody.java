package com.googoocorn.lifoo.src.SocialLoginActivity.models;

import com.google.gson.annotations.SerializedName;

public class SocialLoginBody {

    @SerializedName("id")
    private String id;

    @SerializedName("password")
    private String password;

    public SocialLoginBody(String id, String password) {
        this.id = id;
        this.password = password;
    }
}
