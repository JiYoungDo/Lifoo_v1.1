package com.GoogooCorn.lifoo_v11.src.FeedFragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.GoogooCorn.lifoo_v11.R;
import com.GoogooCorn.lifoo_v11.src.FeedFragment.interfaces.FeedFragmentActivityView;
import com.GoogooCorn.lifoo_v11.src.FeedFragment.models.FeedFragmentResponse;
import com.GoogooCorn.lifoo_v11.src.MainActivity.MainActivity;
import com.GoogooCorn.lifoo_v11.src.PostDetailActivity.PostDetailActivity;
import com.GoogooCorn.lifoo_v11.src.SearchActivity.SearchActivity;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.GoogooCorn.lifoo_v11.ApplicationClass.X_ACCESS_TOKEN;
import static com.GoogooCorn.lifoo_v11.ApplicationClass.sSharedPreferences;

public class FeedFragment  extends Fragment implements FeedFragmentActivityView {

    MainActivity mainActivity;
    ViewGroup viewGroup;

    ImageView title_iv;
    ImageView circle_search_iv;

    FeedAdapter feedAdapter;
    ArrayList feed_list;
    RecyclerView feed_recyclerView;


    FeedService feedService = new FeedService(this);

    int page_num ;
    String current_time;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
       //  String jwtToken = sSharedPreferences.getString(X_ACCESS_TOKEN, null);
       //  Log.d("피드 프레그먼트 jwt", jwtToken);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_feed,container,false);

        title_iv = viewGroup.findViewById(R.id.feed_fragment_iv_title);
        title_iv.bringToFront();

        // bg_scrollView = viewGroup.findViewById(R.id.feed_fragment_sv_animation);

        // Feed list
        feed_recyclerView = viewGroup.findViewById(R.id.feed_fragment_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity,LinearLayoutManager.VERTICAL,false);
        feed_recyclerView.setLayoutManager(linearLayoutManager);



        page_num = 0;

        feed_list = new ArrayList<FeedItem>();


        // 통신을 통해 피드 받아 오는.
        TryGetPosts(0);

        // 어댑터
        feedAdapter = new FeedAdapter(feed_list);
        feed_recyclerView.setAdapter(feedAdapter);

        feedAdapter.notifyDataSetChanged();

