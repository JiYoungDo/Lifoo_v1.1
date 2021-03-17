package com.example.lifoo_v11.src.PhotoPostActivity.interfaces;

import com.example.lifoo_v11.src.PhotoPostActivity.models.PhotoPostBody;
import com.example.lifoo_v11.src.PhotoPostActivity.models.PhotoPostResponse;
import com.example.lifoo_v11.src.SocialLoginActivity.models.SocialLoginBody;
import com.example.lifoo_v11.src.SocialLoginActivity.models.SocialLoginResponse;

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
