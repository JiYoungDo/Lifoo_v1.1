package com.GoogooCorn.lifoo_v11.src.SocialLoginActivity;

import com.GoogooCorn.lifoo_v11.src.SocialLoginActivity.interfaces.SocialLoginActivityView;
import com.GoogooCorn.lifoo_v11.src.SocialLoginActivity.interfaces.SocialLoginRetrofitInterface;
import com.GoogooCorn.lifoo_v11.src.SocialLoginActivity.models.SocialLoginBody;
import com.GoogooCorn.lifoo_v11.src.SocialLoginActivity.models.SocialLoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.GoogooCorn.lifoo_v11.ApplicationClass.getRetrofit;

public class SocialLoginService {

    private final SocialLoginActivityView socialLoginActivityView;

    public SocialLoginService(SocialLoginActivityView mSocialLoginActivityView) {
        this.socialLoginActivityView = mSocialLoginActivityView;
    }

    // sns 로그인 통신
    void postSocialLogIn(String accessToken) {

        final SocialLoginRetrofitInterface socialLoginRetrofitInterface = getRetrofit().create(SocialLoginRetrofitInterface.class);
        socialLoginRetrofitInterface.socialLoginTest(new SocialLoginBody(accessToken)).enqueue(new Callback<SocialLoginResponse>() {


            // 통신 성공 시
            @Override
            public void onResponse(Call<SocialLoginResponse> call, Response<SocialLoginResponse> response) {
                final SocialLoginResponse socialLoginResponse = response.body();

                if (socialLoginResponse == null) {
                    // 통신은 됬는데 결과 값이 null 이면?
                    socialLoginActivityView.SocialLoginFailure("null", 0);
                    return;
                }
                // 통신도 성공! 받아오는 값도 성공!
                socialLoginActivityView.SocialLogInSuccess(socialLoginResponse,socialLoginResponse.getMessage(),socialLoginResponse.getCode());
            }

            // API 통신 자체가 실패
            @Override
            public void onFailure(Call<SocialLoginResponse> call, Throwable t) {
                socialLoginActivityView.SocialLoginFailure("통신 자체 실패",0);
            }
        });
    }
}
