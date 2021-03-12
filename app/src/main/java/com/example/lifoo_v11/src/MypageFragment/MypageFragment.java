package com.example.lifoo_v11.src.MypageFragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifoo_v11.R;
import com.example.lifoo_v11.src.MainActivity.MainActivity;
import com.example.lifoo_v11.src.PostDetailActivity.PostDetailActivity;
import com.example.lifoo_v11.src.RankingFragment.RankingAdapter;
import com.example.lifoo_v11.src.RankingFragment.RankingItem;

import java.util.ArrayList;
import java.util.List;

public class MypageFragment extends Fragment {

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

        SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration();
        recyclerView.addItemDecoration(spaceItemDecoration);

        btnViewAll = viewGroup.findViewById(R.id.btnViewAll);
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewAllPost.class);
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

}
