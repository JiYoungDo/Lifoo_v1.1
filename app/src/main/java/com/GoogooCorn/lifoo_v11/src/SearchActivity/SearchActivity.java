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

    List<String> recent_search_list;

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


        recent_search_list = new ArrayList<>();


        sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
        SharedPreferences.Editor editor = sSharedPreferences.edit();

        // 저장해둔 갯수.
        count = sSharedPreferences.getInt("recent_search_count",0);

        // 저장해둔 갯수에 따라 최근 검색어 보이
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
                recent_search_1.setText(sSharedPreferences.getString("recent_search_1",""));
                recent_search_2.setText("");
                recent_search_3.setText("");
                recent_search_4.setText("");
                recent_search_5.setText("");
                recent_search_6.setText("");
                recent_search_7.setText("");
                recent_search_8.setText("");
                recent_search_9.setText("");
                recent_search_10.setText("");
                recent_search_list.add(sSharedPreferences.getString("recent_search_1",""));
                break;
            case 2:
                recent_search_1.setText(sSharedPreferences.getString("recent_search_1",""));
                recent_search_2.setText(sSharedPreferences.getString("recent_search_2",""));
                recent_search_3.setText("");
                recent_search_4.setText("");
                recent_search_5.setText("");
                recent_search_6.setText("");
                recent_search_7.setText("");
                recent_search_8.setText("");
                recent_search_9.setText("");
                recent_search_10.setText("");
                recent_search_list.add(sSharedPreferences.getString("recent_search_1",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_2",""));

                break;
            case 3:
                recent_search_1.setText(sSharedPreferences.getString("recent_search_1",""));
                recent_search_2.setText(sSharedPreferences.getString("recent_search_2",""));
                recent_search_3.setText(sSharedPreferences.getString("recent_search_3",""));
                recent_search_4.setText("");
                recent_search_5.setText("");
                recent_search_6.setText("");
                recent_search_7.setText("");
                recent_search_8.setText("");
                recent_search_9.setText("");
                recent_search_10.setText("");
                recent_search_list.add(sSharedPreferences.getString("recent_search_1",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_2",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_3",""));
                break;
            case 4:
                recent_search_1.setText(sSharedPreferences.getString("recent_search_1",""));
                recent_search_2.setText(sSharedPreferences.getString("recent_search_2",""));
                recent_search_3.setText(sSharedPreferences.getString("recent_search_3",""));
                recent_search_4.setText(sSharedPreferences.getString("recent_search_4",""));
                recent_search_5.setText("");
                recent_search_6.setText("");
                recent_search_7.setText("");
                recent_search_8.setText("");
                recent_search_9.setText("");
                recent_search_10.setText("");
                recent_search_list.add(sSharedPreferences.getString("recent_search_1",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_2",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_3",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_4",""));

                break;
            case 5:
                recent_search_1.setText(sSharedPreferences.getString("recent_search_1",""));
                recent_search_2.setText(sSharedPreferences.getString("recent_search_2",""));
                recent_search_3.setText(sSharedPreferences.getString("recent_search_3",""));
                recent_search_4.setText(sSharedPreferences.getString("recent_search_4",""));
                recent_search_5.setText(sSharedPreferences.getString("recent_search_5",""));
                recent_search_6.setText("");
                recent_search_7.setText("");
                recent_search_8.setText("");
                recent_search_9.setText("");
                recent_search_10.setText("");
                recent_search_list.add(sSharedPreferences.getString("recent_search_1",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_2",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_3",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_4",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_5",""));

                break;
            case 6:
                recent_search_1.setText(sSharedPreferences.getString("recent_search_1",""));
                recent_search_2.setText(sSharedPreferences.getString("recent_search_2",""));
                recent_search_3.setText(sSharedPreferences.getString("recent_search_3",""));
                recent_search_4.setText(sSharedPreferences.getString("recent_search_4",""));
                recent_search_5.setText(sSharedPreferences.getString("recent_search_5",""));
                recent_search_6.setText(sSharedPreferences.getString("recent_search_6",""));
                recent_search_7.setText("");
                recent_search_8.setText("");
                recent_search_9.setText("");
                recent_search_10.setText("");
                recent_search_list.add(sSharedPreferences.getString("recent_search_1",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_2",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_3",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_4",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_5",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_6",""));
                break;
            case 7:
                recent_search_1.setText(sSharedPreferences.getString("recent_search_1",""));
                recent_search_2.setText(sSharedPreferences.getString("recent_search_2",""));
                recent_search_3.setText(sSharedPreferences.getString("recent_search_3",""));
                recent_search_4.setText(sSharedPreferences.getString("recent_search_4",""));
                recent_search_5.setText(sSharedPreferences.getString("recent_search_5",""));
                recent_search_6.setText(sSharedPreferences.getString("recent_search_6",""));
                recent_search_7.setText(sSharedPreferences.getString("recent_search_7",""));
                recent_search_8.setText("");
                recent_search_9.setText("");
                recent_search_10.setText("");
                recent_search_list.add(sSharedPreferences.getString("recent_search_1",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_2",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_3",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_4",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_5",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_6",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_7",""));

                break;
            case 8:
                recent_search_1.setText(sSharedPreferences.getString("recent_search_1",""));
                recent_search_2.setText(sSharedPreferences.getString("recent_search_2",""));
                recent_search_3.setText(sSharedPreferences.getString("recent_search_3",""));
                recent_search_4.setText(sSharedPreferences.getString("recent_search_4",""));
                recent_search_5.setText(sSharedPreferences.getString("recent_search_5",""));
                recent_search_6.setText(sSharedPreferences.getString("recent_search_6",""));
                recent_search_7.setText(sSharedPreferences.getString("recent_search_7",""));
                recent_search_8.setText(sSharedPreferences.getString("recent_search_8",""));
                recent_search_9.setText("");
                recent_search_10.setText("");
                recent_search_list.add(sSharedPreferences.getString("recent_search_1",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_2",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_3",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_4",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_5",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_6",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_7",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_8",""));

                break;
            case 9:
                recent_search_1.setText(sSharedPreferences.getString("recent_search_1",""));
                recent_search_2.setText(sSharedPreferences.getString("recent_search_2",""));
                recent_search_3.setText(sSharedPreferences.getString("recent_search_3",""));
                recent_search_4.setText(sSharedPreferences.getString("recent_search_4",""));
                recent_search_5.setText(sSharedPreferences.getString("recent_search_5",""));
                recent_search_6.setText(sSharedPreferences.getString("recent_search_6",""));
                recent_search_7.setText(sSharedPreferences.getString("recent_search_7",""));
                recent_search_8.setText(sSharedPreferences.getString("recent_search_8",""));
                recent_search_9.setText(sSharedPreferences.getString("recent_search_9",""));
                recent_search_10.setText("");
                recent_search_list.add(sSharedPreferences.getString("recent_search_1",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_2",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_3",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_4",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_5",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_6",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_7",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_8",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_9",""));

                break;
            case 10:
                recent_search_1.setText(sSharedPreferences.getString("recent_search_1",""));
                recent_search_2.setText(sSharedPreferences.getString("recent_search_2",""));
                recent_search_3.setText(sSharedPreferences.getString("recent_search_3",""));
                recent_search_4.setText(sSharedPreferences.getString("recent_search_4",""));
                recent_search_5.setText(sSharedPreferences.getString("recent_search_5",""));
                recent_search_6.setText(sSharedPreferences.getString("recent_search_6",""));
                recent_search_7.setText(sSharedPreferences.getString("recent_search_7",""));
                recent_search_8.setText(sSharedPreferences.getString("recent_search_8",""));
                recent_search_9.setText(sSharedPreferences.getString("recent_search_9",""));
                recent_search_10.setText(sSharedPreferences.getString("recent_search_10",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_1",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_2",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_3",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_4",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_5",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_6",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_7",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_8",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_9",""));
                recent_search_list.add(sSharedPreferences.getString("recent_search_10",""));
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

        Log.d("검색 성",  " && "+String.valueOf(code));

        // 내부 갯수, 검색어 저
        String now = search_et.getText().toString();

        sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
        SharedPreferences.Editor editor = sSharedPreferences.edit();


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

        editor.putInt("recent_search_count",count);

        editor.commit();

        if(count > 0)
        {
            recent_search_list.remove(0);
            recent_search_list.add(0,now);

            for(int i = 0; i< count; i++)
            {
                sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
                SharedPreferences.Editor editor_3 = sSharedPreferences.edit();
                String temp = "recent_search_"+ String.valueOf(i+1);
                editor_3.putString(temp,recent_search_list.get(i));
                editor_3.commit();

            }

        }else{
            sSharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
            SharedPreferences.Editor editor_2 = sSharedPreferences.edit();
            editor_2.putString("recent_search_1",now);
            editor_2.commit();
        }


        if(count < 10){count += 1;}

        // 통신 후 찾은 결과 값.
        // [?] 어디로 보내서 보여주지 ?

    }
}