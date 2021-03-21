package com.GoogooCorn.lifoo_v11.src.FeedFragment;

import com.GoogooCorn.lifoo_v11.src.FeedFragment.interfaces.FeedFragmentActivityView;
import com.GoogooCorn.lifoo_v11.src.FeedFragment.interfaces.FeedFragmentRetrofitInterface;
import com.GoogooCorn.lifoo_v11.src.FeedFragment.models.FeedFragmentResponse;

import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.GoogooCorn.lifoo_v11.ApplicationClass.getRetrofit;

public class FeedService {

    private final FeedFragmentActivityView feedFragmentActivityView;

    public FeedService(FeedFragmentActivityView mFeedFragmentActivityView) {
        this.feedFragmentActivityView = mFeedFragmentActivityView;
    }


    void GetBasicPosts(int page_num) {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("type","BASIC");
        parameters.put("size","6");
        parameters.put("page",String.valueOf(page_num));

        final FeedFragmentRetrofitInterface feedFragmentRetrofitInterface = getRetrofit().create(FeedFragmentRetrofitInterface.class);
        feedFragmentRetrofitInterface.GetFeedTest(parameters).enqueue(new Callback<FeedFragmentResponse>() {


            // 통신 성공 시
            @Override
            public void onResponse(Call<FeedFragmentResponse> call, Response<FeedFragmentResponse> response) {
                final FeedFragmentResponse feedFragmentResponse = response.body();

                if (feedFragmentResponse == null) {
                    // 통신은 됬는데 결과 값이 null 이면?
                    feedFragmentActivityView.GetPostsBasicFailure("null", 0);
                    return;
                }
                // 통신도 성공! 받아오는 값도 성공!
                feedFragmentActivityView.GetPostsBasicSuccess(feedFragmentResponse,feedFragmentResponse.getCode());
            }

            // API 통신 자체가 실패
            @Override
            public void onFailure(Call<FeedFragmentResponse> call, Throwable t) {
                feedFragmentActivityView.GetPostsBasicFailure("통신 자체 실패",0);
            }
        });
    }
}
