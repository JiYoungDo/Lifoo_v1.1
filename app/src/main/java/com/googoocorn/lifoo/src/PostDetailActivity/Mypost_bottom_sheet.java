package com.googoocorn.lifoo.src.PostDetailActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.googoocorn.lifoo.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class Mypost_bottom_sheet extends BottomSheetDialogFragment {

    private Context context;

    PostDetailActivity postDetailActivity;
    ViewGroup viewGroup;

    TextView btn_post_edit;
    TextView btn_post_delete;
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
        viewGroup = (ViewGroup) inflater.inflate(R.layout.mypost_bottom_sheet_dialog,container,false);

        btn_post_edit = viewGroup.findViewById(R.id.btn_post_edit);
        btn_post_delete = viewGroup.findViewById(R.id.btn_post_delete);
        btn_cancel = viewGroup.findViewById(R.id.btn_cancel);

        btn_post_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                // 게시물 수정 페이지로 이동
                Intent intent = new Intent(getActivity(), EditMyPost.class);
                startActivity(intent);
                getActivity().finish();
                // 게시물 수정사항이 갱신되어야함 !!!
            }
        });
        btn_post_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                // 게시물 삭제 다이얼로그 띄우기
                PostDeleteDialog postDeleteDialog = new PostDeleteDialog(getContext());
                postDeleteDialog.show();
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

}
