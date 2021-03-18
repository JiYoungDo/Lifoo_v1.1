package com.GoogooCorn.lifoo_v11.src.SplashActivity.interfaces;

import com.GoogooCorn.lifoo_v11.src.SplashActivity.models.Splash_AutoLogin_Response;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SplashRetrofitInterface {

    // 자동 로그인
    @GET("/login/jwt")
    Call<Splash_AutoLogin_Response> Auto_Login_Test ();
}
