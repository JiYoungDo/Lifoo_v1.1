package com.googoocorn.lifoo.src.PhotoPickActivity.interfaces;

import com.googoocorn.lifoo.src.PhotoPickActivity.models.PhotoPostBody;
import com.googoocorn.lifoo.src.PhotoPickActivity.models.PhotoPostResponse;

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
