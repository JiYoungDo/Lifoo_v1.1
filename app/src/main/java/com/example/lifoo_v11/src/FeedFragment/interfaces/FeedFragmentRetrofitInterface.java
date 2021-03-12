package com.example.lifoo_v11.src.FeedFragment.interfaces;

import android.renderscript.Type;

import com.example.lifoo_v11.src.FeedFragment.models.FeedFragmentResponse;
import com.example.lifoo_v11.src.RegisterAgainActivity.models.GetNicknameResponse;


import java.sql.Types;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface FeedFragmentRetrofitInterface {

    // 게시물 조회
    @GET("/posts")
    Call<FeedFragmentResponse> GetFeedTest(
            @Query("type") String type,
            @Query("size") Integer size_num,
            @Query("page") Integer page_num
    );


//    @GET("/posts")
//    Call<FeedFragmentResponse> GetFeedTest(
//            @Query("page") Integer page_num,
//            @QueryMap Map< String, Object> parameters
//    );

}
