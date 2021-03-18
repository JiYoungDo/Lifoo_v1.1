package com.GoogooCorn.lifoo_v11.src.SearchActivity.interfaces;


import com.GoogooCorn.lifoo_v11.src.SearchActivity.models.SearchResponse;

public interface SearchView {

    // 게시물 검
    void GetPostsSearchFailure(String message, int code);

    void GetPostsSearchSuccess(SearchResponse searchResponse, int code);
}
