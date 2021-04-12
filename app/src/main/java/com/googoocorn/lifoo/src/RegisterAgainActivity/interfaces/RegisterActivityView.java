package com.googoocorn.lifoo.src.RegisterAgainActivity.interfaces;

import com.googoocorn.lifoo.src.RegisterAgainActivity.models.RegisterResponse;

public interface RegisterActivityView {

    // 닉네임 생성
    void GetNicknameFailure(String message, int code);

    void GetNicknameSuccess(String message, String nickname, int code);





}
