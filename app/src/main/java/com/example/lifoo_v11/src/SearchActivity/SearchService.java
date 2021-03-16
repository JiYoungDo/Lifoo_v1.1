package com.example.lifoo_v11.src.SearchActivity;


import com.example.lifoo_v11.src.SearchActivity.interfaces.SearchRetrofitInterface;
import com.example.lifoo_v11.src.SearchActivity.interfaces.SearchView;
import com.example.lifoo_v11.src.SearchActivity.models.SearchResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.lifoo_v11.ApplicationClass.getRetrofit;

public class SearchService {

    private final SearchView searchView;

    public SearchService(SearchView mSearchView) {
        this.searchView = mSearchView;
    }


    public void GetSearchPosts(String user_keyword) {

        final SearchRetrofitInterface searchRetrofitInterface = getRetrofit().create(SearchRetrofitInterface.class);
        searchRetrofitInterface.GetSearchTest("SEARCH", 6,0,user_keyword).enqueue(new Callback<SearchResponse>() {

            // 통신 성공 시
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                final SearchResponse searchResponse = response.body();

                if (searchResponse == null) {
                    // 통신은 됬는데 결과 값이 null 이면?
                    searchView.GetPostsSearchFailure("null", 0);
                    return;
                }
                // 통신도 성공! 받아오는 값도 성공!
                searchView.GetPostsSearchSuccess(searchResponse,searchResponse.getCode());
            }

            // API 통신 자체가 실패
            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                searchView.GetPostsSearchFailure("통신 자체 실패",0);
            }
        });
    }


}
