package com.googoocorncorn.lifoo_v11.src.MypageFragment.interfaces;

import com.googoocorncorn.lifoo_v11.src.MypageFragment.models.ImogeResponse;
import com.googoocorncorn.lifoo_v11.src.MypageFragment.models.MyPostResponse;
import com.googoocorncorn.lifoo_v11.src.MypageFragment.models.MypageFragmentResponse;
import com.googoocorncorn.lifoo_v11.src.MypageFragment.models.NicknameResponse;

public interface MypageFragmentActivityView {
    // 회원 정보 조회 실패
    void GetProfileFailure(String message, int code);
    // 회원 정보 조회 성공
    void GetProfileSuccess(MypageFragmentResponse mypageFragmentResponse, int code);

    // 닉네임 변경 실패
    void EditProfileFailure(String message, int code);
    // 닉네임 변경 성공
    void EditProfileSuccess(NicknameResponse nicknameResponse, int code);

    // 이모지 조회 실패
    void GetImogeFailure(String message, int code);
    // 이모지 조회 성공
    void GetImogeSuccess(ImogeResponse imogeResponse, int code);

    // 내 게시물 조회 실패
    void GetMyPostFailure(String message, int code);
    // 내 게시물 조회 성공
    void GetMyPostSuccess(MyPostResponse myPostResponse, int code);

}
