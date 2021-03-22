package com.GoogooCorn.lifoo_v11.src.MypageFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.GoogooCorn.lifoo_v11.R;

public class EditMyPost extends AppCompatActivity {

    Button btnBack;
    EditText modifiable_title;
    EditText modifiable_content;
    TextView btnFinishTop;
    Button btnFinishBottom;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        modifiable_title = findViewById(R.id.modifiable_title);
        modifiable_content = findViewById(R.id.modifiable_content);

        // enter 누르면 키보드 내리기
        modifiable_title.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow( modifiable_title.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        btnFinishTop = findViewById(R.id.btnFinishTop);
        btnFinishBottom = findViewById(R.id.btnFinishBottom);

        btnFinishTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), ViewAllPost.class);
//                startActivity(intent);
                finish();
            }
        });
        btnFinishBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
