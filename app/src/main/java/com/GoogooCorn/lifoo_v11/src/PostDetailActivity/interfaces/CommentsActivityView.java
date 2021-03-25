package com.GoogooCorn.lifoo_v11.src.PostDetailActivity.interfaces;

public interface CommentsActivityView {

    // 댓글 좋아요
    void PostCommentsLikesFailure(String message, int code);
    void PostCommentsLikesSuccess(String message, int code);
}
