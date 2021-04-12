package com.googoocorn.lifoo.src.SearchActivity.interfaces;


import com.googoocorn.lifoo.src.SearchActivity.models.SearchResponse;

public interface SearchView {

    // 게시물 검
    void GetPostsSearchFailure(String message, int code);

    void GetPostsSearchSuccess(SearchResponse searchResponse, int code);
}
