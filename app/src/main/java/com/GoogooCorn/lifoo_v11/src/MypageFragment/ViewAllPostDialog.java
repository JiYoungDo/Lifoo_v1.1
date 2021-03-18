package com.GoogooCorn.lifoo_v11.src.MypageFragment;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.GoogooCorn.lifoo_v11.R;

public class ViewAllPostDialog extends AppCompatActivity {
    private Context context;

    public ViewAllPostDialog(Context context) {
        this.context = context;
    }

    // 호출할 다이얼로그 함수를 정의
    public void callFunction() {

        final Dialog dlg = new Dialog(context);

        // 액티비티의 타이틀바를 숨긴다.
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlg.setContentView(R.layout.mypost_view_all_dialog);
        dlg.show();

        // 커스텀 다이얼로그의 각 위젯들을 정의한다.
        final ImageView btnEdit = dlg.findViewById(R.id.btn_contect_edit);
        final ImageView btnDelete = dlg.findViewById(R.id.btn_content_delete);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 게시물 수정 페이지로 이동

                // 커스텀 다이얼로그를 종료
                dlg.dismiss();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 삭제 다이얼로그

                // 커스텀 다이얼로그를 종료
                dlg.dismiss();
            }
        });
    }
}
