package com.googoocorn.lifoo.src.SearchActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.googoocorn.lifoo.R;
import com.googoocorn.lifoo.src.BaseActivity;
import com.googoocorn.lifoo.src.FeedFragment.FeedItem;
import com.googoocorn.lifoo.src.MainActivity.MainActivity;
import com.googoocorn.lifoo.src.SearchActivity.interfaces.SearchView;
import com.googoocorn.lifoo.src.SearchActivity.models.SearchResponse;

import java.util.ArrayList;

import static com.googoocorn.lifoo.ApplicationClass.TAG;
import static com.googoocorn.lifoo.ApplicationClass.sSharedPreferences;

public class SearchActivity extends BaseActivity implements SearchView {

    ImageView back_btn;
    EditText search_et;
    ImageButton search_btn;
    TextView recent_search_1,recent_search_2,recent_search_3,recent_search_4,recent_search_5,recent_search_6,
            recent_search_7,recent_search_8,recent_search_9,recent_search_10;

    String temp_1, temp_2,  temp_3, temp_4, temp_5, temp_6, temp_7, temp_8, temp_9, temp_10;

    ArrayList result_feed_list;
    RecyclerView result_feed_rv;
    LinearLayout recenet_string_ll;
    FeedResultAdapter result_feedAdapter;

    SearchService searchService = new SearchService(this);

    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // 아래 두가지 뷰는 왔다 갔다 gone, visible
        result_feed_rv = findViewById(R.id.search_rv_result_feed);
        recenet_string_ll = findViewById(R.id.search_ll_recent_search);

        recenet_string_ll.setVisibility(View.VISIBLE);
        result_feed_rv.setVisibility(View.GONE);


