package com.googoocorn.lifoo.src.RankingFragment.interfaces;

import com.googoocorn.lifoo.src.RankingFragment.models.RankingResponse;

public interface RankingActivityView {
    // 랭킹 조회 실패
    void GetRankingFailure(String message, int code);
    // 랭킹 조회 성공
    void GetRankingSuccess(RankingResponse rankingResponse, int code);
}
