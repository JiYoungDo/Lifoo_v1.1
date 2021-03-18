package com.GoogooCorn.lifoo_v11.src.SocialLoginActivity.models;

import com.google.gson.annotations.SerializedName;

public class SocialLoginResponse {


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

    public Result getResult() {
        return result;
    }

    public class Result {

        @SerializedName("userIdx")
        private String userIdx;

        @SerializedName("jwt")
        private String jwt;

        public String getUserIdx() {
            return userIdx;
        }

        public String getJwt() {
            return jwt;
        }
    }
}
