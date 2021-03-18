package com.GoogooCorn.lifoo_v11.src.SocialLoginActivity.models;

import com.google.gson.annotations.SerializedName;

public class SocialLoginBody {

    @SerializedName("accessToken")
    private String accessToken;

    public SocialLoginBody(String accessToken) {
        this.accessToken = accessToken;
    }
}
