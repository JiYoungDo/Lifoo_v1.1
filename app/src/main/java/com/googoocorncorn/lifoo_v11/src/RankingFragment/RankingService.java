package com.googoocorncorn.lifoo_v11.src.RankingFragment;

import com.googoocorncorn.lifoo_v11.src.RankingFragment.interfaces.RankingActivityView;
import com.googoocorncorn.lifoo_v11.src.RankingFragment.interfaces.RankingRetrofitInterface;
import com.googoocorncorn.lifoo_v11.src.RankingFragment.models.RankingResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.googoocorncorn.lifoo_v11.ApplicationClass.getRetrofit;

public class RankingService {

    private final RankingActivityView rankingActivityView;

    public RankingService(RankingActivityView rankingActivityView) {
        this.rankingActivityView = rankingActivityView;
    }

    // 랭킹 게시물 가져오기
    public void GetRanking(int page_num){
        final RankingRetrofitInterface rankingRetrofitInterface = getRetrofit().create(RankingRetrofitInterface.class);
        rankingRetrofitInterface.GetRankingTest("RANK", 10, page_num).enqueue(new Callback<RankingResponse>() {
            // 통신 성공
            @Override
            public void onResponse(Call<RankingResponse> call, Response<RankingResponse> response) {
                final RankingResponse rankingResponse = response.body();

                if (rankingResponse == null) {
                    // 통신 성공인데 값 받아오기 실패
                    rankingActivityView.GetRankingFailure("null", 0);
                    return;
                }
                // 통신, 값 받아오기 성공
                rankingActivityView.GetRankingSuccess(rankingResponse, rankingResponse.getCode());
            }

            // API 통신 자체가 실패
            @Override
            public void onFailure(Call<RankingResponse> call, Throwable t) {
                rankingActivityView.GetRankingFailure("통신 자체 실패", 0);
            }
        });
    }
}
