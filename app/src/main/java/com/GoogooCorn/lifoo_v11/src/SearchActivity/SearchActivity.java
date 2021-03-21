package com.GoogooCorn.lifoo_v11.src.SearchActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.GoogooCorn.lifoo_v11.R;
import com.GoogooCorn.lifoo_v11.src.BaseActivity;
import com.GoogooCorn.lifoo_v11.src.MainActivity.MainActivity;
import com.GoogooCorn.lifoo_v11.src.SearchActivity.interfaces.SearchView;
import com.GoogooCorn.lifoo_v11.src.SearchActivity.models.SearchResponse;

import java.util.ArrayList;
import java.util.List;

import static com.GoogooCorn.lifoo_v11.ApplicationClass.TAG;
import static com.GoogooCorn.lifoo_v11.ApplicationClass.sSharedPreferences;

public class SearchActivity extends BaseActivity implements SearchView {

    ImageView back_btn;
    EditText search_et;
    ImageView search_btn;
    TextView recent_search_1,recent_search_2,recent_search_3,recent_search_4,recent_search_5,recent_search_6,
            recent_search_7,recent_search_8,recent_search_9,recent_search_10;

    String temp_1, temp_2,  temp_3, temp_4, temp_5, temp_6, temp_7, temp_8, temp_9, temp_10;

