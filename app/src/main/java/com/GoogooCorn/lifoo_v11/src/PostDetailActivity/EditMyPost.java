package com.GoogooCorn.lifoo_v11.src.PostDetailActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.GoogooCorn.lifoo_v11.R;
import com.GoogooCorn.lifoo_v11.src.MainActivity.MainActivity;
import com.GoogooCorn.lifoo_v11.src.MypageFragment.models.NicknameBody;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.interfaces.PostDetailActivityView;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.models.GetCommentResponse;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.models.GetPostResponse;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.models.PostDeleteResponse;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.models.PostEditBody;
import com.bumptech.glide.Glide;

import static com.GoogooCorn.lifoo_v11.ApplicationClass.TAG;
import static com.GoogooCorn.lifoo_v11.ApplicationClass.sSharedPreferences;

public class EditMyPost extends AppCompatActivity implements PostDetailActivityView {

    Button btnBack;
    ImageView post_img;
    EditText modifiable_title;
    EditText modifiable_content;
    TextView btnFinishTop;
    Button btnFinishBottom;

    String post_img_url;

    // 서비스
    PostDetailActivityService postDetailActivityService = new PostDetailActivityService(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);

        // post_idx로 게시물 조회
        String get_post_idx;
        sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
        get_post_idx= sSharedPreferences.getString("clicked_post_idx", "");
        Log.d("포스트 인덱스" , get_post_idx);

        int post_idx = Integer.parseInt(get_post_idx);

        TryPostDetail(post_idx);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        modifiable_title = findViewById(R.id.modifiable_title);
        modifiable_content = findViewById(R.id.modifiable_content);
        post_img = findViewById(R.id.post_img);

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
                // 수정한 내용 body에 담아서 통신
                PostEditBody postEditBody = new PostEditBody(post_img_url, modifiable_title.getText().toString(), modifiable_content.getText().toString());
                postDetailActivityService.EditMyPost(post_idx, postEditBody);

//                Intent intent = new Intent(getApplicationContext(), ViewAllPost.class);
//                startActivity(intent);
                finish();
            }
        });
        btnFinishBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 수정한 내용 body에 담아서 통신
                PostEditBody postEditBody = new PostEditBody(post_img_url, modifiable_title.getText().toString(), modifiable_content.getText().toString());
                postDetailActivityService.EditMyPost(post_idx, postEditBody);

                finish();
            }
        });
    }

    private void TryPostDetail(int postIdx)
    {
        postDetailActivityService.getDetailPost(postIdx);
    }

    @Override
    public void GetPostDetailFailure(String message, int code) {
        Log.d("게시물 상세 가져오기 실패" , message + "&&" + String.valueOf(code));
    }

    @Override
    public void GetPostDetailSuccess(GetPostResponse getPostResponse, int code) {
        Log.d("게시물 상세 가져오기 성공" , String.valueOf(code));
        post_img_url = getPostResponse.getResult().getPostUrl();

        Glide.with(this).load(getPostResponse.getResult().getPostUrl()).into(post_img);
        modifiable_title.setText(getPostResponse.getResult().getPostTitle());
        modifiable_content.setText(getPostResponse.getResult().getPostBody());
    }

    @Override
    public void EditPostFailure(String message, int code) {
        Log.d("게시물 수정 실패 ", message+ "&& " + String.valueOf(code));
    }

    @Override
    public void EditPostSuccess(GetPostResponse getPostResponse, int code) {
        if(code == 2000){
            Log.d("게시물 수정 성공 ",  String.valueOf(code));

            Toast.makeText(getApplicationContext(), "게시물이 수정되었습니다 :)" , Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

            finish();
        }

        else{
            Log.d("게시물 리스폰스 오류", String.valueOf(code));
            Toast.makeText(getApplicationContext(),"시스템 오류! sorry x_x",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void DeletePostFailure(String message, int code) {

    }

    @Override
    public void DeletePostSuccess(PostDeleteResponse postDeleteResponse, int code) {

    }

    @Override
    public void PostImogeFailure(String message, int code) {

    }

    @Override
    public void PostImogeSuccess(String message, int code) {

    }

    @Override
    public void GetCommentsFailure(String message, int code) {

    }

    @Override
    public void GetCommentsSuccess(GetCommentResponse getCommentResponse, int code) {

    }

    @Override
    public void PostCommentsFailure(String message, int code) {

    }

    @Override
    public void PostCommentsSuccess(String message, int code) {

    }
}
