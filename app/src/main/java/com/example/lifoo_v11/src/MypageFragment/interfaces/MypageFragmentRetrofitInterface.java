package com.example.lifoo_v11.src.MypageFragment.interfaces;

import com.example.lifoo_v11.src.MypageFragment.models.MypageFragmentResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MypageFragmentRetrofitInterface {

    // 회원 정보 조회
    @GET("/users/2")
    Call<MypageFragmentResponse> GetProfileTest();
}
