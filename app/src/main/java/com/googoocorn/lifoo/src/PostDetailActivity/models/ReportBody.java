package com.googoocorn.lifoo.src.PostDetailActivity.models;

import com.google.gson.annotations.SerializedName;

public class ReportBody {

    @SerializedName("status")
    private String status;


    @SerializedName("targetIdx")
    private String targetIdx;

    public ReportBody(String status, String targetIdx) {
        this.status = status;
        this.targetIdx = targetIdx;
    }
}
