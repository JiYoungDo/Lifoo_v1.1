package com.example.lifoo_v11.src.SearchActivity.interfaces;

import com.example.lifoo_v11.src.FeedFragment.models.FeedFragmentResponse;
import com.example.lifoo_v11.src.SearchActivity.models.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchRetrofitInterface {

    // 게시물 검색
    @GET("/posts")
    Call<SearchResponse> GetSearchTest(
            @Query("type") String type,
            @Query("size") Integer size_num,
            @Query("page") Integer page_num,
            @Query("keyword") String keyword
    );
}
