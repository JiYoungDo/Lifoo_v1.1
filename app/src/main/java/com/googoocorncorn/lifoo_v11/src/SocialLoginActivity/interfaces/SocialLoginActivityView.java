package com.googoocorncorn.lifoo_v11.src.SocialLoginActivity.interfaces;

import com.googoocorncorn.lifoo_v11.src.SocialLoginActivity.models.SocialLoginResponse;

public interface SocialLoginActivityView {

    // SocialLogin 실패시
    void SocialLoginFailure(String message, int code);

    //로그인 시 jwt 토큰 값 넘겨주며, 성공
    void SocialLogInSuccess(SocialLoginResponse socialLoginResponse, String message, int code);

}
