package com.googoocorn.lifoo.src.RegisterAgainActivity;

import com.googoocorn.lifoo.src.RegisterAgainActivity.interfaces.RegisterActivityView;
import com.googoocorn.lifoo.src.RegisterAgainActivity.interfaces.RegisterRetrofitInterface;
import com.googoocorn.lifoo.src.RegisterAgainActivity.models.GetNicknameResponse;
import com.googoocorn.lifoo.src.RegisterAgainActivity.models.RegisterBody;
import com.googoocorn.lifoo.src.RegisterAgainActivity.models.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.googoocorn.lifoo.ApplicationClass.getRetrofit;

public class RegisterService {


    private final RegisterActivityView registerActivityView;

    public RegisterService(RegisterActivityView mRegisterActivityView) {
        this.registerActivityView = mRegisterActivityView;
    }

    // 닉네임 통신
    void GetNickname() {

        final RegisterRetrofitInterface registerRetrofitInterface = getRetrofit().create(RegisterRetrofitInterface.class);
        registerRetrofitInterface.NicknameTest().enqueue(new Callback<GetNicknameResponse>() {

            // 통신 성공 시
            @Override
            public void onResponse(Call<GetNicknameResponse> call, Response<GetNicknameResponse> response) {

                final GetNicknameResponse getNicknameResponse = response.body();

                if (getNicknameResponse == null) {
                    // 통신은 됬는데 결과 값이 null 이면?
                    registerActivityView.GetNicknameFailure("null", 0);
                    return;
                }
                // 통신도 성공! 받아오는 값도 성공!
                registerActivityView.GetNicknameSuccess(getNicknameResponse.getMessage(),getNicknameResponse.getResult().getNickname(), getNicknameResponse.getCode());
            }

            // API 통신 자체가 실패
            @Override
            public void onFailure(Call<GetNicknameResponse> call, Throwable t) {
                registerActivityView.GetNicknameFailure("통신 자체 실패",0);
            }
        });
    }


}
