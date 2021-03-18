package com.GoogooCorn.lifoo_v11.src.PhotoPickActivity.interfaces;

import com.GoogooCorn.lifoo_v11.src.PhotoPickActivity.models.PhotoPostBody;
import com.GoogooCorn.lifoo_v11.src.PhotoPickActivity.models.PhotoPostResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PhotoPostRetrofitInterface {

    //SNS 로그인
    @POST("/posts")
    Call<PhotoPostResponse> PhotoPostTest(
            @Body PhotoPostBody photoPostBody
    );
}
