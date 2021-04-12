package com.googoocorn.lifoo.src.RegisterAgainActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



import com.googoocorn.lifoo.R;
import com.googoocorn.lifoo.src.BaseActivity;
import com.googoocorn.lifoo.src.MainActivity.MainActivity;
import com.googoocorn.lifoo.src.RegisterActivity.RegisterActivity;
import com.googoocorn.lifoo.src.RegisterAgainActivity.interfaces.RegisterActivityView;
import com.googoocorn.lifoo.src.RegisterAgainActivity.models.RegisterResponse;
import com.googoocorn.lifoo.src.SocialLoginActivity.SocialLoginActivity;


import static com.googoocorn.lifoo.ApplicationClass.TAG;
import static com.googoocorn.lifoo.ApplicationClass.X_ACCESS_TOKEN;
import static com.googoocorn.lifoo.ApplicationClass.sSharedPreferences;

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
                    sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
                    SharedPreferences.Editor editor = sSharedPreferences.edit();
                    // 내부에 저장 되어 있는 jwt 값을 지움
                    editor.remove("nick");
                    editor.putString("nick",user_nick_name);
                    editor.commit();

                    Log.d("레지스터 어게인에서 유저 닉네임",user_nick_name);

                    Intent intent = new Intent(RegisterAgainActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }

            }

        });

    }


    private void TryGetNickname()
    {
        showProgressDialog();
        registerService.GetNickname();
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
            sixthLetter.setText("");

        } else if(temp_str_size == 4) {

            firstLetter.setText(temp_str.substring(0,1));
            secondLetter.setText(temp_str.substring(1,2));
            thirdLetter.setText(temp_str.substring(2,3));
            fourthLetter.setText(temp_str.substring(3,4));
            fifthLetter.setText("");
            sixthLetter.setText("");

        }else if(temp_str_size == 3) {

            firstLetter.setText(temp_str.substring(0,1));
            secondLetter.setText(temp_str.substring(1,2));
            thirdLetter.setText(temp_str.substring(2,3));
            fourthLetter.setText("");
            fifthLetter.setText("");
            sixthLetter.setText("");

        }else if(temp_str_size == 2) {

            firstLetter.setText(temp_str.substring(0,1));
            secondLetter.setText(temp_str.substring(1,2));
            thirdLetter.setText("");
            fourthLetter.setText("");
            fifthLetter.setText("");
            sixthLetter.setText("");
        }



    }


}
