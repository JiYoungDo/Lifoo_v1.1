package com.GoogooCorn.lifoo_v11.src.MypageFragment;


import com.GoogooCorn.lifoo_v11.src.MypageFragment.interfaces.MypageFragmentActivityView;
import com.GoogooCorn.lifoo_v11.src.MypageFragment.interfaces.MypageFragmentRetrofitInterface;
import com.GoogooCorn.lifoo_v11.src.MypageFragment.models.ImogeResponse;
import com.GoogooCorn.lifoo_v11.src.MypageFragment.models.MyPostResponse;
import com.GoogooCorn.lifoo_v11.src.MypageFragment.models.MypageFragmentResponse;
import com.GoogooCorn.lifoo_v11.src.MypageFragment.models.NicknameBody;
import com.GoogooCorn.lifoo_v11.src.MypageFragment.models.NicknameResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.GoogooCorn.lifoo_v11.ApplicationClass.getRetrofit;

public class MypageService {
    private final MypageFragmentActivityView mypageFragmentActivityView;

    public MypageService(MypageFragmentActivityView mypageFragmentActivityView) {
        this.mypageFragmentActivityView = mypageFragmentActivityView;
    }

    // 닉네임 받아오기
    public void GetNicknames(String userIdx) {

        final MypageFragmentRetrofitInterface mypageFragmentRetrofitInterface = getRetrofit().create(MypageFragmentRetrofitInterface.class);
        mypageFragmentRetrofitInterface.GetProfileTest(userIdx).enqueue(new Callback<MypageFragmentResponse>() {

            // 통신 성공
            @Override
            public void onResponse(Call<MypageFragmentResponse> call, Response<MypageFragmentResponse> response) {
                final MypageFragmentResponse mypageFragmentResponse = response.body();

                if (mypageFragmentResponse == null) {
                    // 통신 성공인데 값 받아오기 실패
                    mypageFragmentActivityView.GetProfileFailure("null", 0);
                    return;
                }
                // 통신, 값 받아오기 성공
                mypageFragmentActivityView.GetProfileSuccess(mypageFragmentResponse,mypageFragmentResponse.getCode());
            }

            // API 통신 자체가 실패
            @Override
            public void onFailure(Call<MypageFragmentResponse> call, Throwable t) {
                mypageFragmentActivityView.GetProfileFailure("통신 자체 실패",0);
            }
        });
    }

    // 닉네임 수정하기
    public void SetNickname(String userIdx, NicknameBody body){
        final MypageFragmentRetrofitInterface mypageFragmentRetrofitInterface = getRetrofit().create(MypageFragmentRetrofitInterface.class);
        mypageFragmentRetrofitInterface.SetProfileTest(userIdx, body).enqueue(new Callback<NicknameResponse>() {

            // 통신 성공
            @Override
            public void onResponse(Call<NicknameResponse> call, Response<NicknameResponse> response) {
                final NicknameResponse nicknameResponse = response.body();

                if (nicknameResponse == null) {
                    // 통신 성공인데 값 받아오기 실패
                    mypageFragmentActivityView.GetProfileFailure("null", 0);
                    return;
                }
                // 통신, 값 받아오기 성공
                mypageFragmentActivityView.GetProfileSuccess(nicknameResponse,nicknameResponse.getCode());
            }

            // API 통신 자체가 실패
            @Override
            public void onFailure(Call<NicknameResponse> call, Throwable t) {
                mypageFragmentActivityView.GetProfileFailure("통신 자체 실패",0);
            }
        });
    }

    // 이모지 받아오기
    public void GetImoge(){
        final MypageFragmentRetrofitInterface mypageFragmentRetrofitInterface = getRetrofit().create(MypageFragmentRetrofitInterface.class);
        mypageFragmentRetrofitInterface.GetImogeTest().enqueue(new Callback<ImogeResponse>() {
            // 통신 성공
            @Override
            public void onResponse(Call<ImogeResponse> call, Response<ImogeResponse> response) {
                final ImogeResponse imogeResponse = response.body();

                if (imogeResponse == null) {
                    // 통신 성공인데 값 받아오기 실패
                    mypageFragmentActivityView.GetImogeFailure("null", 0);
                    return;
                }
                // 통신, 값 받아오기 성공
                mypageFragmentActivityView.GetImogeSuccess(imogeResponse, imogeResponse.getCode());
            }

            // API 통신 자체가 실패
            @Override
            public void onFailure(Call<ImogeResponse> call, Throwable t) {
                mypageFragmentActivityView.GetImogeFailure("통신 자체 실패", 0);
            }
        });
    }

    public void GetMyPost(){
        final MypageFragmentRetrofitInterface mypageFragmentRetrofitInterface = getRetrofit().create(MypageFragmentRetrofitInterface.class);
        mypageFragmentRetrofitInterface.GetMyPostTest("USER", 30, 0).enqueue(new Callback<MyPostResponse>() {
            // 통신 성공
            @Override
            public void onResponse(Call<MyPostResponse> call, Response<MyPostResponse> response) {
                final MyPostResponse myPostResponse = response.body();

                if (myPostResponse == null) {
                    // 통신 성공인데 값 받아오기 실패
                    mypageFragmentActivityView.GetMyPostFailure("null", 0);
                    return;
                }
                // 통신, 값 받아오기 성공
                mypageFragmentActivityView.GetMyPostSuccess(myPostResponse, myPostResponse.getCode());
            }

            // API 통신 자체가 실패
            @Override
            public void onFailure(Call<MyPostResponse> call, Throwable t) {
                mypageFragmentActivityView.GetMyPostFailure("통신 자체 실패", 0);
            }
        });
    }
}
