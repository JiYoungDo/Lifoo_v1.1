package com.example.lifoo_v11.src.SocialLoginActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lifoo_v11.R;
import com.example.lifoo_v11.src.BaseActivity;
import com.example.lifoo_v11.src.MainActivity.MainActivity;
import com.example.lifoo_v11.src.RegisterAgainActivity.RegisterAgainActivity;
import com.example.lifoo_v11.src.SocialLoginActivity.interfaces.SocialLoginActivityView;
import com.example.lifoo_v11.src.SocialLoginActivity.models.SocialLoginResponse;
import com.kakao.auth.ApiErrorCode;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.auth.authorization.accesstoken.AccessToken;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.exception.KakaoException;

import static com.example.lifoo_v11.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.lifoo_v11.ApplicationClass.sSharedPreferences;
import static com.example.lifoo_v11.ApplicationClass.TAG;


public class SocialLoginActivity extends BaseActivity implements SocialLoginActivityView {

    // 카카오 로그
    SocialLoginService socialLoginService = new SocialLoginService(this);

    private KakaoCallback kakaoCallback;

    LoginButton kakao_login_btn_invisible;
    Button login_btn;


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_login);

        login_btn = findViewById(R.id.btnSocialLogin);
        kakao_login_btn_invisible = findViewById(R.id.login_btn_kakao_gone);

        // 카카오 로그
        kakaoCallback = new KakaoCallback();

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                kakao_login_btn_invisible.callOnClick();

                Session.getCurrentSession().addCallback(kakaoCallback);
                Session.getCurrentSession().checkAndImplicitOpen();
            }
        });

    }



    private void TrySocialLogin(String accessToken)
    {
        showProgressDialog();
        socialLoginService.postSocialLogIn(accessToken);
    }

    @Override

    public void SocialLoginFailure(String message, int code) {

        hideProgressDialog();

        // 통신이 실패 하지 않는 이상 이 구간은 없을 수 있음.

        Log.d("카카오 로그인 실패", message+" & "+String.valueOf(code));

    }

    @Override
    public void SocialLogInSuccess(SocialLoginResponse socialLoginResponse, String message, int code) {


        hideProgressDialog();


        if(code == 2000)
        {
            // 이미 가입되 있는 사람. jwt 재발급 됨.

            Log.d("카카오 로그인 성공 2000", socialLoginResponse.getResult().getJwt() + " && " + socialLoginResponse.getResult().getUserIdx());
            // 소셜 로그인이 성공 한 부분.(유효한 토큰을 가지고 있는 유저임)

            sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
            SharedPreferences.Editor editor = sSharedPreferences.edit();
            // 내부에 저장 되어 있는 jwt 값을 지움
            editor.remove(X_ACCESS_TOKEN);
            editor.putString(X_ACCESS_TOKEN,socialLoginResponse.getResult().getJwt());
            editor.putString("user_idx",socialLoginResponse.getResult().getUserIdx());
            editor.commit();


            // jwt 잘 들어 가 있나 확인.
            String x_access = sSharedPreferences.getString(X_ACCESS_TOKEN,null);
            String index = sSharedPreferences.getString("user_idx",null);

            Log.d("jwt 확인. 카카오 2000",x_access + "하고" + index);

            Intent intent = new Intent(SocialLoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        }else if(code == 3202) {

            // 신규
            sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
            SharedPreferences.Editor editor = sSharedPreferences.edit();
            // 내부에 저장 되어 있는 jwt 값을 지움
            editor.remove(X_ACCESS_TOKEN);
            editor.remove("user_idx");
            editor.commit();

            String x_access = sSharedPreferences.getString(X_ACCESS_TOKEN,null);
            String index = sSharedPreferences.getString("user_idx",null);
            Log.d("jwt 확인. 카카오 3202",x_access + "하고" + index);

            Intent intent = new Intent(SocialLoginActivity.this, RegisterAgainActivity.class);
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();

        }else if(code ==3203 || code == 3016) {
            // 유효하지 않은 토큰
            Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
            // 내부에 저장 되어 있는 jwt 값을 지움
            sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
            SharedPreferences.Editor editor = sSharedPreferences.edit();
            editor.remove(X_ACCESS_TOKEN);
            editor.remove("user_idx");
            editor.commit();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
    }

    private class KakaoCallback implements ISessionCallback {

        /*
        onSessionOpened() : 로그인 세션 열렸을 때.       onSewwionOpenFailed() : 로그인 세션 정상적으로 열리지 않았을 때.
        * */
        @Override
        public void onSessionOpened() {

            UserManagement.getInstance().me(new MeV2ResponseCallback() {
                @Override
                public void onFailure(ErrorResult errorResult) {
                    // 로그인 실패시 (네트워크 문제 포함)
                    int result = errorResult.getErrorCode();
                    if(result == ApiErrorCode.CLIENT_ERROR_CODE)
                    {
                        Toast.makeText(getApplicationContext(), R.string.network_error,Toast.LENGTH_SHORT).show();
                    }else
                    {
                        Toast.makeText(getApplicationContext(),R.string.login_error,Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                    // 로그인 세션 도중 비정상적 이유로 닫힐때
                    Toast.makeText(getApplicationContext(), R.string.session_error,Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess(MeV2Response result) {

                    // 로그인 성공 시 카카오 토큰 값을 받아서, 서버의 카카오 로그인 api 에 사용해야 한다!
                    AccessToken accessToken;
                    accessToken = Session.getCurrentSession().getTokenInfo();

                    long user_id;
                    user_id = result.getId();

                    Log.e("카카오 토큰",accessToken.getAccessToken());
                    Log.e("카카오 유저 아이디", String.valueOf(user_id));

                    sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
                    SharedPreferences.Editor editor = sSharedPreferences.edit();
                    editor.remove(X_ACCESS_TOKEN);
                    editor.putString(X_ACCESS_TOKEN,accessToken.getAccessToken());
                    editor.commit();

                    sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
                    SharedPreferences.Editor editor2 = sSharedPreferences.edit();
                    editor2.remove("user_id");
                    editor2.putString("user_id",String.valueOf(user_id));
                    editor2.commit();


                    TrySocialLogin(accessToken.getAccessToken());

                }
            });
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            Toast.makeText(getApplicationContext(),R.string.login_error+exception.toString(),Toast.LENGTH_SHORT).show();
        }

    }

}