        back_btn = findViewById(R.id.search_iv_back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent  = new Intent(SearchActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        search_et = findViewById(R.id.search_et_search);
        search_btn = findViewById(R.id.search_iv_search);

        recent_search_1 = findViewById(R.id.search_recent_1);
        recent_search_2 = findViewById(R.id.search_recent_2);
        recent_search_3 = findViewById(R.id.search_recent_3);
        recent_search_4 = findViewById(R.id.search_recent_4);
        recent_search_5 = findViewById(R.id.search_recent_5);
        recent_search_6 = findViewById(R.id.search_recent_6);
        recent_search_7 = findViewById(R.id.search_recent_7);
        recent_search_8 = findViewById(R.id.search_recent_8);
        recent_search_9 = findViewById(R.id.search_recent_9);
        recent_search_10 = findViewById(R.id.search_recent_10);


        sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);

        // 저장해둔 갯수.
        count = sSharedPreferences.getInt("recent_search_count",0);

        // sharedPreference 에 저장 되어있는 값들 화면에 세팅
        switch (count)
        {
            case 0:
                recent_search_1.setText("");
                recent_search_2.setText("");
                recent_search_3.setText("");
                recent_search_4.setText("");
                recent_search_5.setText("");
                recent_search_6.setText("");
                recent_search_7.setText("");
                recent_search_8.setText("");
                recent_search_9.setText("");
                recent_search_10.setText("");
                break;
            case 1:
                recent_search_1.setText(sSharedPreferences.getString("recent_search_1","empty"));
                recent_search_2.setText("");
                recent_search_3.setText("");
                recent_search_4.setText("");
                recent_search_5.setText("");
                recent_search_6.setText("");
                recent_search_7.setText("");
                recent_search_8.setText("");
                recent_search_9.setText("");
                recent_search_10.setText("");
                temp_1 = sSharedPreferences.getString("recent_search_1","empty");
                break;
            case 2:
                recent_search_1.setText(sSharedPreferences.getString("recent_search_1","empty"));
                recent_search_2.setText(sSharedPreferences.getString("recent_search_2","empty"));
                recent_search_3.setText("");
                recent_search_4.setText("");
                recent_search_5.setText("");
                recent_search_6.setText("");
                recent_search_7.setText("");
                recent_search_8.setText("");
                recent_search_9.setText("");
                recent_search_10.setText("");
                temp_1 = sSharedPreferences.getString("recent_search_1","empty");
                temp_2 = sSharedPreferences.getString("recent_search_2","empty");
                break;
            case 3:
                recent_search_1.setText(sSharedPreferences.getString("recent_search_1","empty"));
                recent_search_2.setText(sSharedPreferences.getString("recent_search_2","empty"));
                recent_search_3.setText(sSharedPreferences.getString("recent_search_3","empty"));
                recent_search_4.setText("");
                recent_search_5.setText("");
                recent_search_6.setText("");
                recent_search_7.setText("");
                recent_search_8.setText("");
                recent_search_9.setText("");
                recent_search_10.setText("");
                temp_1 = sSharedPreferences.getString("recent_search_1","empty");
                temp_2 = sSharedPreferences.getString("recent_search_2","empty");
                temp_3 = sSharedPreferences.getString("recent_search_3","empty");
                break;
            case 4:
                recent_search_1.setText(sSharedPreferences.getString("recent_search_1","empty"));
                recent_search_2.setText(sSharedPreferences.getString("recent_search_2","empty"));
                recent_search_3.setText(sSharedPreferences.getString("recent_search_3","empty"));
                recent_search_4.setText(sSharedPreferences.getString("recent_search_4","empty"));
                recent_search_5.setText("");
                recent_search_6.setText("");
                recent_search_7.setText("");
                recent_search_8.setText("");
                recent_search_9.setText("");
                recent_search_10.setText("");
                temp_1 = sSharedPreferences.getString("recent_search_1","empty");
                temp_2 = sSharedPreferences.getString("recent_search_2","empty");
                temp_3 = sSharedPreferences.getString("recent_search_3","empty");
                temp_4 = sSharedPreferences.getString("recent_search_4","empty");
                break;
            case 5:
                recent_search_1.setText(sSharedPreferences.getString("recent_search_1","empty"));
                recent_search_2.setText(sSharedPreferences.getString("recent_search_2","empty"));
                recent_search_3.setText(sSharedPreferences.getString("recent_search_3","empty"));
                recent_search_4.setText(sSharedPreferences.getString("recent_search_4","empty"));
                recent_search_5.setText(sSharedPreferences.getString("recent_search_5","empty"));
                recent_search_6.setText("");
                recent_search_7.setText("");
                recent_search_8.setText("");
                recent_search_9.setText("");
                recent_search_10.setText("");
                temp_1 = sSharedPreferences.getString("recent_search_1","empty");
                temp_2 = sSharedPreferences.getString("recent_search_2","empty");
                temp_3 = sSharedPreferences.getString("recent_search_3","empty");
                temp_4 = sSharedPreferences.getString("recent_search_4","empty");
                temp_5 = sSharedPreferences.getString("recent_search_5","empty");
                break;
            case 6:
                recent_search_1.setText(sSharedPreferences.getString("recent_search_1","empty"));
                recent_search_2.setText(sSharedPreferences.getString("recent_search_2","empty"));
                recent_search_3.setText(sSharedPreferences.getString("recent_search_3","empty"));
                recent_search_4.setText(sSharedPreferences.getString("recent_search_4","empty"));
                recent_search_5.setText(sSharedPreferences.getString("recent_search_5","empty"));
                recent_search_6.setText(sSharedPreferences.getString("recent_search_6","empty"));
                recent_search_7.setText("");
                recent_search_8.setText("");
                recent_search_9.setText("");
                recent_search_10.setText("");
                temp_1 = sSharedPreferences.getString("recent_search_1","empty");
                temp_2 = sSharedPreferences.getString("recent_search_2","empty");
                temp_3 = sSharedPreferences.getString("recent_search_3","empty");
                temp_4 = sSharedPreferences.getString("recent_search_4","empty");
                temp_5 = sSharedPreferences.getString("recent_search_5","empty");
                temp_6 = sSharedPreferences.getString("recent_search_6","empty");
                break;
            case 7:
                recent_search_1.setText(sSharedPreferences.getString("recent_search_1","empty"));
                recent_search_2.setText(sSharedPreferences.getString("recent_search_2","empty"));
                recent_search_3.setText(sSharedPreferences.getString("recent_search_3","empty"));
                recent_search_4.setText(sSharedPreferences.getString("recent_search_4","empty"));
                recent_search_5.setText(sSharedPreferences.getString("recent_search_5","empty"));
                recent_search_6.setText(sSharedPreferences.getString("recent_search_6","empty"));
                recent_search_7.setText(sSharedPreferences.getString("recent_search_7","empty"));
                recent_search_8.setText("");
                recent_search_9.setText("");
                recent_search_10.setText("");
                temp_1 = sSharedPreferences.getString("recent_search_1","empty");
                temp_2 = sSharedPreferences.getString("recent_search_2","empty");
                temp_3 = sSharedPreferences.getString("recent_search_3","empty");
                temp_4 = sSharedPreferences.getString("recent_search_4","empty");
                temp_5 = sSharedPreferences.getString("recent_search_5","empty");
                temp_6 = sSharedPreferences.getString("recent_search_6","empty");
                temp_7 = sSharedPreferences.getString("recent_search_7","empty");
                break;
            case 8:
                recent_search_1.setText(sSharedPreferences.getString("recent_search_1","empty"));
                recent_search_2.setText(sSharedPreferences.getString("recent_search_2","empty"));
                recent_search_3.setText(sSharedPreferences.getString("recent_search_3","empty"));
                recent_search_4.setText(sSharedPreferences.getString("recent_search_4","empty"));
                recent_search_5.setText(sSharedPreferences.getString("recent_search_5","empty"));
                recent_search_6.setText(sSharedPreferences.getString("recent_search_6","empty"));
                recent_search_7.setText(sSharedPreferences.getString("recent_search_7","empty"));
                recent_search_8.setText(sSharedPreferences.getString("recent_search_8","empty"));
                recent_search_9.setText("");
                recent_search_10.setText("");
                temp_1 = sSharedPreferences.getString("recent_search_1","empty");
                temp_2 = sSharedPreferences.getString("recent_search_2","empty");
                temp_3 = sSharedPreferences.getString("recent_search_3","empty");
                temp_4 = sSharedPreferences.getString("recent_search_4","empty");
                temp_5 = sSharedPreferences.getString("recent_search_5","empty");
                temp_6 = sSharedPreferences.getString("recent_search_6","empty");
                temp_7 = sSharedPreferences.getString("recent_search_7","empty");
                temp_8 = sSharedPreferences.getString("recent_search_8","empty");

                break;
            case 9:
                recent_search_1.setText(sSharedPreferences.getString("recent_search_1","empty"));
                recent_search_2.setText(sSharedPreferences.getString("recent_search_2","empty"));
                recent_search_3.setText(sSharedPreferences.getString("recent_search_3","empty"));
                recent_search_4.setText(sSharedPreferences.getString("recent_search_4","empty"));
                recent_search_5.setText(sSharedPreferences.getString("recent_search_5","empty"));
                recent_search_6.setText(sSharedPreferences.getString("recent_search_6","empty"));
                recent_search_7.setText(sSharedPreferences.getString("recent_search_7","empty"));
                recent_search_8.setText(sSharedPreferences.getString("recent_search_8","empty"));
                recent_search_9.setText(sSharedPreferences.getString("recent_search_9","empty"));
                recent_search_10.setText("");
                temp_1 = sSharedPreferences.getString("recent_search_1","empty");
                temp_2 = sSharedPreferences.getString("recent_search_2","empty");
                temp_3 = sSharedPreferences.getString("recent_search_3","empty");
                temp_4 = sSharedPreferences.getString("recent_search_4","empty");
                temp_5 = sSharedPreferences.getString("recent_search_5","empty");
                temp_6 = sSharedPreferences.getString("recent_search_6","empty");
                temp_7 = sSharedPreferences.getString("recent_search_7","empty");
                temp_8 = sSharedPreferences.getString("recent_search_8","empty");
                temp_9 = sSharedPreferences.getString("recent_search_9","empty");

                break;
            case 10:
                recent_search_1.setText(sSharedPreferences.getString("recent_search_1","empty"));
                recent_search_2.setText(sSharedPreferences.getString("recent_search_2","empty"));
                recent_search_3.setText(sSharedPreferences.getString("recent_search_3","empty"));
                recent_search_4.setText(sSharedPreferences.getString("recent_search_4","empty"));
                recent_search_5.setText(sSharedPreferences.getString("recent_search_5","empty"));
                recent_search_6.setText(sSharedPreferences.getString("recent_search_6","empty"));
                recent_search_7.setText(sSharedPreferences.getString("recent_search_7","empty"));
                recent_search_8.setText(sSharedPreferences.getString("recent_search_8","empty"));
                recent_search_9.setText(sSharedPreferences.getString("recent_search_9","empty"));
                recent_search_10.setText(sSharedPreferences.getString("recent_search_10","empty"));
                temp_1 = sSharedPreferences.getString("recent_search_1","empty");
                temp_2 = sSharedPreferences.getString("recent_search_2","empty");
                temp_3 = sSharedPreferences.getString("recent_search_3","empty");
                temp_4 = sSharedPreferences.getString("recent_search_4","empty");
                temp_5 = sSharedPreferences.getString("recent_search_5","empty");
                temp_6 = sSharedPreferences.getString("recent_search_6","empty");
                temp_7 = sSharedPreferences.getString("recent_search_7","empty");
                temp_8 = sSharedPreferences.getString("recent_search_8","empty");
                temp_9 = sSharedPreferences.getString("recent_search_9","empty");
                temp_10 = sSharedPreferences.getString("recent_search_10","empty");

                break;
        }


        //  검색 버튼 :   & 통신
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TryGetSearch(search_et.getText().toString());
            }
        });


    }

    private void TryGetSearch(String user_keyword)
    {
        searchService.GetSearchPosts(user_keyword);
    }


    @Override
    public void GetPostsSearchFailure(String message, int code) {
        Log.d("검색 실패", message + " && "+String.valueOf(code));
    }


    // 내부에 저장해둔 갯수, 검색어 저장.
    @Override
    public void GetPostsSearchSuccess(SearchResponse searchResponse, int code) {

        Log.d("검색 성공",  " && "+ String.valueOf(code));

        // 내부 갯수, 검색어 저장.
        String now = search_et.getText().toString();
        search_et.setText("");

        sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
        SharedPreferences.Editor editor = sSharedPreferences.edit();

        // 저장해둔 갯수.
        count = sSharedPreferences.getInt("recent_search_count",0);
        int next_count;
        if (count+1 >10) { next_count = 10; }else{next_count = count+1;}
        editor.remove("recent_search_count");
        editor.remove("recent_search_1");
        editor.remove("recent_search_2");
        editor.remove("recent_search_3");
        editor.remove("recent_search_4");
        editor.remove("recent_search_5");
        editor.remove("recent_search_6");
        editor.remove("recent_search_7");
        editor.remove("recent_search_8");
        editor.remove("recent_search_9");
        editor.remove("recent_search_10");
        editor.putInt("recent_search_count",next_count);
        editor.commit();


        // sharedPreference 에 저장 되어있는 값들 다시 세팅
        SharedPreferences.Editor editor2 = sSharedPreferences.edit();
        switch (count)
        {
            case 0:

                editor2.putString("recent_search_1",now);
                editor2.commit();
                break;
            case 1:
                editor2.putString("recent_search_1",now);
                editor2.putString("recent_search_2",temp_1);
                editor2.commit();
                break;
            case 2:
                editor2.putString("recent_search_1",now);
                editor2.putString("recent_search_2",temp_1);
                editor2.putString("recent_search_3",temp_2);
                editor2.commit();
                break;
            case 3:
                editor2.putString("recent_search_1",now);
                editor2.putString("recent_search_2",temp_1);
                editor2.putString("recent_search_3",temp_2);
                editor2.putString("recent_search_4",temp_3);
                editor2.commit();
                break;
            case 4:
                editor2.putString("recent_search_1",now);
                editor2.putString("recent_search_2",temp_1);
                editor2.putString("recent_search_3",temp_2);
                editor2.putString("recent_search_4",temp_3);
                editor2.putString("recent_search_5",temp_4);
                editor2.commit();
                break;
            case 5:
                editor2.putString("recent_search_1",now);
                editor2.putString("recent_search_2",temp_1);
                editor2.putString("recent_search_3",temp_2);
                editor2.putString("recent_search_4",temp_3);
                editor2.putString("recent_search_5",temp_4);
                editor2.putString("recent_search_6",temp_5);
                editor2.commit();
                break;
            case 6:
                editor2.putString("recent_search_1",now);
                editor2.putString("recent_search_2",temp_1);
                editor2.putString("recent_search_3",temp_2);
                editor2.putString("recent_search_4",temp_3);
                editor2.putString("recent_search_5",temp_4);
                editor2.putString("recent_search_6",temp_5);
                editor2.putString("recent_search_7",temp_6);
                editor2.commit();
                break;
            case 7:
                editor2.putString("recent_search_1",now);
                editor2.putString("recent_search_2",temp_1);
                editor2.putString("recent_search_3",temp_2);
                editor2.putString("recent_search_4",temp_3);
                editor2.putString("recent_search_5",temp_4);
                editor2.putString("recent_search_6",temp_5);
                editor2.putString("recent_search_7",temp_6);
                editor2.putString("recent_search_8",temp_7);
                editor2.commit();
                break;
            case 8:
                editor2.putString("recent_search_1",now);
                editor2.putString("recent_search_2",temp_1);
                editor2.putString("recent_search_3",temp_2);
                editor2.putString("recent_search_4",temp_3);
                editor2.putString("recent_search_5",temp_4);
                editor2.putString("recent_search_6",temp_5);
                editor2.putString("recent_search_7",temp_6);
                editor2.putString("recent_search_8",temp_7);
                editor2.putString("recent_search_9",temp_8);
                editor2.commit();
                break;
            case 9:
            case 10:
                editor2.putString("recent_search_1",now);
                editor2.putString("recent_search_2",temp_1);
                editor2.putString("recent_search_3",temp_2);
                editor2.putString("recent_search_4",temp_3);
                editor2.putString("recent_search_5",temp_4);
                editor2.putString("recent_search_6",temp_5);
                editor2.putString("recent_search_7",temp_6);
                editor2.putString("recent_search_8",temp_7);
                editor2.putString("recent_search_9",temp_8);
                editor2.putString("recent_search_10",temp_9);
                editor2.commit();
                break;
        }


        /**
         * 통신 코드
         * 2000 : 요청 성공         3000 : jwt 입력           3200 : 유효 안한 jwt
         * 3201 : 존재 x 회원           3001 : type 입력          3204 : type형식 아님
         * 3003 : size 입력          3004 : page 입력        3013:  검색어 입력
         * 3208 : 게시물 없음
         * */
        switch(code)
        {
            case 2000:
                recenet_string_ll.setVisibility(View.GONE);
                result_feed_rv.setVisibility(View.VISIBLE);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
                result_feed_rv.setLayoutManager(linearLayoutManager);


                result_feed_list = new ArrayList<FeedItem>();

                // searchResponse 검색 결과 값
                int search_result_list_size = searchResponse.getResult().getPostList().size();

                if(search_result_list_size < 6)
                {
                    switch (search_result_list_size)
                    {
                        case 1:
                            FeedItem feedItem1 = new FeedItem(searchResponse.getResult().getPostList().get(0).getPostUrl(), "", "", "", "","",
                                    searchResponse.getResult().getPostList().get(0).getCreatedAt().substring(2,10),"","","","","",
                                    String.valueOf(searchResponse.getResult().getPostList().get(0).getTotalImoge()), "", "", "", "", "",
                                    String.valueOf(searchResponse.getResult().getPostList().get(0).getPostIdx()),"","", "", "", "",
                                    searchResponse.getResult().getPostList().get(0).getPostTitle(),"","","","","");
                            result_feed_list.add(feedItem1);
                            break;
                        case 2:
                            FeedItem feedItem2 = new FeedItem(searchResponse.getResult().getPostList().get(0).getPostUrl(), searchResponse.getResult().getPostList().get(1).getPostUrl(), "", "", "","",
                                    searchResponse.getResult().getPostList().get(0).getCreatedAt().substring(2,10),searchResponse.getResult().getPostList().get(1).getCreatedAt().substring(2,10),"","","","",
                                    String.valueOf(searchResponse.getResult().getPostList().get(0).getTotalImoge()), String.valueOf(searchResponse.getResult().getPostList().get(1).getTotalImoge()), "", "", "", "",
                                    String.valueOf(searchResponse.getResult().getPostList().get(0).getPostIdx()), String.valueOf(searchResponse.getResult().getPostList().get(1).getPostIdx()),"", "", "", "",
                                    searchResponse.getResult().getPostList().get(0).getPostTitle(),searchResponse.getResult().getPostList().get(1).getPostTitle(),"","","","");

                            result_feed_list.add(feedItem2);
                            break;
                        case 3:
                            FeedItem feedItem3 = new FeedItem(searchResponse.getResult().getPostList().get(0).getPostUrl(), searchResponse.getResult().getPostList().get(1).getPostUrl(), searchResponse.getResult().getPostList().get(2).getPostUrl(), "", "","",
                                    searchResponse.getResult().getPostList().get(0).getCreatedAt().substring(2,10),searchResponse.getResult().getPostList().get(1).getCreatedAt().substring(2,10),searchResponse.getResult().getPostList().get(2).getCreatedAt().substring(2,10),"","","",
                                    String.valueOf(searchResponse.getResult().getPostList().get(0).getTotalImoge()), String.valueOf(searchResponse.getResult().getPostList().get(1).getTotalImoge()), String.valueOf(searchResponse.getResult().getPostList().get(2).getTotalImoge()), "", "", "",
                                    String.valueOf(searchResponse.getResult().getPostList().get(0).getPostIdx()), String.valueOf(searchResponse.getResult().getPostList().get(1).getPostIdx()), String.valueOf(searchResponse.getResult().getPostList().get(2).getPostIdx()), "", "", "",
                                    searchResponse.getResult().getPostList().get(0).getPostTitle(),searchResponse.getResult().getPostList().get(1).getPostTitle(),searchResponse.getResult().getPostList().get(2).getPostTitle(),"","","");

                            result_feed_list.add(feedItem3);
                            break;
                        case 4:
                            FeedItem feedItem4 = new FeedItem(searchResponse.getResult().getPostList().get(0).getPostUrl(), searchResponse.getResult().getPostList().get(1).getPostUrl(), searchResponse.getResult().getPostList().get(2).getPostUrl(), searchResponse.getResult().getPostList().get(3).getPostUrl(), "","",
                                    searchResponse.getResult().getPostList().get(0).getCreatedAt().substring(2,10),searchResponse.getResult().getPostList().get(1).getCreatedAt().substring(2,10),searchResponse.getResult().getPostList().get(2).getCreatedAt().substring(2,10),searchResponse.getResult().getPostList().get(3).getCreatedAt().substring(2,10),"","",
                                    String.valueOf(searchResponse.getResult().getPostList().get(0).getTotalImoge()), String.valueOf(searchResponse.getResult().getPostList().get(1).getTotalImoge()), String.valueOf(searchResponse.getResult().getPostList().get(2).getTotalImoge()), String.valueOf(searchResponse.getResult().getPostList().get(3).getTotalImoge()), "", "",
                                    String.valueOf(searchResponse.getResult().getPostList().get(0).getPostIdx()), String.valueOf(searchResponse.getResult().getPostList().get(1).getPostIdx()), String.valueOf(searchResponse.getResult().getPostList().get(2).getPostIdx()), String.valueOf(searchResponse.getResult().getPostList().get(3).getPostIdx()), "", "",
                                    searchResponse.getResult().getPostList().get(0).getPostTitle(),searchResponse.getResult().getPostList().get(1).getPostTitle(),searchResponse.getResult().getPostList().get(2).getPostTitle(),searchResponse.getResult().getPostList().get(3).getPostTitle(),"","");

                            result_feed_list.add(feedItem4);

                            break;

                        case 5:

                            FeedItem feedItem5 = new FeedItem(searchResponse.getResult().getPostList().get(0).getPostUrl(), searchResponse.getResult().getPostList().get(1).getPostUrl(), searchResponse.getResult().getPostList().get(2).getPostUrl(), searchResponse.getResult().getPostList().get(3).getPostUrl(), searchResponse.getResult().getPostList().get(4).getPostUrl(),"",
                                    searchResponse.getResult().getPostList().get(0).getCreatedAt().substring(2,10),searchResponse.getResult().getPostList().get(1).getCreatedAt().substring(2,10),searchResponse.getResult().getPostList().get(2).getCreatedAt().substring(2,10),searchResponse.getResult().getPostList().get(3).getCreatedAt().substring(2,10),searchResponse.getResult().getPostList().get(4).getCreatedAt().substring(2,10),"",
                                    String.valueOf(searchResponse.getResult().getPostList().get(0).getTotalImoge()), String.valueOf(searchResponse.getResult().getPostList().get(1).getTotalImoge()), String.valueOf(searchResponse.getResult().getPostList().get(2).getTotalImoge()), String.valueOf(searchResponse.getResult().getPostList().get(3).getTotalImoge()), String.valueOf(searchResponse.getResult().getPostList().get(4).getTotalImoge()), "",
                                    String.valueOf(searchResponse.getResult().getPostList().get(0).getPostIdx()), String.valueOf(searchResponse.getResult().getPostList().get(1).getPostIdx()), String.valueOf(searchResponse.getResult().getPostList().get(2).getPostIdx()), String.valueOf(searchResponse.getResult().getPostList().get(3).getPostIdx()), String.valueOf(searchResponse.getResult().getPostList().get(4).getPostIdx()), "",
                                    searchResponse.getResult().getPostList().get(0).getPostTitle(),searchResponse.getResult().getPostList().get(1).getPostTitle(),searchResponse.getResult().getPostList().get(2).getPostTitle(),searchResponse.getResult().getPostList().get(3).getPostTitle(),searchResponse.getResult().getPostList().get(4).getPostTitle(),"");

                            result_feed_list.add(feedItem5);

                            break;
                    }

                }else{
                    FeedItem feedItem = new FeedItem(searchResponse.getResult().getPostList().get(0).getPostUrl(), searchResponse.getResult().getPostList().get(1).getPostUrl(), searchResponse.getResult().getPostList().get(2).getPostUrl(), searchResponse.getResult().getPostList().get(3).getPostUrl(), searchResponse.getResult().getPostList().get(4).getPostUrl(),searchResponse.getResult().getPostList().get(5).getPostUrl(),
                searchResponse.getResult().getPostList().get(0).getCreatedAt().substring(2,10),searchResponse.getResult().getPostList().get(1).getCreatedAt().substring(2,10),searchResponse.getResult().getPostList().get(2).getCreatedAt().substring(2,10),searchResponse.getResult().getPostList().get(3).getCreatedAt().substring(2,10),searchResponse.getResult().getPostList().get(4).getCreatedAt().substring(2,10),searchResponse.getResult().getPostList().get(5).getCreatedAt().substring(2,10),
                String.valueOf(searchResponse.getResult().getPostList().get(0).getTotalImoge()), String.valueOf(searchResponse.getResult().getPostList().get(1).getTotalImoge()), String.valueOf(searchResponse.getResult().getPostList().get(2).getTotalImoge()), String.valueOf(searchResponse.getResult().getPostList().get(3).getTotalImoge()), String.valueOf(searchResponse.getResult().getPostList().get(4).getTotalImoge()), String.valueOf(searchResponse.getResult().getPostList().get(5).getTotalImoge()),
                            String.valueOf(searchResponse.getResult().getPostList().get(0).getPostIdx()), String.valueOf(searchResponse.getResult().getPostList().get(1).getPostIdx()), String.valueOf(searchResponse.getResult().getPostList().get(2).getPostIdx()), String.valueOf(searchResponse.getResult().getPostList().get(3).getPostIdx()), String.valueOf(searchResponse.getResult().getPostList().get(4).getPostIdx()), String.valueOf(searchResponse.getResult().getPostList().get(5).getPostIdx()),
                            searchResponse.getResult().getPostList().get(0).getPostTitle(),searchResponse.getResult().getPostList().get(1).getPostTitle(),searchResponse.getResult().getPostList().get(2).getPostTitle(),searchResponse.getResult().getPostList().get(3).getPostTitle(),searchResponse.getResult().getPostList().get(4).getPostTitle(),searchResponse.getResult().getPostList().get(5).getPostTitle());

                    result_feed_list.add(feedItem);

                }



                // 어댑터
                result_feedAdapter = new FeedResultAdapter(result_feed_list);
                result_feed_rv.setAdapter(result_feedAdapter);

                result_feedAdapter.notifyDataSetChanged();

                break;

            case 3208:
                Toast.makeText(this, "해당 게시물이 존재하지 않습니다! XoX",Toast.LENGTH_SHORT).show();
                // 게시물 존재 하지 않으면 해당 페이지 화면을 다시 띄어줌.
                Intent intent  = new Intent(SearchActivity.this, SearchActivity.class);
                startActivity(intent);
                finish();
                break;

            case 3000:
            case 3200:
            case 3201:
            case 3001:
            case 3204:
            case 3003:
            case 3004:
            case 3013:
                break;

        }

    }




}