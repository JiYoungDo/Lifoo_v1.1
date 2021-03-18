package com.GoogooCorn.lifoo_v11.src.PhotoPickActivity.interfaces;

public interface PhotoPostActivityView {

    // post 업로드 실패시
    void PhotoPostFailure(String message, int code);

    // post 업로드 성
    void PhotoPostSuccess(String message, int code);

}
