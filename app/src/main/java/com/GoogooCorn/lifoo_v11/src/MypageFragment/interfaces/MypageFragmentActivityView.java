package com.GoogooCorn.lifoo_v11.src.MypageFragment.interfaces;

import com.GoogooCorn.lifoo_v11.src.MypageFragment.models.ImogeResponse;
import com.GoogooCorn.lifoo_v11.src.MypageFragment.models.MyPostResponse;
import com.GoogooCorn.lifoo_v11.src.MypageFragment.models.MypageFragmentResponse;
import com.GoogooCorn.lifoo_v11.src.MypageFragment.models.NicknameResponse;

public interface MypageFragmentActivityView {
    // 회원 정보 조회 실패
    void GetProfileFailure(String message, int code);
    // 회원 정보 조회 성공
    void GetProfileSuccess(MypageFragmentResponse mypageFragmentResponse, int code);
    void GetProfileSuccess(NicknameResponse nicknameResponse, int code);

    // 이모지 조회 실패
    void GetImogeFailure(String message, int code);
    // 이모지 조회 성공
    void GetImogeSuccess(ImogeResponse imogeResponse, int code);

    // 내 게시물 조회 실패
    void GetMyPostFailure(String message, int code);
    // 내 게시물 조회 성공
    void GetMyPostSuccess(MyPostResponse myPostResponse, int code);

}
