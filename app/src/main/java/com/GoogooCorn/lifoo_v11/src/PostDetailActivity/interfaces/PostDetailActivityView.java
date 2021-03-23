package com.GoogooCorn.lifoo_v11.src.PostDetailActivity.interfaces;


import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.models.GetPostResponse;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.models.PostDeleteResponse;

public interface PostDetailActivityView {

    // 게시물 상세 조회
    void GetPostDetailFailure(String message, int code);
    void GetPostDetailSuccess(GetPostResponse getPostResponse, int code);

    // 게시물 수정
    void EditPostFailure(String message, int code);
    void EditPostSuccess(GetPostResponse getPostResponse, int code);

    //게시물 삭제
    void DeletePostFailure(String message, int code);
    void DeletePostSuccess(PostDeleteResponse postDeleteResponse, int code);
}
