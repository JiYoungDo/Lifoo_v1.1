package com.googoocorn.lifoo.src.PostDetailActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.googoocorn.lifoo.R;
import com.googoocorn.lifoo.src.PostDetailActivity.interfaces.PostDetailActivityView;
import com.googoocorn.lifoo.src.PostDetailActivity.models.GetCommentResponse;
import com.googoocorn.lifoo.src.PostDetailActivity.models.GetPostResponse;
import com.googoocorn.lifoo.src.PostDetailActivity.models.PostDeleteResponse;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class Otherspost_bottom_sheet extends BottomSheetDialogFragment implements PostDetailActivityView {

    private Context context;

    PostDetailActivity postDetailActivity;
    ViewGroup viewGroup;

    TextView btn_report;
    ImageView btn_cancel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        postDetailActivity = (PostDetailActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        postDetailActivity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.otherspost_bottom_sheet_dialog,container,false);

        btn_report = viewGroup.findViewById(R.id.btn_report);
        btn_cancel = viewGroup.findViewById(R.id.btn_cancel);

        btn_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostReportDialog postReportDialog = new PostReportDialog(getContext());
                postReportDialog.show();

            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return viewGroup;
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
        Log.d("게시물 신고 성공", message +"&&" + String.valueOf(code));
        Toast.makeText(getContext(), "게시물이 신고되었습니다!" , Toast.LENGTH_SHORT).show();
    }
}
