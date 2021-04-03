package com.googoocorncorn.lifoo_v11.src.SearchActivity.interfaces;


import com.googoocorncorn.lifoo_v11.src.SearchActivity.models.SearchResponse;

public interface SearchView {

    // 게시물 검
    void GetPostsSearchFailure(String message, int code);

    void GetPostsSearchSuccess(SearchResponse searchResponse, int code);
}
