package com.googoocorn.lifoo.src.MypageFragment;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.googoocorn.lifoo.R;
import com.googoocorn.lifoo.src.MypageFragment.Decorations.SpaceGridDecoration;
import com.googoocorn.lifoo.src.MypageFragment.interfaces.MypageFragmentActivityView;
import com.googoocorn.lifoo.src.MypageFragment.models.ImogeResponse;
import com.googoocorn.lifoo.src.MypageFragment.models.MyPostResponse;
import com.googoocorn.lifoo.src.MypageFragment.models.MypageFragmentResponse;
import com.googoocorn.lifoo.src.MypageFragment.models.NicknameResponse;

import java.util.ArrayList;
import java.util.List;

public class ViewAllPost extends AppCompatActivity implements MypageFragmentActivityView {

    Button btnBack;
    MypageFragment mypageFragment;
    ImageView btnInformBadge;
    ImageView imgInformBadge;
    ImageView btnEdit;
    ImageView btnDelete;

    RecyclerView recyclerView;
    ViewAllPostAdapter viewAllPostAdapter;
    GridLayoutManager gridlayoutManager;
    ArrayList<MypageItem> view_all_list;

    int page_num;

    // 서비스 선언
    MypageService mypageService = new MypageService(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_mypost);

        page_num = 0;

//        Intent intent = getIntent();
//        list = (ArrayList<MypageItem>) intent.getSerializableExtra("list");

        btnBack = findViewById(R.id.btnBack);
        btnInformBadge = findViewById(R.id.btnInformBadge);
        imgInformBadge = findViewById(R.id.imgInformBadge);
        btnEdit = findViewById(R.id.btn_contect_edit);
        btnDelete = findViewById(R.id.btn_content_delete);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnInformBadge.bringToFront();
        imgInformBadge.bringToFront();
        btnInformBadge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imgInformBadge.getVisibility() == View.VISIBLE){
                    imgInformBadge.setVisibility(View.INVISIBLE);
                }
                else if(imgInformBadge.getVisibility() == View.INVISIBLE){
                    imgInformBadge.setVisibility(View.VISIBLE);
                }
            }
        });


        view_all_list = new ArrayList<MypageItem>();

