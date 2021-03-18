package com.GoogooCorn.lifoo_v11.src.SocialLoginActivity.interfaces;

import com.GoogooCorn.lifoo_v11.src.SocialLoginActivity.models.SocialLoginBody;
import com.GoogooCorn.lifoo_v11.src.SocialLoginActivity.models.SocialLoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SocialLoginRetrofitInterface {

    //SNS 로그인
    @POST("/login/kakao")
    Call<SocialLoginResponse> socialLoginTest(
            @Body SocialLoginBody socialLoginBody
    );

}