    SearchService searchService = new SearchService(this);

    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

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
                recent_search_1.setText(sSharedPreferences.getString("recent_search_1","정보 없"));
                recent_search_2.setText("");
                recent_search_3.setText("");
                recent_search_4.setText("");
                recent_search_5.setText("");
                recent_search_6.setText("");
                recent_search_7.setText("");
                recent_search_8.setText("");
                recent_search_9.setText("");
                recent_search_10.setText("");
                temp_1 = sSharedPreferences.getString("recent_search_1","정보 없");
                break;
            case 2:
                recent_search_1.setText(sSharedPreferences.getString("recent_search_1","정보 없음"));
                recent_search_2.setText(sSharedPreferences.getString("recent_search_2","정보 없음"));
                recent_search_3.setText("");
                recent_search_4.setText("");
                recent_search_5.setText("");
                recent_search_6.setText("");
                recent_search_7.setText("");
                recent_search_8.setText("");
                recent_search_9.setText("");
                recent_search_10.setText("");
                temp_1 = sSharedPreferences.getString("recent_search_1","정보 없");
                temp_2 = sSharedPreferences.getString("recent_search_2","정보 없");
                break;
            case 3:
                recent_search_1.setText(sSharedPreferences.getString("recent_search_1","정보 없음"));
                recent_search_2.setText(sSharedPreferences.getString("recent_search_2","정보 없음"));
                recent_search_3.setText(sSharedPreferences.getString("recent_search_3","정보 없음"));
                recent_search_4.setText("");
                recent_search_5.setText("");
                recent_search_6.setText("");
                recent_search_7.setText("");
                recent_search_8.setText("");
                recent_search_9.setText("");
                recent_search_10.setText("");
                temp_1 = sSharedPreferences.getString("recent_search_1","정보 없");
                temp_2 = sSharedPreferences.getString("recent_search_2","정보 없");
                temp_3 = sSharedPreferences.getString("recent_search_3","정보 없");
                break;
            case 4:
                recent_search_1.setText(sSharedPreferences.getString("recent_search_1","정보 없음"));
                recent_search_2.setText(sSharedPreferences.getString("recent_search_2","정보 없음"));
                recent_search_3.setText(sSharedPreferences.getString("recent_search_3","정보 없음"));
                recent_search_4.setText(sSharedPreferences.getString("recent_search_4","정보 없음"));
                recent_search_5.setText("");
                recent_search_6.setText("");
                recent_search_7.setText("");
                recent_search_8.setText("");
                recent_search_9.setText("");
                recent_search_10.setText("");
                temp_1 = sSharedPreferences.getString("recent_search_1","정보 없");
                temp_2 = sSharedPreferences.getString("recent_search_2","정보 없");
                temp_3 = sSharedPreferences.getString("recent_search_3","정보 없");
                temp_4 = sSharedPreferences.getString("recent_search_4","정보 없");
                break;
            case 5:
                recent_search_1.setText(sSharedPreferences.getString("recent_search_1","정보 없음"));
                recent_search_2.setText(sSharedPreferences.getString("recent_search_2","정보 없음"));
                recent_search_3.setText(sSharedPreferences.getString("recent_search_3","정보 없음"));
                recent_search_4.setText(sSharedPreferences.getString("recent_search_4","정보 없음"));
                recent_search_5.setText(sSharedPreferences.getString("recent_search_5","정보 없음"));
                recent_search_6.setText("");
                recent_search_7.setText("");
                recent_search_8.setText("");
                recent_search_9.setText("");
                recent_search_10.setText("");
                temp_1 = sSharedPreferences.getString("recent_search_1","정보 없");
                temp_2 = sSharedPreferences.getString("recent_search_2","정보 없");
                temp_3 = sSharedPreferences.getString("recent_search_3","정보 없");
                temp_4 = sSharedPreferences.getString("recent_search_4","정보 없");
                temp_5 = sSharedPreferences.getString("recent_search_5","정보 없");
                break;
            case 6:
                recent_search_1.setText(sSharedPreferences.getString("recent_search_1","정보 없음"));
                recent_search_2.setText(sSharedPreferences.getString("recent_search_2","정보 없음"));
                recent_search_3.setText(sSharedPreferences.getString("recent_search_3","정보 없음"));
                recent_search_4.setText(sSharedPreferences.getString("recent_search_4","정보 없음"));
                recent_search_5.setText(sSharedPreferences.getString("recent_search_5","정보 없음"));
                recent_search_6.setText(sSharedPreferences.getString("recent_search_6","정보 없음"));
                recent_search_7.setText("");
                recent_search_8.setText("");
                recent_search_9.setText("");
                recent_search_10.setText("");
                temp_1 = sSharedPreferences.getString("recent_search_1","정보 없");
                temp_2 = sSharedPreferences.getString("recent_search_2","정보 없");
                temp_3 = sSharedPreferences.getString("recent_search_3","정보 없");
                temp_4 = sSharedPreferences.getString("recent_search_4","정보 없");
                temp_5 = sSharedPreferences.getString("recent_search_5","정보 없");
                temp_6 = sSharedPreferences.getString("recent_search_6","정보 없");
                break;
            case 7:
                recent_search_1.setText(sSharedPreferences.getString("recent_search_1","정보 없음"));
                recent_search_2.setText(sSharedPreferences.getString("recent_search_2","정보 없음"));
                recent_search_3.setText(sSharedPreferences.getString("recent_search_3","정보 없음"));
                recent_search_4.setText(sSharedPreferences.getString("recent_search_4","정보 없음"));
                recent_search_5.setText(sSharedPreferences.getString("recent_search_5","정보 없음"));
                recent_search_6.setText(sSharedPreferences.getString("recent_search_6","정보 없음"));
                recent_search_7.setText(sSharedPreferences.getString("recent_search_7","정보 없음"));
                recent_search_8.setText("");
                recent_search_9.setText("");
                recent_search_10.setText("");
                temp_1 = sSharedPreferences.getString("recent_search_1","정보 없");
                temp_2 = sSharedPreferences.getString("recent_search_2","정보 없");
                temp_3 = sSharedPreferences.getString("recent_search_3","정보 없");
                temp_4 = sSharedPreferences.getString("recent_search_4","정보 없");
                temp_5 = sSharedPreferences.getString("recent_search_5","정보 없");
                temp_6 = sSharedPreferences.getString("recent_search_6","정보 없");
                temp_7 = sSharedPreferences.getString("recent_search_7","정보 없");
                break;
            case 8:
                recent_search_1.setText(sSharedPreferences.getString("recent_search_1","정보 없음"));
                recent_search_2.setText(sSharedPreferences.getString("recent_search_2","정보 없음"));
                recent_search_3.setText(sSharedPreferences.getString("recent_search_3","정보 없음"));
                recent_search_4.setText(sSharedPreferences.getString("recent_search_4","정보 없음"));
                recent_search_5.setText(sSharedPreferences.getString("recent_search_5","정보 없음"));
                recent_search_6.setText(sSharedPreferences.getString("recent_search_6","정보 없음"));
                recent_search_7.setText(sSharedPreferences.getString("recent_search_7","정보 없음"));
                recent_search_8.setText(sSharedPreferences.getString("recent_search_8","정보 없음"));
                recent_search_9.setText("");
                recent_search_10.setText("");
                temp_1 = sSharedPreferences.getString("recent_search_1","정보 없");
                temp_2 = sSharedPreferences.getString("recent_search_2","정보 없");
                temp_3 = sSharedPreferences.getString("recent_search_3","정보 없");
                temp_4 = sSharedPreferences.getString("recent_search_4","정보 없");
                temp_5 = sSharedPreferences.getString("recent_search_5","정보 없");
                temp_6 = sSharedPreferences.getString("recent_search_6","정보 없");
                temp_7 = sSharedPreferences.getString("recent_search_7","정보 없");
                temp_8 = sSharedPreferences.getString("recent_search_8","정보 없");

