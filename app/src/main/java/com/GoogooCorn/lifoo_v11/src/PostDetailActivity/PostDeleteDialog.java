package com.GoogooCorn.lifoo_v11.src.PostDetailActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.GoogooCorn.lifoo_v11.R;
import com.GoogooCorn.lifoo_v11.src.MainActivity.MainActivity;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.interfaces.PostDetailActivityView;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.models.GetPostResponse;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.models.PostDeleteResponse;

import static com.GoogooCorn.lifoo_v11.ApplicationClass.TAG;
import static com.GoogooCorn.lifoo_v11.ApplicationClass.sSharedPreferences;


public class PostDeleteDialog extends AppCompatActivity implements PostDetailActivityView {
    private Context context;

    private TextView btnCancel;
    private TextView btnDelete;
    private TextView post_delete_dialog_guide;
    private TextView btnConfirm;

    
    PostDetailActivityService postDetailActivityService = new PostDetailActivityService(this);

    public PostDeleteDialog(@NonNull Context context) {
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_delete_dialog);

        // 위젯 정의
        btnCancel = findViewById(R.id.btnCancel);
        btnDelete = findViewById(R.id.btnDelete);
        post_delete_dialog_guide = findViewById(R.id.post_delete_dialog_guide);
        btnConfirm = findViewById(R.id.btnConfirm);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post_delete_dialog_guide.setText(R.string.post_delete_dialog_2);
                btnCancel.setVisibility(View.INVISIBLE);
                btnDelete.setVisibility(View.INVISIBLE);
                btnConfirm.setVisibility(View.VISIBLE);

                // post_idx로 게시물 조회
                String get_post_idx;
                sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
                get_post_idx= sSharedPreferences.getString("clicked_post_idx", "");
                Log.d("포스트 인덱스" , get_post_idx);

                int post_idx = Integer.parseInt(get_post_idx);

                // 게시물 삭제
//                postDetailActivityService.DeleteMyPost(post_idx);

            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                // 리스트에서 삭제된 게시물 사라져야됨
            }
        });

    }

    @Override
    public void GetPostDetailFailure(String message, int code) {
        
    }

    @Override
    public void GetPostDetailSuccess(GetPostResponse getPostResponse, int code) {

    }

    @Override
    public void EditPostFailure(String message, int code) {

    }

    @Override
    public void EditPostSuccess(GetPostResponse getPostResponse, int code) {

    }

    @Override
    public void DeletePostFailure(String message, int code) {
        Log.d("게시물 삭제 실패", message + "&&" + String.valueOf(code));
    }

    @Override
    public void DeletePostSuccess(PostDeleteResponse postDeleteResponse, int code) {
        if(code == 2000){
            Log.d("게시물 삭제 성공",  String.valueOf(code));

            Toast.makeText(getApplicationContext(), "게시물이 삭제되었습니다 !" , Toast.LENGTH_SHORT).show();

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
    public void PostImogeFailure(String message, int code) {

    }

    @Override
    public void PostImogeSuccess(String message, int code) {

    }
}
