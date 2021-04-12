package com.googoocorn.lifoo.src.SocialLoginActivity;

import android.app.Application;
import android.content.Context;

import androidx.annotation.Nullable;

import com.kakao.auth.ApprovalType;
import com.kakao.auth.AuthType;
import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.ISessionConfig;
import com.kakao.auth.KakaoAdapter;
import com.kakao.auth.KakaoSDK;


public class KakaoApp extends Application {

    private static volatile KakaoApp instance = null;

    private static class KakaoSDKAdapter extends KakaoAdapter {

        // 카카오 로그인 세션 불러올 때 설정값 설정 부분
        public ISessionConfig getSessionConfig() {
            return new ISessionConfig() {
                @Override
                public AuthType[] getAuthTypes() {
                    //로그인 방식 지정 하는 부분
                    return new AuthType[]{AuthType.KAKAO_LOGIN_ALL};
                }

                @Override
                public boolean isUsingWebviewTimer() {
                    return false;
                }

                @Override
                public boolean isSecureMode() {
                    return false;
                }

                @Nullable
                @Override
                public ApprovalType getApprovalType() {
                    return ApprovalType.INDIVIDUAL;
                }

                // 다음 번 다시 로그인 시 email 폼을 누르면 이전에 입력했던 이메일 나타난다.
                @Override
                public boolean isSaveFormData() {
                    return true;
                }
            };
        }

        @Override
        public IApplicationConfig getApplicationConfig() {
            return new IApplicationConfig() {
                @Override
                public Context getApplicationContext() {
                    return KakaoApp.getGlobalApplicationContext();
                }
            };
        }
    }


    public static KakaoApp getGlobalApplicationContext()
    {
        if(instance == null)
        {
            throw  new IllegalStateException("this application does not inherit com.kakao.GlobalApplication");
        }
        return instance;
    }


//    public static Context getContext(){
//        return instance;
//    }



    public static KakaoApp getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        KakaoSDK.init(new KakaoSDKAdapter());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }
}
