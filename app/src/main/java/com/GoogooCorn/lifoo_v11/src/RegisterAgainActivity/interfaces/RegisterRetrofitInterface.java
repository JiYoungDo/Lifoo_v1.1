package com.GoogooCorn.lifoo_v11.src.RegisterAgainActivity.interfaces;

import com.GoogooCorn.lifoo_v11.src.RegisterAgainActivity.models.GetNicknameResponse;
import com.GoogooCorn.lifoo_v11.src.RegisterAgainActivity.models.RegisterBody;
import com.GoogooCorn.lifoo_v11.src.RegisterAgainActivity.models.RegisterResponse;

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
