package com.GoogooCorn.lifoo_v11.src.MypageFragment.models;

import com.GoogooCorn.lifoo_v11.src.FeedFragment.models.FeedFragmentResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.xml.transform.Result;

public class ImogeResponse {
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

        @SerializedName("imogeList")
        private List<Imoge> imogeList;

        public List<Imoge> getImogeList() {
            return imogeList;
        }
    }

    public class Imoge {
        @SerializedName("imogeIdx")
        private int imogeIdx;
        @SerializedName("imogeNum")
        private String imogeNum;


        public int getimogeIdx() {
            return imogeIdx;
        }

        public String getimogeNum() {
            return imogeNum;
        }
    }
}
