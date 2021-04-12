package com.googoocorn.lifoo.src.RegisterActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.googoocorn.lifoo.R;
import com.googoocorn.lifoo.src.MainActivity.MainActivity;
import com.googoocorn.lifoo.src.RegisterActivity.interfaces.newRegisterActiityView;
import com.googoocorn.lifoo.src.RegisterActivity.models.NewRegisterResponse;
import com.googoocorn.lifoo.src.RegisterAgainActivity.RegisterAgainActivity;
import com.googoocorn.lifoo.src.RegisterAgainActivity.RegisterService;
import com.googoocorn.lifoo.src.SocialLoginActivity.SocialLoginActivity;

import static com.googoocorn.lifoo.ApplicationClass.TAG;
import static com.googoocorn.lifoo.ApplicationClass.X_ACCESS_TOKEN;
import static com.googoocorn.lifoo.ApplicationClass.sSharedPreferences;

public class RegisterActivity extends AppCompatActivity implements newRegisterActiityView {

    EditText id_et;
    EditText pw_et;
    EditText pw_et_confirm;
    TextView register_btn;


    ImageView back_btn;

    newregisterservice new_register_service = new newregisterservice(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        id_et = findViewById(R.id.register_et_id);
        pw_et = findViewById(R.id.register_et_pw);
        pw_et_confirm = findViewById(R.id.register_et_pw_confirm);

        register_btn = findViewById(R.id.register_tv_register_btn);


        back_btn = findViewById(R.id.register_back);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, RegisterAgainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = id_et.getText().toString();
                String pw = pw_et.getText().toString();
                String pw_confirm = pw_et_confirm.getText().toString();

                sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
                String nick = sSharedPreferences.getString("nick","");
                Log.d("가져온 저장되어있던 닉네임 값", nick);

                TryPostRegister(nick, id, pw, pw_confirm);
            }
        });
    }

    private void TryPostRegister(String nickname, String id, String pw, String pw_confirm)
    {
        new_register_service.PostRegister(nickname,id,pw,pw_confirm);
    }


    @Override
    public void NewRegisterFailure(String message, int code) {

    }

    @Override
    public void NewRegisterSuccess(NewRegisterResponse newRegisterResponse, String message, int code) {

        if (code == 2000) {

            Log.d("로컬 회원가입 성공 2000", newRegisterResponse.getResult().getJwt() + " && " + newRegisterResponse.getResult().getUserIdx());

            sSharedPreferences = getSharedPreferences(TAG, MODE_PRIVATE);
            SharedPreferences.Editor editor = sSharedPreferences.edit();
            // 내부에 저장 되어 있는 jwt 값을 지움
            editor.remove(X_ACCESS_TOKEN);
            editor.putString(X_ACCESS_TOKEN, newRegisterResponse.getResult().getJwt());
            editor.putString("user_idx", newRegisterResponse.getResult().getUserIdx());
            editor.commit();

            // jwt 잘 들어 가 있나 확인.
            String x_access = sSharedPreferences.getString(X_ACCESS_TOKEN, null);
            String index = sSharedPreferences.getString("user_idx", null);

            Log.d("jwt 확인. 로컬 2000", x_access + "하고" + index);

            Intent intent = new Intent(RegisterActivity.this, SocialLoginActivity.class);
            startActivity(intent);
            finish();

            Toast.makeText(getApplicationContext(), "회원가입이 완료 되었습니다. 로그인해주세요 : )",Toast.LENGTH_LONG);

        }
        if (code == 3100) {
            Toast.makeText(this, "이미 존재하는 닉네임 입니다", Toast.LENGTH_SHORT);
            Intent intent = new Intent(RegisterActivity.this, RegisterAgainActivity.class);
            startActivity(intent);
            finish();
        }
        if (code == 3101)
        {
            Toast.makeText(this, "이미 존재하는 회원입니다.", Toast.LENGTH_SHORT);
            Intent intent = new Intent(RegisterActivity.this, SocialLoginActivity.class);
            startActivity(intent);
            finish();
        }if(code == 3017)
        {
            Toast.makeText(this, "두 개의 비밀번호가 다릅니다.", Toast.LENGTH_SHORT);
            pw_et_confirm.setText("");
            pw_et.setText("");
        }if(code == 3018)
        {
            Toast.makeText(this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT);

        }if(code == 3019)
        {
            Toast.makeText(this, "비밀번호 확인란을 입력해주세요.", Toast.LENGTH_SHORT);
        }if(code == 3020)
        {
            Toast.makeText(this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT);
        }if(code == 3213)
        {
            Toast.makeText(this, "중복된 아이디입니다.", Toast.LENGTH_SHORT);
            id_et.setText("");
        }
    }
}