//        Drawable drawable1 = getResources().getDrawable(R.drawable.food_image);
//        Drawable drawable2 = getResources().getDrawable(R.drawable.food_image2);
//
//        Drawable drawable3 = getResources().getDrawable(R.drawable.badge_blue_40);
//        Drawable drawable4 = getResources().getDrawable(R.drawable.badge_red_40);
//
//        MypageItem mypageItem1 = new MypageItem("http://res.heraldm.com/phpwas/restmb_idxmake.php?idx=507&simg=/content/image/2019/09/27/20190927000594_0.jpg", drawable3, 1);
//        MypageItem mypageItem2 = new MypageItem("http://res.heraldm.com/phpwas/restmb_idxmake.php?idx=507&simg=/content/image/2019/09/27/20190927000594_0.jpg", drawable4, 1);
//        MypageItem mypageItem3 = new MypageItem("http://res.heraldm.com/phpwas/restmb_idxmake.php?idx=507&simg=/content/image/2019/09/27/20190927000594_0.jpg", drawable3, 1);
//        MypageItem mypageItem4 = new MypageItem("http://res.heraldm.com/phpwas/restmb_idxmake.php?idx=507&simg=/content/image/2019/09/27/20190927000594_0.jpg", drawable4, 1);
//        MypageItem mypageItem5 = new MypageItem("http://res.heraldm.com/phpwas/restmb_idxmake.php?idx=507&simg=/content/image/2019/09/27/20190927000594_0.jpg", drawable3, 1);
//        MypageItem mypageItem6 = new MypageItem("http://res.heraldm.com/phpwas/restmb_idxmake.php?idx=507&simg=/content/image/2019/09/27/20190927000594_0.jpg", drawable4, 1);
//        MypageItem mypageItem7 = new MypageItem("http://res.heraldm.com/phpwas/restmb_idxmake.php?idx=507&simg=/content/image/2019/09/27/20190927000594_0.jpg", drawable3, 1);
//        MypageItem mypageItem8 = new MypageItem("http://res.heraldm.com/phpwas/restmb_idxmake.php?idx=507&simg=/content/image/2019/09/27/20190927000594_0.jpg", drawable4, 1);
//
//        view_all_list.add(mypageItem1);
//        view_all_list.add(mypageItem2);
//        view_all_list.add(mypageItem3);
//        view_all_list.add(mypageItem4);
//        view_all_list.add(mypageItem5);
//        view_all_list.add(mypageItem6);
//        view_all_list.add(mypageItem7);
//        view_all_list.add(mypageItem8);

        // 통신으로 내 게시물 받아오기
        TryGetMyPosts(0);

        recyclerView = findViewById(R.id.mypagefragment_viewall_recyclerview);
        viewAllPostAdapter = new ViewAllPostAdapter(view_all_list);

        gridlayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        recyclerView.addItemDecoration(new SpaceGridDecoration(30));

        recyclerView.setLayoutManager(gridlayoutManager);
        recyclerView.setAdapter(viewAllPostAdapter);
        viewAllPostAdapter.notifyDataSetChanged();

        viewAllPostAdapter.setOnItemClickListener(new ViewAllPostAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
//                Intent intent = new Intent(getApplicationContext(), PostDetailActivity.class);
//                startActivity(intent);
            }
        });
        viewAllPostAdapter.setOnItemLongClickListener(new ViewAllPostAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View v, int pos) {
//                ViewAllPostDialog viewAllPostDialog = new ViewAllPostDialog(v.getContext());
//                viewAllPostDialog.show();

            }
        });
    }

    private void TryGetMyPosts(int page_num)
    {
        mypageService.GetMyPost(page_num);
    }


    @Override
    public void GetProfileFailure(String message, int code) {

    }

    @Override
    public void GetProfileSuccess(MypageFragmentResponse mypageFragmentResponse, int code) {

    }

    @Override
    public void EditProfileFailure(String message, int code) {

    }

    @Override
    public void EditProfileSuccess(NicknameResponse nicknameResponse, int code) {

    }

    @Override
    public void GetImogeFailure(String message, int code) {

    }

    @Override
    public void GetImogeSuccess(ImogeResponse imogeResponse, int code) {

    }

    @Override
    public void GetMyPostFailure(String message, int code) {
        Log.d("내 게시물 받아오기 실패", message + "&&" + String.valueOf(code));
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void GetMyPostSuccess(MyPostResponse myPostResponse, int code) {
        Log.d("내 게시물 받아오기 성공", String.valueOf(code));

        if(code == 2000) {
            Log.d("내 게시물 받아오기 성공", String.valueOf(code));
            Log.d("postList 사이즈", String.valueOf(myPostResponse.getResult().getPostList().size()));
            MyPostResponse.Result result = myPostResponse.getResult();

            List<MyPostResponse.Post> postList = result.getPostList();

            MypageItem mypageItem;

            for (int i = 0; i < postList.size(); i++) {
                Drawable badge = null;

                if (postList.get(i).getTotalImoge() >= 50
                        && postList.get(i).getTotalImoge() < 100) {
                    badge = getResources().getDrawable(R.drawable.badge_red_30);
                } else if (postList.get(i).getTotalImoge() >= 100
                        && postList.get(i).getTotalImoge() < 500) {
                    badge = getResources().getDrawable(R.drawable.badge_yellow_30);
                } else if (postList.get(i).getTotalImoge() >= 500
                        && postList.get(i).getTotalImoge() < 1000) {
                    badge = getResources().getDrawable(R.drawable.badge_green_30);
                } else if (postList.get(i).getTotalImoge() >= 1000) {
                    badge = getResources().getDrawable(R.drawable.badge_blue_30);
                }

                mypageItem = new MypageItem(postList.get(i).getPostUrl(), badge, postList.get(i).getPostIdx());
                view_all_list.add(mypageItem);
                viewAllPostAdapter.notifyDataSetChanged();

                if(i==9)
                {
                    page_num += 1;
                    TryGetMyPosts(page_num);
                }
            }

        }
        else if(code == 3208){
            Log.d("게시물 목록 없음", String.valueOf(code));
        }

        else{
            Log.d("내 게시물 리스폰스 오류", String.valueOf(code));
            Toast.makeText(getApplicationContext(),"시스템 오류! sorry x_x",Toast.LENGTH_SHORT).show();
        }

    }
}


