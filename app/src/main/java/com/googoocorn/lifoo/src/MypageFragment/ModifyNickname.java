package com.googoocorn.lifoo.src.MypageFragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.googoocorn.lifoo.R;
import com.googoocorn.lifoo.src.MainActivity.MainActivity;
import com.googoocorn.lifoo.src.MypageFragment.interfaces.MypageFragmentActivityView;
import com.googoocorn.lifoo.src.MypageFragment.models.ImogeResponse;
import com.googoocorn.lifoo.src.MypageFragment.models.MyPostResponse;
import com.googoocorn.lifoo.src.MypageFragment.models.MypageFragmentResponse;
import com.googoocorn.lifoo.src.MypageFragment.models.NicknameBody;
import com.googoocorn.lifoo.src.MypageFragment.models.NicknameResponse;

import static com.googoocorn.lifoo.ApplicationClass.TAG;
import static com.googoocorn.lifoo.ApplicationClass.sSharedPreferences;

public class ModifyNickname extends AppCompatActivity implements MypageFragmentActivityView {
    Button btnBack;
    EditText modify_nickname;
    TextView btnFinishTop;
    Button btnFinishBottom;

    MypageService mypageService = new MypageService(this);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_nickname);

        Intent intent = getIntent();

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        modify_nickname = findViewById(R.id.modify_nickname);
        modify_nickname.setText(intent.getStringExtra("Nickname"));
        String origin_nickname = intent.getStringExtra("Nickname");

//        Context context = getApplicationContext();
        sSharedPreferences = getSharedPreferences(TAG, MODE_PRIVATE);
        SharedPreferences.Editor editor = sSharedPreferences.edit();

        String userIdx = sSharedPreferences.getString("user_idx",null);

        // enter ????????? ????????? ?????????
        modify_nickname.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow( modify_nickname.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        btnFinishTop = findViewById(R.id.btnSuccess);
        btnFinishTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(origin_nickname.equals(modify_nickname.getText().toString())){
                    Toast.makeText(getApplicationContext(),"???????????? ??????????????????!",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (modify_nickname.getText().toString().replaceAll(" ","").length() > 6
                            || modify_nickname.getText().toString().replaceAll(" ","").length() < 1) {
                        Toast.makeText(getApplicationContext(), "1~6????????? ??????????????????", Toast.LENGTH_SHORT).show();
                    } else { //????????? ??????
                        NicknameBody nicknameBody = new NicknameBody(modify_nickname.getText().toString());
                        mypageService.EditNickname(userIdx, nicknameBody);

                    }
                }
            }
        });

        btnFinishBottom = findViewById(R.id.btnModifyFinish);
        btnFinishBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(origin_nickname.equals(modify_nickname.getText().toString())){
                    Toast.makeText(getApplicationContext(),"???????????? ??????????????????!",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (modify_nickname.getText().toString().replaceAll(" ","").length() > 6
                            || modify_nickname.getText().toString().replaceAll(" ","").length() < 1) {
                        Toast.makeText(getApplicationContext(), "1~6????????? ??????????????????", Toast.LENGTH_SHORT).show();
                    } else { //????????? ??????
                        NicknameBody nicknameBody = new NicknameBody(modify_nickname.getText().toString());
                        mypageService.EditNickname(userIdx, nicknameBody);

                    }
                }
            }
        });

    }

    @Override
    public void GetProfileFailure(String message, int code) {

    }

    @Override
    public void GetProfileSuccess(MypageFragmentResponse mypageFragmentResponse, int code) {

    }

    @Override
    public void EditProfileFailure(String message, int code) {
        Log.d("????????? ?????? ?????? ", message+ "&& " + String.valueOf(code));
    }

    @Override
    public void EditProfileSuccess(NicknameResponse nicknameResponse, int code) {
        Log.d("????????? ?????? ?????? ",  String.valueOf(code));

        if(code == 2000){
            Log.d("????????? ?????? ?????? ",  String.valueOf(code));

            Toast.makeText(getApplicationContext(), "???????????? ????????????????????? :)" , Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

            finish();
        }

        else{
            Log.d("?????? ?????? ???????????? ??????", String.valueOf(code));
            Toast.makeText(getApplicationContext(),"????????? ??????! sorry x_x",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void GetImogeFailure(String message, int code) {

    }

    @Override
    public void GetImogeSuccess(ImogeResponse imogeResponse, int code) {

    }

    @Override
    public void GetMyPostFailure(String message, int code) {

    }

    @Override
    public void GetMyPostSuccess(MyPostResponse myPostResponse, int code) {

    }
}
