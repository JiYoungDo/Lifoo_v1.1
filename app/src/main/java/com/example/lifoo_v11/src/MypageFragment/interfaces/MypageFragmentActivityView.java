package com.example.lifoo_v11.src.MypageFragment.interfaces;

import com.example.lifoo_v11.src.MypageFragment.models.MypageFragmentResponse;

public interface MypageFragmentActivityView {
    // 회원 정보 조회 실패
    void GetProfileFailure(String message, int code);

    // 회원 정보 조회 성공
    void GetProfileSuccess(MypageFragmentResponse mypageFragmentResponse, int code);
}
