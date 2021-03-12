package com.example.lifoo_v11.src.RegisterAgainActivity.models;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse {

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

        @SerializedName("jwt")
        private String jwt;

        @SerializedName("userIdx")
        private String userIdx;

        public String getJwt() {
            return jwt;
        }

        public String getUserIdx() {
            return userIdx;
        }
    }
}
