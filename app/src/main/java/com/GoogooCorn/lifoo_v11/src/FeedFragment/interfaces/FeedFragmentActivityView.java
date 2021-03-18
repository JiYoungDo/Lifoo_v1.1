package com.GoogooCorn.lifoo_v11.src.FeedFragment.interfaces;

import com.GoogooCorn.lifoo_v11.src.FeedFragment.models.FeedFragmentResponse;

public interface FeedFragmentActivityView {

    // 게시물 조회
    void GetPostsBasicFailure(String message, int code);

    void GetPostsBasicSuccess(FeedFragmentResponse feedFragmentResponse, int code);
}
