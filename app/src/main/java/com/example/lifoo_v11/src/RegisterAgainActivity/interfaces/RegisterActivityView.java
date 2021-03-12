package com.example.lifoo_v11.src.RegisterAgainActivity.interfaces;

import com.example.lifoo_v11.src.RegisterAgainActivity.models.RegisterResponse;

public interface RegisterActivityView {

    // 닉네임 생성
    void GetNicknameFailure(String message, int code);

    void GetNicknameSuccess(String message, String nickname, int code);



    // 회원 가입
    void RegisterFailure(String message, int code);

    void RegisterSuccess(RegisterResponse registerResponse,String message, int code);



}
