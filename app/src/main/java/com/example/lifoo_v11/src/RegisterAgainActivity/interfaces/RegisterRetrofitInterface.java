package com.example.lifoo_v11.src.RegisterAgainActivity.interfaces;

import com.example.lifoo_v11.src.RegisterAgainActivity.models.GetNicknameResponse;
import com.example.lifoo_v11.src.RegisterAgainActivity.models.RegisterBody;
import com.example.lifoo_v11.src.RegisterAgainActivity.models.RegisterResponse;
import com.example.lifoo_v11.src.SocialLoginActivity.models.SocialLoginBody;
import com.example.lifoo_v11.src.SocialLoginActivity.models.SocialLoginResponse;
import com.example.lifoo_v11.src.SplashActivity.models.Splash_AutoLogin_Response;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RegisterRetrofitInterface {

    // 닉네임 생성
    @GET("/nicknames")
    Call<GetNicknameResponse> NicknameTest();


    // 회원 가입
    @POST("/users")
    Call<RegisterResponse> RegisterTest(
            @Body RegisterBody registerBody
    );



}