        feedAdapter.setOnItemClickListener(new FeedAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                // post 세부화면으로 넘겨 주기.
            }
        });

        circle_search_iv = viewGroup.findViewById(R.id.btnInformBadge);
        circle_search_iv.bringToFront();

        circle_search_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
                mainActivity.finish();
            }
        });



        return viewGroup;
    }

    public static void move_to_post_Detail(Activity context)
    {
        Intent intent = new Intent(context, PostDetailActivity.class);
        context.startActivity(intent);
        context.finish();
    }


    private void TryGetPosts(int page_num)
    {
        feedService.GetBasicPosts(page_num);
    }


    @Override
    public void GetPostsBasicFailure(String message, int code) {
        Log.d("피드 실패 ", message+ "&& " + String.valueOf(code));
    }

    @Override
    public void GetPostsBasicSuccess(FeedFragmentResponse feedFragmentResponse, int code) {

        Log.d("피드 성공 ",  "&& " + String.valueOf(code));

        /**
         *  parameters.put("type","BASIC");
         *  parameters.put("size","6");
         *  parameters.put("page",String.valueOf(page_num));
         * */



        if(code == 2000 && feedFragmentResponse.getResult().getPostList().size() == 6)
        {
            Log.d("피드 성공 ",  "&& " + String.valueOf(code));
            String Img_url_1, Img_url_2, Img_url_3, Img_url_4, Img_url_5, Img_url_6;
            String hour_1, hour_2, hour_3, hour_4, hour_5, hour_6;
            String Imoji_1, Imoji_2, Imoji_3, Imoji_4, Imoji_5, Imoji_6;
            String Imoji_idx_1, Imoji_idx_2, Imoji_idx_3, Imoji_idx_4, Imoji_idx_5, Imoji_idx_6;
            String title_1, title_2, title_3, title_4,title_5, title_6;


            Img_url_1 = feedFragmentResponse.getResult().getPostList().get(0).getPostUrl();
            Img_url_2 = feedFragmentResponse.getResult().getPostList().get(1).getPostUrl();
            Img_url_3 = feedFragmentResponse.getResult().getPostList().get(2).getPostUrl();
            Img_url_4 = feedFragmentResponse.getResult().getPostList().get(3).getPostUrl();
            Img_url_5 = feedFragmentResponse.getResult().getPostList().get(4).getPostUrl();
            Img_url_6 = feedFragmentResponse.getResult().getPostList().get(5).getPostUrl();


            long mNow = System.currentTimeMillis();
            Date mReDate = new Date(mNow);
            SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formatDate = mFormat.format(mReDate);
            current_time = formatDate;

//            hour_1 = feedFragmentResponse.getResult().getPostList().get(0).getCreatedAt().substring(2,10);
//            hour_2 = feedFragmentResponse.getResult().getPostList().get(1).getCreatedAt().substring(2,10);
//            hour_3 = feedFragmentResponse.getResult().getPostList().get(2).getCreatedAt().substring(2,10);
//            hour_4 = feedFragmentResponse.getResult().getPostList().get(3).getCreatedAt().substring(2,10);
//            hour_5 = feedFragmentResponse.getResult().getPostList().get(4).getCreatedAt().substring(2,10);
//            hour_6 = feedFragmentResponse.getResult().getPostList().get(5).getCreatedAt().substring(2,10);

            hour_1 = Calc_time(feedFragmentResponse.getResult().getPostList().get(0).getCreatedAt(),current_time);
            hour_2 = Calc_time(feedFragmentResponse.getResult().getPostList().get(1).getCreatedAt(),current_time);
            hour_3 = Calc_time(feedFragmentResponse.getResult().getPostList().get(2).getCreatedAt(),current_time);
            hour_4 = Calc_time(feedFragmentResponse.getResult().getPostList().get(3).getCreatedAt(),current_time);
            hour_5 = Calc_time(feedFragmentResponse.getResult().getPostList().get(4).getCreatedAt(),current_time);
            hour_6 = Calc_time(feedFragmentResponse.getResult().getPostList().get(5).getCreatedAt(),current_time);



            Imoji_1 = String.valueOf(feedFragmentResponse.getResult().getPostList().get(0).getTotalImoge())+" 개";
            Imoji_2 = String.valueOf(feedFragmentResponse.getResult().getPostList().get(1).getTotalImoge())+" 개";
            Imoji_3 = String.valueOf(feedFragmentResponse.getResult().getPostList().get(2).getTotalImoge())+" 개";
            Imoji_4 = String.valueOf(feedFragmentResponse.getResult().getPostList().get(3).getTotalImoge())+" 개";
            Imoji_5 = String.valueOf(feedFragmentResponse.getResult().getPostList().get(4).getTotalImoge())+" 개";
            Imoji_6 = String.valueOf(feedFragmentResponse.getResult().getPostList().get(5).getTotalImoge())+" 개";


            // Imoji_idx_1 이게 post_Idx
            Imoji_idx_1 = String.valueOf(feedFragmentResponse.getResult().getPostList().get(0).getPostIdx());
            Imoji_idx_2 = String.valueOf(feedFragmentResponse.getResult().getPostList().get(1).getPostIdx());
            Imoji_idx_3 = String.valueOf(feedFragmentResponse.getResult().getPostList().get(2).getPostIdx());
            Imoji_idx_4 = String.valueOf(feedFragmentResponse.getResult().getPostList().get(3).getPostIdx());
            Imoji_idx_5 = String.valueOf(feedFragmentResponse.getResult().getPostList().get(4).getPostIdx());
            Imoji_idx_6 = String.valueOf(feedFragmentResponse.getResult().getPostList().get(5).getPostIdx());

            title_1 = feedFragmentResponse.getResult().getPostList().get(0).getPostTitle();
            title_2 = feedFragmentResponse.getResult().getPostList().get(1).getPostTitle();
            title_3 = feedFragmentResponse.getResult().getPostList().get(2).getPostTitle();
            title_4 = feedFragmentResponse.getResult().getPostList().get(3).getPostTitle();
            title_5 = feedFragmentResponse.getResult().getPostList().get(4).getPostTitle();
            title_6 = feedFragmentResponse.getResult().getPostList().get(5).getPostTitle();



            FeedItem feedItem = new FeedItem(Img_url_1,Img_url_2,Img_url_3,Img_url_4,Img_url_5,Img_url_6,
                    hour_1,hour_2,hour_3,hour_4,hour_5,hour_6,
                    Imoji_1,Imoji_2,Imoji_3,Imoji_4,Imoji_5,Imoji_6,
                    Imoji_idx_1,Imoji_idx_2,Imoji_idx_3,Imoji_idx_4,Imoji_idx_5,Imoji_idx_6,
                    title_1, title_2, title_3, title_4, title_5, title_6);

            feed_list.add(feedItem);

            page_num +=1;

            if(page_num <=10)
                TryGetPosts(page_num);
            feedAdapter.notifyDataSetChanged();

        }
        else{
            Log.d("피드 리스폰스 오류 : ", String.valueOf(code));
            // Toast.makeText(getContext(),"시스템 오류! sorry x_x",Toast.LENGTH_SHORT).show();
        }


    }

    @NotNull
    private String Calc_time(String input_time, String formatDate)
    {

        int result;
        String str_result;

        int i_year, i_month, i_date, i_hour, i_min, i_sec;
        int n_year, n_month, n_date, n_hour, n_min, n_sec;


        // 2021-03-10T10:06:10.000+00:00
        // 2021 03 10 10 06 10.000+00:00
        String Input_0 = input_time.replaceAll("-","");
        String Input_1 = Input_0.replaceAll("T","");
        String Input = Input_1.replaceAll(":","");
        i_year = Integer.parseInt(Input.substring(0,4));
        i_month = Integer.parseInt(Input.substring(4,6));
        i_date = Integer.parseInt(Input.substring(6,8));
        i_hour = Integer.parseInt(Input.substring(8,10));
        i_min = Integer.parseInt(Input.substring(10,12));
        i_sec = Integer.parseInt(Input.substring(12,14));




        String now_1 = formatDate.replaceAll("-","");
        String now_2 = now_1.replaceAll(" ","");
        String NOW = now_2.replaceAll(":","");
        n_year = Integer.parseInt(NOW.substring(0,4));
        n_month = Integer.parseInt(NOW.substring(4,6));
        n_date = Integer.parseInt(NOW.substring(6,8));
        n_hour = Integer.parseInt(NOW.substring(8,10));
        n_min = Integer.parseInt(NOW.substring(10,12));
        n_sec = Integer.parseInt(NOW.substring(12,13));


        if(i_year != n_year) {
            str_result = String.valueOf(n_year - i_year) + "년 전";
        }else if(i_month != n_month){
            str_result = String.valueOf(n_month - i_month) +"개월 전";
        }else if(i_date != n_date){
            str_result = String.valueOf(n_date - i_date)+"일 전";
        }else if(i_hour != n_hour) {
            str_result = String.valueOf(n_hour - i_hour) + "시간 전";
        }else if(i_min != n_min){
            str_result = String.valueOf(n_min - i_min) +"분 전";
        }else{
            str_result = String.valueOf(n_sec-i_sec) +"초 전";
        }


        return str_result;
    }
}
