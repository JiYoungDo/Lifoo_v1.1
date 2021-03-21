package com.GoogooCorn.lifoo_v11.src;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.GoogooCorn.lifoo_v11.R;

public class PostDeleteDialog extends Dialog {
    private Context context;

    private TextView btnCancel;
    private TextView btnDelete;
    private TextView post_delete_dialog_guide;
    private TextView btnConfirm;

    public PostDeleteDialog(@NonNull Context context) {
        super(context);
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

            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                // 리스트에서 삭제된 게시물 사라져야됨
            }
        });

    }
}
