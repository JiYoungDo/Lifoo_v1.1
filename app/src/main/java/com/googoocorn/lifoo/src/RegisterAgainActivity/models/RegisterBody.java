package com.googoocorn.lifoo.src.RegisterAgainActivity.models;

import com.google.gson.annotations.SerializedName;

public class RegisterBody {

    @SerializedName("nickname")
    private String nickname;

    @SerializedName("snsId")
    private String snsId;

    @SerializedName("loginType")
    private String loginType;

    public RegisterBody(String nickname, String snsId, String loginType) {
        this.nickname = nickname;
        this.snsId = snsId;
        this.loginType = loginType;
    }
}
