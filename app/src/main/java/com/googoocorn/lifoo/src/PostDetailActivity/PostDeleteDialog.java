package com.googoocorn.lifoo.src.PostDetailActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.googoocorn.lifoo.R;
import com.googoocorn.lifoo.src.MainActivity.MainActivity;
import com.googoocorn.lifoo.src.PostDetailActivity.interfaces.PostDetailActivityView;
import com.googoocorn.lifoo.src.PostDetailActivity.models.GetCommentResponse;
import com.googoocorn.lifoo.src.PostDetailActivity.models.GetPostResponse;
import com.googoocorn.lifoo.src.PostDetailActivity.models.PostDeleteResponse;

import static com.googoocorn.lifoo.ApplicationClass.TAG;
import static com.googoocorn.lifoo.ApplicationClass.sSharedPreferences;


public class PostDeleteDialog extends Dialog implements PostDetailActivityView {
    private Context context;

    private TextView btnCancel;
    private TextView btnDelete;
    private TextView post_delete_dialog_guide;
    private TextView btnConfirm;


    PostDetailActivityService postDetailActivityService = new PostDetailActivityService(this);

    public PostDeleteDialog(@NonNull Context context) {
        super(context);
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



        // post_idx로 게시물 조회
        String get_post_idx;
        sSharedPreferences = getContext().getSharedPreferences(TAG, getContext().MODE_PRIVATE);
        get_post_idx= sSharedPreferences.getString("clicked_post_idx", "");
        Log.d("포스트 인덱스" , get_post_idx);

        int post_idx = Integer.parseInt(get_post_idx);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post_delete_dialog_guide.setText(R.string.post_delete_dialog_2);
                btnCancel.setVisibility(View.INVISIBLE);
                btnDelete.setVisibility(View.INVISIBLE);
                btnConfirm.setVisibility(View.VISIBLE);

                // 게시물 삭제
                postDetailActivityService.DeleteMyPost(post_idx);
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Intent intent = new Intent(getContext(), MainActivity.class);
                getContext().startActivity(intent);
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

            Toast.makeText(getContext(), "게시물이 삭제되었습니다 !" , Toast.LENGTH_SHORT).show();

        }

        else{
            Log.d("게시물 리스폰스 오류", String.valueOf(code));
            Toast.makeText(getContext(),"시스템 오류! sorry x_x",Toast.LENGTH_SHORT).show();
        }
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

    @Override
    public void ReportPostFailure(String message, int code) {

    }

    @Override
    public void ReportPostSuccess(String message, int code) {

    }
}
