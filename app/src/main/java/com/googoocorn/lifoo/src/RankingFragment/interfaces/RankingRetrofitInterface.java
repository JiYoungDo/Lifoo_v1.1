package com.googoocorn.lifoo.src.RankingFragment.interfaces;

import com.googoocorn.lifoo.src.RankingFragment.models.RankingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RankingRetrofitInterface {
    // 랭킹 게시물 조회
    @GET("/posts")
    Call<RankingResponse> GetRankingTest(
            @Query("type") String type,
            @Query("size") Integer size_num,
            @Query("page") Integer page_num
    );
}
