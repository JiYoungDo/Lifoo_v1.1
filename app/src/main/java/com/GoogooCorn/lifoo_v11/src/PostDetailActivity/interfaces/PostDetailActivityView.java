package com.GoogooCorn.lifoo_v11.src.PostDetailActivity.interfaces;


import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.models.GetCommentResponse;
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

    // 이모지 등록
    void PostImogeFailure(String message, int code);
    void PostImogeSuccess(String message, int code);

    // 댓글 등록 조회
    void GetCommentsFailure(String message, int code);
    void GetCommentsSuccess(GetCommentResponse getCommentResponse, int code);

    // 댓글 게시
    void PostCommentsFailure(String message, int code);
    void PostCommentsSuccess(String message, int code);

    // 댓글 신고
    void ReportPostFailure(String message, int code);
    void ReportPostSuccess(String message, int code);
}
