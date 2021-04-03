package com.googoocorncorn.lifoo_v11.src.SplashActivity.interfaces;

public interface SplashActivityView {

    // SocialLogin 실패시
    void AutoLoginFailure(String message, int code);

    //로그인 시 jwt 토큰 값 넘겨주며, 성공
    void AutoLogInSuccess(String message, int code);
}
