package com.GoogooCorn.lifoo_v11.src.MypageFragment.models;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.GoogooCorn.lifoo_v11.R;
import com.GoogooCorn.lifoo_v11.src.MypageFragment.Decorations.SpaceGridDecoration;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.PostDetailActivity;

import java.util.ArrayList;

public class ViewAllPost extends AppCompatActivity{

    Button btnBack;
    MypageFragment mypageFragment;
    ImageView btnInformBadge;
    ImageView imgInformBadge;
    ImageView btnEdit;
    ImageView btnDelete;

    RecyclerView recyclerView;
    ViewAllPostAdapter viewAllPostAdapter;
    GridLayoutManager gridlayoutManager;
    ArrayList<MypageItem> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_mypost);

//        Intent intent = getIntent();
//        list = (ArrayList<MypageItem>) intent.getSerializableExtra("list");

        btnBack = findViewById(R.id.btnBack);
        btnInformBadge = findViewById(R.id.btnInformBadge);
        imgInformBadge = findViewById(R.id.imgInformBadge);
        btnEdit = findViewById(R.id.btn_contect_edit);
        btnDelete = findViewById(R.id.btn_content_delete);

//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainActivity mainActivity = (MainActivity)getApplicationContext();
//                mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.bottom_navigation_fl,mypageFragment).commit();
//            }
//        });

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


        list = new ArrayList<MypageItem>();

        Drawable drawable1 = getResources().getDrawable(R.drawable.food_image);
        Drawable drawable2 = getResources().getDrawable(R.drawable.food_image2);

        Drawable drawable3 = getResources().getDrawable(R.drawable.badge_blue_40);
        Drawable drawable4 = getResources().getDrawable(R.drawable.badge_red_40);

        MypageItem mypageItem1 = new MypageItem(drawable1, drawable3);
        MypageItem mypageItem2 = new MypageItem(drawable2, drawable4);
        MypageItem mypageItem3 = new MypageItem(drawable1, drawable3);
        MypageItem mypageItem4 = new MypageItem(drawable2, drawable4);

        list.add(mypageItem1);
        list.add(mypageItem2);
        list.add(mypageItem3);
        list.add(mypageItem4);

        recyclerView = findViewById(R.id.mypagefragment_viewall_recyclerview);
        viewAllPostAdapter = new ViewAllPostAdapter(list);

        gridlayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        recyclerView.addItemDecoration(new SpaceGridDecoration(30));

        recyclerView.setLayoutManager(gridlayoutManager);
        recyclerView.setAdapter(viewAllPostAdapter);
        viewAllPostAdapter.setOnItemClickListener(new ViewAllPostAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Intent intent = new Intent(getApplicationContext(), PostDetailActivity.class);
                startActivity(intent);
            }
        });
        viewAllPostAdapter.setOnItemLongClickListener(new ViewAllPostAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View v, int pos) {
                v.findViewById(R.id.btn_contect_edit).setVisibility(View.VISIBLE);
                v.findViewById(R.id.btn_content_delete).setVisibility(View.VISIBLE);
            }
        });

    }

}

