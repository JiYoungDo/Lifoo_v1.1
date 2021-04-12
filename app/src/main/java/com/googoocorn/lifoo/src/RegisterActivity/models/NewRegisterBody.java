package com.googoocorn.lifoo.src.RegisterActivity.models;

import com.google.gson.annotations.SerializedName;

public class NewRegisterBody {

    @SerializedName("nickname")
    private String nickname;

    @SerializedName("id")
    private String id;

    @SerializedName("password")
    private String password;

    @SerializedName("passwordCheck")
    private String passwordCheck;

    public NewRegisterBody(String nickname, String id, String password, String passwordCheck) {
        this.nickname = nickname;
        this.id = id;
        this.password = password;
        this.passwordCheck = passwordCheck;
    }
}
