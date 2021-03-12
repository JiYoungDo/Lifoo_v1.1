package com.example.lifoo_v11.src.FeedFragment.interfaces;

import com.example.lifoo_v11.src.FeedFragment.models.FeedFragmentResponse;

public interface FeedFragmentActivityView {

    // 게시물 조
    void GetPostsBasicFailure(String message, int code);

    void GetPostsBasicSuccess(FeedFragmentResponse feedFragmentResponse, int code);
}
