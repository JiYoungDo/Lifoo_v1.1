package com.googoocorncorn.lifoo_v11.src.PostDetailActivity.interfaces;

public interface CommentsActivityView {

    // 댓글 좋아요
    void PostCommentsLikesFailure(String message, int code);
    void PostCommentsLikesSuccess(String message, int code);


    // 댓글 삭제
    void DeleteCommentsFailure(String message, int code);
    void DeleteCommentsSuccess(String message, int code);


    // 댓글 수정
    void EditommentsFailure(String message, int code);
    void EditCommentsSuccess(String message, int code);

    // 댓글 신고
    void ReportommentsFailure(String message, int code);
    void ReportCommentsSuccess(String message, int code);

}
