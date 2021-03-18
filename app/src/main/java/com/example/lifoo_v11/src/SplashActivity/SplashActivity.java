package com.example.lifoo_v11.src.SplashActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.lifoo_v11.R;
import com.example.lifoo_v11.src.BaseActivity;
import com.example.lifoo_v11.src.MainActivity.MainActivity;
import com.example.lifoo_v11.src.SocialLoginActivity.SocialLoginActivity;
import com.example.lifoo_v11.src.SplashActivity.interfaces.SplashActivityView;

import static com.example.lifoo_v11.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.lifoo_v11.ApplicationClass.sSharedPreferences;
import static com.example.lifoo_v11.ApplicationClass.TAG;


public class SplashActivity extends BaseActivity implements SplashActivityView {

    // 자동 로그인
    SplashService splashService = new SplashService(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //  jwt 정보 확인하여
        sSharedPreferences = getSharedPreferences(TAG, MODE_PRIVATE);
        String jwtToken = sSharedPreferences.getString(X_ACCESS_TOKEN, "");
        Log.d("자동 로그인 jwt 토큰 ", jwtToken);

        // 자동 로그인 통신
        TryAutoLogin();
    }


    private void TryAutoLogin()
    {
        // showProgressDialog();
        splashService.getAutoLogIn();
    }

    @Override
    public void AutoLoginFailure(String message, int code) {
        // null 값 혹은 통신 실
        sSharedPreferences = getSharedPreferences(TAG, MODE_PRIVATE);
        String jwtToken = sSharedPreferences.getString(X_ACCESS_TOKEN, "");

        Log.d("자동 로그인 실 ", jwtToken+" & "+ message);

        Intent intent = new Intent(SplashActivity.this, SocialLoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void AutoLogInSuccess(String message, int code) {

        if(code == 2000)
        {
            // 요청 성공
            Log.d("자동로그인","성공 +2000 ");
            Toast.makeText(getApplicationContext(),"자동 로그인 입니다", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        } else if(code == 3000){

            //jwt 입력해 주세요
            Log.d("자동로그인","jwt 없음+ 3000");
            Intent intent0 = new Intent(SplashActivity.this, SocialLoginActivity.class);
            startActivity(intent0);
            finish();

        }else if(code == 3200){

            // 유효하지 않은 jwt
            Log.d("자동로그인","jwt 유효 x + 3200");
            Intent intent1 = new Intent(SplashActivity.this, SocialLoginActivity.class);
            startActivity(intent1);
            finish();

        }else if(code == 3201){
            // 존재하지 않는 회원함
            Log.d("자동로그인","존재 안");
            Intent intent2 = new Intent(SplashActivity.this, SocialLoginActivity.class);
            startActivity(intent2);
            finish();

        }

    }
}