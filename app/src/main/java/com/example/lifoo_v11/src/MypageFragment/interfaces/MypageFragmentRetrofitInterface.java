package com.example.lifoo_v11.src.MypageFragment.interfaces;

import com.example.lifoo_v11.src.MypageFragment.models.MypageFragmentResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MypageFragmentRetrofitInterface {

    // 회원 정보 조회
    @GET("/users/{userIdx}")
    Call<MypageFragmentResponse> GetProfileTest(
           @Path("userIdx") int param);
}
