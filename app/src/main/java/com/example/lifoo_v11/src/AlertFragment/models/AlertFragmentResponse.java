package com.example.lifoo_v11.src.AlertFragment.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

//요청 데이터
public class AlertFragmentResponse {
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

    public AlertFragmentResponse.Result getResult() {
        return result;
    }


    public class Result {
        @SerializedName("alarmList")
        private List<alarm> alarmList;

        public List<alarm> getAlarmList() {
            return alarmList;
        }
    }

    public class alarm {
        @SerializedName("postIdx")
        private int postIdx;
        @SerializedName("postUrl")
        private String postUrl;
        @SerializedName("ImogeIdx")
        private int ImogeIdx;
        @SerializedName("alarmTxt")
        private String alarmTxt;
        @SerializedName("createdAt")
        private String createdAt;


        public int getPostIdx() {
            return postIdx;
        }

        public String getPostUrl() {
            return postUrl;
        }

        public int getImogeIdx() {
            return ImogeIdx;
        }

        public String getAlarmTxt() {
            return alarmTxt;
        }

        public String getCreatedAt() {
            return createdAt;
        }
    }

}
