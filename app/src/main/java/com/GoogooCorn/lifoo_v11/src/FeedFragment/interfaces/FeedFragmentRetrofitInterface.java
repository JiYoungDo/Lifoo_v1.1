package com.GoogooCorn.lifoo_v11.src.FeedFragment.interfaces;

import com.GoogooCorn.lifoo_v11.src.FeedFragment.models.FeedFragmentResponse;


import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface FeedFragmentRetrofitInterface {

//    // 게시물 조회
//    @GET("/posts")
//    Call<FeedFragmentResponse> GetFeedTest(
//            @Query("type") String type,
//            @Query("size") Integer size_num,
//            @Query("page") Integer page_num
//    );
//


    @GET("/posts")
    Call<FeedFragmentResponse> GetFeedTest(
            // @Query("page") Integer page_num,
            @QueryMap Map< String, String> parameters
    );

}
