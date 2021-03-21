package com.GoogooCorn.lifoo_v11.src.MypageFragment.models;

import com.google.gson.annotations.SerializedName;

public class NicknameBody {
    @SerializedName("nickname")
    private String nickname;


    public NicknameBody(String nickname) {
        this.nickname = nickname;
    }
}
