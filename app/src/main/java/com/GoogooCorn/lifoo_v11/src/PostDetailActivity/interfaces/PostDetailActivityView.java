package com.GoogooCorn.lifoo_v11.src.PostDetailActivity.interfaces;


import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.models.GetPostResponse;

public interface PostDetailActivityView {

    // 게시물 상세 조회
    void GetPostDetailFailure(String message, int code);
    void GetPostDetailSuccess(GetPostResponse getPostResponse, int code);
}
