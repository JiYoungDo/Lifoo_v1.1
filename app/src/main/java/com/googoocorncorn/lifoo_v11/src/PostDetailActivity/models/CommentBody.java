package com.googoocorncorn.lifoo_v11.src.PostDetailActivity.models;

import com.google.gson.annotations.SerializedName;

public class CommentBody {
    @SerializedName("postIdx")
    private String postIdx;

    @SerializedName("commentBody")
    private String commentBody;

    public CommentBody(String postIdx, String commentBody) {
        this.postIdx = postIdx;
        this.commentBody = commentBody;
    }
}
