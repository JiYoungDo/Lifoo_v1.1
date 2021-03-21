package com.GoogooCorn.lifoo_v11.src.MypageFragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.GoogooCorn.lifoo_v11.R;
import com.GoogooCorn.lifoo_v11.src.PostDeleteDialog;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.PostDetailActivity;

import java.util.Objects;

public class ViewAllPostDialog extends Dialog {
    private Context context;

    private ImageView btnEdit;
    private ImageView btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypost_view_all_dialog);

        // 배경 투명
        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // 위젯 정의
        btnEdit = findViewById(R.id.btn_contect_edit);
        btnDelete = findViewById(R.id.btn_content_delete);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 커스텀 다이얼로그를 종료
                dismiss();

                // 게시물 수정 페이지로 이동
                Intent intent = new Intent(context, EditMyPost.class);
                context.startActivity(intent);
//                ((Activity)context).finish();

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 커스텀 다이얼로그를 종료
                dismiss();

                // 삭제 다이얼로그
                PostDeleteDialog postDeleteDialog = new PostDeleteDialog(context);
                postDeleteDialog.show();
            }
        });
    }

    public ViewAllPostDialog(Context context) {
        super(context);
        this.context = context;
    }

}

//    // 호출할 다이얼로그 함수를 정의
//    public void callFunction() {
//
//        final Dialog dlg = new Dialog(context);
//
//        // 액티비티의 타이틀바를 숨긴다.
//        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dlg.setContentView(R.layout.mypost_view_all_dialog);
//        dlg.show();
//
//        // 커스텀 다이얼로그의 각 위젯들을 정의한다.
//        btnEdit = dlg.findViewById(R.id.btn_contect_edit);
//        btnDelete = dlg.findViewById(R.id.btn_content_delete);
//
//        btnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // 게시물 수정 페이지로 이동
//                Intent intent = new Intent(ViewAllPostDialog.this, EditMyPost.class);
//                startActivity(intent);
//
//                // 커스텀 다이얼로그를 종료
//                dlg.dismiss();
//            }
//        });
//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // 삭제 다이얼로그
//
//                // 커스텀 다이얼로그를 종료
//                dlg.dismiss();
//            }
//        });
//    }
//}
