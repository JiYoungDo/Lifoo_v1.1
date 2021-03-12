package com.example.lifoo_v11.src.RegisterAgainActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;



import com.example.lifoo_v11.R;
import com.example.lifoo_v11.src.BaseActivity;
import com.example.lifoo_v11.src.MainActivity.MainActivity;
import com.example.lifoo_v11.src.RegisterAgainActivity.interfaces.RegisterActivityView;
import com.example.lifoo_v11.src.RegisterAgainActivity.models.RegisterResponse;
import com.example.lifoo_v11.src.SocialLoginActivity.SocialLoginActivity;


import static com.example.lifoo_v11.ApplicationClass.TAG;
import static com.example.lifoo_v11.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.lifoo_v11.ApplicationClass.sSharedPreferences;

public class RegisterAgainActivity extends BaseActivity implements RegisterActivityView {


    RegisterService registerService = new RegisterService(this);

    Button btnAgain;
    Button btnSignUp;
    TextView firstLetter;
    TextView secondLetter;
    TextView thirdLetter;
    TextView fourthLetter;
    TextView fifthLetter;
    TextView sixthLetter;

    String user_nick_name ="";
    String user_sns_Id;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_again);


        btnAgain = findViewById(R.id.btnAgain);
        btnSignUp = findViewById(R.id.btnSignUp);
        firstLetter = findViewById(R.id.firstLetter);
        secondLetter = findViewById(R.id.secondLetter);
        thirdLetter = findViewById(R.id.thirdLetter);
        fourthLetter = findViewById(R.id.fourthLetter);
        fifthLetter = findViewById(R.id.fifthLetter);
        sixthLetter = findViewById(R.id.sixthLetter);

        //다시뽑기 버튼
        btnAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //슬롯머신 작동
                TryGetNickname();
            }

        });




        //가입하기 버튼
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
                user_sns_Id = sSharedPreferences.getString("user_id","");


                if(user_nick_name.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "닉네임을 뽑아 주세요!",Toast.LENGTH_SHORT).show();
                }else
                {
                    TryRegister(user_nick_name, user_sns_Id);
                }

            }

        });

    }


    private void TryGetNickname()
    {
        showProgressDialog();
        registerService.GetNickname();
    }


    private void TryRegister(String nick_name, String snsId)
    {
        showProgressDialog();
        registerService.PostRegister(nick_name, snsId);
    }


    @Override
    public void GetNicknameFailure(String message, int code) {

        hideProgressDialog();
        Log.d("닉네임 api :", "오류메세지: " + message + "  오류 코드:" + String.valueOf(code));
        Toast.makeText(getApplicationContext(), message , Toast.LENGTH_SHORT).show();

    }

    @Override
    public void GetNicknameSuccess(String message, String nickname, int code) {

        hideProgressDialog();
        user_nick_name = nickname;

        String temp_str = nickname.replaceAll(" ","");
        Log.d("빈칸 없나 확인", temp_str);
        int temp_str_size = temp_str.length();
        Log.d("닉넴 사이즈 확인" , String.valueOf(temp_str_size));

        if(temp_str_size >= 6)
        {
            firstLetter.setText(temp_str.substring(0,1));
            secondLetter.setText(temp_str.substring(1,2));
            thirdLetter.setText(temp_str.substring(2,3));
            fourthLetter.setText(temp_str.substring(3,4));
            fifthLetter.setText(temp_str.substring(4,5));
            sixthLetter.setText(temp_str.substring(5,6));

        } else if(temp_str_size == 5) {

            firstLetter.setText(temp_str.substring(0,1));
            secondLetter.setText(temp_str.substring(1,2));
            thirdLetter.setText(temp_str.substring(2,3));
            fourthLetter.setText(temp_str.substring(3,4));
            fifthLetter.setText(temp_str.substring(4,5));

        } else if(temp_str_size == 4) {

            firstLetter.setText(temp_str.substring(0,1));
            secondLetter.setText(temp_str.substring(1,2));
            thirdLetter.setText(temp_str.substring(2,3));
            fourthLetter.setText(temp_str.substring(3,4));

        }else if(temp_str_size == 3) {

            firstLetter.setText(temp_str.substring(0,1));
            secondLetter.setText(temp_str.substring(1,2));
            thirdLetter.setText(temp_str.substring(2,3));

        }else if(temp_str_size == 2) {

            firstLetter.setText(temp_str.substring(0,1));
            secondLetter.setText(temp_str.substring(1,2));
        }



    }

    @Override
    public void RegisterFailure(String message, int code) {

        hideProgressDialog();
        Log.d("회원 가입 api :", "오류메세지: " + message + "  오류 코드:" + String.valueOf(code));
        Toast.makeText(getApplicationContext(), message , Toast.LENGTH_SHORT).show();

    }

    @Override
    public void RegisterSuccess(RegisterResponse registerResponse, String message, int code) {

        hideProgressDialog();
        Log.d("회원 가입 api :", "통신 성공 메세지: " + message + "  오류 코드:" + String.valueOf(code));



        switch(code)
        {
            case 2000:
                // 회원 가입 성공
                sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
                SharedPreferences.Editor editor = sSharedPreferences.edit();
                // 내부에 저장 되어 있는 jwt 값을 지움
                editor.remove(X_ACCESS_TOKEN);
                editor.remove("user_idx");
                editor.commit();

                SharedPreferences.Editor editor2 = sSharedPreferences.edit();
                // 소셜 로그인을 통해 jwt 값을
                editor2.putString(X_ACCESS_TOKEN,registerResponse.getResult().getJwt());
                editor2.putString("user_idx",registerResponse.getResult().getUserIdx());
                editor2.commit();

                String x_access = sSharedPreferences.getString(X_ACCESS_TOKEN,null);
                String index = sSharedPreferences.getString("user_idx",null);
                Log.d("jwt 확인. 회원가입 2000",x_access + "하고" + index);

                Toast.makeText(getApplicationContext(), message , Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(RegisterAgainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

                break;

            case 3100:
                // 이미 존재하는 닉네임
                Toast.makeText(getApplicationContext(), message + "\n닉네임을 다시 랜덤 생성 해주세요 :-)" , Toast.LENGTH_SHORT).show();
                firstLetter.setText("");
                secondLetter.setText("");
                thirdLetter.setText("");
                fourthLetter.setText("");
                fifthLetter.setText("");
                sixthLetter.setText("");

                break;

            case 3101:

            case 3104:
                // 이미 가입된 sns_ID 회원
                // 이미 존재하는 회원
                Toast.makeText(getApplicationContext(), message , Toast.LENGTH_SHORT).show();
                Intent intent_0 = new Intent(RegisterAgainActivity.this, SocialLoginActivity.class);
                startActivity(intent_0);
                finish();

                break;


        }
    }
}
