package com.googoocorn.lifoo.src.RegisterAgainActivity.interfaces;

import com.googoocorn.lifoo.src.RegisterAgainActivity.models.GetNicknameResponse;
import com.googoocorn.lifoo.src.RegisterAgainActivity.models.RegisterBody;
import com.googoocorn.lifoo.src.RegisterAgainActivity.models.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RegisterRetrofitInterface {

    // 닉네임 생성
    @GET("/nicknames")
    Call<GetNicknameResponse> NicknameTest();


}
