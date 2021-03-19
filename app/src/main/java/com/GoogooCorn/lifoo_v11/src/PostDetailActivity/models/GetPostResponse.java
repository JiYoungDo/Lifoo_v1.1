package com.GoogooCorn.lifoo_v11.src.PostDetailActivity.models;

import com.GoogooCorn.lifoo_v11.src.FeedFragment.models.FeedFragmentResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPostResponse {
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

        @SerializedName("postIdx")
        private int postIdx;

        @SerializedName("postTitle")
        private String postTitle;

        @SerializedName("postBody")
        private String postBody;

        @SerializedName("hostUserIdx")
        private int hostUserIdx;

        @SerializedName("hostNickname")
        private String hostNickname;

        @SerializedName("totalImoge")
        private int totalImoge;

        @SerializedName("postUrl")
        private String postUrl;

        @SerializedName("createdAt")
        private String createdAt;

        @SerializedName("mostImoge")
        private int mostImoge;

        @SerializedName("imogeList")
        private List<Imoji> imogeList;

        public int getPostIdx() {
            return postIdx;
        }

        public String getPostTitle() {
            return postTitle;
        }

        public String getPostBody() {
            return postBody;
        }

        public int getHostUserIdx() {
            return hostUserIdx;
        }

        public String getHostNickname() {
            return hostNickname;
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

        public int getMostImoge() {
            return mostImoge;
        }

        public List<Imoji> getImogeList() {
            return imogeList;
        }
    }

    public class Imoji
    {
        @SerializedName("imogeIdx")
        private int imogeIdx;

        @SerializedName("isImogeClicked")
        private String isImogeClicked;

        public int getImogeIdx() {
            return imogeIdx;
        }

        public String getIsImogeClicked() {
            return isImogeClicked;
        }
    }



}
