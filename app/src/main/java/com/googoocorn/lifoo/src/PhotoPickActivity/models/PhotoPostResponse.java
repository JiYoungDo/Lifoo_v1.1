package com.googoocorn.lifoo.src.PhotoPickActivity.models;

import com.google.gson.annotations.SerializedName;

public class PhotoPostResponse {

    @SerializedName("isSuccess")
    private Boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public Boolean getSuccess() {
        return isSuccess;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