                break;
            case 9:
                recent_search_1.setText(sSharedPreferences.getString("recent_search_1","정보 없음"));
                recent_search_2.setText(sSharedPreferences.getString("recent_search_2","정보 없음"));
                recent_search_3.setText(sSharedPreferences.getString("recent_search_3","정보 없음"));
                recent_search_4.setText(sSharedPreferences.getString("recent_search_4","정보 없음"));
                recent_search_5.setText(sSharedPreferences.getString("recent_search_5","정보 없음"));
                recent_search_6.setText(sSharedPreferences.getString("recent_search_6","정보 없음"));
                recent_search_7.setText(sSharedPreferences.getString("recent_search_7","정보 없음"));
                recent_search_8.setText(sSharedPreferences.getString("recent_search_8","정보 없음"));
                recent_search_9.setText(sSharedPreferences.getString("recent_search_9","정보 없음"));
                recent_search_10.setText("");
                temp_1 = sSharedPreferences.getString("recent_search_1","정보 없");
                temp_2 = sSharedPreferences.getString("recent_search_2","정보 없");
                temp_3 = sSharedPreferences.getString("recent_search_3","정보 없");
                temp_4 = sSharedPreferences.getString("recent_search_4","정보 없");
                temp_5 = sSharedPreferences.getString("recent_search_5","정보 없");
                temp_6 = sSharedPreferences.getString("recent_search_6","정보 없");
                temp_7 = sSharedPreferences.getString("recent_search_7","정보 없");
                temp_8 = sSharedPreferences.getString("recent_search_8","정보 없");
                temp_9 = sSharedPreferences.getString("recent_search_9","정보 없");

                break;
            case 10:
                recent_search_1.setText(sSharedPreferences.getString("recent_search_1","정보 없음"));
                recent_search_2.setText(sSharedPreferences.getString("recent_search_2","정보 없음"));
                recent_search_3.setText(sSharedPreferences.getString("recent_search_3","정보 없음"));
                recent_search_4.setText(sSharedPreferences.getString("recent_search_4","정보 없음"));
                recent_search_5.setText(sSharedPreferences.getString("recent_search_5","정보 없음"));
                recent_search_6.setText(sSharedPreferences.getString("recent_search_6","정보 없음"));
                recent_search_7.setText(sSharedPreferences.getString("recent_search_7","정보 없음"));
                recent_search_8.setText(sSharedPreferences.getString("recent_search_8","정보 없음"));
                recent_search_9.setText(sSharedPreferences.getString("recent_search_9","정보 없음"));
                recent_search_10.setText(sSharedPreferences.getString("recent_search_10","정보 없음"));
                temp_1 = sSharedPreferences.getString("recent_search_1","정보 없");
                temp_2 = sSharedPreferences.getString("recent_search_2","정보 없");
                temp_3 = sSharedPreferences.getString("recent_search_3","정보 없");
                temp_4 = sSharedPreferences.getString("recent_search_4","정보 없");
                temp_5 = sSharedPreferences.getString("recent_search_5","정보 없");
                temp_6 = sSharedPreferences.getString("recent_search_6","정보 없");
                temp_7 = sSharedPreferences.getString("recent_search_7","정보 없");
                temp_8 = sSharedPreferences.getString("recent_search_8","정보 없");
                temp_9 = sSharedPreferences.getString("recent_search_9","정보 없");
                temp_10 = sSharedPreferences.getString("recent_search_10","정보 없");

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

    }




}