package com.googoocorncorn.lifoo_v11.src.PostDetailActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.googoocorncorn.lifoo_v11.R;
import com.googoocorncorn.lifoo_v11.src.MainActivity.MainActivity;
import com.googoocorncorn.lifoo_v11.src.PostDetailActivity.interfaces.PostDetailActivityView;
import com.googoocorncorn.lifoo_v11.src.PostDetailActivity.models.GetCommentResponse;
import com.googoocorncorn.lifoo_v11.src.PostDetailActivity.models.GetPostResponse;
import com.googoocorncorn.lifoo_v11.src.PostDetailActivity.models.PostDeleteResponse;

import static com.googoocorncorn.lifoo_v11.ApplicationClass.TAG;
import static com.googoocorncorn.lifoo_v11.ApplicationClass.sSharedPreferences;

public class PostReportDialog extends Dialog implements PostDetailActivityView {
    private Context context;

    private TextView btnCancel;
    private TextView btnReport;
    private TextView post_report_dialog_guide;
    private TextView btnConfirm;


    PostDetailActivityService postDetailActivityService = new PostDetailActivityService(this);

    public PostReportDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_report_dialog);

        // 위젯 정의
        btnCancel = findViewById(R.id.btnCancel);
        btnReport = findViewById(R.id.btnReport);
        post_report_dialog_guide = findViewById(R.id.post_report_dialog_guide);
        btnConfirm = findViewById(R.id.btnConfirm);


        // post_idx로 게시물 조회
        String get_post_idx;
        sSharedPreferences = getContext().getSharedPreferences(TAG, getContext().MODE_PRIVATE);
        get_post_idx = sSharedPreferences.getString("clicked_post_idx", "");
        Log.d("포스트 인덱스", get_post_idx);

//        int post_idx = Integer.parseInt(get_post_idx);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 게시물 신고
                postDetailActivityService.ReportPost(get_post_idx);

                btnCancel.setVisibility(View.INVISIBLE);
                btnReport.setVisibility(View.INVISIBLE);
                btnConfirm.setVisibility(View.VISIBLE);

            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Intent intent = new Intent(getContext(), MainActivity.class);
                getContext().startActivity(intent);
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

    @Override
    public void ReportPostFailure(String message, int code) {
        Log.d("게시물 신고 실패", message +"&&" + String.valueOf(code));

    }

    @Override
    public void ReportPostSuccess(String message, int code) {
        if(code == 2000){
            Log.d("게시물 신고 성공", message +"&&" + String.valueOf(code));
//            Toast.makeText(getContext(), "게시물이 신고되었습니다!" , Toast.LENGTH_SHORT).show();
            post_report_dialog_guide.setText(R.string.post_declartion_dialog_2);
        }
        else if(code == 3102)
        {
            Log.d("이미 신고한 게시물", message +"&&" + String.valueOf(code));
//            Toast.makeText(getContext(), "이미 신고한 게시물입니다." , Toast.LENGTH_SHORT).show();
            post_report_dialog_guide.setText(R.string.already_report);
        }
        else{
            Log.d("게시물 신고 실패", message +"&&" + String.valueOf(code));
        }

    }
}