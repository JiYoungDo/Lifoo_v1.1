package com.GoogooCorn.lifoo_v11.src.RankingFragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.GoogooCorn.lifoo_v11.R;
import com.GoogooCorn.lifoo_v11.src.MainActivity.MainActivity;
import com.GoogooCorn.lifoo_v11.src.MypageFragment.MypageItem;
import com.GoogooCorn.lifoo_v11.src.MypageFragment.models.MyPostResponse;
import com.GoogooCorn.lifoo_v11.src.RankingFragment.interfaces.RankingActivityView;
import com.GoogooCorn.lifoo_v11.src.RankingFragment.models.RankingResponse;

import java.util.ArrayList;
import java.util.List;

public class RankingFragment extends Fragment implements RankingActivityView {

    MainActivity mainActivity;
    ViewGroup viewGroup;
    RankingAdapter rankingAdapter;

    RecyclerView recyclerView;
    ArrayList ranking_list;
    LinearLayoutManager linearLayoutManager;
    List<Integer> count;
    int i = 0;
    int page_num;

    SwipeRefreshLayout mSwipeRefreshLayout;

    // 서비스 선언
    RankingService rankingService = new RankingService(this);

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

        ranking_list = new ArrayList<RankingItem>();

        // refresh_ll
        mSwipeRefreshLayout = viewGroup.findViewById(R.id.refreshll_ranking);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                /* swipe 시 진행할 동작 */
                ranking_list.clear();
                TryGetRankings(0);

                /* 업데이트가 끝났음을 알림 */
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });


//        Drawable drawable1 = getResources().getDrawable(R.drawable.food_image);
//        Drawable drawable2 = getResources().getDrawable(R.drawable.food_image2);
//
//        Drawable drawable3 = getResources().getDrawable(R.drawable.badge_blue_50);
//        Drawable drawable4 = getResources().getDrawable(R.drawable.badge_green_50);
//
//        RankingItem rankingItem1 = new RankingItem("http://res.heraldm.com/phpwas/restmb_idxmake.php?idx=507&simg=/content/image/2019/09/27/20190927000594_0.jpg", drawable3, "제목1", "1시간전", 100);
//        RankingItem rankingItem2 = new RankingItem("http://res.heraldm.com/phpwas/restmb_idxmake.php?idx=507&simg=/content/image/2019/09/27/20190927000594_0.jpg", drawable4, "제목2", "2시간전", 200);
//
//        ranking_list.add(rankingItem1);
//        ranking_list.add(rankingItem2);

        // 통신으로 랭킹 게시물 받아오기
        TryGetRankings(0);

        rankingAdapter = new RankingAdapter(ranking_list);
        recyclerView.setAdapter(rankingAdapter);
        rankingAdapter.setOnItemClickListener(new RankingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

            }
        });

        rankingAdapter.notifyDataSetChanged();
        return viewGroup;
    }

    private void TryGetRankings(int page_num)
    {
        rankingService.GetRanking(page_num);
    }

    @Override
    public void GetRankingFailure(String message, int code) {
        Log.d("랭킹 게시물 받아오기 실패", message + "&&" + String.valueOf(code));
    }

    @Override
    public void GetRankingSuccess(RankingResponse rankingResponse, int code) {
        Log.d("랭킹 게시물 받아오기 성공", String.valueOf(code));

        if(code == 2000){
            Log.d("랭킹 게시물 받아오기 성공", String.valueOf(code));
            Log.d("postList 사이즈", String.valueOf(rankingResponse.getResult().getPostList().size()));
            RankingResponse.Result result = rankingResponse.getResult();
            List<RankingResponse.Post> postList = result.getPostList();
            RankingItem rankingItem;

            for(int i = 0; i < postList.size(); i++) {
                Drawable badge = null;

                if(postList.get(i).getTotalImoge() >= 20
                        && postList.get(i).getTotalImoge() < 100){
                    badge = getResources().getDrawable(R.drawable.badge_red_50);
                }
                else if(postList.get(i).getTotalImoge() >= 100
                        && postList.get(i).getTotalImoge() < 500){
                    badge = getResources().getDrawable(R.drawable.badge_yellow_50);
                }
                else if(postList.get(i).getTotalImoge() >= 500
                        && postList.get(i).getTotalImoge() < 1000){
                    badge = getResources().getDrawable(R.drawable.badge_green_50);
                }
                else if(postList.get(i).getTotalImoge() >= 1000){
                    badge = getResources().getDrawable(R.drawable.badge_blue_50);
                }
                rankingItem = new RankingItem(postList.get(i).getPostUrl(), badge, postList.get(i).getPostTitle(),
                        postList.get(i).getCreatedAt().substring(2, 10), postList.get(i).getTotalImoge(),
                        postList.get(i).getPostIdx());
                ranking_list.add(rankingItem);
//                page_num += 1;
//
//                if(page_num <= 10)
//                    TryGetRankings(page_num);
                rankingAdapter.notifyDataSetChanged();

                if(i == 9){
                    page_num += 1;
                    TryGetRankings(page_num);
                }
            }
            page_num = 0;
        }

        else if(code == 3208){
            Log.d("랭킹 게시물 목록 없음", String.valueOf(code));
        }

        else{
            Log.d("랭킹 리스폰스 오류", String.valueOf(code));
            Toast.makeText(getContext(),"시스템 오류! sorry x_x",Toast.LENGTH_SHORT).show();
        }

    }
}
