package com.GoogooCorn.lifoo_v11.src.PostDetailActivity.models;

import com.google.gson.annotations.SerializedName;

public class EditCommentsBody {

    @SerializedName("commentBody")
    private String commentBody;

    public EditCommentsBody(String commentBody) {
        this.commentBody = commentBody;
    }

}
