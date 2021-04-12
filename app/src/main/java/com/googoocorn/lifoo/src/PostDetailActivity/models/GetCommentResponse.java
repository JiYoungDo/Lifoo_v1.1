package com.googoocorn.lifoo.src.PostDetailActivity.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetCommentResponse {

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

    public class Result
    {
        @SerializedName("commentLists")
        private List<Comments> commentLists;

        public List<Comments> getCommentLists() {
            return commentLists;
        }
    }


    public class Comments
    {
        @SerializedName("commentIdx")
        private int commentIdx;

        @SerializedName("commentUserIdx")
        private int commentUserIdx;

        @SerializedName("commentNickname")
        private String commentNickname;

        @SerializedName("commentBody")
        private String commentBody;

        @SerializedName("likeNum")
        private int likeNum;

        @SerializedName("isLikeClicked")
        private String isLikeClicked;

        public int getCommentIdx() {
            return commentIdx;
        }

        public int getCommentUserIdx() {
            return commentUserIdx;
        }

        public String getCommentNickname() {
            return commentNickname;
        }

        public String getCommentBody() {
            return commentBody;
        }

        public int getLikeNum() {
            return likeNum;
        }

        public String getIsLikeClicked() {
            return isLikeClicked;
        }
    }


}
