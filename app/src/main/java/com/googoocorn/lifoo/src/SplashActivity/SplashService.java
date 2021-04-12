package com.googoocorn.lifoo.src.SplashActivity;

import com.googoocorn.lifoo.src.SplashActivity.interfaces.SplashActivityView;
import com.googoocorn.lifoo.src.SplashActivity.interfaces.SplashRetrofitInterface;
import com.googoocorn.lifoo.src.SplashActivity.models.Splash_AutoLogin_Response;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.googoocorn.lifoo.ApplicationClass.getRetrofit;

public class SplashService {

    private final SplashActivityView splashActivityView;

    public SplashService(SplashActivityView mSplashActivityView) {
        this.splashActivityView = mSplashActivityView;
    }

    // sns 로그인 통신
    void getAutoLogIn() {

        final SplashRetrofitInterface splashRetrofitInterface = getRetrofit().create(SplashRetrofitInterface.class);
        splashRetrofitInterface.Auto_Login_Test().enqueue(new Callback<Splash_AutoLogin_Response>() {

            // 통신 성공 시
            @Override
            public void onResponse(Call<Splash_AutoLogin_Response> call, Response<Splash_AutoLogin_Response> response) {
                final Splash_AutoLogin_Response splash_autoLogin_response = response.body();

                if (splash_autoLogin_response == null) {
                    // 통신은 됬는데 결과 값이 null 이면?
                    splashActivityView.AutoLoginFailure("null", 1234);
                    return;
                }
                // 통신도 성공! 받아오는 값도 성공!
                splashActivityView.AutoLogInSuccess(splash_autoLogin_response.getMessage(), splash_autoLogin_response.getCode());
            }

            // API 통신 자체가 실패
            @Override
            public void onFailure(Call<Splash_AutoLogin_Response> call, Throwable t) {
                splashActivityView.AutoLoginFailure("통신 자체 실패",0);
            }
        });
    }
}
