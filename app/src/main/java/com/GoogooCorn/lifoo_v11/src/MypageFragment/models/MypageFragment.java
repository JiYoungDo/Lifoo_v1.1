package com.GoogooCorn.lifoo_v11.src.MypageFragment.models;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.GoogooCorn.lifoo_v11.R;
import com.GoogooCorn.lifoo_v11.src.MainActivity.MainActivity;
import com.GoogooCorn.lifoo_v11.src.MypageFragment.Decorations.SpaceDecoration;
import com.GoogooCorn.lifoo_v11.src.MypageFragment.interfaces.MypageFragmentActivityView;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.PostDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class MypageFragment extends Fragment implements MypageFragmentActivityView {

    MainActivity mainActivity;
    ViewGroup viewGroup;
    MypageAdapter mypageAdapter;

    RecyclerView recyclerView;
    ArrayList list;
    LinearLayoutManager linearLayoutManager;
    List<Integer> count;
    int i = 0;

    Button btnViewAll;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_mypage,container,false);
        recyclerView = viewGroup.findViewById(R.id.mypagefragment_recyclerview);
        linearLayoutManager = new LinearLayoutManager(mainActivity,LinearLayoutManager.HORIZONTAL,true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        SpaceDecoration spaceItemDecoration = new SpaceDecoration();
        recyclerView.addItemDecoration(spaceItemDecoration);

        btnViewAll = viewGroup.findViewById(R.id.btnViewAll);
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewAllPost.class);
//                intent.putExtra("list", list);
                startActivity(intent);
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

        mypageAdapter = new MypageAdapter(list);
        recyclerView.setAdapter(mypageAdapter);
        mypageAdapter.setOnItemClickListener(new MypageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Intent intent = new Intent(getActivity(), PostDetailActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        recyclerView.scrollToPosition(mypageAdapter.getItemCount() - 1);
        mypageAdapter.notifyDataSetChanged();
        return viewGroup;
    }

    @Override
    public void GetProfileFailure(String message, int code) {
        Log.d("회원 정보 받아오기 실패 ", message+ "&& " + String.valueOf(code));
    }

    @Override
    public void GetProfileSuccess(MypageFragmentResponse mypageFragmentResponse, int code) {
        Log.d("회원 정보 받아오기 성공 ",  "&& " + String.valueOf(code));
    }
}
