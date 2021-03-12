package com.example.lifoo_v11.src.RankingFragment;

import android.content.ClipData;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifoo_v11.R;
import com.example.lifoo_v11.src.MainActivity.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class RankingFragment extends Fragment {

    MainActivity mainActivity;
    ViewGroup viewGroup;
    RankingAdapter rankingAdapter;

    RecyclerView recyclerView;
    ArrayList list;
    LinearLayoutManager linearLayoutManager;
    List<Integer> count;
    int i = 0;

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
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_ranking,container,false);
        recyclerView = viewGroup.findViewById(R.id.rankingfragment_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity,LinearLayoutManager.VERTICAL,false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        list = new ArrayList<RankingItem>();

        Drawable drawable1 = getResources().getDrawable(R.drawable.food_image);
        Drawable drawable2 = getResources().getDrawable(R.drawable.food_image2);

        Drawable drawable3 = getResources().getDrawable(R.drawable.badge_blue_50);
        Drawable drawable4 = getResources().getDrawable(R.drawable.badge_green_50);

        RankingItem rankingItem1 = new RankingItem(drawable1, drawable3, "제목1", "1시간전", "100개");
        RankingItem rankingItem2 = new RankingItem(drawable2, drawable4, "제목2", "2시간전", "200개");

        list.add(rankingItem1);
        list.add(rankingItem2);

        rankingAdapter = new RankingAdapter(list);
        recyclerView.setAdapter(rankingAdapter);
        rankingAdapter.setOnItemClickListener(new RankingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

            }
        });

        rankingAdapter.notifyDataSetChanged();
        return viewGroup;

    }
}
