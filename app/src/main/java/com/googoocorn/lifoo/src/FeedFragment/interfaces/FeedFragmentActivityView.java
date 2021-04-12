package com.googoocorn.lifoo.src.FeedFragment.interfaces;

import com.googoocorn.lifoo.src.FeedFragment.models.FeedFragmentResponse;

public interface FeedFragmentActivityView {

    // 게시물 조회
    void GetPostsBasicFailure(String message, int code);

    void GetPostsBasicSuccess(FeedFragmentResponse feedFragmentResponse, int code);
}
