package com.googoocorncorn.lifoo_v11.src.MypageFragment.models;

import com.google.gson.annotations.SerializedName;

//요청 데이터
public class MypageFragmentResponse {
    @SerializedName("isSuccess")
    private Boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("result")
    private Result result;


    public Boolean getSuccess() {
        return isSuccess;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public MypageFragmentResponse.Result getResult() {
        return result;
    }


    public class Result {
        @SerializedName("nickname")
        private String nickname;

        public String getNickname() {
            return nickname;
        }

    }


}
