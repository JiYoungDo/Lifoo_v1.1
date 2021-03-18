package com.GoogooCorn.lifoo_v11.src.SearchActivity.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse {

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

        @SerializedName("postList")
        private List<Post> postList;

        public List<Post> getPostList() {
            return postList;
        }
    }

    public class Post {
        @SerializedName("postIdx")
        private int postIdx;
        @SerializedName("postTitle")
        private String postTitle;
        @SerializedName("totalImoge")
        private int totalImoge;
        @SerializedName("postUrl")
        private String postUrl;
        @SerializedName("createdAt")
        private String createdAt;

        public int getPostIdx() {
            return postIdx;
        }

        public String getPostTitle() {
            return postTitle;
        }

        public int getTotalImoge() {
            return totalImoge;
        }

        public String getPostUrl() {
            return postUrl;
        }

        public String getCreatedAt() {
            return createdAt;
        }
    }

}
