package com.example.lifoo_v11.src.PhotoPostActivity.interfaces;

import com.example.lifoo_v11.src.SocialLoginActivity.models.SocialLoginResponse;

public interface PhotoPostActivityView {

    // post 업로드 실패시
    void PhotoPostFailure(String message, int code);

    // post 업로드 성
    void PhotoPostSuccess(String message, int code);

}
