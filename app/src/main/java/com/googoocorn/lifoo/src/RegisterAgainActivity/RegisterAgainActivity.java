package com.googoocorn.lifoo.src.RegisterAgainActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

    ImageView back_btn;

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

        back_btn = findViewById(R.id.register_again_back);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterAgainActivity.this,SocialLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //???????????? ??????
        btnAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //???????????? ??????
                TryGetNickname();
            }
        });




        //???????????? ??????
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
                user_sns_Id = sSharedPreferences.getString("user_id","");


                if(user_nick_name.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "???????????? ?????? ?????????!",Toast.LENGTH_SHORT).show();
                }else
                {
                    sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
                    SharedPreferences.Editor editor = sSharedPreferences.edit();
                    // ????????? ?????? ?????? ?????? jwt ?????? ??????
                    editor.remove("nick");
                    editor.putString("nick",user_nick_name);
                    editor.commit();

                    Log.d("???????????? ??????????????? ?????? ?????????",user_nick_name);

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
        Log.d("????????? api :", "???????????????: " + message + "  ?????? ??????:" + String.valueOf(code));
        Toast.makeText(getApplicationContext(), message , Toast.LENGTH_SHORT).show();

    }

    @Override
    public void GetNicknameSuccess(String message, String nickname, int code) {

        hideProgressDialog();
        user_nick_name = nickname;

        String temp_str = nickname.replaceAll(" ","");
        Log.d("?????? ?????? ??????", temp_str);
        int temp_str_size = temp_str.length();
        Log.d("?????? ????????? ??????" , String.valueOf(temp_str_size));

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
