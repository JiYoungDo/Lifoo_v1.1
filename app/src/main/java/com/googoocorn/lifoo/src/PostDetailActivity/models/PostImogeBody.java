package com.googoocorn.lifoo.src.PostDetailActivity.models;

import com.google.gson.annotations.SerializedName;

public class PostImogeBody {

    @SerializedName("postIdx")
    private String postIdx;

    @SerializedName("imogeIdx")
    private String imogeIdx;

    public PostImogeBody(String postIdx, String imogeIdx) {
        this.postIdx = postIdx;
        this.imogeIdx = imogeIdx;
    }
}
