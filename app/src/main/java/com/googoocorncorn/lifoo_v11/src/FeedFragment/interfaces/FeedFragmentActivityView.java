package com.googoocorncorn.lifoo_v11.src.FeedFragment.interfaces;

import com.googoocorncorn.lifoo_v11.src.FeedFragment.models.FeedFragmentResponse;

public interface FeedFragmentActivityView {

    // 게시물 조회
    void GetPostsBasicFailure(String message, int code);

    void GetPostsBasicSuccess(FeedFragmentResponse feedFragmentResponse, int code);
}
