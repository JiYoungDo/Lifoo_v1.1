package com.googoocorncorn.lifoo_v11.src.SplashActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.googoocorncorn.lifoo_v11.R;
import com.googoocorncorn.lifoo_v11.src.BaseActivity;
import com.googoocorncorn.lifoo_v11.src.MainActivity.MainActivity;
import com.googoocorncorn.lifoo_v11.src.SocialLoginActivity.SocialLoginActivity;
import com.googoocorncorn.lifoo_v11.src.SplashActivity.interfaces.SplashActivityView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.googoocorncorn.lifoo_v11.ApplicationClass.X_ACCESS_TOKEN;
import static com.googoocorncorn.lifoo_v11.ApplicationClass.sSharedPreferences;
import static com.googoocorncorn.lifoo_v11.ApplicationClass.TAG;


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

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("MY KEY HASH:",
                        Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

        String keyHash = com.kakao.util.helper.Utility.getKeyHash(this /* context */);
        Log.d("카카오 키 해시", keyHash);

        getHashKey();
        // 자동 로그인 통신
        TryAutoLogin();



        byte[] sha1 = {
                (byte)0x77, (byte)0xA8, (byte)0x9B,  (byte)0xE9, (byte)0xA6, (byte)0x8A,
                (byte)0xFB, (byte)0x3F, (byte)0xC2, (byte)0xE8, (byte)0xEB, (byte)0x0C,
                (byte)0x71, (byte)0x85, (byte)0x84, (byte)0x63, (byte)0x72, (byte)0x72,
                (byte)0x28, (byte)0x0A};
        Log.d("앱서명키 SHA1 : ", Base64.encodeToString(sha1, Base64.NO_WRAP));
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



    private void getHashKey(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }

}