package com.googoocorncorn.lifoo_v11.src.MypageFragment.interfaces;

import com.googoocorncorn.lifoo_v11.src.MypageFragment.models.ImogeResponse;
import com.googoocorncorn.lifoo_v11.src.MypageFragment.models.MyPostResponse;
import com.googoocorncorn.lifoo_v11.src.MypageFragment.models.MypageFragmentResponse;
import com.googoocorncorn.lifoo_v11.src.MypageFragment.models.NicknameBody;
import com.googoocorncorn.lifoo_v11.src.MypageFragment.models.NicknameResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MypageFragmentRetrofitInterface {

    // 회원 정보(닉네임) 조회
    @GET("/users/{userIdx}")
    Call<MypageFragmentResponse> GetProfileTest(
            @Path("userIdx") String userIdx);

    // 회원 정보(닉네임) 수정
    @PATCH("/users/{userIdx}")
    Call<NicknameResponse> EditProfileTest(
            @Path("userIdx") String userIdx,
            @Body NicknameBody body
    );

    // 이모지 조회
    @GET("/imoges")
    Call<ImogeResponse> GetImogeTest();

    // 내 게시물 조회
    @GET("/posts")
    Call<MyPostResponse> GetMyPostTest(
            @Query("type") String type,
            @Query("size") Integer size_num,
            @Query("page") Integer page_num
    );

}
